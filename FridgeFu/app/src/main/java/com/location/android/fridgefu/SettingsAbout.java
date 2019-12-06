package com.location.android.fridgefu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class SettingsAbout extends AppCompatActivity implements View.OnClickListener {

    private ImageButton groceryListMenu, fridgeMenu, recipeBookMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_about);

        ActionBar a = getSupportActionBar();
        a.setDisplayHomeAsUpEnabled(true);

        groceryListMenu = findViewById(R.id.grocery_list);
        fridgeMenu = findViewById(R.id.fridge_menu);
        recipeBookMenu = findViewById(R.id.recipe_book);

        groceryListMenu.setOnClickListener(this);
        fridgeMenu.setOnClickListener(this);
        recipeBookMenu.setOnClickListener(this);

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(getApplicationContext(), SettingsLanding.class);
        startActivityForResult(intent, 0);
        return true;

    }

    public void onClick(View view) {

        switch (view.getId()) {
            case (R.id.grocery_list): {
                Intent intent = new Intent(this, GroceryLanding.class);
                startActivity(intent);
                break;
            }
            case (R.id.fridge_menu): {
                Intent intent = new Intent(this, FridgeLanding.class);
                startActivity(intent);
                break;
            }
            case (R.id.recipe_book): {
                Intent intent = new Intent(this, RecipeBook.class);
                startActivity(intent);
                break;
            }
        }

    }

}
