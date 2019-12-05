package com.location.android.fridgefu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button list, fridge, add, help, about;
    private ImageButton groceryListMenu, fridgeMenu, recipeBookMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                final AlertDialog.Builder b = new AlertDialog.Builder(this);
                final EditText input = new EditText(this);
                input.setSingleLine();
                FrameLayout container = new FrameLayout(this);
                FrameLayout.LayoutParams params = new  FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.leftMargin = 50;
                params.rightMargin = 50;
                input.setLayoutParams(params);
                container.addView(input);
                b.setMessage("Add a new ingredient to your default grocery list.");
                b.setView(container);
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
                Intent intent = new Intent(this, Settingshelp.class);
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

// OLD VERSION
/*package com.location.android.fridgefu;

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






}*/
