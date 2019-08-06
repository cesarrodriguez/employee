# Spring Boot "Microservice" Luiza Labs Project
REST APIs implemented using Spring Boot Maven Project

## Requirements

1. Java - 1.8.x

## How to Run

* Build the project by running `./mvnw clean package` inside employee project
* Execute the command `cd employee-backend`to in child project
* Once successfully built, run the service by using the following command:`./mvnw spring-boot:run`

Alternatively, you can run the app without packaging it using -
`bash
java -jar target/employee-backend-0.0.1-SNAPSHOT.jar
`

## How to docker Run

* Build the docker by running `docker build -t luizalabs/employee-parent .` inside employee project (`cd ..)
* Once successfully built, run the service by using the following command:
`docker run --name luizalabs -p 8080:8080 -t luizalabs/employee-parent luizalabs-employee-parent`

## REST APIs Endpoints
### Create a new Employee resource
```
POST /employee
Accept: application/json
Content-Type: application/json;charset=UTF-8

{"name":"new Employee","id":0,"department":"TI","email":"ti@luizalabs.com.br"}

```

### Delete a Employee resource
```
DELETE /employee/{id}
Accept: application/json
Content-Type: application/json;charset=UTF-8


```

### Get EmployeeS resource
```
GET /employee
Accept: application/json
Content-Type: application/json;charset=UTF-8

```


### To view Swagger 2 API docs
```
Run the server and browse to localhost:8080/swagger-ui.html
```
## Employee Management
### To view Employee Management
	
```
Run the browse to localhost:8080
```