## Introduction

Microservices -  Autonomous Small and Focussed unlike the Monolith style
Avoids codebase to grow too large.
They can be deployed on different machines
Communicate through network calls
Each service exposes an API to let others consume them.

Goal: Change one service without impacting the other services.

Characteristics of MicroServices:
1. Heterogenous : Each service can be made in different P language on different platform
   (Choose technology suited to application or function of service)
2. Robustness : One drop does not affect other services
3. Scalability : Easy to grow (specific to modules that need to grow :specific services)
4. Easy to deploy : Change where required and redeploy only them
5. Reusable and Replaceable

SOA and Microservices ?
MS are extensions of Service Oriented Architecture.
SOA is a design approach where multiple services collaborate to provide business e2e
functionality.
Failed due to lack of industry standard.

REST

Architectural guidelines that help communication
1. Uniform Interface and easy access -> ex. HTTP verbs GET, PUT, POST, DELETE
2. Nouns + verbs used for crud. (Nouns is the uri -> ex. /employees)
3. Support multiple formats ex JSON, XML, plain text, etc

HATEOAS
* Hypermedia as the Engine Of Application State
* 100 % REST compatible application, it should support HATEOS
* Enables to access all resources using hypermedia links.
* HATEOAS  states that rest responses should include links to navigate through

HAL (Hypertext Application Language)
* It is implementation of HATEOAS

(Spring Data automatically does this)

Q) Why REST for microservices ?
* Only needed is HTTP and data format like JSON, XML
* Gracefully can be degraded
* Applications are stateless
* Easy to deploy
* Reusable and Replaceable

### Spring Data REST

Before Spring Data REST,
we used to create Spring MVC -> Services -> DAO (JPA, connection, exposing data models)
HATEOAS would require additional support

Spring Data simplifies Data Access Layer for us.
1. Create JPA Entity
2. Implement/extend interface like CrudRepository

Spring Data Rest used this to expose all these operations as REST Resources.
(HAL and HATEOAS support included)

## Steps

1. Create the Spring Boot Project
2. Create the Domain Model/Entities
3. Create the Repositories
4. Configure the DataSource
5. Up and Running


#### GET REQUEST RESPONSE
HTTP METHOD: GET
``` yaml {
{
    "_links": {
        "employees": {
            "href": "http://localhost:8080/employees"
        },
        "profile": {
            "href": "http://localhost:8080/profile"
        }
    }
}
```

#### POST REQUEST RESPONSE
HTTP METHOD: POST
URL: http://localhost:8080/employees
Request
```yaml {
{
    "firstName": "Sumit",
    "lastName": "Singh"
}
```

Response
```yaml {
{
    "firstName": "Sumit",
    "lastName": "Singh",
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees/1"
        },
        "employee": {
            "href": "http://localhost:8080/employees/1"
        }
    }
}
```
#### PUT Request Response
HTTP METHOD: PUT
(updates whole object, if something is not passed -> null )
URL: http://localhost:8080/employees/1

Request: 
```yaml {
{
    "firstName": "Sumit",
    "lastName": "Kumar Singh"
}
```

Response: 
```yaml {
{
    "firstName": "Sumit",
    "lastName": "Kumar Singh",
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees/1"
        },
        "employee": {
            "href": "http://localhost:8080/employees/1"
        }
    }
}
```

#### Partial Update using PATCH
HTTP METHOD: PATCH
URL: http://localhost:8080/employees/1

Request: 
```yaml {
{
    "firstName": "Sumit Kumar"
}
```

Response: 
```yaml {
{
    "firstName": "Sumit Kumar",
    "lastName": "Kumar Singh",
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees/1"
        },
        "employee": {
            "href": "http://localhost:8080/employees/1"
        }
    }
}
```
#### Delete 
HTTP METHOD: DELETE
URL: http://localhost:8080/employees/1
Response Code: 204

* As per safe rules, id is not exposed as a separate field in the response json.
The getter method for id field is ignored.
However, we can get around that by providing a getter with alias for id.
  ex: response after adding getResourceId() getter
  
``` yaml {
  {
    "firstName": "Sumit Kumar",
    "lastName": "Singh",
    "resourceId": 2,
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees/2"
        },
        "employee": {
            "href": "http://localhost:8080/employees/2"
        }
    }
}
```


  

