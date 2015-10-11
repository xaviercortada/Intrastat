package cat.alkaid.intrastat.web.security.model;

import cat.alkaid.intrastat.model.Account;
import cat.alkaid.intrastat.service.AccountService;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.UUID;

/**
 * Created by xavier on 4/09/15.
 */

@Stateless
public class IdentityModelManager {

    @EJB
    private AccountService service;

/*
    @Inject
    private Token.Provider<JWSToken> tokenProvider;
*/

    public Account createAccount(UserRegistration request) {
        if (!request.isValid()) {
            throw new IllegalArgumentException("Insuficient information.");
        }

        Account account = new Account();


        account.setEmail(request.getEmail());
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());

        String activationCode = UUID.randomUUID().toString();

        account.setActivationCode(activationCode); // we set an activation code for future use.

        service.create(account);

        //updatePassword(newUser, request.getPassword());

        //disableAccount(newUser);

        return account;
    }

    public void updatePassword(Account account, String password) {
        service.updateCredential(account, password);
    }

    public void disableAccount(Account user) {
/*
        if (hasRole(user, ApplicationRole.ADMINISTRATOR)) {
            throw new IllegalArgumentException("Administrators can not be disabled.");
        }

        user.setEnabled(false);

        if (user.getId() != null) {
            issueToken(user); // we invalidate the current token and create a new one. so any token stored by clients will be no longer valid.
            this.identityManager.update(user);
        }
*/
    }


    private void issueToken(Account account) {

        //return this.tokenProvider.issue(account);
    }

    public Object findByLoginName(String email) {

        return null;
    }

    public Account enableAccount(String activationCode) {
        return service.activateAccount(activationCode);
    }




}
