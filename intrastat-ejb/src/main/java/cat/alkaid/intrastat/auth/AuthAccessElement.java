package cat.alkaid.intrastat.auth;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by xavier on 26/07/15.
 */

@XmlRootElement
public class AuthAccessElement implements Serializable {
    public static final String PARAM_AUTH_ID = "auth-id";
    public static final String PARAM_AUTH_TOKEN = "auth-token";

    private String authId;
    private String authToken;
    private String authPermission;

    public AuthAccessElement(){}

    public AuthAccessElement(String authId){
        this.authId = authId;
        //this.authToken = UUID.randomUUID().toString();
        //this.setAuthPermission(authPermission);
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthPermission() {
        return authPermission;
    }

    public void setAuthPermission(String authPermission) {
        this.authPermission = authPermission;
    }


}
