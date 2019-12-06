//package com.location.android.fridgefu;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.app.AlertDialog;
//import android.app.Dialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.Spinner;
//import android.widget.TextView;
//
package com.location.android.fridgefu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SingleRecipe extends AppCompatActivity {
    ArrayList<Ingredients> ingredientsList = new ArrayList<>();

    HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>> recipes = new HashMap<String, HashMap<String, ArrayList<ArrayList<String>>>>();
    //    HashMap<String, ArrayList<String>> all_recipes_shown = new HashMap<String, ArrayList<String>>();
    HashMap<String, Integer> recipe_pics = new HashMap<String, Integer>();
//    ArrayList<ArrayList<String>> all_recipes_shown_string = new ArrayList<ArrayList<String>>();
//
//    ArrayList<ArrayList<String>> filter_recipes_shown = new ArrayList<ArrayList<String>>();

//    Button del;
//    TextView tvName;

    public class Ingredients {

        public String name;
        public String amount;

        public Ingredients(String name, String amount) {
            this.name = name;
            this.amount = amount;
        }
    }

    public class Steps {

        public String name;
        public String amount;

        public Steps(String name, String amount) {
            this.name = name;
            this.amount = amount;
        }
    }

    public class DeleteIngredientAdapter extends ArrayAdapter<String> {
        private List<String> ingredients;

        public DeleteIngredientAdapter(Context context, List<String> ingredients) {
            super(context, 0, ingredients);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            String food = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.delete_ingredient_recipe_row, parent, false);
            }

            TextView tvName = (TextView) convertView.findViewById(R.id.food);

            tvName.setText(food);
            // Return the completed view to render on screen
            return convertView;
        }
    }

    public class ServingsAdapter extends ArrayAdapter<String> {

        public ServingsAdapter(Context theContext, List<String> objects, int theLayoutResId) {
            super(theContext, theLayoutResId, objects);
        }

        @Override
        public int getCount() {
            // don't display last item. It is used as hint.
            int count = super.getCount();
            return count > 0 ? count - 1 : count;
        }
    }


    public class ExpandIngredientAdapter extends ArrayAdapter<Ingredients> {
        public ExpandIngredientAdapter(Context context, ArrayList<Ingredients> ingredients) {
            super(context, 0, ingredients);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Ingredients ingredient = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingredient, parent, false);
            }
            // Lookup view for data population
            TextView name = (TextView) convertView.findViewById(R.id.name);
            TextView amount = (TextView) convertView.findViewById(R.id.amount);

            // Populate the data into the template view using the data object
            name.setText(ingredient.name);
            amount.setText(ingredient.amount);

            return convertView;
        }
    }

    public class ExpandStepsAdapter extends ArrayAdapter<Steps> {
        public ExpandStepsAdapter(Context context, ArrayList<Steps> steps) {
            super(context, 0, steps);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Steps step = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.step, parent, false);
            }
            // Lookup view for data population
            TextView name = (TextView) convertView.findViewById(R.id.name);
            TextView amount = (TextView) convertView.findViewById(R.id.amount);

            // Populate the data into the template view using the data object
            name.setText(step.name);
            amount.setText(step.amount);

            return convertView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe);

        Intent intent = getIntent();
        String  recipe_name = (String) intent.getSerializableExtra("recipe_name");

        TextView tname = (TextView) findViewById(R.id.name);
        tname.setText(recipe_name);

        makeRecipes();
        HashMap<String, ArrayList<ArrayList<String>>> recipe_info = recipes.get(recipe_name);
        ArrayList<ArrayList<String>> ingredients_info = recipe_info.get("Ingredients");
        for (int i = 0; i < ingredients_info.size(); i++){
            String amount = ingredients_info.get(i).get(1);
            amount =  amount.concat(" " + ingredients_info.get(i).get(2));
            ingredientsList.add(new Ingredients(ingredients_info.get(i).get(0), amount));
        }


        //get List of ingredients and steps

//        ingredientsList.add(new Ingredients("Broccoli", "1 cup"));
//        ingredientsList.add(new Ingredients("cheese", "1 cup"));
//        ingredientsList.add(new Ingredients("Apple", "1 cup"));
//        ingredientsList.add(new Ingredients("cheese", "1 cup"));



        SingleRecipe.ExpandIngredientAdapter adapterIngredients = new SingleRecipe.ExpandIngredientAdapter(this, ingredientsList);

        ListView listView = (ListView) findViewById(R.id.ingredientlist);
        listView.setAdapter(adapterIngredients);

        //SERVINGS
        Spinner spinner = (Spinner) findViewById(R.id.servings);
        List<String> objects = new ArrayList<String>();
        objects.add("     1");
        objects.add("     2");
        objects.add("     3");
        // add hint as last item
        objects.add("Servings");
//        spinner.setOnItemClickListener();
        ServingsAdapter servingsAdapter = new ServingsAdapter(this, objects, android.R.layout.simple_spinner_item);
        servingsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerFilmType = (Spinner) findViewById(R.id.servings);
        spinner.setAdapter(servingsAdapter);
        // show hint
        spinner.setSelection(servingsAdapter.getCount());



        ArrayList<Steps> stepsList = new ArrayList<>();
        ArrayList<ArrayList<String>> steps_info = recipe_info.get("Steps");
        for (int i = 0; i < steps_info.size(); i++){
            String amount = steps_info.get(i).get(1);
            amount =  amount.concat(" "+ steps_info.get(i).get(2));
            stepsList.add(new Steps(steps_info.get(i).get(0), amount));
        }


        //Steps
//        ArrayList<Steps> stepsList = new ArrayList<>();
//        stepsList.add(new Steps("Step 1: cook the past", "40 mins"));
//        stepsList.add(new Steps("Step 2: cook the past", "40 mins"));
//        stepsList.add(new Steps("Step 3: cook the past", "40 mins"));
//        stepsList.add(new Steps("Step 4: cook the past", "40 mins"));
//        stepsList.add(new Steps("Step 5: cook the past", "40 mins"));
//        stepsList.add(new Steps("Step 6: cook the past", "40 mins"));
//        stepsList.add(new Steps("Step 7: cook the past", "40 mins"));
//        stepsList.add(new Steps("Step 8: cook the past", "40 mins"));

        SingleRecipe.ExpandStepsAdapter adapterSteps = new SingleRecipe.ExpandStepsAdapter(this, stepsList);
        ListView listView2 = (ListView) findViewById(R.id.stepslist);
        listView2.setAdapter(adapterSteps);


    }

    /**
     * Look at DeleteIngredientAdapter - sets up the popup
     * delete() set up as onClick inside delete_ingredient_recipe_row.xml
     *
     * @param view
     */

    public void delete(View view){
        LinearLayout vwParentRow = (LinearLayout)view.getParent();

        TextView ingredient = (TextView)vwParentRow.getChildAt(0);
        Button btnChild = (Button)vwParentRow.getChildAt(1);
        btnChild.setClickable(false);

        FridgeContents fridgeContents = FridgeContents.getInstance(new HashMap<String, List<FridgeItem>>());
        fridgeContents.removeIngredient(ingredient.getText().toString());

    }
    public List<String> IngredientToString(){
        List<String> stringIng = new ArrayList<String>();
        for (int i = 0; i < ingredientsList.size(); i++){
            stringIng.add(ingredientsList.get(i).name);
        }
        return stringIng;
    }

    public void completedPopup(View view){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        View rowList = getLayoutInflater().inflate(R.layout.delete_ingredient_recipe_popup, null);
        ListView listView = rowList.findViewById(R.id.removeIngredients);

        List<String> ingredientsStringList = IngredientToString();
        DeleteIngredientAdapter adapter = new DeleteIngredientAdapter(this, ingredientsStringList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        alertDialog.setView(rowList);
        AlertDialog dialog = alertDialog.create();
        dialog.show();
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
        startActivity(intent);
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
        butter.add("Butter");
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

//        ArrayList<String> lasagna = new ArrayList<String>();
//        lasagna.add("LASAGNA");
//        lasagna.add("10-15 minutes cook time");
//        lasagna.add("2000 calories");
//        lasagna.add("8 servings");
//        all_recipes_shown.put("Lasagna", lasagna);
//        ArrayList<String> pbj = new ArrayList<String>();
//        pbj.add("Peanut Butter Jelly Sandwich");
//        pbj.add("2-5 minutes cook time");
//        pbj.add("200 calories");
//        pbj.add("1 servings");
//
//        all_recipes_shown.put("Peanut Butter Jelly Sandwich", pbj);
//        ArrayList<String> grillchs = new ArrayList<String>();
//        grillchs.add("Grilled Cheese");
//        grillchs.add("10-15 minutes cook time");
//        grillchs.add("200 calories");
//        grillchs.add("1 servings");
//        all_recipes_shown.put("Grilled Cheese", grillchs);


        //THESE KEYS MUST BE THE SAME AS THE FIRST ENTRY IN lasagna.add("Grilled Cheese");
        recipe_pics.put("LASAGNA", R.drawable.fridge);
        recipe_pics.put("Peanut Butter Jelly Sandwich", R.drawable.grocery_list);
        recipe_pics.put("Grilled Cheese", R.drawable.settings);


    }
}
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class SingleRecipe extends AppCompatActivity {
//    ArrayList<Ingredients> ingredientsList = new ArrayList<>();
//
////    Button del;
////    TextView tvName;
//
//    public class Ingredients {
//
//        public String name;
//        public String amount;
//
//        public Ingredients(String name, String amount) {
//            this.name = name;
//            this.amount = amount;
//        }
//    }
//
//    public class Steps {
//
//        public String name;
//        public String amount;
//
//        public Steps(String name, String amount) {
//            this.name = name;
//            this.amount = amount;
//        }
//    }
//
//    public class DeleteIngredientAdapter extends ArrayAdapter<String> {
//        private List<String> ingredients;
//
//        public DeleteIngredientAdapter(Context context, List<String> ingredients) {
//            super(context, 0, ingredients);
//        }
//
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            // Get the data item for this position
//            String food = getItem(position);
//            // Check if an existing view is being reused, otherwise inflate the view
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.delete_ingredient_recipe_row, parent, false);
//            }
//
//            TextView tvName = (TextView) convertView.findViewById(R.id.food);
//
//            tvName.setText(food);
//            // Return the completed view to render on screen
//            return convertView;
//        }
//    }
//
//    public class ServingsAdapter extends ArrayAdapter<String> {
//
//        public ServingsAdapter(Context theContext, List<String> objects, int theLayoutResId) {
//            super(theContext, theLayoutResId, objects);
//        }
//
//        @Override
//        public int getCount() {
//            // don't display last item. It is used as hint.
//            int count = super.getCount();
//            return count > 0 ? count - 1 : count;
//        }
//    }
//
//
//    public class ExpandIngredientAdapter extends ArrayAdapter<Ingredients> {
//        public ExpandIngredientAdapter(Context context, ArrayList<Ingredients> ingredients) {
//            super(context, 0, ingredients);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            // Get the data item for this position
//            Ingredients ingredient = getItem(position);
//            // Check if an existing view is being reused, otherwise inflate the view
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingredient, parent, false);
//            }
//            // Lookup view for data population
//            TextView name = (TextView) convertView.findViewById(R.id.name);
//            TextView amount = (TextView) convertView.findViewById(R.id.amount);
//
//            // Populate the data into the template view using the data object
//            name.setText(ingredient.name);
//            amount.setText(ingredient.amount);
//
//            return convertView;
//        }
//    }
//
//    public class ExpandStepsAdapter extends ArrayAdapter<Steps> {
//        public ExpandStepsAdapter(Context context, ArrayList<Steps> steps) {
//            super(context, 0, steps);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            // Get the data item for this position
//            Steps step = getItem(position);
//            // Check if an existing view is being reused, otherwise inflate the view
//            if (convertView == null) {
//                convertView = LayoutInflater.from(getContext()).inflate(R.layout.step, parent, false);
//            }
//            // Lookup view for data population
//            TextView name = (TextView) convertView.findViewById(R.id.name);
//            TextView amount = (TextView) convertView.findViewById(R.id.amount);
//
//            // Populate the data into the template view using the data object
//            name.setText(step.name);
//            amount.setText(step.amount);
//
//            return convertView;
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_single_recipe);
//
//        //get List of ingredients and steps
//
//        ingredientsList.add(new Ingredients("Broccoli", "1 cup"));
//        ingredientsList.add(new Ingredients("cheese", "1 cup"));
//        ingredientsList.add(new Ingredients("Apple", "1 cup"));
//        ingredientsList.add(new Ingredients("cheese", "1 cup"));
//
//
//
//        SingleRecipe.ExpandIngredientAdapter adapterIngredients = new SingleRecipe.ExpandIngredientAdapter(this, ingredientsList);
//
//        ListView listView = (ListView) findViewById(R.id.ingredientlist);
//        listView.setAdapter(adapterIngredients);
//
//        //SERVINGS
//        Spinner spinner = (Spinner) findViewById(R.id.servings);
//        List<String> objects = new ArrayList<String>();
//        objects.add("     1");
//        objects.add("     2");
//        objects.add("     3");
//        // add hint as last item
//        objects.add("Servings");
////        spinner.setOnItemClickListener();
//        ServingsAdapter servingsAdapter = new ServingsAdapter(this, objects, android.R.layout.simple_spinner_item);
//        servingsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        Spinner spinnerFilmType = (Spinner) findViewById(R.id.servings);
//        spinner.setAdapter(servingsAdapter);
//        // show hint
//        spinner.setSelection(servingsAdapter.getCount());
//
//
//        //Steps
//        ArrayList<Steps> stepsList = new ArrayList<>();
//        stepsList.add(new Steps("Step 1: cook the past", "40 mins"));
//        stepsList.add(new Steps("Step 2: cook the past", "40 mins"));
//        stepsList.add(new Steps("Step 3: cook the past", "40 mins"));
//        stepsList.add(new Steps("Step 4: cook the past", "40 mins"));
//        stepsList.add(new Steps("Step 5: cook the past", "40 mins"));
//        stepsList.add(new Steps("Step 6: cook the past", "40 mins"));
//        stepsList.add(new Steps("Step 7: cook the past", "40 mins"));
//        stepsList.add(new Steps("Step 8: cook the past", "40 mins"));
//
//        SingleRecipe.ExpandStepsAdapter adapterSteps = new SingleRecipe.ExpandStepsAdapter(this, stepsList);
//        ListView listView2 = (ListView) findViewById(R.id.stepslist);
//        listView2.setAdapter(adapterSteps);
//
//
//    }
//
//    /**
//     * Look at DeleteIngredientAdapter - sets up the popup
//     * delete() set up as onClick inside delete_ingredient_recipe_row.xml
//     *
//     * @param view
//     */
//
//    public void delete(View view){
//        LinearLayout vwParentRow = (LinearLayout)view.getParent();
//
//        TextView ingredient = (TextView)vwParentRow.getChildAt(0);
//        Button btnChild = (Button)vwParentRow.getChildAt(1);
//        btnChild.setClickable(false);
//
//        FridgeContents fridgeContents = FridgeContents.getInstance(new HashMap<String, List<FridgeItem>>());
//        fridgeContents.removeIngredient(ingredient.getText().toString());
//
//    }
//    public List<String> IngredientToString(){
//        List<String> stringIng = new ArrayList<String>();
//        for (int i = 0; i < ingredientsList.size(); i++){
//            stringIng.add(ingredientsList.get(i).name);
//        }
//        return stringIng;
//    }
//
//    public void completedPopup(View view){
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//        View rowList = getLayoutInflater().inflate(R.layout.delete_ingredient_recipe_popup, null);
//        ListView listView = rowList.findViewById(R.id.removeIngredients);
//
//        List<String> ingredientsStringList = IngredientToString();
//        DeleteIngredientAdapter adapter = new DeleteIngredientAdapter(this, ingredientsStringList);
//        listView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        alertDialog.setView(rowList);
//        AlertDialog dialog = alertDialog.create();
//        dialog.show();
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
//        Intent intent = new Intent(getBaseContext(), RecipeBook.class);
//        startActivity(intent);
//    }
//}