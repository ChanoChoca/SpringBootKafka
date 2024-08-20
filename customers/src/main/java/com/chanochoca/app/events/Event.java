package com.chanochoca.app.events;

import lombok.Data;
import java.util.Date;

/**
 * Clase abstracta que representa un evento genérico en la aplicación.
 *
 * Esta clase utiliza un parámetro genérico `<T>` que permite que el evento contenga
 * cualquier tipo de dato en su contenido. Los eventos pueden ser de diferentes tipos
 * (`EventType`) y contienen información relevante como un ID, la fecha del evento,
 * el tipo de evento y los datos asociados al mismo.
 *
 * @param <T> El tipo de los datos contenidos en el evento.
 */
@Data
public abstract class Event<T> {

    /**
     * Identificador único del evento.
     */
    private String id;

    /**
     * Fecha y hora en que se creó el evento.
     */
    private Date date;

    /**
     * Tipo del evento, representado por la enumeración `EventType`.
     * Este campo permite clasificar los eventos según su propósito o naturaleza.
     */
    private EventType type;

    /**
     * Datos específicos del evento, cuyo tipo es determinado por el parámetro genérico `<T>`.
     * Este campo permite que la clase `Event` sea flexible y pueda ser reutilizada para
     * representar diferentes tipos de eventos que contienen distintos tipos de datos.
     */
    private T data;
}
