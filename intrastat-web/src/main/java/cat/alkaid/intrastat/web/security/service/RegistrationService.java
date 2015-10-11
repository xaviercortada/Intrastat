package cat.alkaid.intrastat.web.security.service;

import cat.alkaid.intrastat.model.Account;
import cat.alkaid.intrastat.model.Email;
import cat.alkaid.intrastat.util.MessageBuilder;
import cat.alkaid.intrastat.web.security.model.IdentityModelManager;
import cat.alkaid.intrastat.web.security.model.UserRegistration;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by xavier on 4/09/15.
 */


@Stateless
@Path("/register")
@Produces("application/json")
@Consumes("application/json")
public class RegistrationService {
    @Inject
    private IdentityModelManager identityModelManager;

    @Inject
    @Any
    private Event<Email> event;

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMember(UserRegistration request) {

        if (!request.getPassword().equals(request.getPasswordConfirmation())) {
            return MessageBuilder.badRequest().message("Password mismatch.").build();
        }

        MessageBuilder message;


        try {
            // if there is no user with the provided e-mail, perform registration
            if (this.identityModelManager.findByLoginName(request.getEmail()) == null) {
                Account newUser = this.identityModelManager.createAccount(request);

                //this.identityModelManager.grantRole(newUser, USER);

                String activationCode = newUser.getActivationCode();

                sendNotification(request, activationCode);

                message = MessageBuilder.ok().activationCode(activationCode);
            } else {
                message = MessageBuilder.badRequest().message("This username is already in use. Try another one.");
            }
        } catch (Exception e) {
            message = MessageBuilder.badRequest().message(e.getMessage());
        }

        return message.build();

    }

    @POST
    @Path("/activation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response activateAccount(String activationCode) {
        MessageBuilder message;

        Account account = identityModelManager.enableAccount(activationCode);
        if(account != null) {


            try {
                //JsonWebSignature jws = identityModelManager.getToken(account);
                //String jwt = jws.getCompactSerialization();

                //System.out.println("JWT: " + jwt);
                message = MessageBuilder.ok().token("");
            } catch (Exception e) {
                message = MessageBuilder.badRequest().message(e.getMessage());
            }
            return message.build();
        }else{
            return null;
        }
    }


    private void sendNotification(UserRegistration request, String activationCode) {
        Email email = new Email("Please complete the signup", "http://localhost:8080/intrastat/#/activate/" + activationCode, request.getEmail());

        event.fire(email);
    }


}
