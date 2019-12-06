package com.location.android.fridgefu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IngredientToGroup {
    public static HashMap<String, String> getData() {
        HashMap<String, String> expandableListDetail = new HashMap<String, String>();

        expandableListDetail.put("milk", "dairy");
        expandableListDetail.put("yogurt", "dairy");
        expandableListDetail.put("cheese", "dairy");
        expandableListDetail.put("cream cheese", "dairy");
        expandableListDetail.put("condensed milk", "dairy");
        expandableListDetail.put("celery", "vegetables");
        expandableListDetail.put("cauliflower", "vegetables");
        expandableListDetail.put("potatoes", "vegetables");
        expandableListDetail.put("spinach", "vegetables");
        expandableListDetail.put("apple", "fruits");
        expandableListDetail.put("orange", "fruits");
        expandableListDetail.put("cucumber", "fruits");



        return expandableListDetail;
    }
}
