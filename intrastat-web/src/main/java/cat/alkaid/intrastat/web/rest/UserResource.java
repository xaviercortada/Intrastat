package cat.alkaid.intrastat.web.rest;


import cat.alkaid.intrastat.model.Account;
import cat.alkaid.intrastat.service.AccountService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 * Created by xavier on 7/07/15.
 */
// The Java class will be hosted at the URI path "/helloworld"
// The Java class will be hosted at the URI path "/helloworld"
@Stateless
@Path("/account")
public class UserResource {

    @EJB
    private AccountService service;

    @GET
    @Path("/{id}")
    @Produces({"application/xml", "application/json"})
    public Account getUserById(@PathParam("id") String id) {
        Account dto = service.findByUsername(id);

        return dto;
    }


    @GET
    @Path("/")
    @Produces({"application/xml", "application/json"})
    public Users findAll() {
        // Return some cliched textual content

        System.out.println("all");

        return new Users(service.findAll());
    }

    @GET
    @Path("/finder/{username}")
    @Produces({"application/xml", "application/json"})
    public Account findByUsername(@PathParam("username") String username) {
        Account dto = service.findByUsername(username);

        return dto;
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    public boolean createUser(Account dto) {
        System.out.println(dto);
        return service.create(dto);

    }

    @POST
    @Path("/")
    @Consumes("application/json")
    public boolean updateUser(Account dto) {
        return service.update(dto);

    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public boolean deleteUser(@PathParam("id") String id) {
        return service.delete(Long.parseLong(id));
    }


    @POST
    @Path("/password")
    @Consumes("application/json")
    public boolean changePassword(Account dto, String password) {
        return service.changePassword(dto.getId(), password);
    }


}