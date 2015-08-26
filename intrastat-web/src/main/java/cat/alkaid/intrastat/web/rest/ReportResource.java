package cat.alkaid.intrastat.web.rest;

import cat.alkaid.intrastat.service.ReportService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 * Created by xavier on 7/07/15.
 */

@Stateless
@Path("/report")
//@PermitAll()
public class ReportResource {

    @EJB
    private ReportService service;

    @GET
    @Path("/")
    public Response excel(@HeaderParam("per-id") String id_periodo) {
        Long idPer = Long.parseLong(id_periodo);

        StreamingOutput streamout = service.Basic(idPer);

        //Response.ResponseBuilder response = Response.ok(streamout, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        Response.ResponseBuilder response = Response.ok(streamout, "application/vnd.oasis.opendocument.spreadsheet");
        response.header("content-disposition", "attachment; filename=\"Report-"+"intrastat"+"\".xls");

        return response.build();
    }


}