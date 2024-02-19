package com.example.demo.exception;

public class InvalidWeatherDataException extends RuntimeException {

	  public InvalidWeatherDataException(String message) {
	      super(message);
	  }

	}