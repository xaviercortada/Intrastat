package cat.alkaid.intrastat.web.rest;

import cat.alkaid.intrastat.model.Proveedor;

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
@XmlSeeAlso(Proveedor.class)
public class Proveedores extends ArrayList<Proveedor> {
    private static final long serialVersionUID = 1L;

    public Proveedores() {
        super();
    }
    public Proveedores(Collection<? extends Proveedor> c) {
        super(c);
    }

    @XmlElement(name = "proveedor")
    public List<Proveedor> getProveedores() {
        return this;
    }
    public void setProveedores(List<Proveedor> proveedores) {
        this.addAll(proveedores);
    }
}
