# javamysql-backend

This is a sample project that demonstrates a Dockerized MySQL database accessed by a simple backend with CRUD functionality using Java Spring Boot technology.

## Pre-Requisites

- **Operating System**: macOS 11.7.1 (Big Sur) or later
- **Docker Desktop**: v4.1.0 or later
- **Maven**: v3.8.7 or later
- **Java (JDK 17)** with **Spring Boot 3.3.3**

## Steps to Initialize

### 1. Setup MySQL in Docker

- **Create a Docker Network**: This network will allow the backend and MySQL containers to communicate with each other.

    ```bash
    docker network create --driver bridge javamysql-network
    ```

- **Run the MySQL Container**: This command will start a MySQL container named `docker-mysql` with the root password `1q2w3e4r5t` and a new database named `javamysqldb`.

    ```bash
    docker container run -p 3306:3306 --name docker-mysql --network javamysql-network -e MYSQL_ROOT_PASSWORD=1q2w3e4r5t -e MYSQL_DATABASE=javamysqldb -d mysql:latest
    ```

- **Access the MySQL Container**: Use this command to get a bash shell inside the running MySQL container.

    ```bash
    docker container exec -it docker-mysql bash
    ```

- **Log in to MySQL**: Log in using the root user and the password specified earlier.

    ```bash
    mysql -u root -p javamysqldb
    ```

- **Create the `users` Table**:

    ```sql
    CREATE TABLE users (
        id INT NOT NULL AUTO_INCREMENT,
        first_name VARCHAR(255) NOT NULL,
        last_name VARCHAR(255),
        email VARCHAR(255),
        gender VARCHAR(255),
        PRIMARY KEY (id)
    );
    ```

- **Insert Sample Data**:

    ```sql
    INSERT INTO users (first_name, last_name, gender, email) VALUES ('Teste', 'Teste', 'male', 'teste@gmail.com');
    ```

- **Verify the Data**:

    ```sql
    SELECT * FROM users;
    ```

### 2. Setup the Java Backend

- **Clean and Package the Project Using Maven**:

    ```bash
    mvn clean package
    ```

- **Skip Tests and Package the Project**: This step skips tests and builds the application.

    ```bash
    mvn -Dmaven.test.skip=true package
    ```

- **Build the Docker Image for the Java Backend**: This command builds a Docker image named `javamysql-backend:latest`.

    ```bash
    docker build -t javamysql-backend:latest .
    ```

- **Run the Java Backend Container**: Start the backend container and map it to port 8080 on the host.

    ```bash
    docker run -p 8080:8080 --name java-backend --network javamysql-network javamysql-backend:latest
    ```

- **Access the API**: The API is now available at:

    ```
    http://localhost:8080/api/users
    ```

### 3. Stopping and Removing the Docker Container

If you need to stop and remove the backend container to rebuild or restart it, use the following commands:

- **Stop the Container**:

    ```bash
    docker stop java-backend
    ```

- **Remove the Container**:

    ```bash
    docker rm java-backend
    ```

These commands are useful when you need to rebuild the Docker image after making changes to your Java code or environment.
