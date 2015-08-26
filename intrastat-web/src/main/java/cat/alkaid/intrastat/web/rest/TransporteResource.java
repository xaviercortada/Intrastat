package cat.alkaid.intrastat.web.rest;

import cat.alkaid.intrastat.model.Transporte;
import cat.alkaid.intrastat.service.TransporteService;

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
@Path("/transporte")
@PermitAll()
public class TransporteResource {

    @EJB
    private TransporteService service;

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    //@Produces("text/plain")
    @Path("/{id}")
    @Produces({"application/xml", "application/json"})
    public Transporte getTransporteById(@PathParam("id") String codigo) {
        // Return some cliched textual content

        Transporte dto = service.findByCodigo(codigo);

        return dto;
    }


    @GET
    @Path("/")
    @Produces({"application/xml", "application/json"})
    public Transportes findAll() {
        // Return some cliched textual content

        System.out.println("all");

        return new Transportes(service.findAll());
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    public boolean createTransporte(Transporte dto) {
        System.out.println(dto);
        return service.create(dto);

    }

    @POST
    @Path("/")
    @Consumes("application/json")
    public boolean updateTransporte(Transporte dto) {
        return service.update(dto);

    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public boolean deleteTransporte(@PathParam("codigo") String codigo) {
        return service.delete(codigo);

    }

}