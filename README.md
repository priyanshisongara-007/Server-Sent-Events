# Server-Sent Events (SSE) Example in Java with Embedded Jetty

This project demonstrates how to implement **Server-Sent Events (SSE)** using Java servlets with an embedded Jetty server. SSE enables a server to push real-time updates to clients over HTTP in a simple and efficient way.

---

## What is Server-Sent Events?

Server-Sent Events are a standard allowing servers to send automatic updates to clients through a persistent HTTP connection. Unlike WebSockets, SSEs are one-way (server to client) and use plain HTTP, making them simpler to implement for streaming data or event notifications.

---

## Features

- Embedded Jetty server running on port 8080
- SSE servlet at `/sse` endpoint that:
  - Sends current server time every second as events
  - Uses proper SSE headers and event formatting
- Example JavaScript client code to receive and display events
- Lightweight and easy to extend for custom data streams

---

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven (optional for building)

### Running the Server

1. Build the project (if using Maven):

    ```bash
    mvn clean package
    ```

2. Run the server main class:

    ```bash
    java -cp target/your-jar.jar fully.qualified.SSEServerClass
    ```

3. Open a browser and navigate to:

    ```
    http://localhost:8080/sse
    ```

You will see the SSE stream of events (usually raw text).

---

## Dependencies
This project uses:
Jetty Server 11.x
Jakarta Servlet API 5.0+

