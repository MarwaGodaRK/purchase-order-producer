package org.learning.resource;


import org.learning.model.PurchaseOrder;
import org.learning.service.PurchaseOrderService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/purchaseorder")
public class PurchaseOrderResource {

    @Inject
    private PurchaseOrderService purchaseOrderService;

    @GET
    @Path("/{salesPersonId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PurchaseOrder> getPurchaseOrderList(@PathParam("salesPersonId") int salesPersonId) {
        return purchaseOrderService.getPurchaseOrderList(salesPersonId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder){
        return purchaseOrderService.createPurchaseOrder(purchaseOrder);
    }
}