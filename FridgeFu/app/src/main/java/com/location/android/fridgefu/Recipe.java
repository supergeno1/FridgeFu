package com.location.android.fridgefu;

import java.util.ArrayList;

public class Recipe {
    String section;
    ArrayList<String> sectionDetails;
    Boolean isSteps;
    Boolean isIngredients;

    public void Recipe(String section, ArrayList<String> sectionDetails){
        if (section.equals("Ingredients")){
            isIngredients = true;
            isSteps = false;
        }
        else {
            isIngredients = false;
            isSteps = true;
        }

        this.section = section;
        this.sectionDetails = sectionDetails;
    }


}
