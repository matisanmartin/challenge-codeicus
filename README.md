# Challenge Codeicus

### Información del proyecto

Proyecto basado en Spring Boot con Gradle.

* Versión de Java: 1.8

* BBDD: MySQL

* MQ: RabbitMQ

* [Docker installation](https://docs.docker.com/install/)

* Intalación MySQL: ```docker run --net=host --name mysql-docker -e MYSQL_ROOT_PASSWORD=admin -d mysql:latest```
* Instalación rabbitMQ: ```docker run --net=host -d --hostname my-rabbit --name some-rabbit rabbitmq:3``` 

Para correr la aplicación: ```gradle bootRun``` desde la consola o abrir la aplicación en un IDE y correr la clase ChallengeApplication


