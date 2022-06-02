

# Descrição

Esse projeto implementa o backend de uma aplicação que permite o usuário adicionar produtos eum carrinho e realizar
a compra dos produtos presentes no carrinho.

O objetivo deste projeto é utilizar a regra de negócio acima para exercitar os conceitos de microserviços, portante
neste projeto foram criados 3 microserviços:

* [usuários](https://github.com/amandaamabili/e-commerce-user)
* [vendas](https://github.com/amandaamabili/e-commerce-sales)
* [produtos](https://github.com/amandaamabili/e-commerce-products)


## Tecnologias utilizadas

### Todos os microserviços

* Docker
* Docker Compose


### Microserviços de usuários e vendas

* Gradle
* Java 11
* Spring Boot
* Spring Data
* Spring JPA
* Lombok
* MongoDB

### Microserviçõs de produtos
* PHP 8
* Laravel 9
* PostgreSQL


### Clone

```console
https://github.com/amandaamabili/e-commerce-products
https://github.com/amandaamabili/e-commerce-products
https://github.com/amandaamabili/e-commerce-sales
```

### Deploy

O deploy foi realizado em uma instância EC2 da Amazon e está disponível na urls

- usuário ->  http://ec2-3-85-103-111.compute-1.amazonaws.com:8001
- vendas ->   http://ec2-3-85-103-111.compute-1.amazonaws.com:8002
- produto ->

## Requisições

### Postman
Coleção disponibilizada no postman.

https://www.getpostman.com/collections/7677562a62db209b5669

### Swagger

```
{"openapi":"3.0.1","info":{"title":"Ecommerce API","description":"Information about e-commerce user API","version":"1.0"},"servers":[{"url":"http://localhost:8005/user","description":"Generated server url"}],"paths":{"/user/{id}":{"get":{"tags":["user-controller"],"operationId":"getById","parameters":[{"name":"id","in":"path","required":true,"schema":{"type":"string"}}],"responses":{"200":{"description":"OK","content":{"*/*":{"schema":{"$ref":"#/components/schemas/User"}}}}}},"put":{"tags":["user-controller"],"operationId":"update","parameters":[{"name":"id","in":"path","required":true,"schema":{"type":"string"}}],"requestBody":{"content":{"application/json":{"schema":{"$ref":"#/components/schemas/UserDTO"}}},"required":true},"responses":{"200":{"description":"OK","content":{"*/*":{"schema":{"type":"object"}}}}}},"delete":{"tags":["user-controller"],"operationId":"deleteById","parameters":[{"name":"id","in":"path","required":true,"schema":{"type":"string"}}],"responses":{"200":{"description":"OK","content":{"*/*":{"schema":{"type":"object"}}}}}}},"/user":{"post":{"tags":["user-controller"],"operationId":"save","requestBody":{"content":{"application/json":{"schema":{"$ref":"#/components/schemas/UserDTO"}}},"required":true},"responses":{"200":{"description":"OK","content":{"*/*":{"schema":{"type":"object"}}}}}}}},"components":{"schemas":{"UserDTO":{"type":"object","properties":{"id":{"type":"string"},"name":{"type":"string"},"email":{"type":"string"},"cpf":{"type":"string"}}},"User":{"type":"object","properties":{"get_id":{"type":"string"},"name":{"type":"string"},"email":{"type":"string"}}}}}}

```
