package cat.alkaid.intrastat.web.rest;


import cat.alkaid.intrastat.model.Factura;

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
@XmlSeeAlso(Factura.class)
public class Facturas extends ArrayList<Factura> {
    private static final long serialVersionUID = 3L;

    public Facturas() {
        super();
    }
    public Facturas(Collection<? extends Factura> c) {
        super(c);
    }

    @XmlElement(name = "factura")
    public List<Factura> getFacturas() {
        return this;
    }
    public void setFactura(List<Factura> data) {
        this.addAll(data);
    }
}
