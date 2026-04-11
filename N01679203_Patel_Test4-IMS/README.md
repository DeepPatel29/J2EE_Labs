# Product Inventory Management System

**Student Name**: Deep Patel  
**Humber ID**: N01679203  
**Course**: ITE5432 - J2EE Business Components  

## Project Description
This is a Product Inventory Management System built with Spring Boot for managing product stock, pricing, and descriptions.

## Technologies Used
- Java 17
- Spring Boot 4.x
- Spring Data JPA
- Lombok
- MySQL Database
- Maven

## Features
- Create new products
- View all products
- Update product details
- Delete products
- Update stock levels

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/products | Get all products |
| GET | /api/products/{id} | Get product by ID |
| POST | /api/products | Add new product |
| PUT | /api/products/{id} | Update product |
| DELETE | /api/products/{id} | Delete product |
| PATCH | /api/products/{id}/stock | Update stock |

## How to Run
1. Create MySQL database: `product_inventory_db`
2. Update `application.properties` with your MySQL credentials
3. Run: `mvnw spring-boot:run`
4. Access: http://localhost:8080/api/products

## Project Structure
```
src/main/java/com/humber/productims/
├── entity/
│   └── Product.java
├── repository/
│   └── ProductRepository.java
├── service/
│   ├── ProductService.java
│   └── ProductServiceImpl.java
└── controller/
    └── ProductController.java
```