package cat.alkaid.intrastat.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by xavier on 2/08/15.
 */

public class FormatterBean implements Formatter {
    private static FormatterBean instance = null;

    public static FormatterBean getInstance(){
        if(instance == null){
            instance = new FormatterBean();
        }

        return instance;
    }

    private DecimalFormat decimalFormat;

    public FormatterBean(){
        Locale locale = new Locale("es", "ES");
        decimalFormat = (DecimalFormat) NumberFormat.getInstance(locale);
        //decimalFormat.applyPattern(pattern);
    }


    @Override
    public DecimalFormat getDecimalFormat() {
        return decimalFormat;
    }

}
