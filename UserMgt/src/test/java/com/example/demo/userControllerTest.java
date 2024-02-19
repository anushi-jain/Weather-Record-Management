package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Weather;
import com.example.demo.entity.user;
import com.example.demo.exception.DuplicatePasswordException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.userService;
import org.springframework.web.client.RestTemplate;
import com.example.demo.controller.userController;

class userControllerTest {

    @Mock
    private userService userService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private userController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_Success() {
        user newUser = new user();
        when(userService.saveUser(newUser)).thenReturn(newUser);

        ResponseEntity<Object> response = userController.createUser(newUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newUser, response.getBody());
    }

    @Test
    void testCreateUser_DuplicatePasswordException() {
        user newUser = new user();
        when(userService.saveUser(newUser)).thenThrow(new DuplicatePasswordException("Duplicate Password"));

        ResponseEntity<Object> response = userController.createUser(newUser);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Duplicate Password", response.getBody());
    }

    @Test
    void testGetAllUsers() {
        List<user> users = Arrays.asList(new user(), new user());
        when(userService.getAllUser()).thenReturn(users);

        ResponseEntity<List<user>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void testGetWeatherRecordsByUserId_Success() {
        int userId = 1;
        user existingUser = new user();
        when(userService.getUserById(userId)).thenReturn(existingUser);

        List<Weather> weatherRecords = Arrays.asList(new Weather(), new Weather());
        ResponseEntity<List<Weather>> weatherResponse = new ResponseEntity<>(weatherRecords, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), any(), any(), eq(new ParameterizedTypeReference<List<Weather>>() {})))
                .thenReturn(weatherResponse);

        ResponseEntity<Object> response = userController.getWeatherRecordsByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(weatherRecords, response.getBody());
    }

    @Test
    void testGetWeatherRecordsByUserId_UserNotFoundException() {
        int userId = 1;
        when(userService.getUserById(userId)).thenThrow(new UserNotFoundException("User not found"));

        ResponseEntity<Object> response = userController.getWeatherRecordsByUserId(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User with id: 1 hasn't made any entry", response.getBody());
    }
}

