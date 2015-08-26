package cat.alkaid.intrastat.web.auth;

import cat.alkaid.intrastat.auth.AuthAccessElement;
import cat.alkaid.intrastat.auth.AuthLoginElement;
import cat.alkaid.intrastat.auth.AuthService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

/**
 * Created by xavier on 26/07/15.
 */

@Stateless
@Path("/auth")
@Produces("application/json")
@Consumes("application/json")
public class AuthResource {

    @EJB
    AuthService service;

    @POST
    @Path("/")
    public AuthAccessElement login(@Context HttpServletRequest request, AuthLoginElement loginElement){
        AuthAccessElement accessElement = service.Login(loginElement);
        if(accessElement != null){
            request.getSession().setAttribute(AuthAccessElement.PARAM_AUTH_ID, accessElement.getAuthId());
            request.getSession().setAttribute(AuthAccessElement.PARAM_AUTH_TOKEN, accessElement.getAuthToken());
        }
        return accessElement;
    }

    @GET
    @Path("/")
    public String login(){
        return "hello";
    }
}
