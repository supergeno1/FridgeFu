package com.location.android.fridgefu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.LruCacheKt;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FridgeLanding extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<FridgeItem>> expandableListDetail;
    Boolean fridgeFilled = false;
    FridgeContents fridgeContents;
    FloatingActionButton fab;
    HashMap<String, String> mappedIngredientToGroup;
    GroceryContents groceryContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge_landing);
        mappedIngredientToGroup = IngredientToGroup.getData();
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        fridgeContents = fridgeContents.getInstance(ExpandableListDataPump.getData());
//        if (!fridgeFilled) {
//            Log.e("FRIDGE", "making form dump");
//            expandableListDetail = ExpandableListDataPump.getData();
//        }
//        else {
//            fridgeContents = FridgeContents.getInstance(expandableListDetail);
//            Log.e("FRIDGE", "from global");
//
//            expandableListDetail = fridgeContents.globalFridgeHash;
//        }

        expandableListDetail = fridgeContents.globalFridgeHash;
        Calendar exd = GregorianCalendar.getInstance();
        exd.setTime(new Date());
        exd.add(Calendar.DAY_OF_YEAR, 2);
        Long from_now = exd.getTimeInMillis();

        Iterator it = expandableListDetail.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            List<FridgeItem> gl = (List<FridgeItem>) pair.getValue();
            Iterator glit = gl.iterator();
            while (glit.hasNext()) {
                FridgeItem glpair = (FridgeItem) glit.next();
                Long exp_time = glpair.expiration_date.getTimeInMillis();
                boolean show_expire = (exp_time - from_now) <= 0;
                glpair.is_expired = show_expire;
            }
        }





        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = (ExpandableListAdapter) new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
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
                View vxl = v.findViewById(R.id.fridgeInnerLinearLayout);
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

        if (!fridgeFilled) {
            Log.e("FRIDGE", "from global hwere");
            fridgeContents = FridgeContents.getInstance(expandableListDetail);
            expandableListDetail = fridgeContents.globalFridgeHash;
            fridgeFilled = true;
        }



        fab = (FloatingActionButton) findViewById(R.id.FAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // does nothing hahhaha lolololol FUCK
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(FridgeLanding.this);
                final View popUp = getLayoutInflater().inflate(R.layout.new_item_pop_up, null);
                CheckBox dfck = popUp.findViewById(R.id.dfck);
                CheckBox csck = popUp.findViewById(R.id.csck);

                dfck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        CheckBox csck = popUp.findViewById(R.id.csck);
                        csck.setChecked(!isChecked);
                    }
                });

                csck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        CheckBox dfck = popUp.findViewById(R.id.dfck);
                        dfck.setChecked(!isChecked);
                    }
                });

                final EditText name_text = popUp.findViewById(R.id.ing_name);
                final TextView exp_date = popUp.findViewById(R.id.exp_date);

                exp_date.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Calendar now = Calendar.getInstance();
                            final Calendar c = Calendar.getInstance();

                            DatePickerDialog dpd = new DatePickerDialog(popUp.getContext(),
                                    new DatePickerDialog.OnDateSetListener() {

                                        @Override
                                        public void onDateSet(DatePicker view, int year,
                                                              int monthOfYear, int dayOfMonth) {
                                            exp_date.setText(dayOfMonth + "-"
                                                    + (monthOfYear + 1) + "-" + year);
                                        }
                                    }, c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE));
                            dpd.show();
                        }
                    }
                );


                alertDialog
                        .setCancelable(false)
                        .setPositiveButton("Add Ingredient",new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick (DialogInterface dialog,int id){
                                                Toast.makeText(FridgeLanding.this, " New ingredient added! \n ", Toast.LENGTH_LONG).show();
                                                String group = mappedIngredientToGroup.get(name_text.getText().toString().toLowerCase());
                                                String[] arrOfStr = ((String)exp_date.getText()).split("-");
                                                for (String i : arrOfStr) {
                                                    Log.e("EXP", i);
                                                }
                                                Log.e("GROUP", group);
                                                Calendar exd = GregorianCalendar.getInstance();
                                                exd.setTime(new Date());
                                                exd.add(Calendar.DAY_OF_YEAR, 7);;
                                                FridgeItem to_add = new FridgeItem(name_text.getText().toString(), exd.get(Calendar.YEAR), exd.get(Calendar.MONTH), exd.get(Calendar.DAY_OF_MONTH) );
                                                CheckBox csck = popUp.findViewById(R.id.csck);
                                                if (csck.isChecked()) { to_add= new FridgeItem(name_text.getText().toString(), Integer.parseInt(arrOfStr[2]), Integer.parseInt(arrOfStr[1]), Integer.parseInt(arrOfStr[0]));}
                                                expandableListDetail.get(group).add(to_add);

                                                groceryContents = groceryContents.getInstance(GroceryListDataPump.getData());
                                                HashMap<String, List<GroceryItem>> gexpandableListDetail = groceryContents.globalGroceryHash;
                                                CheckBox add_to_default = popUp.findViewById(R.id.add_to_default);
                                                if (add_to_default.isChecked()) {
                                                    GroceryItem gto_add = new GroceryItem(name_text.getText().toString(), true);
                                                    gexpandableListDetail.get(group).add(gto_add);
                                                }
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


//        Log.e("SIZE", fridgeContents.globalFridgeHash.get(fridgeContents.globalFridgeHash.keySet().toArray()[0]).get(0).ingredient);

    }

    public void to_fridge(View view){
        return;
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

        Intent intent = new Intent(getBaseContext(), RecipeBook.class);
        startActivity(intent);

    }

}
