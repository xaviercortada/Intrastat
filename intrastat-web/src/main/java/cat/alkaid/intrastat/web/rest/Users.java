package cat.alkaid.intrastat.web.rest;

import cat.alkaid.intrastat.model.Account;

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
@XmlSeeAlso(Account.class)
public class Users extends ArrayList<Account> {
    private static final long serialVersionUID = 2L;

    public Users() {
        super();
    }
    public Users(Collection<? extends Account> c) {
        super(c);
    }

    @XmlElement(name = "user")
    public List<Account> getUsers() {
        return this;
    }
    public void setUsers(List<Account> data) {
        this.addAll(data);
    }
}
