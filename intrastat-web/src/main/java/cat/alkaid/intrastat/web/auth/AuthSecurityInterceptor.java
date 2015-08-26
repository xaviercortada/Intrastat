package cat.alkaid.intrastat.web.auth;


import cat.alkaid.intrastat.auth.AuthAccessElement;
import cat.alkaid.intrastat.auth.AuthService;

import javax.annotation.Priority;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xavier on 26/07/15.
 */

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
        String authToken = requestContext.getHeaderString(AuthAccessElement.PARAM_AUTH_TOKEN);

        System.out.println(requestContext.getHeaders());

        Class classInvoked = resourceInfo.getResourceClass();


        if (classInvoked.isAnnotationPresent(PermitAll.class)) {
            //RolesAllowed rolesAllowedAnnotation = methodInvoked.getAnnotation(RolesAllowed.class);
            Set<String> rolesAllowed = new HashSet<>();//Arrays.asList(rolesAllowedAnnotation.value()));
            SecurityContext authContext = authService.getSecurityContext(authId, authToken, rolesAllowed);
            if (authContext == null) {
                requestContext.abortWith(ACCESS_UNAUTHORIZED);
            }else{
                requestContext.setSecurityContext(authContext);
            }
        }

    }
}
