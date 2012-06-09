package com.samepage.jaxrs.prototype.rest;

import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class App extends Application {
    private Set<Object> singletons = new HashSet<Object>();


    public App() {
        // Add Jackson's JAX-RS provider to use both JAXB and Jackson annotations
        singletons.add(new JacksonJaxbJsonProvider());
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();

        // Add JAX-RS Resource Classes
        classes.add(UserResource.class);

        System.out.println("Calling getClasses()");
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        System.out.println("Calling getSingletons");
        return singletons;
    }
}
