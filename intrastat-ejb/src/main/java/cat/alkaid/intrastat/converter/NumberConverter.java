package cat.alkaid.intrastat.converter;

import cat.alkaid.intrastat.utils.FormatterBean;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Created by xavier on 5/08/15.
 */

@Converter
public class NumberConverter implements AttributeConverter<String, Float>{
    private final String pattern = "#,###.00";

    @Override
    public Float convertToDatabaseColumn(String s) {
        DecimalFormat decimalFormat = FormatterBean.getInstance().getDecimalFormat();
        decimalFormat.applyPattern(pattern);
        try {
            Number number = decimalFormat.parse(s);
            return number.floatValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public String convertToEntityAttribute(Float aFloat) {
        DecimalFormat decimalFormat = FormatterBean.getInstance().getDecimalFormat();
        decimalFormat.applyPattern(pattern);
        return decimalFormat.format(aFloat);

    }
}
