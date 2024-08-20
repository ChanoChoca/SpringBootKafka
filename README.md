## Spring Boot with Apache Kafka

Kafka es un bus de mensajes **asincrónico** de código abierto considerado escalable, altamente distribuido y muy resiliente o resistente a fallos.

![Main image](kafka.svg)

Características:
* Alto rendimiento, muy cercano al tiempo real.
* Distribuido, escalable y tolerante a fallos.
* Guarda los registros, lo que permite recuperación ante desastres mediante reprocesamiento.
* Conectores, KSQL y más.

Se tienen dos microservicios
- Customers que recibe la petición y produce el evento encolandolo en el bus de mensajes de Kafka.
- Notifications que los recibe y hace algo con los datos recibidos.

## Tabla de Contenidos

1. [Herramientas](#herramientas-usadas)
2. [Testeo](#testeo)
3. [Endpoints](#endpoints)

## Herramientas usadas

- Spring Boot
    - Lombok
    - Spring Web
    - Spring for Apache Kafka

## Testeo

Debes tener instalado Apache Kafka en tu ordenador.

Iniciar Zookepeer, iniciar Kafka.

Crear un topic en el servidor kafka, llamado `customers` con puerto por defecto de Kafka `9092` e iniciar para ver mensajes de un topic.

Crear un cliente mediante endpoint y verlo en la terminal de mensajes.

## Endpoints

- **POST http://localhost:8080/customers**
  - **Descripción:** Permite crear un nuevo cliente.
  - **Cuerpo de la solicitud:**
    - `{ "id": "ID del cliente", "nombre": "Su nombre", "email": "Su email" }`
  - **Respuesta:**
    - Estado 200 Ok si el cliente se crea exitosamente.
    - Estado 400 Bad Request si hay un error en la solicitud.
    - Cuerpo de la respuesta: `{ "id": "ID del cliente", "nombre": "Su nombre", "email": "Su email" }`

## Authors

- [@Juan Ignacio Caprioli (ChanoChoca)](https://github.com/ChanoChoca)

## Badges

[//]: # (Add badges from somewhere like: [shields.io]&#40;https://shields.io/&#41;)

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)

[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)

[![AGPL License](https://img.shields.io/badge/license-AGPL-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)
