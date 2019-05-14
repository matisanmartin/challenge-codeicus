# Challenge Codeicus

### Información del proyecto

Proyecto basado en Spring Boot con Gradle.

* Versión de Java: 1.8

* Intalación MySQL: ```docker run --name mysql-docker -p 3306:3306 -e MYSQL_ROOT_PASSWORD=admin -d mysql:latest```
* Instalación rabbitMQ: ```docker run -d --hostname my-rabbit -p 5672:5672 -p 15672:15672 --name some-rabbit rabbitmq:3```
* [Docker installation](https://docs.docker.com/install/)

* BBDD: MySQL
    Debe crearse una base llamada TASK_DB
    ```docker exec -it mysql-docker bash``` -> Ingresa al docker de MySQL
    ```mysql --password=admin``` --> Ingresa a la consola de MySQL en el docker
    ```CREATE DATABASE TASK_DB;``` --> Crea el esquema

* MQ: RabbitMQ - localhost:5672

* [Integracion con PrimeFaces basada en el proyecto joinfaces](https://github.com/joinfaces)

* Para correr la aplicación: ```gradle bootRun``` desde la consola o abrir la aplicación en un IDE y correr la clase ChallengeApplication

En el primer run se debe setear la property:
```spring.jpa.hibernate.ddl-auto=create```

Luego modificarla a:
```spring.jpa.hibernate.ddl-auto=update```


