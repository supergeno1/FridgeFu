package com.location.android.fridgefu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FridgeLanding extends AppCompatActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<FridgeItem>> expandableListDetail;
    Boolean fridgeFilled = false;
    FridgeContents fridgeContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge_landing);
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
