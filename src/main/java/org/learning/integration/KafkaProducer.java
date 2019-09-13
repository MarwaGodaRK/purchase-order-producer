package org.learning.integration;

import org.learning.model.PurchaseOrder;

public interface KafkaProducer {
    void send(PurchaseOrder message);
}
