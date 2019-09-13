package org.learning.model;

import lombok.Data;

//TODO add validation
@Data
public class PurchaseOrder {

    private long orderId;
    private int salesPersonId;
    private ProductCategory productCategory;
    private String productName;
    private OrderStatus orderStatus;
    private String location;
    private long quantity;
    private boolean forCompaniesOnly;

}
