﻿# Purchase order producer
Microservice to handle purchase order functionality from sales order perspective
This microservice is based on "Quarkus" https://quarkus.io/
it is supposed to integrate with Kafka to create order events to be consumed by another consumer microservice
## Developer Notes

### TODO list

- [x] Create project skeleton
- [x] create model, entities, service, resource and repository
- [x] Use JPA entity manager to persist and retrieve from PostgreSQL
- [ ] Integrate with Kafka
- [ ] add security
- [ ] add junit
- [ ] add logging
- [ ] add liquibase support
- [ ] add docker image generation commands


## Testing

To run the application start postgreSQL server, create purchaseOrder DB username: learning password: password123
type .\mvnw compile quarkus:dev

Create order:

POST /purchaseorder HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: */*
{
	"salesPersonId": 1,
    "productName": "forks",
    "location": "Cairo",
    "quantity": 10,
    "forCompaniesOnly":true
}

Get orders:

GET /purchaseorder/1 HTTP/1.1
Host: localhost:8080
Accept: */*