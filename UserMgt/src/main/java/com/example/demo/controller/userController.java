package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entity.Weather;
import com.example.demo.entity.user;
import com.example.demo.exception.DuplicatePasswordException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpMethod;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000")
public class userController {
	
	@Autowired
    private userService userService;
	
	@Autowired
    private RestTemplate restTemplate;

    // Create
	@PostMapping("/addUser")
    public ResponseEntity<Object> createUser(@RequestBody user user) {
		try {
            // Save user locally
            user createdUser = userService.saveUser(user);

            // You can add any additional logic here if needed

            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (DuplicatePasswordException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
	
	//login
	@PostMapping("/getuser")
	public ResponseEntity<Object> searchUserByNameAndPassword(@RequestBody Map<String, String> loginData) {
	    try {
	        String username = loginData.get("username");
	        String password = loginData.get("password");

	        user user = userService.getUserByUsername(username);

	        // Check if the retrieved user's password matches the provided password
	        if (user.getPassword().equals(password)) {
	            return new ResponseEntity<>(user, HttpStatus.OK);
	        } else {
	            // Password does not match
	            return new ResponseEntity<>("Invalid password", HttpStatus.NOT_FOUND);
	        }
	    } catch (UserNotFoundException ex) {
	        // User not found
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }
	}


    // Read
    @GetMapping("/showUsers")
    public ResponseEntity<List<user>> getAllUsers() {
        List<user> users = userService.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/showRecordsById/{id}")
    public ResponseEntity<Object> getWeatherRecordsByUserId(@PathVariable int id) {
        try {
            // Fetch the user by ID
            user user = userService.getUserById(id);

            // Make a REST API call to Weather microservice
            String weatherMicroserviceUrl = "http://localhost:8080/api/showByUserId/" + id;
            ResponseEntity<List<Weather>> response = restTemplate.exchange(
                    weatherMicroserviceUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Weather>>() {});

            List<Weather> weatherRecords = response.getBody();
            return new ResponseEntity<>(weatherRecords, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User with id: " + id + " hasn't made any entry", HttpStatus.NOT_FOUND);
        }
    }

    
}
