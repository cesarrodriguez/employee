# Spring Boot "Microservice" Via Varejo Project
REST APIs implemented using Spring Boot Maven Project

## Requirements

1. Java - 1.8.x

## How to Run

* Build the project by running `./mvnw clean package` inside employee project
* Once successfully built, run the service by using the following command:`./mvnw spring-boot:run`

Alternatively, you can run the app without packaging it using -
`bash
java -jar target/exercises-0.0.1-SNAPSHOT.jar
`

## How to docker Run

* Build the docker by running `./mvnw clean install dockerfile:build` inside employee project
* Once successfully built, run the service by using the following command:
`docker run --name luizalabs -p 8080:8080 -t luizalabs/employee luizalabs-employee`

## How to create javadoc
* Build the javadoc by running `./mvnw javadoc:javadoc` inside employee project

## REST APIs Endpoints
### Create a new  Product resource
```
POST /installment/installments
Accept: application/json
Content-Type: application/json;charset=UTF-8

{
	"paymentRules":{"initValue":0.0,"qttInstallments":10},
	"product":{"code":"1","name":"Via Varejo's new product","value":1000.0}
}


```
### To view Swagger 2 API docs
```
Run the server and browse to localhost:8080/swagger-ui.html
```


	