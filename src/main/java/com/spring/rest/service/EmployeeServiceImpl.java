package com.spring.rest.service;

import com.spring.rest.dao.EmployeeDAO;
import com.spring.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    /**
     * getAllEmployees use link to DAO-Object to get Objects from DB
     *
     * @return employeeDAO = List of Objects from DB
     */
    @Override
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    /**
     * saveEmployee use link DAO-Object for Service-Object to save Objects in DB
     */
    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);
    }

    /**
     * Method getEmployee using DAO-Realization of getting Object fom DB
     *
     * @param id of object in DB
     * @return object from DB
     */
    @Override
    @Transactional
    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }

    /**
     * Method deleteEmployee using DAO-Realization of deleting Object fom DB
     *
     * @param id of object in DB
     */
    @Override
    @Transactional
    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }
}
