# EasyDocs

### Description

EasyDocs is a REST API for a document management system built using Java and Spring Boot. 

### Functionalities
 - Manage access to documents through user groups.
 - Possibility to share the document with the selected group.
 - Support for adding, deleting and editing documents (PDF, DOCX).

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

### Users (/user)

| methods  | Endpoint       | Description                                  | Authorisation
|----------|----------------|----------------------------------------------|---------------|
| GET      |/               |Retrieve a list of all users                  |Authenticated user
| POST     |/               |Create a new user                             |Authenticated user
| GET      |/{id}           |Retrieve user details by ID                   |Authenticated user
| PATCH    |/{id}           |Update user details (first name, last name, phone number, password, job title, or description) |Admin or the user themselves
| DELETE   |/{id}           |Delete a user by ID                           | Admin or the user themselves
| GET      |/changeToAdmin/{id} |Grant admin privileges to a user with the given ID | Admin only

**Example request for user creation**
   ```sh
  {
    "email" : "john.doe@corporation.com",
    "first_name" : "John",  
    "last_name" : "Doe",    
    "password" : "Password",
    "job_title" : "IT Administrator",    #not required
    "phone number" : "+48 123 123 123",  #not required
    "description" : "Some descritption"  #not required
}
   ```

### Documents(/document)

| methods  | Endpoint       | Description                                       | Authorisation
|----------|----------------|---------------------------------------------------|---------------|
| GET      |/                         |Retrieve a list of all documents         |Authenticated user
| GET      |/?documentName={name}    |Retrieve a list of all with the given name      |Authenticated user
| POST     |/                   |Upload a new document                          |Authenticated user
| GET      |/{id}           |Retrieve document details by ID                    |Authenticated user
| PATCH    |/{id}           |Update document details (name, description)        |Admin or owner
| DELETE   |/{id}           |Delete a document by ID                            |Admin or owner
| GET      |/documentByCreator/{id}   |Retrieve a list of all documents uploadet by user with given ID |Authenticated user
| GET      |/resource/{id}            |Retrieve a resource associated with a document  |Admin, owner or user with access to the document 

### Groups(/group)

| methods  | Endpoint       | Description                                  | Authorisation
|----------|----------------|----------------------------------------------|---------------|
| GET      |/               |Retrieve a list of all groups                 |Admin only
| POST     |/               |Create a new grouop                           |Authenticated user
| GET      |/{id}           |Retrieve group details by ID                  |Admin or group member
| DELETE   |/{id}           |Delete a group by ID                          |Admin or owner
| PATCH    |/{id}           |Update group details (name)                   |Admin or owner
| POST     |/addUser/{id}?user_id={user_id} |Add user to group |Admin or owner 
| POST     |/addDocument/{id} |Grant access to the document to group users |Admin or group member

**Example request for group creation**
   ```sh
  {
    "name" : "Group name"
  }
   ```
