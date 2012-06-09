package com.samepage.jaxrs.prototype;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {

    @Produces
    @PersistenceContext(name = "main")
    EntityManager em;
}
