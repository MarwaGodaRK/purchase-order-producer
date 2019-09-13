package org.learning.integration;

import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.annotations.Stream;
import org.learning.model.PurchaseOrder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@ApplicationScoped
public class KafkaProducerImpl implements KafkaProducer {

    private static final String EVENT_BUS_NAME = "purchase-order";
    @Inject
    @Stream(EVENT_BUS_NAME)
    Emitter<String> purchaseOrderPublisher;
    private Jsonb jsonb = JsonbBuilder.create();

    @Override
    public void send(PurchaseOrder message) {
        String serializedMessage = jsonb.toJson(message, PurchaseOrder.class);

        send(serializedMessage);

    }

    /**
     * Immediately send a message to a kafka stream
     *
     * @param message
     */

    public void send(String message) {
        try {
            purchaseOrderPublisher.send(message);
        } catch (Exception e) {
            //log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
