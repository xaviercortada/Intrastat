package cat.alkaid.intrastat.web.rest;


import cat.alkaid.intrastat.model.Item;

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
@XmlSeeAlso(Item.class)
public class Items extends ArrayList<Item> {
    private static final long serialVersionUID = 3L;

    public Items() {
        super();
    }
    public Items(Collection<? extends Item> c) {
        super(c);
    }

    @XmlElement(name = "item")
    public List<Item> getItems() {
        return this;
    }
    public void setItems(List<Item> data) {
        this.addAll(data);
    }
}
