package com.location.android.fridgefu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SettingsLanding extends AppCompatActivity implements View.OnClickListener {

    private Button list, fridge, help, about;
    private ImageButton groceryListMenu, fridgeMenu, recipeBookMenu;
    GroceryContents groceryContents;
    FridgeContents fridgeContents;
    HashMap<String, List<FridgeItem>> fexpandableListDetail;
    HashMap<String, List<GroceryItem>> gexpandableListDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        list = findViewById(R.id.list);
        fridge = findViewById(R.id.fridge);
        help = findViewById(R.id.help);
        about = findViewById(R.id.about);

        groceryListMenu = findViewById(R.id.grocery_list);
        fridgeMenu = findViewById(R.id.fridge_menu);
        recipeBookMenu = findViewById(R.id.recipe_book);

        list.setOnClickListener(this);
        fridge.setOnClickListener(this);
        help.setOnClickListener(this);
        about.setOnClickListener(this);

        groceryListMenu.setOnClickListener(this);
        fridgeMenu.setOnClickListener(this);
        recipeBookMenu.setOnClickListener(this);

        groceryContents = groceryContents.getInstance(GroceryListDataPump.getData());
        gexpandableListDetail = groceryContents.globalGroceryHash;
        fridgeContents = fridgeContents.getInstance(ExpandableListDataPump.getData());
        fexpandableListDetail = fridgeContents.globalFridgeHash;

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
                                groceryContents = groceryContents.getInstance(GroceryListDataPump.getData());
                                gexpandableListDetail = groceryContents.globalGroceryHash;
                                Iterator it = gexpandableListDetail.entrySet().iterator();
                                while (it.hasNext()) {
                                    HashMap.Entry pair = (HashMap.Entry)it.next();
                                    List<GroceryItem> gl = (List<GroceryItem>) pair.getValue();
                                    Iterator glit = gl.iterator();
                                    while (glit.hasNext()) {
                                        GroceryItem glpair = (GroceryItem) glit.next();
                                        if (!glpair.is_pinned) {
                                            glit.remove(); // avoids a ConcurrentModificationException
                                        }
                                    }

                                }
//                                groceryContents.globalGroceryHash = gexpandableListDetail;
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
                                fridgeContents = fridgeContents.getInstance(ExpandableListDataPump.getData());
                                Log.e("F", "Entry");
                                fexpandableListDetail = fridgeContents.globalFridgeHash;
                                Iterator it = fexpandableListDetail.entrySet().iterator();
                                while (it.hasNext()) {
                                    HashMap.Entry pair = (HashMap.Entry)it.next();
                                    List<FridgeItem> gl = (List<FridgeItem>) pair.getValue();
                                    Log.e("F", (String)pair.getKey());
                                    gl.clear();
                                }
//                                fridgeContents.globalFridgeHash = fexpandableListDetail;
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
