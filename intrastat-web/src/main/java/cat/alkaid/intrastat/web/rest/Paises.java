package cat.alkaid.intrastat.web.rest;


import cat.alkaid.intrastat.model.Pais;

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
@XmlSeeAlso(Pais.class)
public class Paises extends ArrayList<Pais> {
    private static final long serialVersionUID = 5L;

    public Paises() {
        super();
    }
    public Paises(Collection<? extends Pais> c) {
        super(c);
    }

    @XmlElement(name = "pais")
    public List<Pais> getPaises() {
        return this;
    }
    public void setPaises(List<Pais> data) {
        this.addAll(data);
    }
}
