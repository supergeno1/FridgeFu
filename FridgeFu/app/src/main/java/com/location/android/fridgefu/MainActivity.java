package com.location.android.fridgefu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FridgeContents fridgeContents = FridgeContents.getInstance(ExpandableListDataPump.getData());
        GroceryContents groceryContents = GroceryContents.getInstance(GroceryListDataPump.getData());
        Intent a = new Intent(this, FridgeLanding.class);
        startActivity(a);
    }
}
