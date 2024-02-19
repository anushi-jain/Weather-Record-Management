package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.user;
import com.example.demo.exception.*;
import com.example.demo.repository.userRepository;


@Service
public class userService {

    
	@Autowired
    private userRepository userRepository;

	// Adding users in database
	  public user saveUser(user user) {
		  Optional<user> usersWithSamePassword = userRepository.findByPassword(user.getPassword());
	        if (!usersWithSamePassword.isEmpty()) {
	            throw new DuplicatePasswordException("User with the same password already exists");
	        }
	        
	        return userRepository.save(user);
	    }

    // Display All User
    public List<user> getAllUser() {
        return userRepository.findAll();
    }

    public user getUserById(int id) {
        Optional<user> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
    }

    
	public user getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	
}

