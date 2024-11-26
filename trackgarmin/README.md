# trackgarmin

### Format code

```shell
$ ./mvnw spotless:apply
```
### Azure for the run of things
```shell
$ cd /Volumes/MacProHD/intellijworkspaces/iAC-NikeRuns
$ 

### run confiserver information in Docker

```shell
$ * docker run --rm -e username='someusername' -e pass='somepass' -p 8888:8888 travelhelper0h/sathishproject-config-server
```



### Run tests

```shell
$ ./mvnw clean verify
```

### Run locally

```shell
$ docker-compose -f docker/docker-compose.yml up -d
$ ./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

### Using Testcontainers at Development Time
You can run `TestApplication.java` from your IDE directly.
You can also run the application using Maven as follows:

```shell
./mvnw spring-boot:test-run
```


### Useful Links
* Swagger UI: http://localhost:8080/swagger-ui.html
* Actuator Endpoint: http://localhost:9042/actuator
