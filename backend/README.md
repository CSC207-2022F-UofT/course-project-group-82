# Backend for OuiEat

# Running

1. Ensure your java version is set to 11 (or higher. Tested on Azule 11.0.16, 11.0.17 and Azure 17)
2. Navigate to `src/main/resources/application.properties` and add the key-values for the database connection as follows (If it doesn’t exist, create it):

```
spring.data.mongodb.uri=mongodb+srv://admin:admin@yummy.ocgk0lm.mongodb.net/?retryWrites=true&w=majority
spring.data.mongodb.database=weeat
server.error.whitelabel.enabled=true
```

1. Navigate to the backend folder from a terminal
2. Ensure the port `8080` is free and open for the backend to use
3. Run the command `./mvnw spring-boot:run` on MacOS or `mvnw spring-book:run` on Windows
   - You could use your IDE as well
4. The API is accessible at `[http://localhost:8080/](http://localhost:8080/)` The root route is a test route to ensure the service is active

# Testing

- Navigate to the `src/test/java/com/ouieat/ServiceTest.java` file and run the class using your IDE.
- This will setup a Mocked Spring Repository connection and let you run the tests without any configuration needed

# Contributing

- Open the backend directory in your IDE (not the project root folder) as otherwise your IDE may have trouble downloading the Maven dependencies
- Follow the pre-existing naming convention
- This repository is Prettier enforced (Install prettier globally on your computer and the prettier-java plugin to go with it).

# Debugging

- An error during the run command is usually either due to the wrong java version or blocked port
- The “Whitelabel error” / “Error handling this Response” means you are missing imports specific to Spring or have misconfigured routes in your application
- A sample successful build console output is:

![img.png](images/img.png)

# How It’s Designed

There are 7 packages to note here:

1. `com.ouieat.**********************controllers**********************`
2. `com.ouieat.******************requests******************`
3. `com.ouieat.**models**`
4. `com.ouieat.**interactors**`
5. `com.ouieat.**repository**`
6. `com.ouieat.**implementation**`
7. `com.ouieat.**responses**`

Also to note the class `OuiLogger` that provides a statically accessible logger method for the Backend through `Log4J2`.

And all files in this repository are to be *prettier* formatted before merging

# Flow of Data

```mermaid
flowchart TD
  client(Client) <--> controller(Controller)
	controller --> requests(Requests)
	requests --> implementation(Implementation)
	implementation <--> interactors(Interactors)
	interactors <--> repository
	interactors --> exceptionHandler
	implementation ----> response(Response)
	repository(Repository)
	repository <--> model(Model)
	model --> database(Database)
	response --> controller
	exceptionHandler(ExceptionHandler)
	controller --> exceptionHandler
	requests --> exceptionHandler
	exceptionHandler --> response
	
	
```

# Explanation of the diagram:

## Models

- These are objects that represent what our data will look like in database.
- Each model is representative of one collection in our database.
- A model prefixed class is usually manipulating / accessing data of that model

## Controllers

- These are the endpoints that are accessible to a client
- They define GET, POST, PUT request API endpoints
- They call the required request from each model prefixed class

## Repository

- This package consists of **interfaces** that extend `MongoRepostory<Model, String>`
- It basically works as the middle man between our database connection and our model
- MongoRepository provides us with standard CRUD operations
- We can add on to these by putting complex database manipulation functions here and calling them through an `@Autotowired` instantiation of the interface from anywhere in our code.

## Implementation

- Classes in this package consist of `public` `static` functions that consume a `Repository` object along with some data and create a `Response` object that can be sent back to the client.

## Interactors

- An intermediary layer that contracts between the repository and implementation layers to provide access to the repository layer. This way even if we have to swap out the database at some point, we can leave the business logic intact by letting the interactor fill in the gaps for a new database connection.

## Responses

- The package consists of a Response class, objects of which are what is finally sent back to the client
- It also consists of `*Model*Responses` classes that provide methods to create Model-Implementation specific Responses easily

### Response class

It provides an overriden `toString` method as well as a `getJsonString` method that uses Jackson to serialize a `Response` object to a JSON string that can be sent back to the client.