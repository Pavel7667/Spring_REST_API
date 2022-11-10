package com.spring.rest.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.Property;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;


/**
 * MyConfig instead of applicationContext.xml file = "@Configuration"
 * <p>
 * "@ComponentScan" = package where Spring will be looking Beans
 * "@EnableWebMvc" = mvc able to USE
 * dataSource = jdbc.Driver for DB
 * "@EnableTransactionManagement" = able to Use transaction
 */
@Configuration
@ComponentScan(basePackages = "com.spring.rest")
@EnableWebMvc
@EnableTransactionManagement
public class MyConfig {

    /**
     * The dataSource  "@Bean" creating Bean to configuration with DB using jdbc
     *
     * @return dataSource
     */
    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_db?useSSL=false");
            dataSource.setUser("bestuser");
            dataSource.setPassword("bestuser");

        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }

    /**
     * The LocalSessionFactoryBean  "@Bean" creating Bean to configuration
     * Sessions and DB connection
     *
     * @return sessionFactory
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.spring.rest.entity");

        // set Properties for Hibernate
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty
                ("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty
                ("hibernate.show_sql", "true");

        // Hive Sessions hibernate Properties
        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    /**
     * The transactionManager  "@Bean" creating transaction functional
     *
     * @return transactionManager
     */
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager =
                new HibernateTransactionManager();

        // transformation for LocalSessionFactoryBean -> sessionFactory
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }
}
