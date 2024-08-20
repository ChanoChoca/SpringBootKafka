package com.chanochoca.app.events;

import com.chanochoca.app.entity.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Evento que representa la creación de un nuevo cliente en el sistema.
 *
 * `CustomerCreatedEvent` extiende la clase genérica `Event` y define
 * el tipo de dato específico del evento como `Customer`. Este evento
 * se utiliza para encapsular la información relacionada con un cliente
 * recién creado y para transmitir esta información a través de sistemas
 * de mensajería como Kafka.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerCreatedEvent extends Event<Customer> {
}
