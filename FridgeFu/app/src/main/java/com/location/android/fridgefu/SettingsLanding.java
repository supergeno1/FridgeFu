package com.location.android.fridgefu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;
import java.util.List;

public class SettingsLanding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        HashMap<String, List<FridgeItem>> filler = new HashMap<String, List<FridgeItem>>();

        FridgeContents fridgeContents = FridgeContents.getInstance(filler);
        Log.e("SIZE", fridgeContents.globalFridgeHash.get(fridgeContents.globalFridgeHash.keySet().toArray()[0]).get(0).ingredient);

//
//
//        Button b = (Button) findViewById(R.id.bruh);
//        String firstbutton = printList(fridgeContents.allIngredients());
//        b.setText("LOL");
//        Log.e("YEET", firstbutton);
//        String remove = fridgeContents.globalFridgeHash.get(fridgeContents.globalFridgeHash.keySet().toArray()[0]).get(0).ingredient;
//        fridgeContents.removeIngredient(remove);
//        String secondbutton = printList(fridgeContents.allIngredients());
//        Button b2 = (Button) findViewById(R.id.bruh2);
//        b2.setText("LOL2");
//        Log.e("YEET", secondbutton);





    }

    //testing function
    public String printList(List<String> bruh){
        String out = "";
        for (int i = 0; i < bruh.size(); i++) {

            out = out.concat(" ");
            out = out.concat(bruh.get(i));
        }

        return out;
    }

    public void to_fridge(View view){
        Intent intent = new Intent(getBaseContext(), FridgeLanding.class);
        startActivity(intent);
    }

    public void to_setting(View view){
        return;
    }

    public void to_grocery_list(View view){
        Intent intent = new Intent(getBaseContext(), GroceryLanding.class);
        startActivity(intent);

    }

    public void to_recipe_book(View view){

        Intent intent = new Intent(getBaseContext(), RecipeBook.class);
        startActivity(intent);

    }






}
