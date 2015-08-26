package cat.alkaid.intrastat.web.rest;


import cat.alkaid.intrastat.model.Transporte;

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
@XmlSeeAlso(Transporte.class)
public class Transportes extends ArrayList<Transporte> {
    private static final long serialVersionUID = 2L;

    public Transportes() {
        super();
    }
    public Transportes(Collection<? extends Transporte> c) {
        super(c);
    }

    @XmlElement(name = "transporte")
    public List<Transporte> getTransportes() {
        return this;
    }
    public void setTransportes(List<Transporte> data) {
        this.addAll(data);
    }
}
