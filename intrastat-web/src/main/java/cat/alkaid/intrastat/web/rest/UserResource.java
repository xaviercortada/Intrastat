package cat.alkaid.intrastat.web.rest;


import cat.alkaid.intrastat.model.User;
import cat.alkaid.intrastat.service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 * Created by xavier on 7/07/15.
 */
// The Java class will be hosted at the URI path "/helloworld"
// The Java class will be hosted at the URI path "/helloworld"
@Stateless
@Path("/user")
public class UserResource {

    @EJB
    private UserService service;

    @GET
    @Path("/{id}")
    @Produces({"application/xml", "application/json"})
    public User getUserById(@PathParam("id") String id) {
        User dto = service.findById(Long.parseLong(id));

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
    public User findByUsername(@PathParam("username") String username) {
        User dto = service.findByUsername(username);

        return dto;
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    public boolean createUser(User dto) {
        System.out.println(dto);
        return service.create(dto);

    }

    @POST
    @Path("/")
    @Consumes("application/json")
    public boolean updateUser(User dto) {
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
    public boolean changePassword(User dto, String password) {
        return service.changePassword(dto.getId(), password);
    }

    @POST
    @Path("/periodo")
    @Consumes("application/json")
    public boolean changePeriodo(User user) {
        return service.changePerido(user.getId(), user.getPeriodo());
    }
}