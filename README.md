# alticci-sequence

### What you need to execute this project
* Java 11 or above
* Gradle
* Docker/docker-compose

### How to run

First we need to build our project, so to do that we run the command below:
```
./gradlew build
```
When it finishes, we can create our Docker container to start the application, running:

```
docker build -t alticci-sequence:0.0.1 .
```
After that we will have an image named **alticci-sequence**. Now we run this command to start the container and get access to the application:
```
docker run -p 8080:8080 -d --name alticci-sequence alticci-sequence:0.0.1
```
The command above will create a container named **alticci-sequence**, and will map local port **8080** to the container port, also **8080**.

You can also run a docker-compose file and create a container built by me. In the work directory run:
```
docker-compose up -d
```
### Swagger

This project uses OpenAPI as documentation, there you can find all endpoints of the app.
To access Swagger UI and see the endpoints click here -> [Swagger](http://localhost:8080/swagger-ui/index.html#/).

### Endpoints

* ***Get Method***
  * http://localhost:8080/alticci/{n}
    * **{n}** Represents the index of sequence's value to return.
* ***Post Method***
  * http://localhost:8080/clearCache
    * No params required for this endpoint.

Everytime we access the Get Method, a cache will be created to take advantage of previous calculations.
The systems log the duration of the first call, and print it in milliseconds. 
***It's just printed when a first call happen, when you call the endpoint using the same {n} or lower, it'll use previous calculations present in its cache.***

### Front

This project also have a simple frontend to interact, you can find it in this repository: [alticci-sequence-front](https://github.com/kelvosk/alticci-sequence-front)


