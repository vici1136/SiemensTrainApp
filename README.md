# Siemens Train Ticketing Application

## Overview
This is a Spring Boot REST API built for the Siemens Java Developer Trainee assignment. It manages train routes, ticket bookings with overbooking prevention, and automated email notifications.

## Architecture
The application uses a standard N-Tier Layered Architecture:
* **Controllers:** Expose REST API endpoints.
* **Services:** Contain the core business logic (e.g., BFS/Iterative route searching, capacity validation, email sending).
* **Repositories:** Interface with the Database using Spring Data JPA.
* **Database:** An in-memory H2 database for rapid testing and zero-configuration setup.

## How to Run
1. Ensure you have Java 17 (or newer) and Maven installed.
2. Clone the repository.
3. Run the application using your IDE or via terminal:
   `mvn spring-boot:run`
4. The application will start on `http://localhost:8080`.

## H2 Database Console (For Testing)
You can access the database directly to add test data (Stations, Trains, Stops):
* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:trainsdb`
* **User:** `sa` (no password)

## API Endpoints & Examples

### 1. Book a Ticket (Prevents Overbooking & Sends Email)
**POST** `/api/book`
Example Request (Terminal/cURL):
```bash
curl -X POST "http://localhost:8080/api/book?nrTren=123&idPlecare=1&idDestinatie=2&email=customer@example.com"
```
*Note: The system automatically checks the `capacitate` of the train for that specific day. If full, it returns an overbooking error.*

### 2. Find Routes (Direct & Changeovers)
**GET** `/api/routes`
Example Request (Terminal/cURL or directly in Browser):
```bash
curl -X GET "http://localhost:8080/api/routes?idPlecare=1&idSosire=2"
```
*Returns a list of possible connections, checking for direct routes or routes with 1 changeover, calculating time overlaps.*

### 3. Report Delays (Admin Feature)
**POST** `/api/admin/delay`
Example Request (Terminal/cURL):
```bash
curl -X POST "http://localhost:8080/api/admin/delay?nrTren=123&delayMinutes=45"
```
*Triggers an automated email notification to all customers who have a future booking on this train.*
