package cat.alkaid.intrastat.web.rest;

import cat.alkaid.intrastat.model.Periodo;
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
@Path("/periodo")
public class PeriodoResource {

    @EJB
    private PeriodoService service;

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    //@Produces("text/plain")
    @Path("/{id}")
    @Produces({"application/xml", "application/json"})
    public Periodo getItemById(@PathParam("id") String id) {
        // Return some cliched textual content

        Periodo dto = service.findById(Long.parseLong(id));

        return dto;
    }

    @GET
    @Path("/")
    @Produces({"application/xml", "application/json"})
    public Periodo getItemById(@QueryParam("month") String month, @QueryParam("year") String year) {
        // Return some cliched textual content

        Periodo dto = service.findByMontAndYear(Integer.parseInt(month), Integer.parseInt(year));
        if(dto == null){
            dto = new Periodo();
            dto.setMonth(Integer.parseInt(month));
            dto.setYear(Integer.parseInt(year));
            service.create(dto);
        }

        return dto;
    }

    @GET
    @Path("/all")
    @Produces({"application/xml", "application/json"})
    public Periodos findAll() {
        // Return some cliched textual content

        System.out.println("all");

        return new Periodos(service.findAll());
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    public boolean createItem(Periodo dto) {
        System.out.println(dto);
        return service.create(dto);

    }

    @POST
    @Path("/")
    @Consumes("application/json")
    public boolean updateItem(Periodo dto) {
        return service.update(dto);

    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public boolean deleteItem(@PathParam("id") String id) {
        return service.delete(Long.parseLong(id));

    }

}