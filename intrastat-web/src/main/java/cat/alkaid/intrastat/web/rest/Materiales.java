package cat.alkaid.intrastat.web.rest;


import cat.alkaid.intrastat.model.Material;

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
@XmlSeeAlso(Material.class)
public class Materiales extends ArrayList<cat.alkaid.intrastat.model.Material> {
    private static final long serialVersionUID = 3L;

    public Materiales() {
        super();
    }
    public Materiales(Collection<? extends cat.alkaid.intrastat.model.Material> c) {
        super(c);
    }

    @XmlElement(name = "material")
    public List<cat.alkaid.intrastat.model.Material> getMaterialess() {
        return this;
    }
    public void setMateriales(List<cat.alkaid.intrastat.model.Material> data) {
        this.addAll(data);
    }
}
