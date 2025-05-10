# Proyecto DEMO MICROSERVICES

## Tecnologías utilizadas

En este proyecto, se utilizan las siguientes tecnologías:

- **Kafka**
- **Spring Boot**
- **MySQL**
- **Spring cloud gateway**
- **Eureka**
- **Config server**

### Diagrama de arquitectura

![Diagrama de arquitectura del sistema](https://github.com/user-attachments/assets/58b7ca17-1937-4e7e-b27b-16d7f23769ef)

La estructura del proyecto es la arquitectura hexagonal
### Estructura del proyecto 

<div style="display: flex; justify-content: space-between;">
  
  <img src="https://github.com/user-attachments/assets/a3cc7fba-4ce6-4a88-b70c-9d4b86fff5f3" alt="Diagrama 1" width="400px" style="height: auto;"/>
  ------>
  <img src="https://github.com/user-attachments/assets/a3921513-517e-43b9-8ed2-12649abf5d76" alt="Diagrama 2" width="400px" style="height: auto;"/>

</div>

## Configuraciòn previa
### Docker Compose Configuration

```yaml
version: '1.0'

services:
  ## MySQL Docker Compose Config
  mysql-persistent:
    container_name: mysql-persistent
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: 230011
      MYSQL_DATABASE: demo_db
    volumes:
      - ./mysql-persistent:/var/lib/mysql
    expose:
      - "3306"
    ports:
      - "3306:3306"
    restart: always
    command: --default-authentication-plugin=mysql_native_password

  zookeeper:
    image: confluentinc/cp-zookeeper:7.0.1
    container_name: zookeeper
    ports:
      - "2181:2181"
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000

  broker:
    image: confluentinc/cp-kafka:7.0.1
    container_name: broker
    ports:
      - "9092:9092"
      - "29092:29092"
    depends_on:
      - zookeeper
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: 'zookeeper:2181'
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_INTERNAL:PLAINTEXT
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092,PLAINTEXT_INTERNAL://broker:29092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 1
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 1
```

## Base de Datos

Para inicializar la base de datos, puedes utilizar el archivo SQL disponible en el siguiente enlace:

[BaseDatos.sql](https://github.com/CyberJoel1/microservices-project/blob/master/microservices-parent/src/main/resources/static/BaseDatos.sql)



### Orden de ejecución

1. **config-server**
   - El `config-server` es responsable de proporcionar la configuración externa a todos los microservicios. Debe ser el primero en iniciarse para asegurar que todos los servicios tengan acceso a la configuración necesaria.

2. **discovery-server (Eureka Server)**
   - El `discovery-server` o `Eureka Server` permite la registración y descubrimiento de microservicios entre sí. Después de que `config-server` esté en funcionamiento, se debe iniciar el `discovery-server` para que los servicios puedan registrarse.

3. **api-gateway**
   - El `api-gateway` es el punto de entrada para todas las solicitudes externas, que delega las peticiones a los microservicios correspondientes. Se inicia después de `discovery-server` para que pueda conectarse al servidor Eureka y descubrir los microservicios.

4. **transaction-service**
   - El `transaction-service` se encarga de manejar las transacciones y las operaciones asociadas con movimientos y cuentas. Este servicio debe iniciarse después del `api-gateway`, ya que el gateway lo necesita para enrutar las solicitudes.

5. **user-service**
   - Finalmente, el `user-service` gestiona la lógica de usuario y es el último servicio en iniciar, ya que no depende directamente de los servicios anteriores, pero puede ser consumido por otros servicios a través del `api-gateway`.



## Manejo de Excepciones en el Proyecto

### Uso de Excepciones

El manejo de excepciones es una parte crucial en el desarrollo de aplicaciones robustas. Las excepciones permiten manejar errores de manera controlada y garantizar que la aplicación pueda recuperarse o informar al usuario de manera adecuada. En este proyecto, las excepciones se manejan de manera diferenciada, dependiendo de su tipo y gravedad.

### Excepciones de Lógica de Negocio

Las excepciones de **lógica de negocio** son aquellas que se lanzan cuando se detecta una condición especial dentro de la lógica del negocio de la aplicación. Estas excepciones reflejan errores que deben ser comunicados al usuario o cliente del sistema.

  - **Ejemplo**: Si un usuario intenta realizar una transferencia bancaria cuyo saldo es insuficiente, se generaría una `BusinessLogicException` para que el sistema informe que el saldo es insuficiente para realizar la operación.

### Excepciones Genéricas

Las excepciones genéricas se utilizan para manejar errores inesperados o aquellos que no están directamente relacionados con la lógica de negocio. Estas excepciones pueden ser causadas por problemas en la infraestructura del sistema, como problemas de conexión a la base de datos o errores del servidor.

### Manejo Global de Excepciones

En el proyecto, las excepciones son manejadas globalmente utilizando un controlador de excepciones que se encarga de capturar las excepciones y devolver una respuesta adecuada.

#### Ejemplo de manejo de excepciones en el sistema:

- **Excepción de Lógica de Negocio**: Cuando se lanza una `BusinessLogicException`, el sistema genera una respuesta con un código de error específico y un mensaje que describe el problema. Este tipo de error suele devolver un estado HTTP 500 (Internal Server Error).

- **Excepción Genérica**: Cuando ocurre un error no anticipado, el sistema devuelve una respuesta genérica indicando que algo salió mal, generalmente con un estado HTTP 500.

#### Respuesta en formato JSON:

Cuando ocurre una excepción de lógica de negocio, la respuesta será algo como:

```json
{
  "errorCode": "BUSINESS_LOGIC_ERROR",
  "message": "Balance not available"
}
```
### Manejo de DATA para reporte

#### Petición GET

GET /reportes?clientId=32&startDate=2024-02-24T15:30:00&endDate=2026-03-24T15:30:00


### Resultado en JSON

```json
[
    {
        "account": {
            "accountNumber": "accountNumber",
            "type": "S",
            "accountBalance": {
                "balance": 60.00
            },
            "client": {
                "clientId": 32
            },
            "movements": [
                {
                    "id": 17,
                    "date": "2025-03-07T04:29:09",
                    "type": "C",
                    "amount": 50.00,
                    "remainingBalance": 70.00,
                    "status": true
                },
                {
                    "id": 18,
                    "date": "2025-03-07T04:29:09",
                    "type": "D",
                    "amount": 10.00,
                    "remainingBalance": 60.00,
                    "status": true
                }
            ]
        }
    },
    {
        "account": {
            "accountNumber": "11111",
            "type": "S",
            "accountBalance": {
                "balance": 110.00
            },
            "client": {
                "clientId": 32
            },
            "movements": [
                {
                    "id": 19,
                    "date": "2025-03-07T04:31:26",
                    "type": "C",
                    "amount": 50.00,
                    "remainingBalance": 70.00,
                    "status": true
                },
                {
                    "id": 20,
                    "date": "2025-03-07T04:31:26",
                    "type": "D",
                    "amount": 10.00,
                    "remainingBalance": 60.00,
                    "status": true
                },
                {
                    "id": 21,
                    "date": "2025-03-07T04:32:10",
                    "type": "C",
                    "amount": 50.00,
                    "remainingBalance": 110.00,
                    "status": true
                }
            ]
        }
    },
    {
        "account": {
            "accountNumber": "33333",
            "type": "S",
            "accountBalance": {
                "balance": 60.00
            },
            "client": {
                "clientId": 32
            },
            "movements": [
                {
                    "id": 22,
                    "date": "2025-03-07T04:32:56",
                    "type": "C",
                    "amount": 50.00,
                    "remainingBalance": 70.00,
                    "status": true
                },
                {
                    "id": 23,
                    "date": "2025-03-07T04:32:56",
                    "type": "D",
                    "amount": 10.00,
                    "remainingBalance": 60.00,
                    "status": true
                }
            ]
        }
    },
    {
        "account": {
            "accountNumber": "123456789",
            "type": "S",
            "accountBalance": {
                "balance": 60.00
            },
            "client": {
                "clientId": 32
            },
            "movements": [
                {
                    "id": 24,
                    "date": "2025-03-07T04:33:41",
                    "type": "C",
                    "amount": 50.00,
                    "remainingBalance": 70.00,
                    "status": true
                },
                {
                    "id": 25,
                    "date": "2025-03-07T04:33:41",
                    "type": "D",
                    "amount": 10.00,
                    "remainingBalance": 60.00,
                    "status": true
                }
            ]
        }
    }
]
```
## Uso de pruebas unitarias con MOCKITO JUNIT 5

![image](https://github.com/user-attachments/assets/f7679813-5af8-4845-9834-ea4b5e44ced1)

Se realizó pruebas unitarias para cubrir un 100% de líneas de código para `userService`.


## Prueba de integración con el framework KARATE

- Crear un cliente
- Buscar un cliente

![image](https://github.com/user-attachments/assets/5ee38192-b5e0-48db-95b3-81f7a37b1a32)


