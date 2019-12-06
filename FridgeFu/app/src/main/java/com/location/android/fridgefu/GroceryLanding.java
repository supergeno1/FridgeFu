package com.location.android.fridgefu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class GroceryLanding extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<GroceryItem>> expandableListDetail;
    Boolean groceryFilled = false;
    GroceryContents groceryContents;
    FloatingActionButton fab;
    HashMap<String, String> mappedIngredientToGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_landing);
        mappedIngredientToGroup = IngredientToGroup.getData();
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        groceryContents = groceryContents.getInstance(GroceryListDataPump.getData());
        expandableListDetail = groceryContents.globalGroceryHash;

        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new GroceryExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                View vxl = v.findViewById(R.id.groceryInnerLinearLayout);
                LinearLayout.LayoutParams p = (LinearLayout.LayoutParams) vxl.getLayoutParams();

                expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition).show_settings = !expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition).show_settings;

                float dip = 140f;

                if (!expandableListDetail.get(
                        expandableListTitle.get(groupPosition)).get(
                        childPosition).show_settings) {

                    dip = 0f;
                }

                Resources r = getResources();
                float px = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        dip,
                        r.getDisplayMetrics()
                );
                p.setMargins(p.leftMargin, p.topMargin, (int)(px), p.bottomMargin);
                vxl.setLayoutParams(p);


                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });

        if (!groceryFilled) {
            Log.e("Grocery", "from global where");
            groceryContents = GroceryContents.getInstance(expandableListDetail);
            expandableListDetail = groceryContents.globalGroceryHash;
            groceryFilled = true;
        }

        fab = (FloatingActionButton) findViewById(R.id.FAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // does nothing hahhaha lolololol FUCK
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(GroceryLanding.this);
                final View popUp = getLayoutInflater().inflate(R.layout.new_shopping_pop_up, null);

                final EditText name_text = popUp.findViewById(R.id.ing_name);

                alertDialog
                        .setCancelable(false)
                        .setPositiveButton("Add Ingredient",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick (DialogInterface dialog,int id){
                                Toast.makeText(GroceryLanding.this, " New ingredient added! \n ", Toast.LENGTH_LONG).show();
                                String group = mappedIngredientToGroup.get(name_text.getText().toString().toLowerCase());
                                GroceryItem to_add = new GroceryItem(name_text.getText().toString());
                                CheckBox add_to_default = popUp.findViewById(R.id.add_to_default);
                                if (add_to_default.isChecked()) {
                                    Log.e("CHECKED", "YESS");
                                    to_add = new GroceryItem(name_text.getText().toString(), true);
                                }

                                expandableListDetail.get(group).add(to_add);
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick (DialogInterface dialog,int id){
                                        dialog.cancel();
                                    }
                                });



                alertDialog.setView(popUp);
                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        });

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
        return;
    }

    public void to_recipe_book(View view){
        Intent intent = new Intent(getBaseContext(), RecipeBook.class);
        startActivity(intent);
    }
}
