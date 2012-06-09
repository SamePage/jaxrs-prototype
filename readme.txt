Notes:
* This project currently targets a Java EE App Server. We plan on evolving this to also
  target Java SE Servlet containers (i.e. tomcat).

* This project depends on a container owned data source named: jdbc/jaxrs-prototype.
  You can see this specified in the src/main/resources/META-INF/persistence.xml

The Parts:

* Model Objects:
     - These are just POJOs, annotated with JAXB annotations

     - If they use any special classes like JODA DateTime which require
       special handling for converting into and out of XML/JSON, they
       will need XmlAdapters (which can be registered at the package level
       in a package-info.java file)

     - In this project the domain objects are in:
        com.samepage.jaxrs.prototype.model

* package-info.java:
     - This file registers XmlAdapters at the package level, so that each
       instance of a type does not need to be annotated with the adapter

     - For this project, the package level is the domain package (com.samepage.jaxrs.prototype.model)
       which is why you will find the package-info.java file in that package.

* XmlAdapters:
     - These are extensions of the JAXB XmlAdapter class and provide
       a mechanism for marshalling and unmarshalling classes in a specific way
     - They can be registered at the package level using a package-info.java class

     - For this project, a dependency is in the pom.xml, importing the allegiant-jaxb artifact.
       The allegiant-jax jar contains the adapters.

* Service Implementation:
     - This is the pojo that exposes a resource as a REST service. For instance, if you
       have a User model class and you want to expose a REST service for it,
       you would have a class named UserResource.java.
     - This class has a @Path annotation at the class level, as well as
       @Path, method (@GET, @POST, @PUT, @DELETE), @Produces and @Consumes
       annotations at the method level

     - For this project, an example of a rest service implementation is at:
         com.samepage.jaxrs.prototype.rest.UserResource.java

* Controller
    - This is pojo the contains the business logic. In addition, this is the class that knows how to
      persist and retrieve model instances and control the transactions around
      business logic.

    - In the project, an example of a controller is at:
        com.samepage.jaxrs.prototype.contoller.UserController.java

* Provider class(s):
     - These can be hand coded or provided. They provide implementations of
       MessageBodyReader and MessageBodyWriter whose job it is to convert
       from XML/JSON to Java (Reader) and Java to XML/JSON (Writer)
     - JAXB Implementations provide these without having to register them
       in any way.
     - Hand coded or other provided Providers need to be annotated at the
       class level with the @Provider annotation, OR they can be registered
       in the Application subclass in the getSingletons method.
     - An example of a provided one is:
       org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider

          - This provider requires two dependencies in the pom.xml:
             <dependency>
                 <groupId>org.codehaus.jackson</groupId>
                 <artifactId>jackson-jaxrs</artifactId>
                 <version>1.9.7</version>
             </dependency>
             <dependency>
                 <groupId>org.codehaus.jackson</groupId>
                 <artifactId>jackson-xc</artifactId>
                 <version>1.9.7</version>
             </dependency>

* Application class:
     - This is a hand coded class that extends javax.ws.rs.core.Application
     - It replaces the need for entries in a web.xml
     - It registers any @Providers
     - It registers any rest service @Path providers

     - For this project, the Application subclass in at:
         com.samepage.jaxrs.prototype.rest.App.java



* Maven POM.xml:

