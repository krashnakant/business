# Getting Started

### Reference Documentation

### Pre-Requisites

For further reference, please consider the following sections:

* [Install Docker & Docker compose](https://docs.docker.com)
* [Install openJDK11](https://openjdk.java.net)
* [Install Gradle optional](https://gradle.org)
* Use any of your IDE (Intellij, Eclipse)
### Guides

The following guides illustrate how to use build and run application:

```
navigate to project root directory and run 

docker-compose up # use -d for running in backgroud.

```
### How to use API

use swagger ui to see request response for all api.
```
http://localhost:8080/swagger-ui-custom.html
```
or directly use api described below

```
URI = http://localhost:8080

GET /api/agencies

Response [
  {
    "id": "string",
    "name": "string",
    "countryCode": "string",
    "country": "string",
    "city": "string",
    "street": "string",
    "currency": "string",
    "contactPerson": "string"
  }
]
###

POST /api/agencies
body {
  "name": "string",
  "countryCode": "string",
  "country": "string",
  "city": "string",
  "street": "string",
  "currency": "string",
  "contactPerson": "string"
}
###

DELETE /api/agencies/{id}

###

PUT /api/agencies/{id}
body 
{
  "name": "string",
  "countryCode": "string",
  "country": "string",
  "city": "string",
  "street": "string",
  "currency": "string",
  "contactPerson": "string"
}
```
