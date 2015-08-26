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
@XmlRootElement(name = "transporte")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transporte {

    @Id
    private String codigo;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
