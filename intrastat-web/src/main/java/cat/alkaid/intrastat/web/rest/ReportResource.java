package cat.alkaid.intrastat.web.rest;

import cat.alkaid.intrastat.auth.AuthAccessElement;
import cat.alkaid.intrastat.service.ReportService;
import cat.alkaid.intrastat.web.auth.Secured;

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
public class ReportResource {

    @EJB
    private ReportService service;

    @GET
    @Path("/")
    @Secured
    public Response excel(@HeaderParam(AuthAccessElement.PARAM_AUTH_ID) String authId, @HeaderParam("per-id") String id_periodo) {
        Long idPer = Long.parseLong(id_periodo);

        StreamingOutput streamout = service.Basic(authId, idPer);

        //Response.ResponseBuilder response = Response.ok(streamout, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        Response.ResponseBuilder response = Response.ok(streamout, "application/vnd.oasis.opendocument.spreadsheet");
        response.header("content-disposition", "attachment; filename=\"Report-"+"intrastat"+"\".xls");

        return response.build();
    }


}