package com.location.android.fridgefu;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class FridgeItem {
    public String ingredient;
    public Calendar expiration_date;
    public FridgeItem (String ingredient, int year, int month, int day) {
        expiration_date = new GregorianCalendar();
        expiration_date.set(Calendar.YEAR, year);
        expiration_date.set(Calendar.MONTH, month);
        expiration_date.set(Calendar.DAY_OF_MONTH, day);
        this.ingredient = ingredient;
    }
}
