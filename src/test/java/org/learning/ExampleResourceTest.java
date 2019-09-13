package org.learning;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.Test;
import org.learning.model.OrderStatus;
import org.learning.model.PurchaseOrder;
import org.learning.service.PurchaseOrderService;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;

//TODO complete junit
@QuarkusTest
public class ExampleResourceTest {

    @Inject
    PurchaseOrderService purchaseOrderService;
    @Test
    public void testGetEndpoint() {
        createSalesOrder();
        given()
          .when().get("/purchaseorder/1")
          .then()
             .statusCode(200);
    }

    private void createSalesOrder() {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setProductName("ProductName");
        purchaseOrder.setQuantity(10);
        purchaseOrder.setLocation("Location");
        purchaseOrder.setForCompaniesOnly(true);
        purchaseOrder.setOrderStatus(OrderStatus.NEW);
        purchaseOrder.setSalesPersonId(1);
        purchaseOrderService.createPurchaseOrder(purchaseOrder);

    }

}