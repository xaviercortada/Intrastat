package cat.alkaid.intrastat.web.rest;

import cat.alkaid.intrastat.model.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by xavier on 12/07/15.
 */

@XmlRootElement
@XmlSeeAlso(User.class)
public class Users extends ArrayList<User> {
    private static final long serialVersionUID = 2L;

    public Users() {
        super();
    }
    public Users(Collection<? extends User> c) {
        super(c);
    }

    @XmlElement(name = "user")
    public List<User> getUsers() {
        return this;
    }
    public void setUsers(List<User> data) {
        this.addAll(data);
    }
}
