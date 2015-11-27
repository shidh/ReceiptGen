package com.receipt;

import java.math.BigDecimal;

/**
 * Created by allen on 26/11/15.
 */
public class Util {

    /**
     *
     * @param doubleValue
     * @return np/100 rounded to the nearest 0.05
     */
    public static double roundDouble(double doubleValue) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(doubleValue));
        //the double value keep two decimals and times 100
        double scaledNumber = bigDecimal.setScale(2, BigDecimal.ROUND_UP).doubleValue() * 100;
        double accurate = scaledNumber % 5;
        if (accurate != 0) {
            scaledNumber += (accurate <= 2) ? -accurate : (5 - accurate);
        }
        return scaledNumber / 100;
    }

    /**
     *
     * @param decimalValue
     * @return np/100 rounded *UP* to the nearest 0.05
     */
    public static double roundUpDouble(BigDecimal decimalValue) {
        decimalValue = decimalValue.divide(new BigDecimal("0.05"));
        //Math.ceil(11.25) = 12.0
        decimalValue = new BigDecimal(Math.ceil(decimalValue.doubleValue()));
        decimalValue = decimalValue.multiply(new BigDecimal("0.05"));
        return decimalValue.doubleValue();
    }


}
