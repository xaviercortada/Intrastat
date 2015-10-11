package cat.alkaid.intrastat.web.rest;


import cat.alkaid.intrastat.model.Factura;
import cat.alkaid.intrastat.model.Material;
import cat.alkaid.intrastat.service.FacturaService;
import cat.alkaid.intrastat.service.MaterialService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 * Created by xavier on 7/07/15.
 */

@Stateless
@Path("/material")
public class MaterialResource {

    @EJB
    private MaterialService materialService;

    @EJB
    private FacturaService facturaService;

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    //@Produces("text/plain")
    @Path("/{id}")
    @Produces({"application/xml", "application/json"})
    public cat.alkaid.intrastat.model.Material getItemById(@PathParam("id") String id) {
        // Return some cliched textual content

        cat.alkaid.intrastat.model.Material dto = materialService.findById(Long.parseLong(id));

        return dto;
    }


    @GET
    @Path("/")
    @Produces({"application/xml", "application/json"})
    public Material findAll(@HeaderParam("per-id") String id_periodo) {
        Long idPer = Long.parseLong(id_periodo);

        System.out.println("all");

        return new Material();
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    public boolean createMaterial(cat.alkaid.intrastat.model.Material dto) {

        System.out.println(dto);

        return materialService.create(dto);

    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    public boolean updateItem(cat.alkaid.intrastat.model.Material dto) {
        return materialService.update(dto);

    }

    @DELETE
    @Path("/{idFactura}/{idMaterial}")
    @Consumes("application/json")
    public boolean deleteItem(@PathParam("idFactura") String idFactura, @PathParam("idMaterial") String idMaterial) {
        Factura factura = facturaService.findById(Long.parseLong(idFactura));
        if(factura != null){
            Material material = materialService.findById(Long.parseLong(idMaterial));
            if(material != null){
                facturaService.removeMaterial(factura, material);
            }
        }
        return true;

    }

}