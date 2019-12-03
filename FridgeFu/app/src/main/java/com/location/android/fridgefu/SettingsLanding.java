package com.location.android.fridgefu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;

public class SettingsLanding extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button gl = (Button) findViewById(R.id.grocery_list_button);
        gl.setOnClickListener(this);

        Button f = (Button) findViewById(R.id.fridge_button);
        f.setOnClickListener(this);

        Button rb = (Button) findViewById(R.id.recipe_book_button);
        rb.setOnClickListener(this);

        Button s = (Button) findViewById(R.id.settings_button);
        s.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.grocery_list_button) {
            Intent intent = new Intent(this, GroceryLanding.class);
            startActivity(intent);
        } else if (v.getId() == R.id.fridge_button) {
            Intent intent = new Intent(this, FridgeLanding.class);
            startActivity(intent);
        } else if (v.getId() == R.id.recipe_book_button) {
            Intent intent = new Intent(this, RecipeBook.class);
            startActivity(intent);
        } else if (v.getId() == R.id.settings_button) {
            Intent intent = new Intent(this, SettingsLanding.class);
            startActivity(intent);
        }
    }
}
