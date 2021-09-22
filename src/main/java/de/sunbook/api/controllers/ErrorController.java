package de.sunbook.api.controllers;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

/*
This class defines the Controller for error and exception handling
*/
@ControllerAdvice
public class ErrorController {

    //for SQL Exceptions
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<?> handleSqlException(SQLException e) {

        return new ResponseEntity<String>("SQL Execution Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //for not found Exceptions
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {

        return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //for normal or maybe SQL Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        var msg = e.getMessage();
        return new ResponseEntity<String>(msg.indexOf("sql") > -1 ? "SQL Error" : msg,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //for Null Pointer Exceptions
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException e) {        return new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
