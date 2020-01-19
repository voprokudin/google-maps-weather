package com.vasylprokudin.googlemapsweather.util;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;

public class VPDateFormatter {

    @Inject
    public VPDateFormatter() {}

    @SuppressLint("SimpleDateFormat")
    public String getConvertedDate(long originalDate) {
        Date date = new Date(originalDate * 1000);
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yy / HH:mm");
        return df.format(date);
    }
}
