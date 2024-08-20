package com.chanochoca.app.service;

import com.chanochoca.app.entity.Customer;
import org.springframework.stereotype.Service;

/**
 * Servicio que maneja la lógica de negocio relacionada con la entidad `Customer`.
 *
 * `CustomerService` es responsable de recibir, procesar y guardar los objetos
 * `Customer`. Además, delega la publicación de eventos a `CustomerEventsService`
 * para notificar la creación de nuevos clientes.
 */
@Service
public class CustomerService {

	// Servicio para manejar la publicación de eventos relacionados con clientes.
	private final CustomerEventsService customerEventsService;

	/**
	 * Constructor que inyecta la dependencia `CustomerEventsService`.
	 *
	 * @param customerEventsService El servicio que maneja la publicación de eventos.
	 */
	public CustomerService(CustomerEventsService customerEventsService) {
		super();
		this.customerEventsService = customerEventsService;
	}

	/**
	 * Guarda un cliente y publica un evento de creación.
	 *
	 * Este método recibe un objeto `Customer`, lo guarda (simulado aquí con
	 * una simple impresión en consola), y luego utiliza el `CustomerEventsService`
	 * para publicar un evento que notifica la creación de este cliente.
	 *
	 * @param customer El cliente que se va a guardar.
	 * @return El objeto `Customer` recibido.
	 */
	public Customer save(Customer customer) {
		// Simula el guardado del cliente con una impresión en consola.
		System.out.println("Received " + customer);

		// Publica un evento de creación del cliente.
		this.customerEventsService.publish(customer);

		// Retorna el cliente recibido.
		return customer;
	}
}
