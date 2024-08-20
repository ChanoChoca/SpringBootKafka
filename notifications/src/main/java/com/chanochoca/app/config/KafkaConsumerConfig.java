package com.chanochoca.app.config;

import com.chanochoca.app.events.Event;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuración del consumidor de Kafka en la aplicación.
 *
 * `KafkaConsumerConfig` configura la infraestructura necesaria para consumir
 * mensajes de Kafka dentro de la aplicación. Define cómo los mensajes son
 * deserializados y procesados por los listeners de Kafka.
 */
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    // Dirección del servidor de Kafka.
    private final String bootstrapAddress = "localhost:9092";

    /**
     * Configura y devuelve un `ConsumerFactory` para Kafka.
     *
     * Este método establece las propiedades necesarias para crear un consumidor
     * de Kafka, incluyendo la configuración de la deserialización de mensajes
     * y la dirección del servidor Kafka.
     *
     * @return Un `ConsumerFactory` configurado para consumir eventos de tipo `Event<?>`.
     */
    @Bean
    public ConsumerFactory<String, Event<?>> consumerFactory() {
        Map<String, String> props = new HashMap<>();

        // Configuración del servidor Kafka.
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);

        // Mapeo de tipos para la deserialización JSON.
        props.put(JsonSerializer.TYPE_MAPPINGS, "com.chanochoca.app:com.chanochoca.app.events.Event");

        // Configura un deserializador JSON para los mensajes de Kafka.
        final JsonDeserializer<Event<?>> jsonDeserializer = new JsonDeserializer<>();

        // Crea y retorna el `ConsumerFactory` usando las propiedades y deserializadores configurados.
        return new DefaultKafkaConsumerFactory(
                props,
                new StringDeserializer(),
                jsonDeserializer);
    }

    /**
     * Configura y devuelve un `ConcurrentKafkaListenerContainerFactory`.
     *
     * Este método establece la fábrica de contenedores de listeners de Kafka,
     * que es responsable de crear instancias de listeners para consumir mensajes
     * desde Kafka de manera concurrente.
     *
     * @return Un `ConcurrentKafkaListenerContainerFactory` configurado para manejar eventos `Event<?>`.
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Event<?>> kafkaListenerContainerFactory() {

        // Crea la fábrica de contenedores de listeners para manejar mensajes Kafka.
        ConcurrentKafkaListenerContainerFactory<String, Event<?>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        // Asocia el `ConsumerFactory` con la fábrica de listeners.
        factory.setConsumerFactory(consumerFactory());

        // Retorna la fábrica configurada.
        return factory;
    }
}
