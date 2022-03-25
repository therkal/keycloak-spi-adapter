package io.kennethmartens.keycloak;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeycloakEventListenerFactory implements EventListenerProviderFactory {

    final Logger logger = LoggerFactory.getLogger(KeycloakEventListener.class);

    @Override
    public EventListenerProvider create(KeycloakSession keycloakSession) {
        return new KeycloakEventListener();
    }

    @Override
    public void init(Config.Scope scope) {}

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {}

    @Override
    public void close() {}

    @Override
    public String getId() {
        return "kafka-event-listener";
    }
}
