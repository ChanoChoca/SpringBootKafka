package com.chanochoca.app.events;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Clase abstracta que representa un evento genérico.
 *
 * `Event<T>` es una clase abstracta que define la estructura básica de un
 * evento en el sistema. Todos los eventos específicos deben extender esta
 * clase para heredar sus propiedades comunes, como el ID del evento, la fecha,
 * el tipo de evento y los datos asociados.
 *
 * @param <T> El tipo de datos que este evento transporta.
 */
@ToString
@Data
public abstract class Event<T> {

    /**
     * Identificador único del evento.
     * Este ID se genera para cada evento y se utiliza para rastrear el evento
     * a través del sistema.
     */
    private String id;

    /**
     * Fecha y hora en que se creó el evento.
     * Esta fecha se asigna cuando se genera el evento, permitiendo saber
     * cuándo ocurrió.
     */
    private Date date;

    /**
     * Tipo de evento.
     * El tipo de evento está definido por la enumeración `EventType`, que puede
     * indicar si el evento es de tipo CREACIÓN, ACTUALIZACIÓN o ELIMINACIÓN.
     */
    private EventType type;

    /**
     * Datos asociados al evento.
     * Este campo genérico `T` almacena los datos específicos que este evento
     * está transportando. Por ejemplo, en un evento de creación de cliente,
     * este campo podría contener la entidad `Customer`.
     */
    private T data;
}
