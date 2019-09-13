package org.learning.service;


import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.learning.integration.KafkaProducer;
import org.learning.model.OrderStatus;
import org.learning.model.PurchaseOrder;
import org.learning.repository.PurchaseOrderRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Inject
    PurchaseOrderRepository purchaseOrderRepository;
    @Inject
    KafkaProducer kafkaProducer;

    @Override
    public List<PurchaseOrder> getPurchaseOrderList(int salesPersonId) {
        List<org.learning.entity.PurchaseOrder> result = purchaseOrderRepository.getPurchaseOrders(salesPersonId);
        return mapEntityListToModelList(result);
    }

    @Override
    @Transactional
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
         purchaseOrderRepository.persist(mapModelToEntity(purchaseOrder));

         // publish to kafka
        kafkaProducer.send(purchaseOrder);

         return purchaseOrder;
    }

    private org.learning.entity.PurchaseOrder mapModelToEntity(PurchaseOrder purchaseOrder) {
        org.learning.entity.PurchaseOrder purchaseOrderEntity = new org.learning.entity.PurchaseOrder();
        purchaseOrderEntity.setProductName(purchaseOrder.getProductName());
        purchaseOrderEntity.setQuantity(purchaseOrder.getQuantity());
        purchaseOrderEntity.setLocation(purchaseOrder.getLocation());
        purchaseOrderEntity.setForCompaniesOnly(purchaseOrder.isForCompaniesOnly());
        purchaseOrderEntity.setOrderStatus(OrderStatus.NEW.toString());
        purchaseOrderEntity.setSalesPersonId(purchaseOrder.getSalesPersonId());
        return purchaseOrderEntity;
    }


    private List<PurchaseOrder> mapEntityListToModelList(List<org.learning.entity.PurchaseOrder> result) {
        return result.stream().map(entity -> {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setProductName(entity.getProductName());
            purchaseOrder.setQuantity(entity.getQuantity());
            purchaseOrder.setLocation(entity.getLocation());
            purchaseOrder.setForCompaniesOnly(entity.isForCompaniesOnly());
            purchaseOrder.setOrderStatus(OrderStatus.valueOf(entity.getOrderStatus()));
            purchaseOrder.setSalesPersonId(entity.getSalesPersonId());
            purchaseOrder.setOrderId(entity.getOrderId());

            return purchaseOrder;

        }).collect(Collectors.toList());
    }
}
