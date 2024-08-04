package mff.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Objects;

import mff.R;
import mff.business.AccessFoods;
import mff.objects.Food;
import mff.objects.Unit;

public class FoodItemPageActivity extends AppCompatActivity {

    String foodName;
    int indexOfFood;
    String meal;
    AccessFoods accessFoods;

    @Override

    /**
     * onCreate
     * set up page to show information about the selected food
     * shows name, calories and macros
     * allows you to select the serving size consumed
     * @param savedInstanceState - the current instance
     */
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_item_page); //set up layout
        Toolbar myToolbar = (Toolbar) findViewById(R.id.item_toolbar); //show toolbar
        setSupportActionBar(myToolbar);

        Bundle extras = getIntent().getExtras();

        if (extras !=null){

            //get information about the food and display it
            String name = extras.getString("name");
            meal = extras.getString("meal");

            accessFoods = new AccessFoods();

            ArrayList<Food> foodList = accessFoods.getAllFood();

            indexOfFood = accessFoods.getFoodPosition(name);

            if(indexOfFood!=-1){

                Food displayFood = foodList.get(indexOfFood);
                foodName = displayFood.getName();
                Objects.requireNonNull(getSupportActionBar()).setTitle(foodName);

                int calories = displayFood.getCalories();
                String caloriesString = String.valueOf(calories);
                TextView calorieNumber = findViewById(R.id.numberOfCalories);
                calorieNumber.setText(caloriesString);

                double numServings = displayFood.getNumberOfServings();
                String numServingsString = String.valueOf(numServings);
                TextView numberServings = findViewById(R.id.numberOfServings);
                numberServings.setText(numServingsString);

                double servingSize = displayFood.getServingSize();
                Unit unit = displayFood.getUnit();
                String unitString = unit.toString();
                String servingSizeString = String.valueOf(servingSize) + " " + unitString;
                TextView servingSizeNumber = findViewById(R.id.servingSizeNumber);
                servingSizeNumber.setText(servingSizeString);

                int protein = displayFood.getProtein();
                String proteinString = String.valueOf(protein);
                TextView proteinNumber = findViewById(R.id.proteinGrams);
                proteinNumber.setText(proteinString);

                int carbs = displayFood.getCarbs();
                String carbsString = String.valueOf(carbs);
                TextView carbsNumber = findViewById(R.id.carbGrams);
                carbsNumber.setText(carbsString);

                int fat = displayFood.getFat();
                String fatString = String.valueOf(fat);
                TextView fatNumber = findViewById(R.id.fatGrams);
                fatNumber.setText(fatString);

            }
        }

    }

    /**
     * addFoodOnClick
     * sends index of food to be added to certain meal back to home activity
     * adds food to list on home page
     * @param view - the current view
     */
    public void addFoodOnClick(View view) {

        Intent addFood = new Intent();
        addFood.putExtra("FoodIndex", indexOfFood);
        addFood.putExtra("meal", meal);
        setResult(RESULT_OK, addFood);
        finish();

    }

}


