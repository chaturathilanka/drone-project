# Drone Application

This is a drone application that allows you to register drones and perform various operations on them.

## Prerequisites

Make sure you have the following prerequisites installed on your system:

- Java Development Kit (JDK) 17
- Apache Maven
- Postman (for API testing)

Build the application using Maven: mvn clean package
- This will compile the source code, run tests, and package the application into an executable JAR file.
- If you are using Intellij Idea IDE you can just clean install and run the main class to make the program up and running.
- to run use : java -jar target/drone-0.0.1-SNAPSHOT.jar
- resources/data.sql have initially loaded few medication items. 

## Test APIs with Postman

The application provides a RESTful API to interact with the drones. You can use Postman to test the APIs. Follow these steps to test the APIs:

1. Open Postman.

2. Set the base URL to `http://localhost:8080/drones/api/v1`.

### Register a Drone

- Endpoint: `POST /register`
- Request Body: JSON object representing the drone
  - Example:

    ```json
    {
    "serialNumber": "DRN001",
    "model": "MIDDLEWEIGHT",
    "weightLimit": 500.0,
    "batteryCapacity": 20,
    "state": "LOADING"
      }

### Load Medications to Drone
- Endpoint: POST /{droneId}/load
- Path Variable: droneId - ID of the drone
- Request Body: Array of medication IDs
- Example:

  ```json
    [1,2,3]

### Get Loaded Medications
- Endpoint: GET /{droneId}/medications
- Path Variable: droneId - ID of the drone

### Get Available Drones
- Endpoint: GET /available

### Get Drone Battery Level
- Endpoint: GET /{droneId}/battery-level
- Path Variable: droneId - ID of the drone
Note: Replace {droneId} with the actual ID of the drone you want to interact with






