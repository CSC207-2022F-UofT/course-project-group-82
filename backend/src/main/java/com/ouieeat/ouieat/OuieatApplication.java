package com.ouieeat.ouieat;

import com.ouieeat.ouieat.implementation.UserImplementation;
import com.ouieeat.ouieat.models.User;
import com.ouieeat.ouieat.repository.UserRepository;
import com.ouieeat.ouieat.responses.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableMongoRepositories
public class OuieatApplication {

	@Autowired
	public UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(OuieatApplication.class, args);
	}

	@GetMapping("/register")
	public String register() {
		User newUser = new User("Arihant", "Bapna", "", "ari", "1234", "a@g.com");
		Response response = UserImplementation.saveNewUser(userRepository, newUser);
		return response.getJsonString();
	}

	@GetMapping("/login")
	public String login(@RequestParam(value = "username") String username, @RequestParam(value="password") String password) {
		Response response =  UserImplementation.loginUser(userRepository, username, password);
		return response.getJsonString();
	}

	@GetMapping("/error")
	public String error(){
		return "Error handling this response";
	}
}
