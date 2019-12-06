package com.location.android.fridgefu;

public class GroceryItem {
    public String ingredient;
    public boolean show_settings = false;
    public boolean is_bought = false;
    public boolean is_pinned = false;
    public GroceryItem (String ingredient) {
        this.ingredient = ingredient;
        this.is_pinned = false;
    }
    public GroceryItem (String ingredient, boolean is_pinned) {
        this.ingredient = ingredient;
        this.is_pinned = true;
    }

}
