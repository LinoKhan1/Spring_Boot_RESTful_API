# Spring Boot RESTful API

## Overview

This project is a RESTful API built with Spring Boot. It provides endpoints for managing products in an e-commerce application. The API allows for basic CRUD operations (Create, Read, Update, Delete) on product data.

## Features

- **CRUD Operations**: Create, Read, Update, and Delete products.
- **DTO Mapping**: Utilizes `ModelMapper` for mapping between entity objects and Data Transfer Objects (DTOs).
- **Logging**: Integrated with SLF4J for logging application events.
- **Exception Handling**: Proper exception handling and logging for error scenarios.

## Technologies & Dependencies

- **Java 17**
- **Spring Boot 3.x**
- **Maven** (or Gradle, depending on your configuration)
- **ModelMapper** for DTO mapping
- **Spring Data JPA**
- **MySQL Driver**
- **Spring Web**
- **Lombok**
- **SLF4J** for logging

## Prerequisites

- **Java 17** or later
- **Maven** or **Gradle** for dependency management
- **IDE** (IntelliJ IDEA, Eclipse, VS Code, etc.) for development

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/yourusername/your-repository.git
cd your-repository
```
### Build the Project
For Maven:
```bash
mvn clean install
```
### Run the Application
```bash
mvn spring-boot:run
```
## API Endpoints

### Get All Products

**GET** `/api/products`

Returns a list of all products.

### Get Product by ID

**GET** `/api/products/{id}`

Returns a single product by its ID.

### Create a New Product

**POST** `/api/products`

Creates a new product. Requires a request body with product details.

### Update an Existing Product

**PUT** `/api/products/{id}`

Updates an existing product identified by its ID. Requires a request body with updated product details.

### Delete a Product

**DELETE** `/api/products/{id}`

Deletes a product identified by its ID.

## Configuration

The application can be configured via `application.properties` or `application.yml`. By default, it uses an in-memory H2 database. To configure a different database, update the database connection properties accordingly.

## Testing

Run unit and integration tests using Maven or Gradle:

For Maven:
```bash
mvn test
```
## Contributing
Feel free to submit issues or pull requests. Contributions are welcome!

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgements
Spring Boot for the framework
ModelMapper for DTO mapping
SLF4J for logging

