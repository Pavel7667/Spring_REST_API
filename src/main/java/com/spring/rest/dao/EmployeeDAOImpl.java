package com.spring.rest.dao;

import com.spring.rest.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

// @ Repository this class should work with DB
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Transactions between DB and DAO to get List<Employee>
     *
     * @return allEmployees List of Objects
     */

    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();

        Query<Employee> query =
                session.createQuery("from Employee", Employee.class);

        return query.getResultList();
    }

    /**
     * Method saveEmployee save or update object in DB
     *
     * @param employee object to save in DB
     */
    @Override
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);
    }

    /**
     * Method getEmployee using session and reflection to get Object from DB
     *
     * @param id of object in DB
     * @return object from DB
     */
    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    /**
     * The deleteEmployee create SQL query
     * And replace in query "replace" to id from parameter
     * Use executeUpdate  for update and delete
     *
     * @param id of Object from DB
     */
    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query =
                session.createQuery("delete from Employee where id =:replace");
        query.setParameter("replace", id);
        query.executeUpdate();
    }
}
