package com.location.android.fridgefu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroceryListDataPump {
    public static HashMap<String, List<GroceryItem>> getData() {
        HashMap<String, List<GroceryItem>> expandableListDetail = new HashMap<String, List<GroceryItem>>();

        List<GroceryItem> dairy = new ArrayList<GroceryItem>();
        dairy.add(new GroceryItem("Milk"));

        List<GroceryItem> vegetables = new ArrayList<GroceryItem>();
        vegetables.add(new GroceryItem("Potato"));
        vegetables.add(new GroceryItem("Asparagus"));
        vegetables.add(new GroceryItem("String beans"));

        List<GroceryItem> fruits = new ArrayList<GroceryItem>();
        fruits.add(new GroceryItem("Tomato"));
        fruits.add(new GroceryItem("Dragon Fruit"));

        List<GroceryItem> meats = new ArrayList<GroceryItem>();
        meats.add(new GroceryItem("Chicken"));
        meats.add(new GroceryItem("Bacon"));

        expandableListDetail.put("dairy", dairy);
        expandableListDetail.put("vegetables", vegetables);
        expandableListDetail.put("fruits", fruits);
        expandableListDetail.put("meats", meats);
        return expandableListDetail;
    }
}
