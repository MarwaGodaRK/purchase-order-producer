package org.learning.repository;

import org.learning.entity.PurchaseOrder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class PurchaseOrderRepository {

    @Inject
    EntityManager entityManager;

    public List<PurchaseOrder> getPurchaseOrders(int salesPersonId) {
        TypedQuery<PurchaseOrder> tq = entityManager.createQuery("from PurchaseOrder WHERE salesPersonId= :salesPersonId", PurchaseOrder.class);
        return tq.setParameter("salesPersonId", salesPersonId).getResultList();
    }

    public void persist(PurchaseOrder purchaseOrder) {
        entityManager.persist(purchaseOrder);
    }
}
