package cat.alkaid.intrastat.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Set;

/**
 * Created by xavier on 3/07/15.
 */

@Entity
@XmlRootElement(name = "factura")
@XmlAccessorType(XmlAccessType.FIELD)
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FACTURA_ID")
    private Long id;

    private String codigo;

    private String entrega;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "", nullable = true, updatable = true, insertable = true)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "", nullable = true, updatable = true, insertable = true)
    private Transporte transporte;

    @ManyToOne
    @JoinColumn(name = "", nullable = true, updatable = true, insertable = true)
    private Pais pais;

    @ManyToOne
    @JoinColumn(name = "", nullable = true, updatable = true, insertable = true)
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "", nullable = false, updatable = true, insertable = true)
    private Account account;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "FACT_MATERIAL", joinColumns = @JoinColumn(name = "FACTURA_ID"), inverseJoinColumns = @JoinColumn(name = "MATERIAL_ID"))
    private Set<Material> materiales;

    public Set<Material> getMateriales() {
        return this.materiales;
    }

    public void setMateriales(Set<Material> materiales){
        this.materiales = materiales;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }


    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void addMaterial(Material mat){
        materiales.add(mat);
        //mat.setFactura(this);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
