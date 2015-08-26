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
@XmlRootElement(name = "provincia")
@XmlAccessorType(XmlAccessType.FIELD)
public class Provincia {

    @Id
    private int codigo;
    private String name;


     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
