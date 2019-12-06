package com.location.android.fridgefu;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroceryContents {
    HashMap<String, List<GroceryItem>> globalGroceryHash;
    private static GroceryContents instance = null;
    static Boolean filled;
    protected GroceryContents(HashMap<String, List<GroceryItem>> contents) {
        this.globalGroceryHash = contents;
    }
    public static GroceryContents getInstance(HashMap<String, List<GroceryItem>> contents) {
        if(instance == null) {
            instance = new GroceryContents(contents);
            Log.e("GroceryContents", "create");
            filled = true;
        }
        return instance;
    }

    public int removeIngredient(String removeIngredient){
        ArrayList<String> allIngredients = (ArrayList<String>) this.allIngredients();
        if (allIngredients.contains(removeIngredient)){

            Object [] keys = globalGroceryHash.keySet().toArray();
            for (int i = 0; i < keys.length; i++){
                List<String> inCategory = stringGroceryCategoryList(keys[i].toString());

                if (inCategory.contains(removeIngredient)){
                    int indexOfRemove = inCategory.indexOf(removeIngredient);
                    List <GroceryItem> removeFromList = globalGroceryHash.get(keys[i]);
                    removeFromList.remove(indexOfRemove);
                    return 0;
                }

            }
        }
        return 1;
    }

    public List<String> stringGroceryCategoryList(String key){
        ArrayList<String> stringCategoryList = new ArrayList<String>();

        List<GroceryItem> inCategory = globalGroceryHash.get(key);
        for (int j = 0; j < inCategory.size(); j++){
            stringCategoryList.add(inCategory.get(j).ingredient);
        }
        return stringCategoryList;
    }


    public List<String> allIngredients(){
        Object [] keys = globalGroceryHash.keySet().toArray();
        ArrayList<String> allIngredients = new ArrayList<String>();
        for (int i = 0; i < keys.length; i++){
            List<GroceryItem> inCategory = globalGroceryHash.get(keys[i]);
            for (int j = 0; j < inCategory.size(); j++){
                allIngredients.add(inCategory.get(j).ingredient);
            }

        }
        return allIngredients;
    }

}
