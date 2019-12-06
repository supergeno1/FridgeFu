package com.location.android.fridgefu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<FridgeItem>> getData() {
        HashMap<String, List<FridgeItem>> expandableListDetail = new HashMap<String, List<FridgeItem>>();

        List<FridgeItem> dairy = new ArrayList<FridgeItem>();
        dairy.add(new FridgeItem("Milk", 2019, 11, 11));
        dairy.add(new FridgeItem("Cheese", 2019, 11, 6));
        dairy.add(new FridgeItem("Ice Cream", 2019, 11, 4));
        dairy.add(new FridgeItem("Yogurt", 2019, 11, 10));
        dairy.add(new FridgeItem("Sour Cream", 2019, 11, 1));
        dairy.add(new FridgeItem("Butter", 2019, 11, 1));


        List<FridgeItem> vegetables = new ArrayList<FridgeItem>();
        vegetables.add(new FridgeItem("Broccoli", 2019, 11, 20));
        vegetables.add(new FridgeItem("Bell Peppers", 2019, 11, 25));
        vegetables.add(new FridgeItem("Onions", 2019, 11, 24));
        vegetables.add(new FridgeItem("Celery", 2019, 11, 22));
        vegetables.add(new FridgeItem("Carrot", 2019, 11, 29));

        List<FridgeItem> fruits = new ArrayList<FridgeItem>();
        fruits.add(new FridgeItem("Apple", 2019, 11, 13));
        fruits.add(new FridgeItem("Banana", 2019, 11, 7));
        fruits.add(new FridgeItem("Oranges", 2019, 11, 1));
        fruits.add(new FridgeItem("Blueberries", 2019, 11, 16));
        fruits.add(new FridgeItem("Mango", 2019, 11, 31));
        fruits.add(new FridgeItem("Jelly", 2019, 11, 31));


        List<FridgeItem> grains = new ArrayList<FridgeItem>();
        grains.add(new FridgeItem("Wheat Bread", 2019, 11, 13));


        expandableListDetail.put("dairy", dairy);
        expandableListDetail.put("vegetables", vegetables);
        expandableListDetail.put("fruits", fruits);
        expandableListDetail.put("grains", grains);





        return expandableListDetail;
    }
}
