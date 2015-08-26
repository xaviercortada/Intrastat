package cat.alkaid.intrastat.web.rest;


import cat.alkaid.intrastat.model.Pais;
import cat.alkaid.intrastat.service.PaisService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 * Created by xavier on 7/07/15.
 */
// The Java class will be hosted at the URI path "/helloworld"
// The Java class will be hosted at the URI path "/helloworld"
@Stateless
@Path("/pais")
public class PaisResource {

    @EJB
    private PaisService service;

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    //@Produces("text/plain")
    @Path("/{codigo}")
    @Produces({"application/xml", "application/json"})
    public Pais getPaisByCodigo(@PathParam("codigo") String id) {
        // Return some cliched textual content

        Pais dto = service.findByCodigo(Integer.parseInt(id));

        return dto;
    }


    @GET
    @Path("/")
    @Produces({"application/xml", "application/json"})
    public Paises findAll() {
        // Return some cliched textual content

        System.out.println("all");

        return new Paises(service.findAll());
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    public boolean createPais(Pais dto) {
        System.out.println(dto);
        return service.create(dto);

    }

    @POST
    @Path("/")
    @Consumes("application/json")
    public boolean updatePais(Pais dto) {
        return service.update(dto);

    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public boolean deletePais(@PathParam("id") String codigo) {
        return service.delete(Integer.parseInt(codigo));

    }

}