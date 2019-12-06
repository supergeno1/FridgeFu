package com.location.android.fridgefu;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

//public class FridgeItem {
//    public String ingredient;
//    public Calendar expiration_date;
//    public boolean show_settings = false;
//
//    public FridgeItem (String ingredient, int year, int month, int day) {
//        expiration_date = new GregorianCalendar();
//        expiration_date.set(Calendar.YEAR, year);
//        expiration_date.set(Calendar.MONTH, month);
//        expiration_date.set(Calendar.DAY_OF_MONTH, day);
//        this.ingredient = ingredient;
//    }
//
////    @Override
////    public String toString() {
////        return "FridgeItem [ingredient=" + ingredient + ", expiration_date=" + expiration_date.toString() + ", show_settings=" + Boolean.toString(show_settings)
////                + "]";
////    }
//}

public class FridgeItem implements Parcelable {
    public String ingredient;
    public Calendar expiration_date;
    public boolean show_settings = false;
    public boolean is_expired = false;

    public FridgeItem (String ingredient, int year, int month, int day) {
        expiration_date = new GregorianCalendar();
        expiration_date.set(Calendar.YEAR, year);
        expiration_date.set(Calendar.MONTH, month);
        expiration_date.set(Calendar.DAY_OF_MONTH, day);
        this.ingredient = ingredient;
    }

//    @Override
//    public String toString() {
//        return "FridgeItem [ingredient=" + ingredient + ", expiration_date=" + expiration_date.toString() + ", show_settings=" + Boolean.toString(show_settings)
//                + "]";
//    }

    protected FridgeItem(Parcel in) {
        ingredient = in.readString();
        expiration_date = (Calendar) in.readValue(Calendar.class.getClassLoader());
        show_settings = in.readByte() != 0x00;
        is_expired = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ingredient);
        dest.writeValue(expiration_date);
        dest.writeByte((byte) (show_settings ? 0x01 : 0x00));
        dest.writeByte((byte) (is_expired ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<FridgeItem> CREATOR = new Parcelable.Creator<FridgeItem>() {
        @Override
        public FridgeItem createFromParcel(Parcel in) {
            return new FridgeItem(in);
        }

        @Override
        public FridgeItem[] newArray(int size) {
            return new FridgeItem[size];
        }
    };
}