package com.chanochoca.app.events;

/**
 * Enumeración que define los tipos de eventos posibles en la aplicación.
 *
 * `EventType` se utiliza para clasificar los eventos según su naturaleza,
 * facilitando la identificación y el manejo de las operaciones realizadas
 * sobre los objetos o recursos de la aplicación.
 */
public enum EventType {

	/**
	 * Indica que un recurso u objeto ha sido creado.
	 */
	CREATED,

	/**
	 * Indica que un recurso u objeto ha sido actualizado.
	 */
	UPDATED,

	/**
	 * Indica que un recurso u objeto ha sido eliminado.
	 */
	DELETED
}
