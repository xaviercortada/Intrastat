package cat.alkaid.intrastat.model;

import cat.alkaid.intrastat.converter.NumberConverter;
import cat.alkaid.intrastat.listener.ItemListener;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by xavier on 3/07/15.
 */

@Entity
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
@Access(AccessType.FIELD)
@Converts(value = {@Convert(attributeName = "importe", converter = NumberConverter.class)})
@EntityListeners(value=ItemListener.class)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String codigo;

    private String factura;

    private String entrega;

    @ManyToOne
    @JoinColumn(name = "", nullable = true, updatable = true, insertable = true)
    private Transporte transporte;

    @ManyToOne
    @JoinColumn(name = "", nullable = true, updatable = true, insertable = true)
    private Pais pais;

    @ManyToOne
    @JoinColumn(name = "", nullable = true, updatable = true, insertable = true)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "", nullable = true, updatable = true, insertable = true)
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "", nullable = true, updatable = true, insertable = true)
    private Periodo periodo;

    private Integer peso;

    private String importe;

    private Float price;

    private Integer unidades;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public String getImporte() {
        return this.importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
/*
        String pattern = "#,###.00";
        Locale locale = new Locale("es", "ES");
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(locale);
        decimalFormat.applyPattern(pattern);

        this.importe = importe;
        try {
            //Number number = myformatter.getDecimalFormat().parse(this.importe);
            Number number = decimalFormat.parse(this.importe);
            this.price = number.floatValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
*/
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        String pattern = "#,###.00";
        Locale locale = new Locale("es", "ES");
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(locale);
        decimalFormat.applyPattern(pattern);

        this.price = price;
        this.importe = "";

        if(this.price != null)
            this.importe = decimalFormat.format(this.price);
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
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

}
