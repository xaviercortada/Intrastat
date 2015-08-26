package cat.alkaid.intrastat.web.rest;

import cat.alkaid.intrastat.model.Category;

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
@XmlSeeAlso(Category.class)
public class Categories extends ArrayList<Category> {
    private static final long serialVersionUID = 2L;

    public Categories() {
        super();
    }
    public Categories(Collection<? extends Category> c) {
        super(c);
    }

    @XmlElement(name = "category")
    public List<Category> getCategories() {
        return this;
    }
    public void setCategories(List<Category> data) {
        this.addAll(data);
    }
}
