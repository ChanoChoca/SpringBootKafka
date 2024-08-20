package com.chanochoca.app.config;

import com.chanochoca.app.events.Event;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuración de Kafka para la producción de eventos.
 * Esta clase define la configuración necesaria para crear un productor Kafka que pueda enviar eventos
 * serializados en formato JSON a un clúster de Kafka.
 */
@Configuration
public class KafkaProducerConfig {

    // Dirección del servidor Kafka, en este caso, se asume que el servidor se ejecuta localmente en el puerto 9092.
    private final String bootstrapAddress = "localhost:9092";

    /**
     * Configura la fábrica de productores Kafka.
     *
     * Este método crea un `ProducerFactory`, que es responsable de crear instancias de `KafkaProducer`.
     * La configuración incluye:
     * - La dirección del servidor Kafka (`bootstrap.servers`).
     * - El serializador para las claves (`key.serializer`), que en este caso es `StringSerializer`.
     * - El serializador para los valores (`value.serializer`), que es `JsonSerializer` para serializar los objetos `Event` en formato JSON.
     *
     * @return una instancia de `ProducerFactory<String, Event<?>>` configurada.
     */
    @Bean
    public ProducerFactory<String, Event<?>> producerFactory() {
        // Mapa que contiene las propiedades de configuración del productor.
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);  // Dirección del servidor Kafka.
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);  // Serializador para las claves (String).
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);  // Serializador para los valores (JSON).

        // Retorna una fábrica de productores configurada con las propiedades especificadas.
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Crea una instancia de `KafkaTemplate` que se utilizará para enviar mensajes a Kafka.
     *
     * `KafkaTemplate` es una clase de alto nivel que abstrae la interacción con el productor Kafka,
     * permitiendo enviar mensajes de forma sencilla.
     *
     * @return una instancia de `KafkaTemplate<String, Event<?>>` que utilizará la fábrica de productores configurada.
     */
    @Bean
    public KafkaTemplate<String, Event<?>> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
