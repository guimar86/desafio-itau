# Desafio Itaú Unibanco

Spring boot challenge proposed by ITaú Unibanco for backend developers.

See the [challenge](https://github.com/feltex/desafio-itau-backend) for more details.

## Steps to run

These steps assume you have Java 11 and Maven installed.
- Clone the repository
- Run `mvn spring-boot:run`

### Run with docker

- Create a docker image using the command `mvn spring-boot:build-image`
- Run the docker image using the command `docker run -p 8080:8080 <image-id>`
- Access the application at `http://localhost:8080`
- Enjoy!


## Health check

- `http://localhost:8080/actuator/health`

## Parametrization
The number of minutes can be changed in the application.properties file.

## Swagger
- `http://localhost:8080/swagger-ui.html`