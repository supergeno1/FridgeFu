package com.location.android.fridgefu;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FridgeContents {

    HashMap<String, List<FridgeItem>> globalFridgeHash;
    private static FridgeContents instance = null;
    static Boolean filled;
    protected FridgeContents(HashMap<String, List<FridgeItem>> contents) {
        this.globalFridgeHash = contents;
    }
    public static FridgeContents getInstance(HashMap<String, List<FridgeItem>> contents) {
        if(instance == null) {
            instance = new FridgeContents(contents);
            Log.e("FRidgecontents", "create");
            filled = true;
        }
        return instance;
    }

    public int removeIngredient(String removeIngredient){
        ArrayList<String> allIngredients = (ArrayList<String>) this.allIngredients();
        if (allIngredients.contains(removeIngredient)){

            Object [] keys = globalFridgeHash.keySet().toArray();
            for (int i = 0; i < keys.length; i++){
                List<String> inCategory = stringFridgeCategoryList(keys[i].toString());

                if (inCategory.contains(removeIngredient)){
                    int indexOfRemove = inCategory.indexOf(removeIngredient);
                    List <FridgeItem> removeFromList = globalFridgeHash.get(keys[i]);
                    removeFromList.remove(indexOfRemove);
                    return 0;
                }

            }
        }
        return 1;
    }

    public List<String> stringFridgeCategoryList(String key){
        ArrayList<String> stringCategoryList = new ArrayList<String>();

        List<FridgeItem> inCategory = globalFridgeHash.get(key);
        for (int j = 0; j < inCategory.size(); j++){
            stringCategoryList.add(inCategory.get(j).ingredient);
        }
        return stringCategoryList;
    }


    public List<String> allIngredients(){
        Object [] keys = globalFridgeHash.keySet().toArray();
        ArrayList<String> allIngredients = new ArrayList<String>();
        for (int i = 0; i < keys.length; i++){
            List<FridgeItem> inCategory = globalFridgeHash.get(keys[i]);
            for (int j = 0; j < inCategory.size(); j++){
                allIngredients.add(inCategory.get(j).ingredient);
            }

        }
        return allIngredients;
    }


}
