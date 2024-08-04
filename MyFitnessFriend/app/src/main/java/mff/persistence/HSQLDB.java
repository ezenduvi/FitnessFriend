package mff.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import mff.objects.Day;
import mff.objects.Diary;
import mff.objects.Exercise;
import mff.objects.Food;
import mff.objects.MealType;
import mff.objects.Meals;
import mff.objects.Unit;
import mff.objects.User;

public class HSQLDB implements Database {
    private Connection connection;

    private String dbType;
    private final String dbName;

    private String cmdString;

    public HSQLDB(String dbName) {
        this.dbName = dbName;
    }

    /**
     * open
     * populates the stub database with different food objects
     *
     * @param dbPath - the name of the stub database
     */
    public void open(String dbPath) {
        String url;
        try {
            // Setup for HSQL
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath + ";hsqldb.lock_file=false;"; // stored on disk mode
            connection = DriverManager.getConnection(url, "SA", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Opened " + dbType + " database " + dbPath);
    }

    /**
     * close
     * prints to console that the stub database has been closed
     */
    public void close() {
        try {    // commit all changes to the database
            cmdString = "shutdown compact";
            connection.createStatement().executeQuery(cmdString);
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    /**
     * createSaveState()
     * creates a point that can be rolled back to, for testing use only
     */
    public void createSaveState() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * rollbackSaveState()
     * restores the database back to the savepoint set above, for testing use only
     */
    public void rollbackSaveState() {
        try {
            cmdString = "ROLLBACK WORK";
            connection.createStatement().executeUpdate(cmdString);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * return all of the food options
     * @return - the food options
     */
    public ArrayList<Food> getAllFood() {
        ArrayList<Food> allFood = new ArrayList<>();
        ResultSet results;
        Food newFood;

        try {
            cmdString = "SELECT * FROM food_options RIGHT JOIN units ON food_options.unit_id = units.unit_id";
            results = connection.createStatement().executeQuery(cmdString);

            while (results.next()) {
                newFood = new Food(results.getString("name"), results.getInt("calories"),
                        results.getInt("carbs"), results.getInt("fat"),
                        results.getInt("protein"), results.getDouble("serving_size"),
                        Unit.valueOf(results.getString("unit_name")), 1);

                allFood.add(newFood);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allFood;
    }

    public ArrayList<Diary> getAllReflections() {
        ResultSet diaryResults = null;
        ArrayList<Diary> diaries = new ArrayList<>();

        try {
            cmdString = "SELECT * FROM diaries";
            diaryResults = connection.createStatement().executeQuery(cmdString);

            while (diaryResults.next()) {
                diaries.add(new Diary(diaryResults.getString("reflection")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return diaries;
    }

    public void addReflection(Diary diary) {
        updateDiary(diary.getReflection());
    }

    /**
     * return all of the exercise options
     *
     * @return - the exercise options
     */
    public ArrayList<Exercise> getAllExercises() {
        ArrayList<Exercise> allExercise = new ArrayList<>();
        ResultSet results;
        Exercise newExercise;

        try {
            cmdString = "SELECT * FROM exercise_options";
            results = connection.createStatement().executeQuery(cmdString);

            while (results.next()) {
                newExercise = new Exercise(results.getString("exercise_name"), 1, results.getInt("calories_per_hour"));

                allExercise.add(newExercise);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allExercise;
    }

    /**
     * getUser
     *
     * @return - the user stored in the db
     */
    public User getUser() {
        User user = null;
        ResultSet results;

        try {
            cmdString = "SELECT * FROM users WHERE user_id = 0";
            results = connection.createStatement().executeQuery(cmdString);

            results.next();
            user = new User(results.getString("user_name"), results.getString("user_email"), results.getInt("calorie_goal"), results.getInt("water_goal"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * setUserCalorieGoal
     *
     * @param goal - the new water goal
     */
    public void setUserCalorieGoal(int goal) {
        try {
            cmdString = "UPDATE users SET calorie_goal = " + goal + "WHERE user_id = '" + 0 + "'";
            connection.createStatement().executeUpdate(cmdString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * setUserWaterGoal
     *
     * @param goal - the new water goal
     */
    public void setUserWaterGoal(int goal) {
        try {
            cmdString = "UPDATE users SET water_goal = " + goal + "WHERE user_id = '" + 0 + "'";
            connection.createStatement().executeUpdate(cmdString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * addMealToDay
     * sets the passed meal to the the current day in the correct type
     * @param meal - the meal to be added
     * @param type - the type of the meal to be added
     */
    public void addMealToDay(Meals meal, MealType type) {
        Food food = null;
        int mealId = getMealIdFromDay(type);

        try {
            cmdString = "DELETE FROM food WHERE meal_id = " + mealId;
            connection.createStatement().executeUpdate(cmdString);

            for (int i = 0; i < meal.getNumFood(); i++) {
                food = meal.getFood(i);
                cmdString = "INSERT INTO food (food_option_id, meal_id, serving_num) VALUES (" + getFoodTypeId(food) + ", " + mealId + ", " + food.getServingSize() + ")";
                connection.createStatement().executeUpdate(cmdString);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * getMealFromDay
     * gets the meal of the passed type from the current day
     * @param type - the type of the meal to get from the current day
     * @return Meals - the meal from the current day of the passed type
     */
    public Meals getMealFromDay(MealType type){
        ResultSet mealResults = null;
        int mealId = -1;
        String colLable = type.toString().toLowerCase() + "_meal_id";

        Meals returnMeal = null;

        try {
            cmdString = "SELECT " + colLable + " FROM days ORDER BY day_id DESC LIMIT 1";
            mealResults = connection.createStatement().executeQuery(cmdString);
            mealResults.next();
            mealId = mealResults.getInt(colLable);

            returnMeal = getMealFromId(mealId, type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnMeal;
    }

    /**
     * getWeeklyFoodCals
     * gets the calories gained from food by day from the past up to 7 days
     * @return ArrayList<double> - the list of food by day from the past up to 7 days
     */
    public ArrayList<Double> getWeeklyFoodCals(){
        final int  DAYS_IN_WEEK = 7;
        ArrayList<Double> weeklyFoodCals = new ArrayList<>();
        ResultSet dayResults = null;
        ResultSet foodResults = null;

        int breakfastIds = -1;
        int lunchIds = -1;
        int dinnerIds = -1;
        int snackIds = -1;

        Meals breakfast = null;
        Meals lunch = null;
        Meals dinner = null;
        Meals snack = null;

        Day tempDay = null;

        try {
            cmdString = "SELECT breakfast_meal_id, lunch_meal_id, dinner_meal_id, snack_meal_id " +
                    "FROM days ORDER BY day_id DESC LIMIT 7";
            dayResults = connection.createStatement().executeQuery(cmdString);

            while (dayResults.next()) {
                breakfastIds = dayResults.getInt("breakfast_meal_id");
                lunchIds = dayResults.getInt("lunch_meal_id");
                dinnerIds = dayResults.getInt("dinner_meal_id");
                snackIds = dayResults.getInt("snack_meal_id");

                breakfast = getMealFromId(breakfastIds, MealType.BREAKFAST);
                lunch = getMealFromId(lunchIds, MealType.LUNCH);
                dinner = getMealFromId(dinnerIds, MealType.DINNER);
                snack = getMealFromId(snackIds, MealType.SNACK);

                tempDay = new Day(new User("temp", "temp@email.com"), new ArrayList<>(), breakfast, lunch, dinner, snack);

                weeklyFoodCals.add(tempDay.getDailyCalories());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weeklyFoodCals;
    }

    /**
     * addExerciseToDay
     * adds the passed exercise to the current day
     * @param toAdd - exercise to add to the day
     */
    public void addExerciseToDay(Exercise toAdd) {
        int dayId = getCurrDayId();
        int exerciseId = getExerciseId(toAdd.getName());

        try {
            cmdString = "INSERT INTO exercises (exercise_option_id, length, day_id) VALUES (" + exerciseId + ", " + toAdd.getDuration() + ", " + dayId + ")";
            connection.createStatement().executeUpdate(cmdString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * getExercisesByDay
     * gets a list of the exercises done in the current day
     * @return - the list of exercises done in the current day
     */
    public ArrayList<Exercise> getExercisesByDay() {
        return getExerciseFromDayId(getCurrDayId());
    }

    /**
     * getWeeklyExerciseCals
     * gets the calories burned by exercise by day for the last up to 7 days
     * @return - the calories burned by exercise by day for the last up to 7 days
     */
    public ArrayList<Double> getWeeklyExerciseCals() {
        ArrayList<Double> weeklyExercise = new ArrayList<>();
        ArrayList<Exercise> exercises = new ArrayList<>();
        int dayId = -1;
        Day day = null;
        User user = new User("temp", "temp@temp.temp");
        Meals meal = new Meals(MealType.BREAKFAST);
        ResultSet dayResults = null;
        ResultSet exerciseResults = null;


        try {
            cmdString = "SELECT day_id FROM days ORDER BY day_id DESC LIMIT 7";
            dayResults = connection.createStatement().executeQuery(cmdString);

            while (dayResults.next()) {
                dayId = dayResults.getInt("day_id");
                exercises = getExerciseFromDayId(dayId);
                day = new Day(user, exercises, meal, meal, meal, meal);
                weeklyExercise.add(day.getExerciseCals());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weeklyExercise;
    }

    /**
     * createDay
     * adds a new blank day to the database which is set as the current day
     */
    public void createDay() {
        int[] mealIds;

        mealIds = createMealsForDay();

        createDay(mealIds, "");
    }

    /**
     * createDay
     * adds a new day with meals pre-filled-in which is set as the current day
     * @param breakfast - the breakfast for the day
     * @param lunch - the lunch for the day
     * @param dinner - the dinner for the day
     * @param snack - the snack for the day
     */
    public void createDay(Meals breakfast, Meals lunch, Meals dinner, Meals snack) {
        int[] mealIds;

        mealIds = createMealsForDay();

        createDay(mealIds, "");

        createFoodsFromMeal(breakfast, mealIds[0]);
        createFoodsFromMeal(lunch, mealIds[1]);
        createFoodsFromMeal(dinner, mealIds[2]);
        createFoodsFromMeal(snack, mealIds[3]);
    }

    /**
     * createDay
     * adds a new day with exercises pre-filled-in which is set as the current day
     * @param exercises - the exercises for the day
     */
    public void createDay(ArrayList<Exercise> exercises) {
        int[] mealIds;

        mealIds = createMealsForDay();

        createDay(mealIds, "");

        for (int i = 0; i < exercises.size(); i++) {
            addExerciseToDay(exercises.get(i));
        }
    }

    /**
     * getNumOfDays
     * returns the number of days in the db
     * @return - the number of days in the db
     */
    public int getNumOfDays() {
        ResultSet daysResult = null;
        int numOfDays = -1;

        try {
            cmdString = "SELECT count(day_id) AS num_days FROM days";
            daysResult = connection.createStatement().executeQuery(cmdString);
            daysResult.next();
            numOfDays = daysResult.getInt("num_days");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numOfDays;
    }

    private int getMealTypeId(String mealType) {
        ResultSet results = null;
        int mealId = -1;
        try {
            cmdString = "SELECT meal_type_id FROM meal_types WHERE meal_type = '" + mealType + "'";
            results = connection.createStatement().executeQuery(cmdString);
            results.next();
            mealId = results.getInt("meal_type_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mealId;
    }

    private int getFoodTypeId(Food food) {
        ResultSet results = null;
        int foodId = -1;
        try {
            cmdString = "SELECT food_option_id FROM food_options WHERE name = '" + food.getName() + "'";
            results = connection.createStatement().executeQuery(cmdString);
            results.next();
            foodId = results.getInt("food_option_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foodId;
    }

    private ArrayList<Food> createFoodsFromSet(ResultSet foodResults) {
        ArrayList<Food> foods = new ArrayList<>();

        String name = null;
        int calories = -1;
        int carbs = -1;
        int fat = -1;
        int protein = -1;
        int servingSize = -1;
        Unit unit = Unit.CUP;
        double servingNum = -1;

        if (foodResults == null) {
            throw new NullPointerException("foodResults can not be null");
        }

        try {
            while (foodResults.next()) {
                name = foodResults.getString("name");
                calories = foodResults.getInt("calories");
                carbs = foodResults.getInt("carbs");
                fat = foodResults.getInt("fat");
                protein = foodResults.getInt("protein");
                servingSize = foodResults.getInt("serving_size");
                unit = Unit.valueOf(foodResults.getString("unit_name"));
                servingNum = foodResults.getDouble("serving_num");

                foods.add(new Food(name, calories, carbs, fat, protein, servingSize, unit, servingNum));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foods;
    }

    private Meals getMealFromId(int mealId, MealType type) {
        ResultSet foodResults = null;
        ArrayList<Food> foods;
        Meals returnMeal = null;

        try {
            cmdString = "SELECT name, calories, carbs, fat, protein, serving_size, unit_name, serving_num " +
                    "FROM food " +
                    "LEFT JOIN food_options ON food.food_option_id = food_options.food_option_id " +
                    "LEFT JOIN units ON food_options.unit_id = units.unit_id " +
                    "WHERE meal_id = " + mealId;
            foodResults = connection.createStatement().executeQuery(cmdString);
            foods = createFoodsFromSet(foodResults);

            returnMeal = new Meals(type, foods);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnMeal;
    }

    private int getCurrDayId() {
        int dayId = -1;
        ResultSet dayResults = null;

        try {
            cmdString = "SELECT day_id FROM days ORDER BY day_id DESC LIMIT 1";
            dayResults = connection.createStatement().executeQuery(cmdString);
            dayResults.next();
            dayId = dayResults.getInt("day_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dayId;
    }

    private int getExerciseId(String name) {
        int exerciseId = -1;
        ResultSet exerciseResults = null;

        try {
            cmdString = "SELECT exercise_option_id, exercise_name FROM exercise_options WHERE exercise_name = '" + name + "'";
            exerciseResults = connection.createStatement().executeQuery(cmdString);
            exerciseResults.next();
            exerciseId = exerciseResults.getInt("exercise_option_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exerciseId;
    }

    private ArrayList<Exercise> getExerciseFromDayId(int dayId) {
        ArrayList<Exercise> exercises = new ArrayList<>();
        ResultSet exerciseResults = null;

        String name = "";
        int duration = -1;
        int calsPerHour = -1;

        try {
            cmdString = "SELECT exercise_name, length, calories_per_hour " +
                    "FROM exercises " +
                    "LEFT JOIN exercise_options ON exercise_options.exercise_option_id = exercises.exercise_option_id " +
                    "WHERE day_id = " + dayId;
            exerciseResults = connection.createStatement().executeQuery(cmdString);
            while (exerciseResults.next()) {
                name = exerciseResults.getString("exercise_name");
                duration = exerciseResults.getInt("length");
                calsPerHour = exerciseResults.getInt("calories_per_hour");
                exercises.add(new Exercise(name, duration, calsPerHour));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exercises;
    }

    private int getUserId() {
        ResultSet userIdResults = null;
        int userId = -1;
        try {
            cmdString = "SELECT user_id FROM users";
            userIdResults = connection.createStatement().executeQuery(cmdString);
            userIdResults.next();
            userId = userIdResults.getInt("user_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }

    private int[] createMealsForDay() {
        final int MEALS_PER_DAY = 5;

        ResultSet mealIdsResults = null;
        int[] mealIds = new int[MEALS_PER_DAY];
        try {
            cmdString = "INSERT INTO meals (meal_type_id) VALUES (" + getMealTypeId(MealType.BREAKFAST.toString()) + ")";
            connection.createStatement().executeUpdate(cmdString);
            cmdString = "INSERT INTO meals (meal_type_id) VALUES (" + getMealTypeId(MealType.LUNCH.toString()) + ")";
            connection.createStatement().executeUpdate(cmdString);
            cmdString = "INSERT INTO meals (meal_type_id) VALUES (" + getMealTypeId(MealType.DINNER.toString()) + ")";
            connection.createStatement().executeUpdate(cmdString);
            cmdString = "INSERT INTO meals (meal_type_id) VALUES (" + getMealTypeId(MealType.SNACK.toString()) + ")";
            connection.createStatement().executeUpdate(cmdString);
            cmdString = "INSERT INTO meals (meal_type_id) VALUES (" + getMealTypeId(MealType.WATER.toString()) + ")";
            connection.createStatement().executeUpdate(cmdString);

            cmdString = "SELECT meal_id FROM meals ORDER BY meal_id DESC LIMIT 5";
            mealIdsResults = connection.createStatement().executeQuery(cmdString);

            for (int i = MEALS_PER_DAY - 1; i >= 0; i--) {
                mealIdsResults.next();
                mealIds[i] = mealIdsResults.getInt("meal_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mealIds;
    }

    private void createDay(int[] mealIds, String diaryText) {
        int diaryId = createDiary(diaryText);

        try {
            cmdString = "INSERT INTO days (user_id, water, breakfast_meal_id, lunch_meal_id, dinner_meal_id, snack_meal_id, diary_id)" +
                    "VALUES (" + getUserId() + ", " + mealIds[4] + ", " + mealIds[0] + ", " +
                    mealIds[1] + ", " + mealIds[2] + ", " + mealIds[3] + ", " + diaryId + ")";
            connection.createStatement().executeUpdate(cmdString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createFoodsFromMeal(Meals meal, int meal_id) {
        Food food;
        try {
            for (int i = 0; i < meal.getNumFood(); i++) {
                food = meal.getFood(i);
                cmdString = "INSERT INTO food (food_option_id, meal_id, serving_num) VALUES (" + getFoodTypeId(food) + ", " +
                        meal_id + ", " +
                        food.getNumberOfServings() + ")";
                connection.createStatement().executeUpdate(cmdString);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int createDiary(String text) {
        int diaryId = -1;
        ResultSet diaryIdResults = null;
        try {
            cmdString = "INSERT INTO diaries (reflection) VALUES ('" + text + "')";
            connection.createStatement().executeUpdate(cmdString);

            cmdString = "SELECT diary_id FROM diaries ORDER BY diary_id DESC LIMIT 1";
            diaryIdResults = connection.createStatement().executeQuery(cmdString);
            diaryIdResults.next();
            diaryId = diaryIdResults.getInt("diary_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return diaryId;
    }

    private int getDiaryIdFromDay(int dayId) {
        ResultSet diaryIdResults = null;
        int diaryId = -1;
        try {
            cmdString = "SELECT diary_id FROM days WHERE day_id = " + dayId;
            diaryIdResults = connection.createStatement().executeQuery(cmdString);
            diaryIdResults.next();
            diaryId = diaryIdResults.getInt("diary_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return diaryId;
    }

    private void updateDiary(String newText) {
        int dayId = getCurrDayId();
        int diaryId = getDiaryIdFromDay(dayId);
        try {
            cmdString = "UPDATE diaries SET reflection = '" + newText + "' WHERE diary_id = " + diaryId;
            connection.createStatement().executeUpdate(cmdString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getMealIdFromDay(MealType type) {
        ResultSet mealIdResults = null;
        String colLable = type.toString().toLowerCase() + "_meal_id";
        int mealId = -1;

        try {
            cmdString = "SELECT " + colLable + " FROM days ORDER BY day_id DESC LIMIT 1";
            mealIdResults = connection.createStatement().executeQuery(cmdString);
            mealIdResults.next();
            mealId = mealIdResults.getInt(colLable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mealId;
    }
}
