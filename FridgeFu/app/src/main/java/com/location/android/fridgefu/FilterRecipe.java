package com.location.android.fridgefu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FilterRecipe extends AppCompatActivity {

    ArrayList<String> searchedIngredients = new ArrayList<String>();
    HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>> recipes = new HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>>();


    public class SearchIngredientAdapter extends ArrayAdapter<String> {
        private List<String> ingredients;

        public SearchIngredientAdapter(Context context, List<String> ingredients) {
            super(context, 0, ingredients);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            String food = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.filter_row, parent, false);
            }

            TextView tvName = (TextView) convertView.findViewById(R.id.search_name);

            tvName.setText(food);
            // Return the completed view to render on screen
            return convertView;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_filter);

        HashMap<String, ArrayList<ArrayList<String>>> val1 = new HashMap<String, ArrayList<ArrayList<String>>>();
        ArrayList<ArrayList<String>> val11 = new  ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> val12 = new  ArrayList<ArrayList<String>>();

        ArrayList<String> tomato = new ArrayList<String>();
        tomato.add("Tomato Sauce");
        tomato.add("4");
        tomato.add("Cups");

        ArrayList<String> pasta = new ArrayList<String>();
        tomato.add("Pasta");
        tomato.add("7");
        tomato.add("Cups");

        ArrayList<String> cheese = new ArrayList<String>();
        tomato.add("Cheese");
        tomato.add("5");
        tomato.add("Cups");

        ArrayList<String> spinach = new ArrayList<String>();
        tomato.add("Spinach");
        tomato.add("4");
        tomato.add("Cups");

        val11.add(tomato);
        val11.add(pasta);
        val11.add(cheese);
        val11.add(spinach);

        ArrayList<String> cut = new ArrayList<String>();
        tomato.add("Place oven ready pasta in pan, layer with sauce in cheese");
        tomato.add("20");
        tomato.add("Mins");

        ArrayList<String> bake = new ArrayList<String>();
        tomato.add("Bake lasagna at 300F");
        tomato.add("30");
        tomato.add("Mins");

        ArrayList<String> cool = new ArrayList<String>();
        tomato.add("Let lasagna cool before eating");
        tomato.add("10");
        tomato.add("Mins");


        val12.add(cut);
        val12.add(bake);
        val12.add(cool);

        val1.put("Ingredients", val11);
        val1.put("Steps", val12);
        recipes.put("Lasagna", val1);



        HashMap<String, ArrayList<ArrayList<String>>> val2 = new HashMap<String, ArrayList<ArrayList<String>>>();
        ArrayList<ArrayList<String>> val21 = new  ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> val22 = new  ArrayList<ArrayList<String>>();


        ArrayList<String> bread = new ArrayList<String>();
        tomato.add("Wheat Bread");
        tomato.add("2");
        tomato.add("Slices");

        ArrayList<String> Peanut_Butter = new ArrayList<String>();
        tomato.add("Peanut Butter");
        tomato.add("3");
        tomato.add("Tablespoons");

        ArrayList<String> jelly = new ArrayList<String>();
        tomato.add("Jelly");
        tomato.add("3");
        tomato.add("Tablespoons");


        val21.add(bread);
        val21.add(Peanut_Butter);
        val21.add(jelly);

        ArrayList<String> spread = new ArrayList<String>();
        tomato.add("Spread peanut on one slice of bread, spread jelly on the other half");
        tomato.add("5");
        tomato.add("Mins");

        ArrayList<String> assemble = new ArrayList<String>();
        tomato.add("Assemble bread slices together");
        tomato.add("1");
        tomato.add("Mins");

        ArrayList<String> slice = new ArrayList<String>();
        tomato.add("Cut sandwich in half corner to corner");
        tomato.add("1");
        tomato.add("Mins");


        val22.add(spread);
        val22.add(assemble);
        val22.add(slice);

        val2.put("Ingredients", val21);
        val2.put("Steps", val22);
        recipes.put("Grilled Cheese", val2);

        HashMap<String, ArrayList<ArrayList<String>>> val3 = new HashMap<String, ArrayList<ArrayList<String>>>();
        ArrayList<ArrayList<String>> val31 = new  ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> val32 = new  ArrayList<ArrayList<String>>();







    }

    public void remove_from_filter(View view){
//        LinearLayout vwParentRow = (LinearLayout)view.getParent();
//
//        TextView ingredient = (TextView)vwParentRow.getChildAt(0);
//        Button btnChild = (Button)vwParentRow.getChildAt(1);
//        searchedIngredients.remove(ingredient.getText());
//        ListView listView = (ListView) findViewById(R.id.filter_options);
//        FilterRecipe.SearchIngredientAdapter adapter = new FilterRecipe.SearchIngredientAdapter(this, searchedIngredients);
//        listView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();


    }
    public void search(View view){
//        EditText editText = (EditText)findViewById(R.id.search_text);
//        String search_field = String.valueOf(editText.getText());
//        searchedIngredients.add(search_field);
//        ListView listView = (ListView) findViewById(R.id.filter_options);
//        FilterRecipe.SearchIngredientAdapter adapter = new FilterRecipe.SearchIngredientAdapter(this, searchedIngredients);
//        listView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

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
        Intent intent = new Intent(getBaseContext(), GroceryLanding.class);
        startActivity(intent);

    }

    public void to_recipe_book(View view){
        Intent intent = new Intent(getBaseContext(), RecipeBook.class);
        intent.putExtra("filter_ingredients", (Serializable) searchedIngredients);

//        intent.putStringArrayListExtra("filter_ingredients", (ArrayList<String>) searchedIngredients);
        startActivity(intent);
    }
}
