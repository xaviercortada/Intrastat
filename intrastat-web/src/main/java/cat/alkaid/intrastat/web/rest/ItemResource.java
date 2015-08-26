package cat.alkaid.intrastat.web.rest;


import cat.alkaid.intrastat.model.Item;
import cat.alkaid.intrastat.model.Periodo;
import cat.alkaid.intrastat.service.ItemService;
import cat.alkaid.intrastat.service.PeriodoService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 * Created by xavier on 7/07/15.
 */
// The Java class will be hosted at the URI path "/helloworld"
// The Java class will be hosted at the URI path "/helloworld"
@Stateless
@Path("/item")
public class ItemResource {

    @EJB
    private ItemService service;

    @EJB
    private PeriodoService perService;

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    //@Produces("text/plain")
    @Path("/{id}")
    @Produces({"application/xml", "application/json"})
    public Item getItemById(@PathParam("id") String id) {
        // Return some cliched textual content

        Item dto = service.findById(Long.parseLong(id));

        return dto;
    }


    @GET
    @Path("/")
    @Produces({"application/xml", "application/json"})
    public Items findAll(@HeaderParam("per-id") String id_periodo) {
        Long idPer = Long.parseLong(id_periodo);

        System.out.println("all");

        return new Items(service.findByPeriodo(idPer));
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    public boolean createItem(@HeaderParam("per-id") String id_periodo, Item dto) {
        Long idPer = Long.parseLong(id_periodo);

        System.out.println(dto);

        Periodo periodo = perService.findById(idPer);
        dto.setPeriodo(periodo);

        return service.create(dto);

    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    public boolean updateItem(Item dto) {
        return service.update(dto);

    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public boolean deleteItem(@PathParam("id") String id) {
        return service.delete(Long.parseLong(id));

    }

}