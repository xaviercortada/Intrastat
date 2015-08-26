package cat.alkaid.intrastat.web.rest;


import cat.alkaid.intrastat.model.Category;
import cat.alkaid.intrastat.service.CategoryService;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 * Created by xavier on 7/07/15.
 */
// The Java class will be hosted at the URI path "/helloworld"
// The Java class will be hosted at the URI path "/helloworld"
@Stateless
@Path("/category")
@PermitAll()
public class CategoryResource {

    @EJB
    private CategoryService service;

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    //@Produces("text/plain")
    @Path("/{id}")
    @Produces({"application/xml", "application/json"})
    public Category getProveedorById(@PathParam("id") String id) {
        // Return some cliched textual content

        Category dto = service.findById(Long.parseLong(id));

        return dto;
    }


    @GET
    @Path("/")
    @Produces({"application/xml", "application/json"})
    public Categories findAll() {
        // Return some cliched textual content

        System.out.println("all");

        return new Categories(service.findAll());
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    public boolean createCategory(Category dto) {
        System.out.println(dto);
        return service.create(dto);

    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    public boolean updateCategory(Category dto) {
        return service.update(dto);

    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public boolean deleteCategory(@PathParam("id") String id) {
        return service.delete(Long.parseLong(id));

    }

}