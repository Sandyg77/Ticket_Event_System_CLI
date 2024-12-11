# Event Ticketing System CLI

This project provides a command-line interface (CLI) for interacting with the Event Ticketing System. The CLI allows users to perform operations such as managing tickets, vendors, customers, and logs directly from the terminal. Key features include multithreading for parallel processing of vendor and customer operations, as well as a simulation feature for running the system under predefined configurations.

## Features

- **Ticket Management**: Create and manage tickets for events.
- **Vendor Operations**: Manage multiple vendors who release tickets in a multi-threaded environment.
- **Customer Operations**: Simulate customer ticket purchasing behavior in a multi-threaded environment.
- **Log Management**: Record and retrieve logs for actions performed in the system.
- **Simulation**: Run a full simulation of the event ticketing process, including vendors releasing tickets and customers purchasing them.

## Prerequisites
- Java 11 or later.
- Maven (for building and running the application).

## Installation

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/your-repository/event-ticketing-system-cli.git
