# Project structure:
spring-boot-author-book-management-system/
├── src/
│   └── main/
│       ├── java/com/example/demo/
│       │   ├── models/
│       │   │   ├── Author.java
│       │   │   └── Book.java
│       │   ├── repositories/
│       │   │   ├── AuthorRepository.java
│       │   │   └── BookRepository.java
│       │   ├── routes/
│       │   │   ├── AuthorController.java
│       │   │   └── BookController.java
│       │   └── DemoApplication.java
│       └── resources/
│           ├── application.yml
│           ├── application-dev.yml
│           └── application-prod.yml
├── pom.xml

# Start command:
mvn spring-boot:run


# In Railway’s dashboard:
Build Command: mvn clean package
Start Command: java -jar target/demo-0.0.1-SNAPSHOT.jar