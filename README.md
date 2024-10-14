## Prerequisites

- Java 17
- Apache Maven 3.9.6
- MySQL 8.1.0
- SpringBoot 3.3.4

## Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/maxyamba/bc-test.git
   cd university-console-app
2. **Confidure database**

In application.properties set environment variables
- ${DB_URL} - database connection URL
- ${DB_USERNAME} - database username
- ${DB_PASSWORD} - database password
- ${PORT} - server port

3. **Fill data**

   To test application you have to start application
   ```bash
    mvn clean install
    mvn spring-boot:run

  Then run script to set data from 
   ```bash
src/main/resources/data.sql
