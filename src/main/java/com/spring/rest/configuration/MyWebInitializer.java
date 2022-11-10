package com.spring.rest.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * MyWebInitializer make DispatcherServlet configuration instead of Web.xml file
 */
public class MyWebInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * We don`t have RootConfigClasses = set null
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    /**
     * Set as applicationContext.xml file MyConfig.java
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyConfig.class};
    }

    /**
     * Set base Mappings "/" = base site page
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
