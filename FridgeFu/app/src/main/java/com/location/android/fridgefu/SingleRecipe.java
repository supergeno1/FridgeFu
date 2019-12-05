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

        //get List of ingredients and steps

        ingredientsList.add(new Ingredients("Broccoli", "1 cup"));
        ingredientsList.add(new Ingredients("cheese", "1 cup"));
        ingredientsList.add(new Ingredients("Apple", "1 cup"));
        ingredientsList.add(new Ingredients("cheese", "1 cup"));



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


        //Steps
        ArrayList<Steps> stepsList = new ArrayList<>();
        stepsList.add(new Steps("Step 1: cook the past", "40 mins"));
        stepsList.add(new Steps("Step 2: cook the past", "40 mins"));
        stepsList.add(new Steps("Step 3: cook the past", "40 mins"));
        stepsList.add(new Steps("Step 4: cook the past", "40 mins"));
        stepsList.add(new Steps("Step 5: cook the past", "40 mins"));
        stepsList.add(new Steps("Step 6: cook the past", "40 mins"));
        stepsList.add(new Steps("Step 7: cook the past", "40 mins"));
        stepsList.add(new Steps("Step 8: cook the past", "40 mins"));

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
}