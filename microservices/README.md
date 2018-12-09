# RESTful

This service supporting the basic CRUD operations for products and orders

## Prerequisites

- Java 8
- MongoDB

## Compilation

mvn clean install

mvn package && java -jar target/mycompany-core.jar

curl localhost:8080

## API

/api/v1/products/
/api/v1/orders/

## Recommendations
- I would have liked to use Apache Thrift for the integration between services. I think it's much more transparent.