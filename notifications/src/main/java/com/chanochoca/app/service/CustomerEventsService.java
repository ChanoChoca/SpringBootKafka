package com.chanochoca.app.service;

import com.chanochoca.app.events.CustomerCreatedEvent;
import com.chanochoca.app.events.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Servicio para manejar eventos relacionados con clientes en Kafka.
 *
 * `CustomerEventsService` es responsable de escuchar y procesar eventos de
 * Kafka relacionados con la creación de clientes. Utiliza un listener de
 * Kafka para recibir eventos y registrar la información relevante cuando
 * se recibe un evento de tipo `CustomerCreatedEvent`.
 */
@Slf4j
@Component
public class CustomerEventsService {

	/**
	 * Método que consume eventos de Kafka relacionados con clientes.
	 *
	 * Este método está anotado con `@KafkaListener`, lo que indica que debe
	 * actuar como un listener de Kafka para un tópico específico. Procesa
	 * eventos de tipo `Event<?>` y, si el evento es una instancia de
	 * `CustomerCreatedEvent`, registra los detalles del evento.
	 *
	 * @param event El evento recibido de Kafka, que se espera sea de tipo `Event<?>`.
	 */
	@KafkaListener(
			topics = "${topic.customer.name:customers}",
			containerFactory = "kafkaListenerContainerFactory",
			groupId = "grupo1")
	public void consumer(Event<?> event) {
		// Verifica si el evento es de tipo CustomerCreatedEvent
		if (event.getClass().isAssignableFrom(CustomerCreatedEvent.class)) {
			// Realiza un cast al tipo específico
			CustomerCreatedEvent customerCreatedEvent = (CustomerCreatedEvent) event;

			// Registra la información del evento
			log.info("Received Customer created event .... with Id={}, data={}",
					customerCreatedEvent.getId(),
					customerCreatedEvent.getData().toString());
		}
	}
}
