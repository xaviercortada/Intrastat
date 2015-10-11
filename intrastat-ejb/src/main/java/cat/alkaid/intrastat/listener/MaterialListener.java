package cat.alkaid.intrastat.listener;

import cat.alkaid.intrastat.model.Material;
import cat.alkaid.intrastat.utils.FormatterBean;

import javax.persistence.PostLoad;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Created by xavier on 4/08/15.
 */

public class MaterialListener {
    private final String pattern = "#,###.00";

    @PostLoad
    public void postLoad(Object object){

        DecimalFormat decimalFormat = FormatterBean.getInstance().getDecimalFormat();
        decimalFormat.applyPattern(pattern);
        if(object instanceof Material){
            Material material = (Material)object;
            try {
                Number number = decimalFormat.parse(material.getImporte());
                material.setPrice(number.floatValue());
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

}
