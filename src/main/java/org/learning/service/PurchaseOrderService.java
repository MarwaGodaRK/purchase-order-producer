package org.learning.service;

import org.learning.model.PurchaseOrder;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;


public interface PurchaseOrderService {
    List<PurchaseOrder> getPurchaseOrderList(int salesPersonId);

    PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder);
}
