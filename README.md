# Spring Boot MongoDB Demo

A Spring Boot application demonstrating Spring Data MongoDB integration, including document modelling, embedded references, database references, and automatic index creation.

## Tech Stack

- Java 17
- Spring Boot 4.0.3
- Spring Data MongoDB
- MongoDB (with authentication)
- Maven

## Project Structure

```
src/main/java/com/example/mongodb/
├── Application.java          # Entry point
├── MongoConfig.java          # Custom MongoClient configuration
└── model/
    ├── Product.java          # Main document with indexed field
    ├── Manufacturer.java     # Referenced document (DBRef)
    └── Size.java             # Embedded document
```

## Data Model

**Product** is the primary MongoDB document. It contains:

| Field          | Type           | Notes                              |
|----------------|----------------|------------------------------------|
| `id`           | `String`       | MongoDB `_id`                      |
| `name`         | `String`       | Ascending index, auto-created      |
| `description`  | `String`       | Stored as `desc` in MongoDB        |
| `price`        | `double`       |                                    |
| `size`         | `Size`         | Embedded document                  |
| `manufacturer` | `Manufacturer` | Database reference (`@DBRef`)      |

## Prerequisites

- Java 17+
- MongoDB running on `localhost:27017` with authentication enabled
- A MongoDB user with `readWrite` access to the `products` database, authenticated via the `admin` database

### Creating the MongoDB user

```js
use admin
db.createUser({
  user: "root",
  pwd: "password",
  roles: [
    { role: "readWrite", db: "products" },
    { role: "dbAdmin", db: "products" }
  ]
})
```

## Configuration

Connection settings are in `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb://root:password@localhost:27017/products?authSource=admin
spring.data.mongodb.database=products
spring.data.mongodb.auto-index-creation=true
```

### Note on Spring Boot 4.x and MongoDB authentication

Spring Boot 4.x has a known issue where the auto-configured `MongoClient` does not correctly apply URI credentials before the index creation lifecycle runs. This project works around it with an explicit `MongoConfig` bean that creates the `MongoClient` directly from the URI using the MongoDB driver:

```java
@Bean
public MongoClient mongoClient() {
    return MongoClients.create(mongoUri);
}
```

## Running the Application

```bash
./mvnw spring-boot:run
```

## Running the Tests

```bash
./mvnw test
```

The test in `MongoTemplateTests` uses `MongoTemplate` directly to list collection names, verifying that the application context loads and the database connection is healthy.
