# 🛒 Simple Sales System

A Spring Boot + H2 demo for managing **Products, Clients, Sales, and Transaction Logs**.  
Includes APIs, validation, logging, and documentation with Postman + diagrams.

---

## 📂 Structure

- `src/main/java/com/ag/simplesalessystem/` → controllers, services, repositories, entities, DTOs
- `src/main/resources/application.properties` → H2 + JPA config
- `docs/` → class diagram, DB diagram, Postman collection

---

## ⚙️ Prerequisites

Before running, make sure you have:

- [Java 17+ JDK](https://adoptium.net/) (required to run Spring Boot)
- Maven (optional, since Maven Wrapper is included)
- Postman (optional, to test APIs)

---

## 🚀 How to Run

### 1. Clone the project

```bash
git clone https://github.com/your-username/simple-sales-system.git
cd simple-sales-system
2. Run the application

Using Maven Wrapper (recommended):
mvnw spring-boot:run

Or if Maven is installed globally:
mvn spring-boot:run

3. Access the app
API base URL: 👉 http://localhost:8080

H2 Database Console: 👉 http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:salesdb

User: sa

Password: (leave empty)

📡 API Endpoints
Products
GET /api/products
POST /api/products
PUT /api/products/{id}

Clients
GET /api/clients
POST /api/clients
PUT /api/clients/{id}

Sales
GET /api/sales
POST /api/sales
PUT /api/sales/{id}

Request example:

json
Copy
Edit
{
  "clientId": 1,
  "transactions": [
    { "productId": 1, "quantity": 2, "price": 4.75 },
    { "productId": 2, "quantity": 1 }
  ]
}
Logs
GET /api/logs → shows changes (old/new values + timestamp)

🧰 Documentation
Class Diagram → docs/class-diagram.pdf

DB Diagram → docs/db-diagram.pdf

Postman Collection → docs/SimpleSalesSystem.postman_collection.json

To test APIs with Postman:

Open Postman

Import docs/SimpleSalesSystem.postman_collection.json

Run requests against http://localhost:8080

✅ Features
Products, Clients, Sales APIs

Optional custom prices (defaults to product price)

Transaction update logging

In-memory H2 database

Postman tests + diagrams included
```
