package com.samepage.jaxrs.prototype.rest;

import com.samepage.jaxrs.prototype.dao.UserDao;
import com.samepage.jaxrs.prototype.model.Address;
import com.samepage.jaxrs.prototype.model.User;
import com.samepage.jaxrs.prototype.model.UserList;
import com.samepage.jaxrs.prototype.model.UserType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@RequestScoped
public class UserResource {

    @Inject
    private UserDao userDao;


    @GET
    public Response list() {
        UserList ul = new UserList(userDao.findAll());
        return Response.ok(ul).build();
    }

    @GET
    @Path("/example")
    public Response getExample() {
        User user = new User();
        Address address = new Address();

        user.setId(1L);
        user.setType(UserType.WORKER);
        user.setCompanyName("Some Company Name");
        user.setCompnayTaxNbr("12-345678");
        user.setEmail("username@domain.com");
        user.setFirstName("Firstname");
        user.setLastName("Lastname");
        user.setPhone1("123-345-6789");
        user.setPwd("secret");
        user.setSkypeUsername("blahblah");
        user.setSsn("123-12-1234");

        address.setLine1("123 Street Ave");
        address.setLine2("Apt 4D");
        address.setCity("Cityville");
        address.setState("CT");
        address.setZip5("12345");

        user.setAddress(address);

        return Response.ok(user).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        User foundUser;

        foundUser = userDao.findById(id);

        if (foundUser == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(foundUser).build();
    }

    @GET
    @Path("/email/{email}")
    public Response getByEmail(@PathParam("email") String email, @QueryParam("blah") String someString) {
        User foundUser;

        foundUser = userDao.findByEmail(email);

        if (foundUser == null) {
            return Response.noContent().build();
        }
        return Response.ok(foundUser).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response add(User user) {

        if (user == null) {
            return Response.notModified("Could not save: empty user").build();
        }

        userDao.add(user);
        if (user.getId() != null) {
            try {
                return Response.created(new URI("/" + Long.toString(user.getId()))).build();
            } catch (URISyntaxException e) {
                Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, e);
                return Response.notModified("Server error after save: " + e.getLocalizedMessage()).build();
            }
        } else {
            return Response.notModified("Sever error: No User.id after save").build();
        }

    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response update(User user) {

        if (user == null) {
            return Response.notModified("Could not update: empty user").build();
        }
        if (user.getId() == null) {
            return Response.notModified("Could not update: user does not have and id").build();
        }
        userDao.update(user);
        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        User deletedUser = userDao.deleteById(id);

        if (deletedUser == null) {
            return Response.notModified(String.format("Could not delete: User with id %s", Long.toString(id))).build();
        }
        else {
            return Response.ok(deletedUser).build();
        }
    }
}
