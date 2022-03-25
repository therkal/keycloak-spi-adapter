package io.kennethmartens.messaging;

import com.fasterxml.jackson.databind.JsonNode;
import io.kennethmartens.messaging.serializer.JsonPOJOSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.keycloak.events.Event;

import java.util.Properties;

public class Producer {

    private final static String KAFKA_BOOTSTRAP_SERVER = "kafka:9092";

    public static void publishEvent(String topic, Event event){
        //reset thread context
        resetThreadContext();
        // create the producer
        KafkaProducer<String, Event> producer = new KafkaProducer<>(getProperties());

        // create a producer record
        ProducerRecord<String, Event> eventRecord =
                new ProducerRecord<>("KEYCLOAK_" + topic, event);

        // send data - asynchronous
        producer.send(eventRecord);

        // flush data
        producer.flush();
        // flush and close producer
        producer.close();
    }

    private static void resetThreadContext() {
        Thread.currentThread().setContextClassLoader(null);
    }

    public static Properties getProperties() {
        String bootstrapServer = System.getenv("KAFKA_BOOTSTRAP_SERVER");

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServer != null ? bootstrapServer : KAFKA_BOOTSTRAP_SERVER);

        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonPOJOSerializer.class.getName());
        return properties;
    }

}
