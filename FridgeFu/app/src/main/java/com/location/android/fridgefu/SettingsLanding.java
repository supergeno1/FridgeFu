package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class SettingsLanding extends AppCompatActivity implements View.OnClickListener {

    private Button list, fridge, add, help, about;
    private ImageButton groceryListMenu, fridgeMenu, recipeBookMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        list = findViewById(R.id.list);
        fridge = findViewById(R.id.fridge);
        add = findViewById(R.id.add);
        help = findViewById(R.id.help);
        about = findViewById(R.id.about);

        groceryListMenu = findViewById(R.id.grocery_list);
        fridgeMenu = findViewById(R.id.fridge_menu);
        recipeBookMenu = findViewById(R.id.recipe_book);

        list.setOnClickListener(this);
        fridge.setOnClickListener(this);
        add.setOnClickListener(this);
        help.setOnClickListener(this);
        about.setOnClickListener(this);

        groceryListMenu.setOnClickListener(this);
        fridgeMenu.setOnClickListener(this);
        recipeBookMenu.setOnClickListener(this);

    }

    public void onClick(View view) {

        switch (view.getId()) {

            case (R.id.list): {
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("Are you sure you want to clear your grocery list?");
                b.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                b.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog a = b.create();
                a.show();
                break;
            }
            case (R.id.fridge): {
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("Are you sure you want to clear your fridge?");
                b.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                b.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog a = b.create();
                a.show();
                break;
            }
            case (R.id.add): {
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("Add a new ingredient to your default grocery list.");
                EditText input = new EditText(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                b.setView(input);
                b.setPositiveButton(
                        "Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                b.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog a = b.create();
                a.show();
                break;
            }
            case (R.id.help): {
                Intent intent = new Intent(this, SettingsHelp.class);
                startActivity(intent);
                break;
            }
            case (R.id.about): {
                Intent intent = new Intent(this, SettingsAbout.class);
                startActivity(intent);
                break;
            }
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
