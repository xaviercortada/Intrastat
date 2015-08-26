package cat.alkaid.intrastat.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by xavier on 3/07/15.
 */


@Entity
@XmlRootElement(name = "pais")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pais {

    @Id
    private int codigo;
    private String name;
    private String sigla;


     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
