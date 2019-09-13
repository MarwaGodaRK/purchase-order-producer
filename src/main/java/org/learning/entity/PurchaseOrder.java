package org.learning.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//TODO update relations
@Entity
public class PurchaseOrder {

    private Long orderId;
    private String productName;
    private String orderStatus;
    private String location;
    private long quantity;
    private boolean forCompaniesOnly;
    private int salesPersonId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="orderSeq")
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public boolean isForCompaniesOnly() {
        return forCompaniesOnly;
    }

    public void setForCompaniesOnly(boolean forCompaniesOnly) {
        this.forCompaniesOnly = forCompaniesOnly;
    }

    public int getSalesPersonId() {
        return salesPersonId;
    }

    public void setSalesPersonId(int salesPersonId) {
        this.salesPersonId = salesPersonId;
    }
}