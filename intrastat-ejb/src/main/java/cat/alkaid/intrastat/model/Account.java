package cat.alkaid.intrastat.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by xavier on 27/07/15.
 */

@Entity
@Access(AccessType.FIELD)
@Table(name = "Account")
@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String name;
    private String password;
    private String token;
    private Date activated;

    @ManyToOne
    @JoinColumn(name = "", nullable = true, updatable = true, insertable = true)
    private Company company;
    private String firstName;
    private String lastName;
    private String activationCode;

    public Account(){

    }
    public Account(String name, String password){
        this.name = name;
        this.setPassword(password);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean validate(String passwod){
        return this.getPassword().equals(passwod);
    }

    public void putToken(String token){
        this.token = token;
    }

    public void changePassword(String password) {
        this.setPassword(password);
    }

    public boolean isAuthorized(String authToken) {
        return token.equals(authToken);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public Date getActivated() {
        return activated;
    }

    public void setActivated(Date activated) {
        this.activated = activated;
    }
}
