# Shopping Application

## Architecture:
![Alt text](_assets/shopping-management-architecture.svg?raw=true "Shopping Management Architecture")

## There are three microservices:

- **Orders** : This microservice is responsible for managing orders. 
- **Catalog** : This microservice is responsible for managing catalog.
- **Reports** : This microservice is responsible for managing reports.

### EndPoints ###

| Service | EndPoint        | Port | Method | Description            |
|---------|-----------------|:----:|:------:|------------------------|
| Orders  | /api/v1/orders  | 8080 |  GET   | Return order's list    |
| Orders  | /api/v1/orders  | 8080 |  POST  | Processing order       |
| Reports | /api/v1/reports | 8082 |  GET   | Returns order's report |

### Documentation and examples ###

###Swagger

- **Orders** : http://localhost:8081/orders/swagger-ui.html
- **Reports** : http://localhost:8082/reports/swagger-ui.html

###Postman collection

![Alt text](_assets/postman-collection-folder.png?raw=true "Postman collection folder")


## Build & Run

- *>mvn clean package* : to build
- *>docker-compose up --build* : build docker images and containers and run containers
- *>docker-compose stop* : stop the dockerized services
- Each maven module has a Dockerfile.

In docker-compose.yml file:

- Orders Service : **__8080__** port is mapped to **__8080__** port of host
- Report Service : **__8082__** port is mapped to **__8082__** port of host

## VERSIONS

### 1.0.0

- Spring-Boot 2.6.6
- Spring Cloud Sleuth
- Spring Administration
- Splunk
- Java 17