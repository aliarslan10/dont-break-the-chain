### Don't Break The Chain

This Spring Boot project will help the people to gain good habits. Users define a goal and they try to doing it day by day. For example reading book everyday can be a goal.

* Users can add goals
* They can mark their goals for each day as done
* If they want, they can back-up their goals to reach them from everywhere.

Front-end part of this application will be published in the forthcoming days.

### Tech

- Spring Boot (Rest API, JPA)
- Java 11
- Docker
- Maven
- MySQL

### Deployment

Deploy it over docker

```
docker-compose up
```


Deploy it over Docker on ARM CPU (_Apple M1 etc._)

```
docker-compose -f docker-compose-arm.yml up
```

Application starts over `8085` port from your Docker container on your local. (_localhost:8085_)

If you want to start the project from your IDE (_Intellij IDEA and so on_) for development purpose, 
you can run the application without any configuration after run the docker compose. 
Then application starts on `8080` port on your local (_localhost:8080_).

### Contributions

This project is open to contributions. You can start by creating an issue.

### License

This project is licensed under the MIT License.

### Author

[Ali Arslan](https://aliarslan.blogkafem.net/)
