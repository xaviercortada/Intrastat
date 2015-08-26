package cat.alkaid.intrastat.listener;

import cat.alkaid.intrastat.model.Item;
import cat.alkaid.intrastat.utils.FormatterBean;

import javax.persistence.PostLoad;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Created by xavier on 4/08/15.
 */

public class ItemListener {
    private final String pattern = "#,###.00";

    @PostLoad
    public void postLoad(Object object){

        DecimalFormat decimalFormat = FormatterBean.getInstance().getDecimalFormat();
        decimalFormat.applyPattern(pattern);
        if(object instanceof Item){
            Item item = (Item)object;
            try {
                Number number = decimalFormat.parse(item.getImporte());
                item.setPrice(number.floatValue());
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

}
