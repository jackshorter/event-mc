package dev.rgd.api.text;

import java.math.BigDecimal;

public class Text {

    public static String SplitToComponentTimes(int value){
        long longVal = BigDecimal.valueOf(value).longValue();
        int hours = (int) longVal / 3600;
        int remainder = (int) longVal - hours * 3600;
        int mins = remainder / 60;
        remainder = remainder - mins * 60;
        int secs = remainder;

        return String.format("%02d:%02d",mins,secs);
    }
}