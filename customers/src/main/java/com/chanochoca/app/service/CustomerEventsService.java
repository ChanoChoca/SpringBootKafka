package com.chanochoca.app.service;

import com.chanochoca.app.entity.Customer;
import com.chanochoca.app.events.CustomerCreatedEvent;
import com.chanochoca.app.events.Event;
import com.chanochoca.app.events.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * Servicio responsable de la publicación de eventos relacionados con clientes en Kafka.
 *
 * Esta clase se encarga de crear y enviar eventos al tópico Kafka correspondiente
 * cuando se crean nuevos clientes en el sistema. Utiliza `KafkaTemplate` para enviar
 * los eventos y define la lógica necesaria para configurar y publicar eventos de tipo
 * `CustomerCreatedEvent`.
 */
@Component
public class CustomerEventsService {

	// Inyección del KafkaTemplate que se utilizará para enviar los eventos.
	private final KafkaTemplate<String, Event<?>> producer;

	@Autowired
	public CustomerEventsService(KafkaTemplate<String, Event<?>> producer) {
		this.producer = producer;
	}

	// Nombre del tópico Kafka donde se publicarán los eventos. Se obtiene de la configuración de la aplicación.
	@Value("${topic.customer.name:customers}")
	private String topicCustomer;

	/**
	 * Publica un evento de creación de cliente en el tópico Kafka configurado.
	 *
	 * Este método se encarga de crear una instancia de `CustomerCreatedEvent`,
	 * configurar sus propiedades (como `id`, `type`, `date`, y `data`), y enviarla
	 * al tópico Kafka usando el `KafkaTemplate`.
	 *
	 * @param customer El cliente que ha sido creado y cuyos datos serán incluidos en el evento.
	 */
	public void publish(Customer customer) {
		// Creación de un evento de tipo CustomerCreatedEvent.
		CustomerCreatedEvent created = new CustomerCreatedEvent();

		// Configuración de los datos del evento.
		created.setData(customer);  // Establece el cliente creado como los datos del evento.
		created.setId(UUID.randomUUID().toString());  // Genera un ID único para el evento.
		created.setType(EventType.CREATED);  // Establece el tipo de evento como CREATED.
		created.setDate(new Date());  // Establece la fecha actual como la fecha del evento.

		// Envía el evento al tópico Kafka configurado.
		this.producer.send(topicCustomer, created);
	}
}
