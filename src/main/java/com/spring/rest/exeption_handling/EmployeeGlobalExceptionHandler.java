package com.spring.rest.exeption_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * EmployeeGlobalExceptionHandler class that catch any exception from User
 * wrong request URL form. From any Controllers and for any Controllers
 */
@ControllerAdvice
public class EmployeeGlobalExceptionHandler {

    /**
     * The handleException in case wrong URL catch "NoSuchEmployeeException"
     * And create "EmployeeIncorrectURL" object in which set message txt from
     * "NoSuchEmployeeException"
     * <p>
     * Work only in case wrong id
     *
     * @param exception NoSuchEmployeeException object with txt
     * @return EmployeeIncorrectURL with txt from "getEmployee" method throw
     * Object
     */
    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectURL> handleException(NoSuchEmployeeException exception) {
        EmployeeIncorrectURL data = new EmployeeIncorrectURL();
        data.setInfoMessage(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    /**
     * In case wrong URL throw this massage
     *
     * @param exception catching any Java Exception
     * @return JSON with value of txt any Java Exception
     */
    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectURL> handleException(Exception exception) {
        EmployeeIncorrectURL data = new EmployeeIncorrectURL();
        data.setInfoMessage(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
