package cat.alkaid.intrastat.web.rest;


import cat.alkaid.intrastat.auth.AuthAccessElement;
import cat.alkaid.intrastat.model.Factura;
import cat.alkaid.intrastat.service.FacturaService;
import cat.alkaid.intrastat.service.PeriodoService;
import cat.alkaid.intrastat.web.auth.Secured;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 * Created by xavier on 7/07/15.
 */
// The Java class will be hosted at the URI path "/helloworld"
// The Java class will be hosted at the URI path "/helloworld"
@Stateless
@Path("/factura")
public class FacturaResource {

    @EJB
    private FacturaService service;

    @EJB
    private PeriodoService perService;

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    //@Produces("text/plain")
    @Path("/{id}")
    @Produces({"application/xml", "application/json"})
    public Factura getItemById(@PathParam("id") String id) {
        // Return some cliched textual content

        Factura dto = service.findById(Long.parseLong(id));


        return dto;
    }


    @GET
    @Path("/")
    @Produces({"application/xml", "application/json"})
    @Secured
    public Facturas findAll(@HeaderParam(AuthAccessElement.PARAM_AUTH_ID) String authId) {
        //Long idPer = Long.parseLong(id_periodo);

        System.out.println("all");

        return new Facturas(service.findAll(authId));
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    @Secured
    public boolean createFactura(@HeaderParam(AuthAccessElement.PARAM_AUTH_ID) String authId, Factura dto) {
/*
        Long idPer = Long.parseLong(id_periodo);

        Periodo periodo = perService.findById(idPer);
        dto.setPeriodo(periodo);
*/
        if(authId == null) return false;

        return service.create(authId, dto);

    }


    @PUT
    @Path("/")
    @Consumes("application/json")
    public boolean updateItem(Factura dto) {
        return service.update(dto);

    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public boolean deleteItem(@PathParam("id") String id) {
        return service.delete(Long.parseLong(id));

    }

}