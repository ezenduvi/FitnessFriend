package mff.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import mff.R;
import mff.business.AccessFoods;
import mff.objects.Food;

public class SearchFoodActivity extends AppCompatActivity {

    ListView foodListView;
    EditText inputSearch;
    ArrayAdapter<String> adapter;
    int searchRequestCode = 1;
    String meal;
    AccessFoods accessFoods = new AccessFoods();

    @Override
    /**
     * onCreate
     * sets up search food page
     * @param savedInstanceState - current instance
     */
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_foods);
        foodListView = findViewById(R.id.layout_list);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            meal = extras.getString("meal");
            System.out.println("meal from INTENT: " + meal);
        }

       addFoods();

    }

    /**
     * addFoods
     * populate view holder with foods, filter based on text being typed in
     */
    public void addFoods() {

        ArrayList<Food> foodList = accessFoods.getAllFood();
        System.out.println("FOOD" + foodList.get(0).getName());

        ArrayList<String> foodNames = new ArrayList<>();
        int tag=0;

        for(int i=0; i < foodList.size(); i++) {

            Food currFood = foodList.get(i);
            String foodName = currFood.getName();
            foodNames.add(foodName);

            adapter = new ArrayAdapter<String>(this, R.layout.row_food_database, foodNames);
            foodListView.setAdapter(adapter);

            inputSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    (SearchFoodActivity.this).adapter.getFilter().filter(s);
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent goToFoodPage = new Intent(SearchFoodActivity.this, FoodItemPageActivity.class);
                    String clickedFoodName = adapter.getItem(position);
                    goToFoodPage.putExtra("name", clickedFoodName);
                    goToFoodPage.putExtra("meal", meal);
                    startActivityForResult(goToFoodPage, searchRequestCode);
                }
            });

        }

    }

    @Override
    /**
     * onActivityResult
     * result from input flow, adds food given in the index to the meal returned from activity
     * @param requestCode - integer given when activity was created
     * @param resultCode - integer given when input flow is done
     * @param intent - current Intent
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode==searchRequestCode) {
            if (resultCode == RESULT_OK) {

                int index = intent.getIntExtra("FoodIndex", -1);

                Intent newIntent = new Intent();
                newIntent.putExtra("FoodIndex", index);
                newIntent.putExtra("meal", meal);
                setResult(RESULT_OK, newIntent);
                finish();

            }
        }
    }

    /**
     * backArrowOnClick
     * allows you to go back to home page using back arrow
     * @param v - the current view
     */
    public void backArrowOnClick(View v) {
        Intent backHome = new Intent(SearchFoodActivity.this, HomeActivity.class);
        SearchFoodActivity.this.startActivity(backHome);
    }

}

