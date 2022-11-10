package com.spring.rest.controller;

import com.spring.rest.entity.Employee;
import com.spring.rest.exeption_handling.EmployeeIncorrectURL;
import com.spring.rest.exeption_handling.NoSuchEmployeeException;
import com.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * MyRestController "@RestController" = same controller but for HTTP
 * "@RestController" = "@Controller" = "@Component"
 * "@RequestMapping" = base page
 * <p>
 * Don`t forget all Object from DB are returning in JSON form
 * <p>
 * "@RequestMapping("/api")" help to comping URLs in group by mainClassURL
 */
@RestController
@RequestMapping("/api")
public class MyRESTController {

    // link to EmployeeService object
    @Autowired
    private EmployeeService employeeService;

    /**
     * showAllEmployees "@GetMapping" same "@RequestMapping" but only for
     * Http request GET
     * <p>
     * And link to "api/employees"
     *
     * @return List<Employee> = all objects from DB
     */
    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /**
     * The getEmployee by GET request "/employees/{id}" hive one Object from DB
     *
     * @param id from key=value URL "/employees/{id}" which User set by himself
     * @return Employee by id from DB
     * @throws NoSuchEmployeeException Object with some Message
     */
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);

        if (employee == null) {
            throw new NoSuchEmployeeException("URL is incorrect, There is no " + "Employee  with this id  no, change this ID " + id);
        }
        return employee;
    }

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
