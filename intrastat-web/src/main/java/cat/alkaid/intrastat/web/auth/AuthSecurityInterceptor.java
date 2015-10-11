package cat.alkaid.intrastat.web.auth;


import cat.alkaid.intrastat.auth.AuthAccessElement;
import cat.alkaid.intrastat.auth.AuthService;
import org.jose4j.lang.JoseException;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by xavier on 26/07/15.
 */


@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthSecurityInterceptor implements ContainerRequestFilter {
    // 401 - Access denied
    private static final Response ACCESS_UNAUTHORIZED = Response.status(Response.Status.UNAUTHORIZED).entity("Not authorized.").build();

    @EJB
    AuthService authService;

    @Context
    private HttpServletRequest request;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authId = requestContext.getHeaderString(AuthAccessElement.PARAM_AUTH_ID);
        //String authToken = requestContext.getHeaderString(AuthAccessElement.PARAM_AUTH_TOKEN);

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        String authToken = authHeader.substring("Bearer".length()).trim();


        try {
            authService.isAuthorized(authId, authToken, null);
        } catch (JoseException e) {
            e.printStackTrace();
            requestContext.abortWith(ACCESS_UNAUTHORIZED);
        }

        return;

    }
}
