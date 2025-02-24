# EasyDocs

# Description

EasyDocs is a REST API for a document management system built using Java and Spring Boot. It allows users to manage documents securely, supporting operations such as adding, retrieving, updating, and deleting users and documents.

## Technologies

- Java 17
- Spring Boot 3.4.1
- Spring Web
- Spring Data JPA
- Spring Security
- H2 (embedded data base)
- Maven
- PostgreSQL (target production database)
  
## Requirements

Before running, make sure you have it installed:

- **Java 17** or newer (`java -version`)
- **Maven** (`mvn -version`)

## Run the application locally

1. **Clone repository**
   ```sh
   git clone https://github.com/AgnieszkaNeu/EasyDocs.git
   cd EasyDocs
   ```
2. **Build app**
   ```sh
   mvn clean install
   ```
3. **Run app**
   ```sh
   mvn spring-boot:run
   ```
   
## Authentication & Authorization
EasyDocs uses JWT (JSON Web Token) for authentication.
To access protected endpoints, obtain a token by logging in via /auth/login and include it in requests using the Authorization: Bearer <TOKEN> header.

## Available endpoints
| methods  | Endpoint      | Description                        | Authorisation
|---------|---------------|------------------------------------|---------------|
| GET, POST     |/user   |Retrieve all users or add a new user | Permit all
| GET, PATCH,PUT,DELETE     |/user/{id}  | Retrieve, update, or delete a user by ID    | Permit all
| GET |/document |Retrieve all documents or filter by name using documentName parameter | authenticated
| GET, PATCH,PUT,DELETE | /document/{id} | Retrieve, update, or delete a document by ID| authenticated
| POST | /document |Add a new document| authenticated
| GET | /documentByCreator |Retrieve all documents created by a user using creator_id parameter| authenticated
| GET | /documentResource/{id} |Retrieve a document resource by ID | authenticated
