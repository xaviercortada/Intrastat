package cat.alkaid.intrastat.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by xavier on 27/07/15.
 */

@Entity
@Access(AccessType.FIELD)
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String name;
    private String password;
    private String token;

    @ManyToOne
    @JoinColumn(name = "", nullable = true, updatable = true, insertable = true)
    private Periodo periodo;

    public User(){

    }
    public User(String name, String password){
        this.name = name;
        this.password = password;
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
        return this.password.equals(passwod);
    }

    public void putToken(String token){
        this.token = token;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public boolean isAuthorized(String authToken) {
        return token.equals(authToken);
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
}
