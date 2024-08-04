package mff.presentation;

import mff.R;
import mff.application.Main;
import mff.business.AccessFoods;
import mff.business.AccessProfile;
import mff.objects.Day;
import mff.objects.Food;
import mff.objects.MealType;
import mff.objects.Meals;
import mff.objects.Unit;
import mff.objects.User;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    Day today;
    ArrayList<Food> foodList;
    int searchRequestCode = 0;
    int addFoodRequestCode = 1;
    AccessFoods accessFoods;
    AccessProfile accessProfile;

    ArrayList<String> breakfastNames = new ArrayList<>();
    ArrayList<String> breakfastServingSizes = new ArrayList<>();
    ArrayList<String> breakfastCalories = new ArrayList<>();

    ArrayList<String> lunchServingSizes = new ArrayList<>();
    ArrayList<String> lunchCalories = new ArrayList<>();

    ArrayList<String> dinnerServingSizes = new ArrayList<>();
    ArrayList<String> dinnerCalories = new ArrayList<>();

    ArrayList<String> snackServingSizes = new ArrayList<>();
    ArrayList<String> snackCalories = new ArrayList<>();

    ArrayList<String> lunchNames = new ArrayList<>();
    ArrayList<String> dinnerNames = new ArrayList<>();
    ArrayList<String> snackNames = new ArrayList<>();

    ArrayList<String> waterNames = new ArrayList<>();
    ArrayList<String> waterServingSizes = new ArrayList<>();
    ArrayList<String> waterCalories = new ArrayList<>();

    int totalBreakfastCalories = 0;
    int totalLunchCalories = 0;
    int totalDinnerCalories = 0;
    int totalSnackCalories = 0;

    int waterConsumed = 0;
    int proteinConsumed = 0;
    int carbsConsumed = 0;
    int fatConsumed = 0;

    int totalCaloriesConsumed = 0;
    int remainingCalories;
    int calorieGoal;
    int caloriesBurned = 0;

    User user;

    Meals breakfast;
    Meals lunch;
    Meals dinner;
    Meals snack;
    Meals water;

    RecyclerView breakfastListView;
    RecyclerView lunchListView;
    RecyclerView dinnerListView;
    RecyclerView snackListView;
    RecyclerView waterListView;

    TextView breakfastCalorieView;
    TextView lunchCalorieView;
    TextView dinnerCalorieView;
    TextView snackCalorieView;

    TextView calorieGoalView;
    TextView caloriesConsumedView;
    TextView caloriesExerciseView;
    TextView caloriesRemainingView;

    /**
     * onCreate
     * start app and set up page to show daily_ui (calories, meals, water)
     * @param savedInstanceState - the current instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("============================================\n\n\n");
        copyDatabaseToDevice();

        Main.startUp();
        accessFoods = new AccessFoods();
        accessProfile = new AccessProfile();

        setContentView(R.layout.dailyui);

        foodList = accessFoods.getAllFood();
        user = accessProfile.getUser();

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            caloriesBurned = extras.getInt("caloriesBurned");
        }
        calorieGoal = user.getCalorieGoal();

        breakfast = new Meals(MealType.BREAKFAST);
        lunch = new Meals(MealType.LUNCH);
        dinner = new Meals(MealType.DINNER);
        snack = new Meals(MealType.SNACK);
        water = new Meals(MealType.WATER);

        today = new Day(user, new ArrayList<>(), breakfast, lunch, dinner, snack);

        breakfastListView = findViewById(R.id.breakfastList);
        dinnerListView = findViewById(R.id.dinnerList);
        lunchListView = findViewById(R.id.lunchList);
        snackListView = findViewById(R.id.snackList);
        waterListView = findViewById(R.id.waterList);

        breakfast = accessFoods.getMealFromDay(MealType.BREAKFAST);
        lunch = accessFoods.getMealFromDay(MealType.LUNCH);
        dinner = accessFoods.getMealFromDay(MealType.DINNER);
        snack = accessFoods.getMealFromDay(MealType.SNACK);
        water = accessFoods.getMealFromDay(MealType.WATER);

        remainingCalories = calorieGoal;

        calorieGoalView = findViewById(R.id.calorie_goal_number);
        calorieGoalView.setText(String.valueOf(calorieGoal));

        caloriesConsumedView = findViewById(R.id.calorie_food_number);
        caloriesConsumedView.setText(String.valueOf(totalCaloriesConsumed));

        caloriesExerciseView = findViewById(R.id.calorie_exercise_number);
        caloriesExerciseView.setText(String.valueOf(caloriesBurned));

        caloriesRemainingView = findViewById(R.id.calories_remaining_number);
        caloriesRemainingView.setText(String.valueOf(remainingCalories));

        breakfastCalorieView = findViewById(R.id.totalBreakfastCalories);
        breakfastCalorieView.setText(String.valueOf(totalBreakfastCalories));

        lunchCalorieView = findViewById(R.id.totalLunchCalories);
        lunchCalorieView.setText(String.valueOf(totalLunchCalories));

        dinnerCalorieView = findViewById(R.id.totalDinnerCalories);
        dinnerCalorieView.setText(String.valueOf(totalDinnerCalories));

        snackCalorieView = findViewById(R.id.totalSnackCalories);
        snackCalorieView.setText(String.valueOf(totalSnackCalories));

        setupLists();

    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

    /**
     * setupLists
     * sets up breakfast, lunch, dinner, snack and water lists.
     * gets meals from database and iterates through each food item and sets view adapter
     */
    private void setupLists(){

        //set up breakfast list
        breakfast = accessFoods.getMealFromDay(MealType.BREAKFAST);
        breakfast.printMeal();

        for(int i=0; i<breakfast.getNumFood(); i++){
            Food food = breakfast.getFood(i);
            int calories = food.getCalories();
            String numServings = String.valueOf(food.getNumberOfServings());
            String unit = food.getUnit().toString();
            String serving = numServings + " " + unit;
            breakfastNames.add(food.getName());
            breakfastServingSizes.add(serving);
            breakfastCalories.add(calories+"");
            totalBreakfastCalories+=calories;
            totalCaloriesConsumed+=calories;
            remainingCalories-=calories;
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, breakfastNames, breakfastServingSizes, breakfastCalories);
        breakfastListView.setAdapter(adapter);
        breakfastListView.setLayoutManager(new LinearLayoutManager(this));

        caloriesConsumedView.setText(String.valueOf(totalCaloriesConsumed));
        caloriesRemainingView.setText(String.valueOf(remainingCalories));

        breakfastCalorieView.setText(String.valueOf(totalBreakfastCalories));

        //set up lunch list
        lunch = accessFoods.getMealFromDay(MealType.LUNCH);
        lunch.printMeal();

        for(int i=0; i<lunch.getNumFood(); i++){
            Food food = lunch.getFood(i);
            int calories = food.getCalories();
            String numServings = String.valueOf(food.getNumberOfServings());
            String unit = food.getUnit().toString();
            String serving = numServings + " " + unit;
            lunchNames.add(food.getName());
            lunchServingSizes.add(serving);
            lunchCalories.add(calories+"");
            totalLunchCalories+=calories;
            totalCaloriesConsumed+=calories;
            remainingCalories-=calories;
        }

        adapter = new RecyclerViewAdapter(this, lunchNames, lunchServingSizes, lunchCalories);
        lunchListView.setAdapter(adapter);
        lunchListView.setLayoutManager(new LinearLayoutManager(this));

        caloriesConsumedView.setText(String.valueOf(totalCaloriesConsumed));
        caloriesRemainingView.setText(String.valueOf(remainingCalories));

        lunchCalorieView.setText(String.valueOf(totalLunchCalories));

        //set up dinner list
        dinner = accessFoods.getMealFromDay(MealType.DINNER);
        dinner.printMeal();

        for(int i=0; i<dinner.getNumFood(); i++){
            Food food = dinner.getFood(i);
            int calories = food.getCalories();
            String numServings = String.valueOf(food.getNumberOfServings());
            String unit = food.getUnit().toString();
            String serving = numServings + " " + unit;
            dinnerNames.add(food.getName());
            dinnerServingSizes.add(serving);
            dinnerCalories.add(calories+"");
            totalDinnerCalories+=calories;
            totalCaloriesConsumed+=calories;
            remainingCalories-=calories;
        }

        adapter = new RecyclerViewAdapter(this, dinnerNames, dinnerServingSizes, dinnerCalories);
        dinnerListView.setAdapter(adapter);
        dinnerListView.setLayoutManager(new LinearLayoutManager(this));

        caloriesConsumedView.setText(String.valueOf(totalCaloriesConsumed));
        caloriesRemainingView.setText(String.valueOf(remainingCalories));

        dinnerCalorieView.setText(String.valueOf(totalDinnerCalories));

        //set up snack list
        snack = accessFoods.getMealFromDay(MealType.SNACK);
        snack.printMeal();

        for(int i=0; i<snack.getNumFood(); i++){
            Food food = snack.getFood(i);
            int calories = food.getCalories();
            String numServings = String.valueOf(food.getNumberOfServings());
            String unit = food.getUnit().toString();
            String serving = numServings + " " + unit;
            snackNames.add(food.getName());
            snackServingSizes.add(serving);
            snackCalories.add(calories+"");
            totalSnackCalories+=calories;
            totalCaloriesConsumed+=calories;
            remainingCalories-=calories;
        }

        adapter = new RecyclerViewAdapter(this, snackNames, snackServingSizes, snackCalories);
        snackListView.setAdapter(adapter);
        snackListView.setLayoutManager(new LinearLayoutManager(this));

        caloriesConsumedView.setText(String.valueOf(totalCaloriesConsumed));
        caloriesRemainingView.setText(String.valueOf(remainingCalories));

        snackCalorieView.setText(String.valueOf(totalSnackCalories));

        //set up water list
        water = accessFoods.getMealFromDay(MealType.WATER);
        if(water != null) {
            for (int i = 0; i < water.getNumFood(); i++) {
                Food food = water.getFood(i);
                int calories = food.getCalories();
                String numServings = String.valueOf(food.getNumberOfServings());
                String unit = food.getUnit().toString();
                String serving = numServings + " " + unit;
                waterNames.add(food.getName());
                waterServingSizes.add(serving);
                waterCalories.add(calories + "");
            }
        }

        adapter = new RecyclerViewAdapter(this, waterNames, waterServingSizes, waterCalories);
        waterListView.setAdapter(adapter);
        waterListView.setLayoutManager(new LinearLayoutManager(this));

        caloriesConsumedView.setText(String.valueOf(totalCaloriesConsumed));
        caloriesRemainingView.setText(String.valueOf(remainingCalories));

    }

    /**
     * addFoodToList
     * set up page to show information about the selected food
     * shows name, calories and macros
     * allows you to select the serving size consumed
     * @param index - the index of food to be added
     * @param meal - meal the food should be added to
     */
    private void addFoodToList(int index, String meal) {

        Food food = foodList.get(index);

        int calories = food.getCalories();

        totalCaloriesConsumed += calories;
        caloriesConsumedView.setText(String.valueOf(totalCaloriesConsumed));

        proteinConsumed += food.getProtein();
        carbsConsumed += food.getCarbs();
        fatConsumed += food.getFat();

        remainingCalories -= calories;
        caloriesRemainingView.setText(String.valueOf(remainingCalories));

        switch (meal) {
            case "Breakfast": {

                breakfast.addFood(food);
                breakfastNames.add(food.getName());
                String caloriesString = String.valueOf(calories);
                breakfastCalories.add(caloriesString);
                String numServings = String.valueOf(food.getNumberOfServings());
                String unit = food.getUnit().toString();
                String serving = numServings + " " + unit;

                totalBreakfastCalories += calories;
                breakfastCalorieView.setText(String.valueOf(totalBreakfastCalories));

                breakfastServingSizes.add(serving);

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, breakfastNames, breakfastServingSizes, breakfastCalories);
                breakfastListView.setAdapter(adapter);
                breakfastListView.setLayoutManager(new LinearLayoutManager(this));

                break;
            }
            case "Lunch": {

                lunch.addFood(food);
                lunchNames.add(food.getName());
                String caloriesString = String.valueOf(calories);
                lunchCalories.add(caloriesString);
                String numServings = String.valueOf(food.getNumberOfServings());
                String unit = food.getUnit().toString();

                String serving = numServings + " " + unit;

                totalLunchCalories += calories;
                lunchCalorieView.setText(String.valueOf(totalLunchCalories));

                lunchServingSizes.add(serving);

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, lunchNames, lunchServingSizes, lunchCalories);
                lunchListView.setAdapter(adapter);
                lunchListView.setLayoutManager(new LinearLayoutManager(this));

                break;
            }
            case "Dinner": {

                dinner.addFood(food);
                dinnerNames.add(food.getName());
                String caloriesString = String.valueOf(calories);
                dinnerCalories.add(caloriesString);
                String numServings = String.valueOf(food.getNumberOfServings());
                String unit = food.getUnit().toString();

                String serving = numServings + " " + unit;

                totalDinnerCalories += calories;
                dinnerCalorieView.setText(String.valueOf(totalDinnerCalories));

                dinnerServingSizes.add(serving);

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, dinnerNames, dinnerServingSizes, dinnerCalories);
                dinnerListView.setAdapter(adapter);
                dinnerListView.setLayoutManager(new LinearLayoutManager(this));

                break;
            }
            case "Snack": {

                snack.addFood(food);
                snackNames.add(food.getName());
                String caloriesString = String.valueOf(calories);
                snackCalories.add(caloriesString);
                String numServings = String.valueOf(food.getNumberOfServings());
                String unit = food.getUnit().toString();

                String serving = numServings + " " + unit;

                totalSnackCalories += calories;
                snackCalorieView.setText(String.valueOf(totalSnackCalories));

                snackServingSizes.add(serving);

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, snackNames, snackServingSizes, snackCalories);
                snackListView.setAdapter(adapter);
                snackListView.setLayoutManager(new LinearLayoutManager(this));

                break;
            }
        }

        accessFoods.addMealToDay(breakfast, MealType.BREAKFAST);
        accessFoods.addMealToDay(lunch, MealType.LUNCH);
        accessFoods.addMealToDay(dinner, MealType.DINNER);
        accessFoods.addMealToDay(snack, MealType.SNACK);

        breakfast.printMeal();
        lunch.printMeal();
        dinner.printMeal();
        snack.printMeal();

        calorieGoal = user.getCalorieGoal();
        remainingCalories = calorieGoal;

        calorieGoalView = findViewById(R.id.calorie_goal_number);
        calorieGoalView.setText(String.valueOf(calorieGoal));

        caloriesConsumedView = findViewById(R.id.calorie_food_number);
        caloriesConsumedView.setText(String.valueOf(totalCaloriesConsumed));

        caloriesExerciseView = findViewById(R.id.calorie_exercise_number);
        caloriesExerciseView.setText(String.valueOf(caloriesBurned));

        caloriesRemainingView = findViewById(R.id.calories_remaining_number);
        caloriesRemainingView.setText(String.valueOf(remainingCalories));

        breakfastCalorieView = findViewById(R.id.totalBreakfastCalories);
        breakfastCalorieView.setText(String.valueOf(totalBreakfastCalories));

        lunchCalorieView = findViewById(R.id.totalLunchCalories);
        lunchCalorieView.setText(String.valueOf(totalLunchCalories));

        dinnerCalorieView = findViewById(R.id.totalDinnerCalories);
        dinnerCalorieView.setText(String.valueOf(totalDinnerCalories));

        snackCalorieView = findViewById(R.id.totalSnackCalories);
        snackCalorieView.setText(String.valueOf(totalSnackCalories));

    }

    /**
     * onDestroy
     * shuts down app
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Main.shutDown();
    }

    /**
     * dailySummaryOnClick
     * takes user to daily summary page
     * sends macros and information to daily summary activity
     * @param v - the current view
     */
    public void dailySummaryOnClick(View v) {
        Intent dailySummary = new Intent(HomeActivity.this, DailySummaryActivity.class);
        dailySummary.putExtra("totalCalories", totalCaloriesConsumed);

        breakfast = accessFoods.getMealFromDay(MealType.BREAKFAST);
        lunch = accessFoods.getMealFromDay(MealType.LUNCH);
        dinner = accessFoods.getMealFromDay(MealType.DINNER);
        snack = accessFoods.getMealFromDay(MealType.SNACK);

        double carbsConsumed = breakfast.getCarbs() + lunch.getCarbs() + dinner.getCarbs() + snack.getCarbs();
        double fatConsumed = breakfast.getFat() + lunch.getFat() + dinner.getFat() + snack.getFat();
        double proteinConsumed = breakfast.getProtein() + lunch.getProtein() + dinner.getProtein() + snack.getProtein();

        dailySummary.putExtra("water", waterConsumed);
        dailySummary.putExtra("protein", proteinConsumed);
        dailySummary.putExtra("carbs", carbsConsumed);
        dailySummary.putExtra("fat", fatConsumed);
        HomeActivity.this.startActivity(dailySummary);
    }

    /**
     * weeklySummaryOnClick
     * takes user to weekly summary page
     * @param v - the current view
     */
    public void weeklySummaryOnClick(View v) {
        Intent weeklySummary = new Intent(HomeActivity.this, WeeklySummaryActivity.class);
        HomeActivity.this.startActivity(weeklySummary);
    }

    /**
     * exerciseOnClick
     * takes user to exercise page
     * @param v - the current view
     */
    public void exerciseOnClick(View v) {
        Intent exercisePage = new Intent(HomeActivity.this, ExercisePageActivity.class);
        exercisePage.putExtra("totalCaloriesConsumed",totalCaloriesConsumed);
        HomeActivity.this.startActivity(exercisePage);
    }

    /**
     * reflectionOnClick
     * takes user to add reflection
     * @param v - the current view
     */
    public void reflectionOnClick(View v) {
        Intent addReflection = new Intent(HomeActivity.this, ReflectionActivity.class);
        HomeActivity.this.startActivity(addReflection);
    }

    /**
     * profileOnClick
     * switches to the profile page when the button is clicked
     * @param v - the current view
     */
    public void profileOnClick(View v) {
        Intent profileActivity = new Intent(HomeActivity.this, ProfileActivity.class);
        HomeActivity.this.startActivity(profileActivity);
    }

    /**
     * addFoodOnClick
     * switches to the search food page when the button is clicked
     * @param v - the current view
     */  
    public void addFoodOnClick(View v) {

        Intent searchFood = new Intent(HomeActivity.this, SearchFoodActivity.class);

        switch (v.getId()){
            case(R.id.breakfastButton):
                searchFood.putExtra("meal", "Breakfast");
            break;
            case(R.id.lunchButton):
                searchFood.putExtra("meal", "Lunch");
            break;
            case(R.id.dinnerButton):
                searchFood.putExtra("meal", "Dinner");
            break;
            case(R.id.snackButton):
                searchFood.putExtra("meal", "Snack");
            break;
        }

        startActivityForResult(searchFood, searchRequestCode);
    }

    /**
     * addWaterOnClick
     * switches to the water page when the button is clicked
     * @param v - the current view
     */
    public void addWaterOnClick(View v) {

        Intent addWater = new Intent(HomeActivity.this, WaterPageActivity.class);
        addWater.putExtra("meal", "Water");
        startActivityForResult(addWater, searchRequestCode);
    }

    /**
     * addWaterToList
     * adds water to list on display
     */
    private void addWaterToList() {

        Food waterItem = new Food("Water Tap Municipal",0,0,0,0,1, Unit.CUP,1);
        water = new Meals(MealType.WATER);
        water.addFood(waterItem);
        waterNames.add(waterItem.getName());
        int calories = waterItem.getCalories();
        String caloriesString = String.valueOf(calories);
        waterCalories.add(caloriesString);
        String numServings = String.valueOf(waterItem.getNumberOfServings());
        String unit = waterItem.getUnit().toString();

        String serving = numServings + " " + unit;

        waterConsumed += waterItem.getNumberOfServings();

        waterServingSizes.add(serving);
        accessFoods.addMealToDay(water, MealType.WATER);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, waterNames, waterServingSizes, waterCalories);
        waterListView.setAdapter(adapter);
        waterListView.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * onActivityResult
     * result from input flow, adds food given in the index to the meal returned from activity
     * @param requestCode - integer given when activity was created
     * @param resultCode - integer given when input flow is done
     * @param intent - current Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode==searchRequestCode) {
                if (resultCode == RESULT_OK) {

                    if(intent.getStringExtra("meal").equals("Water")){
                        addWaterToList();
                    } else {
                        int index = intent.getIntExtra("FoodIndex", -1);
                        String meal = intent.getStringExtra("meal");
                        if (index != -1) {
                            addFoodToList(index, meal);
                        }
                    }
                }
        }
    }

}
