package cat.alkaid.intrastat.web.auth;

import cat.alkaid.intrastat.auth.AuthAccessElement;
import cat.alkaid.intrastat.auth.AuthLoginElement;
import cat.alkaid.intrastat.auth.AuthService;
import cat.alkaid.intrastat.util.MessageBuilder;
import cat.alkaid.intrastat.web.security.model.IdentityModelManager;
import org.jose4j.lang.JoseException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Created by xavier on 26/07/15.
 */

@Stateless
@Path("/auth")
@Produces("application/json")
@Consumes("application/json")
public class AuthResource {

    @EJB
    private AuthService service;

    @Inject
    private IdentityModelManager identityModelManager;

    @POST
    @Path("/")
    public Response login(@Context HttpServletRequest request, AuthLoginElement loginElement){
        MessageBuilder message;

        AuthAccessElement accessElement = null;
        try {
            accessElement = service.Login(loginElement);
            if(accessElement != null){
                message = MessageBuilder.ok().token(accessElement.getAuthToken());

                request.getSession().setAttribute(AuthAccessElement.PARAM_AUTH_ID, accessElement.getAuthId());
                request.getSession().setAttribute(AuthAccessElement.PARAM_AUTH_TOKEN, accessElement.getAuthToken());
                return message.build();
            }
        } catch (JoseException e) {
            e.printStackTrace();
        }

        return null;

    }

    @GET
    @Path("/")
    public String login(){
        return "hello";
    }
}
