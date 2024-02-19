package com.example.demo.exception;


//same location and same date can't have different temp/humidity
public class InvalidInsertionException extends RuntimeException {

  public InvalidInsertionException(String message) {
      super(message);
  }

}
