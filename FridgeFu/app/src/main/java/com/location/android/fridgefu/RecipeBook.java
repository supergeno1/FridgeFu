package com.location.android.fridgefu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipeBook extends AppCompatActivity {


    HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>> recipes = new HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>>();
    HashMap<String, ArrayList<String>> all_recipes_shown = new HashMap<String, ArrayList<String>>();
    HashMap<String, Integer> recipe_pics = new HashMap<String, Integer>();
    ArrayList<ArrayList<String>> all_recipes_shown_string = new ArrayList<ArrayList<String>>();

    ArrayList<ArrayList<String>> filter_recipes_shown = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> filter_recipes_shown_final = new ArrayList<ArrayList<String>>();

    ArrayList<ArrayList<String>> filter_recipes_shown_fridge = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> filter_recipes_shown_grocery = new ArrayList<ArrayList<String>>();

    Boolean checkbox1 = false;
    Boolean checkbox2 = false;

    Boolean filtered = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_book);
        makeRecipes();

        Intent intent = getIntent();

        if (intent.hasExtra("filter_ingredients")) {
            ArrayList<String>  list = (ArrayList<String>) intent.getSerializableExtra("filter_ingredients");
            checkbox1 = (Boolean) intent.getSerializableExtra("checkbox1");
            checkbox2 = (Boolean) intent.getSerializableExtra("checkbox2");
            Log.e("CHECKBOX", checkbox1.toString());
            filterRecipes(list, true);
            filterGroceryRecipes(checkbox2);
            filterFridgeRecipes(checkbox1);
            merge_filters();
            filtered = true;
        }

        else {
            ArrayList<String> lol = new ArrayList<String>();
            filterRecipes(lol, false);
            filterGroceryRecipes(checkbox2);
            filterFridgeRecipes(checkbox1);
            merge_filters();

        }

        final RecipeBook.ExpandRecipeFilterAdapter adapterFilterRecipes = new RecipeBook.ExpandRecipeFilterAdapter(this, filter_recipes_shown_final);

        ListView listView = (ListView) findViewById(R.id.each_recipe);
        listView.setAdapter(adapterFilterRecipes);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<String> clickedItem = (ArrayList<String>) adapterFilterRecipes.getItem(i);
                Intent intent = new Intent(getBaseContext(), SingleRecipe.class);
                intent.putExtra("recipe_name", clickedItem.get(0));
                startActivity(intent);

            }
        });


    }


    public class ExpandRecipeFilterAdapter extends ArrayAdapter<ArrayList<String>> {
        public ExpandRecipeFilterAdapter(Context context, ArrayList<ArrayList<String>> shown) {
            super(context, 0, shown);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            ArrayList<String> recipe_detail = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.recipe_row, parent, false);
            }
            // Lookup view for data population
            TextView name = (TextView) convertView.findViewById(R.id.recipe_name);
            name.setText(recipe_detail.get(0));
            TextView cook = (TextView) convertView.findViewById(R.id.recipe_cooktime);
            cook.setText(recipe_detail.get(1));

            TextView cal = (TextView) convertView.findViewById(R.id.recipe_calories);
            cal.setText(recipe_detail.get(2));

            TextView serv = (TextView) convertView.findViewById(R.id.recipe_servings);
            serv.setText(recipe_detail.get(3));




//            ImageView imageView = (ImageView)  convertView.findViewById(R.id.food_pic);
//            imageView.setImageResource(recipe_pics.get(recipe_detail.get(0)));



            return convertView;
        }
    }



    public void go_single_recipe(View view){

        Intent intent = new Intent(getBaseContext(), SingleRecipe.class);
//        intent.putExtra("recipe_name", recipe_name);

        startActivity(intent);

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
        return;
    }

    public void to_filter (View view){
        Intent intent = new Intent(getBaseContext(), FilterRecipe.class);
        startActivity(intent);
    }

    public void merge_filters(){
        Object [] recipe_names = recipes.keySet().toArray();
        for (int i = 0 ; i  < recipe_names.length; i++){
            boolean matched = false;
            String match = recipe_names[i].toString();

            if (filter_recipes_shown_grocery.size() == 0 || filter_recipes_shown.size() == 0 || filter_recipes_shown_fridge.size() == 0){
                break;
            }

            for (int j = 0; j < filter_recipes_shown_grocery.size(); j++ ){
                if (filter_recipes_shown_grocery.get(j).get(0).equals(match)){
                    matched = true;
                    break;
                }
                matched = false;
            }
            Log.e("merger", new Boolean(matched).toString());

            if (matched == false) {
                Log.e("merger", new Boolean(matched).toString());
                break;
            }
            for (int j = 0; j < filter_recipes_shown.size(); j++ ){
                if (filter_recipes_shown.get(j).get(0).equals(match)){
                    matched = true;
                    break;
                }
                matched = false;
            }
            if (matched == false) {
                break;
            }

            for (int j = 0; j < filter_recipes_shown_fridge.size(); j++ ){
                if (filter_recipes_shown_fridge.get(j).get(0).equals(match)){
                    Log.e("IDEK", match);

                    matched = true;
                    break;
                }
                matched = false;
            }
            Log.e("merger before fridge", new Boolean(matched).toString());

            if (matched == false) {
                Log.e("IDEKCHECKMATCH", new Boolean(matched).toString());
                break;
            }
            else{
                filter_recipes_shown_final.add(all_recipes_shown.get(recipe_names[i]));
            }
        }
        return;

    }

    public void filterGroceryRecipes(Boolean filter){
        if (!filter) {
            Object [] recipe_names = recipes.keySet().toArray();
            for (int i = 0 ; i  < recipe_names.length; i++){
                filter_recipes_shown_grocery.add(all_recipes_shown.get(recipe_names[i]));
            }
            return;
        }
        HashMap<String, List<GroceryItem>> empy = new HashMap<String, List<GroceryItem>>();
        GroceryContents groceryContents = GroceryContents.getInstance(empy);
        List <String> groceryIngredients = groceryContents.allIngredients();

        Object [] recipe_names = recipes.keySet().toArray();

        for (int i = 0 ; i  < recipe_names.length; i++) {
            ArrayList<ArrayList<String>> foods = recipes.get(recipe_names[i]).get("Ingredients");
            boolean foundAll = false;
            for (int j = 0; j < foods.size(); j++){
                boolean found = false;
                for (int k = 0; k < groceryIngredients.size(); k++){
                    if (groceryIngredients.get(k).toLowerCase().equals(foods.get(j).get(0).toLowerCase())){
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    foundAll = false;
                    break;
                }else{
                    foundAll = true;
                }
            }
            if (foundAll) {
                filter_recipes_shown_grocery.add(all_recipes_shown.get(recipe_names[i]));
            }
        }

    }

    public void filterFridgeRecipes(Boolean filter){
        Log.e("HELO", filter.toString());

        if (!filter) {
            Object [] recipe_names = recipes.keySet().toArray();
            for (int i = 0 ; i  < recipe_names.length; i++){
                filter_recipes_shown_fridge.add(all_recipes_shown.get(recipe_names[i]));
            }
            return;
        }
        HashMap<String, List<FridgeItem>> empy = new HashMap<String, List<FridgeItem>>();
        FridgeContents fridgeContents = FridgeContents.getInstance(empy);
        List <String> fridgeIngredients = fridgeContents.allIngredients();

        Object [] recipe_names = recipes.keySet().toArray();

        for (int i = 0 ; i  < recipe_names.length; i++) {
            ArrayList<ArrayList<String>> foods = recipes.get(recipe_names[i]).get("Ingredients");
            boolean foundAll = false;
            for (int j = 0; j < foods.size(); j++){
                boolean found = false;
                for (int k = 0; k < fridgeIngredients.size(); k++){
                    if (fridgeIngredients.get(k).toLowerCase().equals(foods.get(j).get(0).toLowerCase())){
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    foundAll = false;
                    break;
                }else{
                    foundAll = true;
                }
            }
            if (foundAll) {
                filter_recipes_shown_fridge.add(all_recipes_shown.get(recipe_names[i]));
            }
        }

        for (int i = 0; i < filter_recipes_shown_fridge.size(); i++){
            Log.e("CHECKFRIDGEFILTER", filter_recipes_shown_fridge.get(i).get(0));
        }

    }

    public void filterRecipes(ArrayList<String>  requiredIngredients, boolean filter){

        if (filter && (requiredIngredients.size() == 0)){
            filter = false;
        }

        if (filter) {
            Object [] recipe_names = recipes.keySet().toArray();

            for (int i = 0 ; i  < recipe_names.length; i++){
                ArrayList<ArrayList<String>> foods = recipes.get(recipe_names[i]).get("Ingredients");

                boolean findAll = false;
                for (int j = 0; j < requiredIngredients.size(); j++){

                    boolean found = false;
                    for (int k = 0; k < foods.size(); k++) {
                        Log.e(requiredIngredients.get(j), foods.get(k).get(0));
                        if (requiredIngredients.get(j).toLowerCase().equals(foods.get(k).get(0).toLowerCase())){
                            Log.e("filter", "found" );
                            found = true;
                            break;
                        }else{
                            found = false;
                        }
                    }
                    if (!found){
                        findAll = false;
                        break;
                    }
                    else{
                        findAll = true;
                    }
                }
                if (!findAll) {
                    continue;
                } else {
                    filter_recipes_shown.add(all_recipes_shown.get(recipe_names[i]));
                }
            }
        }
        else {
            Object [] recipe_names = recipes.keySet().toArray();
            for (int i = 0 ; i  < recipe_names.length; i++){
                filter_recipes_shown.add(all_recipes_shown.get(recipe_names[i]));
            }
        }

    }

    public void makeRecipes(){
        HashMap<String, ArrayList<ArrayList<String>>> val1 = new HashMap<String, ArrayList<ArrayList<String>>>();
        ArrayList<ArrayList<String>> val11 = new  ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> val12 = new  ArrayList<ArrayList<String>>();

        ArrayList<String> tomato = new ArrayList<String>();
        tomato.add("Tomato Sauce");
        tomato.add("4");
        tomato.add("Cups");

        ArrayList<String> pasta = new ArrayList<String>();
        pasta.add("Pasta");
        pasta.add("7");
        pasta.add("Cups");

        ArrayList<String> cheese = new ArrayList<String>();
        cheese.add("Cheese");
        cheese.add("5");
        cheese.add("Cups");

        ArrayList<String> spinach = new ArrayList<String>();
        spinach.add("Spinach");
        spinach.add("4");
        spinach.add("Cups");

        val11.add(tomato);
        val11.add(pasta);
        val11.add(cheese);
        val11.add(spinach);

        ArrayList<String> cut = new ArrayList<String>();
        cut.add("Place oven ready pasta in pan, layer with sauce in cheese");
        cut.add("20");
        cut.add("Mins");

        ArrayList<String> bake = new ArrayList<String>();
        bake.add("Bake lasagna at 300F");
        bake.add("30");
        bake.add("Mins");

        ArrayList<String> cool = new ArrayList<String>();
        cool.add("Let lasagna cool before eating");
        cool.add("10");
        cool.add("Mins");


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
        bread.add("Wheat Bread");
        bread.add("2");
        bread.add("Slices");

        ArrayList<String> Peanut_Butter = new ArrayList<String>();
        Peanut_Butter.add("Peanut Butter");
        Peanut_Butter.add("3");
        Peanut_Butter.add("Tablespoons");

        ArrayList<String> jelly = new ArrayList<String>();
        jelly.add("Jelly");
        jelly.add("3");
        jelly.add("Tablespoons");


        val21.add(bread);
        val21.add(Peanut_Butter);
        val21.add(jelly);

        ArrayList<String> spread = new ArrayList<String>();
        spread.add("Spread peanut on one slice of bread, spread jelly on the other half");
        spread.add("5");
        spread.add("Mins");

        ArrayList<String> assemble = new ArrayList<String>();
        assemble.add("Assemble bread slices together");
        assemble.add("1");
        assemble.add("Mins");

        ArrayList<String> slice = new ArrayList<String>();
        slice.add("Cut sandwich in half corner to corner");
        slice.add("1");
        slice.add("Mins");


        val22.add(spread);
        val22.add(assemble);
        val22.add(slice);

        val2.put("Ingredients", val21);
        val2.put("Steps", val22);
        recipes.put("Peanut Butter Jelly Sandwich", val2);

        HashMap<String, ArrayList<ArrayList<String>>> val3 = new HashMap<String, ArrayList<ArrayList<String>>>();
        ArrayList<ArrayList<String>> val31 = new  ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> val32 = new  ArrayList<ArrayList<String>>();

        ArrayList<String> bread2 = new ArrayList<String>();
        bread2.add("Wheat Bread");
        bread2.add("2");
        bread2.add("Slices");

        ArrayList<String> cheese2 = new ArrayList<String>();
        cheese2.add("Cheese");
        cheese2.add("1");
        cheese2.add("Slice");

        ArrayList<String> butter = new ArrayList<String>();
        butter.add("butter");
        butter.add("1");
        butter.add("Tablespoons");


        val31.add(bread2);
        val31.add(cheese2);
        val31.add(butter);

        ArrayList<String> spread2 = new ArrayList<String>();
        spread2.add("Spread butter onto both slices of bread and place on pan on low - medium heat");
        spread2.add("2");
        spread2.add("Mins");

        ArrayList<String> assemble2 = new ArrayList<String>();
        assemble2.add("Place cheese slice on bread and close sandwich on pan, flip sandwich every 2 mins");
        assemble2.add("6");
        assemble2.add("Mins");

        ArrayList<String> slice2 = new ArrayList<String>();
        slice2.add("Cut sandwich in half corner to corner");
        slice2.add("1");
        slice2.add("Mins");


        val32.add(spread2);
        val32.add(assemble2);
        val32.add(slice2);

        val3.put("Ingredients", val31);
        val3.put("Steps", val32);
        recipes.put("Grilled Cheese", val3);

        ArrayList<String> lasagna = new ArrayList<String>();
        lasagna.add("Lasagna");
        lasagna.add("10-15 minutes cook time");
        lasagna.add("2000 calories");
        lasagna.add("8 servings");
        all_recipes_shown.put("Lasagna", lasagna);
        ArrayList<String> pbj = new ArrayList<String>();
        pbj.add("Peanut Butter Jelly Sandwich");
        pbj.add("2-5 minutes cook time");
        pbj.add("200 calories");
        pbj.add("1 servings");

        all_recipes_shown.put("Peanut Butter Jelly Sandwich", pbj);
        ArrayList<String> grillchs = new ArrayList<String>();
        grillchs.add("Grilled Cheese");
        grillchs.add("10-15 minutes cook time");
        grillchs.add("200 calories");
        grillchs.add("1 servings");
        all_recipes_shown.put("Grilled Cheese", grillchs);


        //THESE KEYS MUST BE THE SAME AS THE FIRST ENTRY IN lasagna.add("Grilled Cheese");
        recipe_pics.put("Lasagna", R.drawable.fridge);
        recipe_pics.put("Peanut Butter Jelly Sandwich", R.drawable.grocery_list);
        recipe_pics.put("Grilled Cheese", R.drawable.settings);


    }

}

//package com.location.android.fridgefu;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//
//public class RecipeBook extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recipe_book);
//
//    }
//
//    public void go_single_recipe(View view){
//        Intent intent = new Intent(getBaseContext(), SingleRecipe.class);
//        startActivity(intent);
//
//    }
//
//    public void to_fridge(View view){
//        Intent intent = new Intent(getBaseContext(), FridgeLanding.class);
//        startActivity(intent);
//    }
//
//    public void to_setting(View view){
//        Intent intent = new Intent(getBaseContext(), SettingsLanding.class);
//        startActivity(intent);
//    }
//
//    public void to_grocery_list(View view){
//        Intent intent = new Intent(getBaseContext(), GroceryLanding.class);
//        startActivity(intent);
//
//    }
//
//    public void to_recipe_book(View view){
//
//        return;
//
//    }
//
//}