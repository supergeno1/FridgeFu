package com.location.android.fridgefu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> dairy = new ArrayList<String>();
        dairy.add("Milk");
        dairy.add("Cheese");
        dairy.add("Ice Cream");
        dairy.add("Yogurt");
        dairy.add("Sour Cream");

        List<String> vegetables = new ArrayList<String>();
        vegetables.add("Broccoli");
        vegetables.add("Bell Peppers");
        vegetables.add("Onions");
        vegetables.add("Celery");
        vegetables.add("Carrots");

        List<String> fruits = new ArrayList<String>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Oranges");
        fruits.add("Blueberries");
        fruits.add("Mango");

        expandableListDetail.put("dairy", dairy);
        expandableListDetail.put("vegetables", vegetables);
        expandableListDetail.put("fruits", fruits);
        return expandableListDetail;
    }
}
