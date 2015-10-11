package cat.alkaid.intrastat.web.rest;


import cat.alkaid.intrastat.model.Company;
import cat.alkaid.intrastat.service.CompanyService;

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
@Path("/company")
@PermitAll()
public class CompanyResource {

    @EJB
    private CompanyService service;

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    //@Produces("text/plain")
    @Path("/{id}")
    @Produces({"application/xml", "application/json"})
    public Company getCompanyById(@PathParam("id") String id) {
        // Return some cliched textual content

        Company dto = service.findById(Long.parseLong(id));

        return dto;
    }


    @POST
    @Path("/")
    @Consumes("application/json")
    public boolean createCompany(Company dto) {
        System.out.println(dto);
        return service.create(dto);
    }

    @PUT
    @Path("/")
    @Consumes("application/json")
    public boolean updateCompany(Company dto) {
        return service.update(dto);
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    public boolean deleteCompany(@PathParam("id") String id) {
        return service.delete(Long.parseLong(id));
    }

}