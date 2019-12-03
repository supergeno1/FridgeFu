package com.location.android.fridgefu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;

public class GroceryLanding extends AppCompatActivity implements View.OnClickListener {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_landing);

        ImageButton gl = (ImageButton) findViewById(R.id.grocery_list_button);
        gl.setOnClickListener(this);

        ImageButton f = (ImageButton) findViewById(R.id.fridge_button);
        f.setOnClickListener(this);

        ImageButton rb = (ImageButton) findViewById(R.id.recipe_book_button);
        rb.setOnClickListener(this);

        ImageButton s = (ImageButton) findViewById(R.id.settings_button);
        s.setOnClickListener(this);


        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = GroceryListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
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
