package cat.alkaid.intrastat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by xavier on 3/07/15.
 */


@Entity
@XmlRootElement(name = "periodo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int month;
    private int year;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
