package com.location.android.fridgefu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroceryListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> dairy = new ArrayList<String>();
        dairy.add("Milk");

        List<String> vegetables = new ArrayList<String>();
        vegetables.add("Potato");
        vegetables.add("Asparagus");
        vegetables.add("String beans");

        List<String> fruits = new ArrayList<String>();
        fruits.add("Tomato");
        fruits.add("Dragon Fruit");

        List<String> meats = new ArrayList<String>();
        meats.add("Chicken");
        meats.add("Bacon");

        expandableListDetail.put("dairy", dairy);
        expandableListDetail.put("vegetables", vegetables);
        expandableListDetail.put("fruits", fruits);
        expandableListDetail.put("meats", meats);
        return expandableListDetail;
    }
}
