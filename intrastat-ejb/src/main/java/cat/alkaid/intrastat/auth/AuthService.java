package cat.alkaid.intrastat.auth;

import cat.alkaid.intrastat.model.User;
import cat.alkaid.intrastat.service.UserService;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.Set;

/**
 * Created by xavier on 21/07/15.
 */

@Stateless
@Local
public class AuthService {
    @EJB
    private UserService service;

    public AuthAccessElement Login(AuthLoginElement loginElement){
        User user = service.findByUsername(loginElement.getUsername());
        if(user != null){
            if(user.validate(loginElement.getPassword())){
                AuthAccessElement accessElement = new AuthAccessElement(loginElement.getUsername());
                user.putToken(accessElement.getAuthToken());
                service.update(user);
                return accessElement;
            }
        }
        return null;
    }


    public boolean isAuthorized(String authId, String authToken, Set<String> role) {
        User user = service.findByUsername(authId);
        if(user != null){
            if(user.isAuthorized(authToken)){
                return true;
            }
        }
        return false;

    }

    public SecurityContext getSecurityContext(String authId, String authToken, Set<String> rolesAllowed) {
        final User user = service.findByUsername(authId);
        if(user != null){
            if(user.isAuthorized(authToken)){
                SecurityContext securityCtx = new SecurityContext() {
                    @Override
                    public Principal getUserPrincipal() {
                        return new Principal() {
                            @Override
                            public String getName() {
                                return user.getName();
                            }
                        };
                    }

                    @Override
                    public boolean isUserInRole(String s) {
                        return true;
                    }

                    @Override
                    public boolean isSecure() {
                        return true;
                    }

                    @Override
                    public String getAuthenticationScheme() {
                        return "authSchema";
                    }
                };
                return securityCtx;
            }
        }
        return null;
    }
}
