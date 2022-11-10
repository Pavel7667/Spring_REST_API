package com.spring.rest.controller;

import com.spring.rest.entity.Employee;
import com.spring.rest.exeption_handling.NoSuchEmployeeException;
import com.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
            throw new NoSuchEmployeeException("URL is incorrect, There is no " +
                    "Employee  with this id, change this ID " + id);
        }
        return employee;
    }

    /**
     * The addNewEmployee for URL "/api/employees" and request POST with some
     * "body" info creat new Object in DB
     * <p>
     * " @PostMapping" request in which sending INFO
     * "@RequestBody" taking INFO for Object in DB from "body" request
     *
     * @param employee object with info
     * @return new Object in DB
     */
    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    /**
     * The updateEmployee take object from DB by ID, change parameters and save
     * <p>
     * "@PutMapping" request in which reSave Object in DB with new Parameters
     * "@RequestBody" taking INFO for Object in DB from "body" request
     *
     * @param employee Java Object
     * @return Update DB Object
     */
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    /**
     * The deleteObject from DB by ID gotten from URL parameters
     * <p>
     * "@DeleteMapping" for this URL request delete Object from DB
     *
     * @param id of Object in "@PathVariable" take id from URL parameters
     * @return message that the Object has been removed from the database
     */
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return "Object with id = " + id + " was deleted from DB";
    }

}
