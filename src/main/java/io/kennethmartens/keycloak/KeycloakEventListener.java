package io.kennethmartens.keycloak;

import io.kennethmartens.messaging.Producer;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeycloakEventListener implements EventListenerProvider {

    final Logger logger = LoggerFactory.getLogger(KeycloakEventListener.class);

    @Override
    public void onEvent(Event event) {
        logger.info("Keycloak event: {}", event);
        Producer.publishEvent(event.getType().toString(), event);
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        logger.info("Keycloak admin event: {}", adminEvent);
    }

    @Override
    public void close() {

    }
}
