## A Spring REST Backend


### Running

1. Ensure your java version is set to 11 (tested on Azule 11.0.16)
2. Navigate to `src/main/resources/application.properties` and add the key-values for the database connection
3. Navigate to the backend folder from a terminal
4. Run the command `./mvnw spring-boot:run` on MacOS or `mvnw spring-boot:run` on Windows
4. The API is accessible at http://localhost:8080/ (A test route at [/hello](http://localhost:8080/hello))

### Debugging

* An error during the run command is usually due to the wrong java version
* "The Whitelabel error" / "Error handling this Response" means you are missing imports specific to Spring or have misconfigured routes\
* Ensure port 8080 is open for the application
* A sample successful build console output ![img.png](img.png)