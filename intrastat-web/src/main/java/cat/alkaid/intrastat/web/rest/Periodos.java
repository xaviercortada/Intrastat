package cat.alkaid.intrastat.web.rest;


import cat.alkaid.intrastat.model.Periodo;

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
@XmlSeeAlso(Periodo.class)
public class Periodos extends ArrayList<Periodo> {
    private static final long serialVersionUID = 2L;

    public Periodos() {
        super();
    }
    public Periodos(Collection<? extends Periodo> c) {
        super(c);
    }

    @XmlElement(name = "periodo")
    public List<Periodo> getPeriodos() {
        return this;
    }
    public void setPeriodos(List<Periodo> data) {
        this.addAll(data);
    }
}
