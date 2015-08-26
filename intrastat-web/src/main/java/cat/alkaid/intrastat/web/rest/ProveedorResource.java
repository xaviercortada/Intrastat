package cat.alkaid.intrastat.web.rest;

import cat.alkaid.intrastat.model.Proveedor;
import cat.alkaid.intrastat.service.ProveedorService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;

/**
 * Created by xavier on 7/07/15.
 */
// The Java class will be hosted at the URI path "/helloworld"
// The Java class will be hosted at the URI path "/helloworld"
@Stateless
@Path("/proveedores")
public class ProveedorResource {

    @EJB
    private ProveedorService proveedorService;

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    //@Produces("text/plain")
    @Path("/{id}")
    @Produces({"application/xml", "application/json"})
    public Proveedor getProveedorById(@PathParam("id") String id) {
        // Return some cliched textual content

        Proveedor dto = proveedorService.findById(Long.parseLong(id));
        //ProveedorDTO dto = new ProveedorDTO(Long.parseLong(id),"40767487E", "BCN Vending", "", "Barcelona");

        return dto;
    }


    @GET
    @Path("/")
    @Produces({"application/xml", "application/json"})
    public Proveedores findAll() {
        // Return some cliched textual content

        System.out.println("all");

        return new Proveedores(proveedorService.findAll());
    }

    @POST
    @Path("/")
    @Consumes("application/json")
    public boolean createProveedor(Proveedor dto) {
        System.out.println(dto);
        return proveedorService.create(dto);

    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    public boolean updateProveedor(Proveedor dto) {
        return proveedorService.update(dto);

    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public boolean deleteProveedor(@PathParam("id") String id) {
        return proveedorService.delete(Long.parseLong(id));

    }

}