package com.spring.rest.controller;

import com.spring.rest.entity.Employee;
import com.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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
     */
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return employeeService.getEmployee(id);
    }
}
