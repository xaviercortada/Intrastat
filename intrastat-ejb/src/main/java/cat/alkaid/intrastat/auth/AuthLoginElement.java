package cat.alkaid.intrastat.auth;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by xavier on 27/07/15.
 */

@XmlRootElement
public class AuthLoginElement implements Serializable{
    private String username;
    private String password;

    public AuthLoginElement(){}

    public AuthLoginElement(String username, String password){
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
