package com.location.android.fridgefu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class RecipeBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_book);

    }

    public void go_single_recipe(View view){
        Intent intent = new Intent(getBaseContext(), SingleRecipe.class);
        startActivity(intent);

    }

    public void to_fridge(View view){
        Intent intent = new Intent(getBaseContext(), FridgeLanding.class);
        startActivity(intent);
    }

    public void to_setting(View view){
        Intent intent = new Intent(getBaseContext(), SettingsLanding.class);
        startActivity(intent);
    }

    public void to_grocery_list(View view){
        Intent intent = new Intent(getBaseContext(), GroceryLanding.class);
        startActivity(intent);

    }

    public void to_recipe_book(View view){

        return;

    }

}