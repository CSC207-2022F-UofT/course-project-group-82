# Backend for OuiEat

## Notion Page for this Documentation: [Here](https://ari-b.notion.site/Backend-for-OuiEat-0bb3343e320249ab8cfcab65c18c9b23)

# Running

1. Ensure your java version is set to 11 (or higher. Tested on Azule 11.0.16 and Azure 17)
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

- Navigate to the `src/test/java/com/ouieat/OuieatApplicationTest.java` file and run the class using your IDE.
- This will setup a Spring environment and establish the database connections required for the rest of the test files to work properly.

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

There are 6 packages to note here:

1. `com.ouieat.controllers`
2. `com.ouieat.requests`
3. `com.ouieat.models`
4. `com.ouieat.repository`
5. `com.ouieat.implementation`
6. `com.ouieat.responses`

Also to note the class `OuiLogger` that provides a statically accessible logger method for the Backend through `Log4J2`.

And all files in this repository are to be *prettier* formatted before merging

# Flow of Data

```mermaid
flowchart TD
  client(Client) <--> controller(Controller)
	controller --> requests(Requests)
	requests --> implementation(Implementation)
	implementation ----> response(Response)
	implementation <--> repository(Repository)
	repository <--> model(Model)
	model --> database(Database)
	response --> controller
	exceptionHandler(ExceptionHandler)
	controller --> exceptionHandler
	requests --> exceptionHandler
	exceptionHandler --> response
	
	
```

# Explanation of the diagram:

Let’s imagine that our Spring Backend server is a fancy Restaurant to make it easier to understand what’s going on here.

You are the client, i.e. a customer going to this fancy Restaurant.

![Untitled](images/Untitled.png)

You step inside and are greeted by the host (Our server). She looks at you and asks you where you’d like to sit. Around the room there’s a lot of tables, some of them already have guests on them, but you could wait for those one’s to open up. Why would you want to do that? Well this restaurant is fancy, and you get a different menu depending on where you sit. Crazy I know right.

![Untitled](images/Untitled%201.png)

You pick a table that’s open and go sit down.

Every table here is a route, or in other words some place that a customer could request their food from. Each route has it’s own fixed menu of things that you can order from, and they all have their own costs (data that you need to give to the route).

Routes are defined by something called the Controller. A single controller can have multiple routes in them. In our restaurant, the Controller is a Waiter. This makes sense right? A waiter waits on multiple tables, not just one.

![Untitled](images/Untitled%202.png)

The waiter comes and asks you what you’d like, you pick from one of the items in the menu for that table, and pay the waiter the cost for it upfront! (They don’t trust people to stick to their word, fair enough imo)

If you don’t give him the right costs to make your order, he’ll get annoyed and tell you off almost instantly!

The waiter takes your order and walks away. Now all he has to do is drop it off in the kitchen, along with the cost of your order with the ticket.

![Untitled](images/Untitled%203.png)

The waiter has no idea what’s going on with that ticket, or your money. He’ll be back soon, but for now he has other customers to look out for.

Let’s take a look inside the kitchen now. You made an order and paid the cost for the order upfront.

One of the chef’s in the kitchen (currently on Aboyeur duty) takes the order and moves it to the station that can prepare it. This chef is the Request layer in our backend.

He ensures that the restaurant has everything it needs from you to make the order (in case the Waiter missed something), and then takes it to the right station that can prepare your order

![Untitled](images/Untitled%204.png)

Next, your order is finally with a chef that can prepare it!

Along with the stuff that you gave the restaurant, the chef also needs some extra ingredients from the pantry. It would be very inconvenient if the chef had to run back and forth everywhere though, so he has a junior chef that does all the running for him.

The chef preparing your order is the Implementation layer and the running around is done by the Repository layer.

The chef howls at his junior to get him what he needs from the pantry. The junior stops what he’s doing and sprints back and forth to satisfy his senior

![Untitled](images/Untitled%205.png)

The Repository interacts with the database and gets the data needed for the Implementation layer to compute the logic.

After the chef is done preparing your order, he just slaps it on a plate and leaves it. He needs to go back to preparing someone else’s order now and doesn’t have time to make things look pretty.

But this is a fancy restaurant. Another chef comes in and takes over now. His job is to make sure that the order prepared by the previous chef looks immaculate so that when you see it, you start drooling.

![Untitled](images/Untitled%206.png)

He sprinkles in some tiny bit of extra stuff like sauce, or salt that the previous chef didn’t have the time or energy for, and makes everything come together for you.

This chef is the Response layer. It puts together the output generated by the Implementation layer and creates a Response object that is ready to be served to you.

After it’s done, the plate is just left there, until the waiter shows up again.

He sees the plate and your name next to it, he doesn’t know how that plate came to be or who worked on it, he only sees the finished product.

He takes that plate and serves it to you

![Untitled](images/Untitled%207.png)

This is the final job of the controller, it needs to give back the data requested by the user that’s produced by the rest of the backend.flAnd that’s it! That’s how our backend works to provide a smooth experience to all it’s customers

![Untitled](images/Untitled%208.png)

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

## Responses

- The package consists of a Response class, objects of which are what is finally sent back to the client
- It also consists of `*Model*Responses` classes that provide methods to create Model-Implementation specific Responses easily

### Response class

It provides an overriden `toString` method as well as a `getJsonString` method that uses Jackson to serialize a `Response` object to a JSON string that can be sent back to the client.