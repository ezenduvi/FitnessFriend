package mff.persistence;

import java.util.ArrayList;

import mff.objects.Day;
import mff.objects.Diary;
import mff.objects.Food;
import mff.objects.MealType;
import mff.objects.Meals;
import mff.objects.Unit;
import mff.objects.User;
import mff.objects.Exercise;

public class DataAccessStub implements Database {

    private final String dbName;
    private final String dbType = "stub";

    private ArrayList<Food> foods;
    private ArrayList<Exercise> exercises;
    private ArrayList<Diary> reflections;
    private ArrayList<Day> days;
    private User user;

    /**
     * DataAccessStub
     * initializes the name of the stub database to the name that is passed
     * @param dbName - the name of the stub database
     */
    public DataAccessStub(String dbName)
    {
        this.dbName = dbName;
    }

    /**
     * open
     * populates the stub database with different food objects
     * @param dbName - the name of the stub database
     */
    public void open(String dbName) {

        Food food;
        foods = new ArrayList<>();
        food = new Food("Skim milk",128,13,4,18,1, Unit.CUP, 1);
        foods.add(food);
        food = new Food("Eggs",150,0,12,12,2,Unit.PIECES,1);
        foods.add(food);
        food = new Food("Butter", 100,0,11,0,1,Unit.TBSP,1);
        foods.add(food);
        food = new Food("Bacon",95,1,8,4,2,Unit.SLICES,1);
        foods.add(food);
        food = new Food("Beef",245,0,16,23,3,Unit.OZ,1);
        foods.add(food);
        food = new Food("Chicken",185,0,9,23,3,Unit.OZ,1);
        foods.add(food);
        food = new Food("Broccoli",45,8,0,5,1,Unit.CUP,1);
        foods.add(food);
        food = new Food("Carrots", 45,10,0,1,1,Unit.CUP,1);
        foods.add(food);
        food = new Food("Cauliflower",30,6,0,3,1,Unit.CUP,1);
        foods.add(food);
        food = new Food("Kale",45,8,1,4,1,Unit.CUP,1);
        foods.add(food);
        food = new Food("Apple", 70,18,0,0,1,Unit.MEDIUM,1);
        foods.add(food);
        food = new Food("Banana",85,23,0,1,1,Unit.MEDIUM,1);
        foods.add(food);
        food = new Food("Orange",60,16,0,2,1,Unit.MEDIUM,1);
        foods.add(food);
        food = new Food("Whole-wheat bread",55,11,1,2,1,Unit.SLICES,1);
        foods.add(food);
        food = new Food("Noodles",200,37,2,7,1,Unit.CUP,1);
        foods.add(food);
        food = new Food("Rice",748,154,3,15,1,Unit.CUP,1);
        foods.add(food);
        food = new Food("Donuts",135,17,7,2,1,Unit.PIECES,1);
        foods.add(food);
        food = new Food("Honey",120,30,0,0,2,Unit.TBSP,1);
        foods.add(food);
        food = new Food("Almonds",425,13,38,13,0.5,Unit.CUP,1);
        foods.add(food);
        food = new Food("Peanut butter",300,9,25,12,0.333,Unit.CUP,1);
        foods.add(food);
        food = new Food("Coffee",3,1,0,0,1,Unit.CUP,1);
        foods.add(food);

        Exercise exercise;
        exercises = new ArrayList<>();
        exercise = new Exercise("Running slow",1,37);
        exercises.add(exercise);
        exercise = new Exercise("Weights",1,37);
        exercises.add(exercise);
        exercise = new Exercise("Jogging",1,37);
        exercises.add(exercise);
        exercise = new Exercise("Jogging on the spot",1,37);
        exercises.add(exercise);
        exercise = new Exercise("Walking slow",1,37);
        exercises.add(exercise);
        exercise = new Exercise("Walking medium",1,37);
        exercises.add(exercise);
        exercise = new Exercise("Walking fast",1,37);
        exercises.add(exercise);
        exercise = new Exercise("Jumping Jacks",1,37);
        exercises.add(exercise);
        exercise = new Exercise("Burpees",1,37);
        exercises.add(exercise);
        exercise = new Exercise("Skipping",1,37);
        exercises.add(exercise);
        exercise = new Exercise("Sprinting",1,37);
        exercises.add(exercise);
        exercise = new Exercise("Unicycling",1,352);
        exercises.add(exercise);
        exercise = new Exercise("Stair machine",1,633);
        exercises.add(exercise);
        exercise = new Exercise("Cycling mountain bike bmx",1,598);
        exercises.add(exercise);


        user = new User("fitness foodie", "test@gmail.com",2000, 3);

        Day day;
        days = new ArrayList<>();
        day = new Day(user);
        days.add(day);

        Diary diary;
        reflections =new ArrayList<>();
        diary = new Diary("today was a great workout");
        reflections.add(diary);
        diary = new Diary("i didn't reach my goals today");
        reflections.add(diary);
        diary = new Diary("i lost some weight today");
        reflections.add(diary);
        diary = new Diary("i have been under my calorie limit for a week");
        reflections.add(diary);


        System.out.println("Opened " + dbType + "db " + dbName);
    }

    /**
     * close
     * prints to console that the stub database has been closed
     */
    public void close() { System.out.println("Closed " + dbType + "db " + dbName); }


    /** getAllFood
     * return all of the food options
     * @return - the food options
     */
    public ArrayList<Food> getAllFood() { return foods; }

    /**
     * addMealToDay
     * sets the passed meal to the the current day in the correct type
     * @param meal - the meal to be added
     * @param type - the type of the meal to be added
     */
    public void addMealToDay(Meals meal, MealType type) {
        days.get(days.size() - 1).addMeal(meal, type);
    }

    /**
     * getMealFromDay
     * gets the meal of the passed type from the current day
     * @param type - the type of the meal to get from the current day
     * @return Meals - the meal from the current day of the passed type
     */
    public Meals getMealFromDay(MealType type) {
        return days.get(days.size() - 1).getMeal(type);
    }

    /**
     * getWeeklyFoodCals
     * gets the calories gained from food by day from the past up to 7 days
     * @return ArrayList<double> - the list of food by day from the past up to 7 days
     */
    public ArrayList<Double> getWeeklyFoodCals() {
        ArrayList<Double> dailyCals = new ArrayList<>();

        for (Day day: days) {
            dailyCals.add(day.getDailyCalories());
        }

        return dailyCals;
    }

    /**
     * return all of the exercises
     * @return - the exercises
     */
    public ArrayList<Exercise> getAllExercises() { return exercises; }

    /**
     * return all of the diary reflections
     * @return - the diary reflections
     */
    public ArrayList<Diary> getAllReflections() { return reflections; }

    public void addReflection(Diary diary) {
        reflections.add(diary);
    }

    /**
     * addExerciseToDay
     * adds the passed exercise to the current day
     * @param exercise - exercise to add to the day
     */
    public void addExerciseToDay(Exercise exercise) {
        days.get(days.size() - 1).addExercise(exercise);
    }

    /**
     * getExercisesByDay
     * gets a list of the exercises done in the current day
     * @return - the list of exercises done in the current day
     */
    public ArrayList<Exercise> getExercisesByDay() {
        return days.get(days.size() - 1).getExercises();
    }

    /**
     * getWeeklyExerciseCals
     * gets the calories burned by exercise by day for the last up to 7 days
     * @return - the calories burned by exercise by day for the last up to 7 days
     */
    public ArrayList<Double> getWeeklyExerciseCals() {
        ArrayList<Double> dailyCals = new ArrayList<>();

        for (Day day: days) {
            dailyCals.add(day.getExerciseCals());
        }

        return dailyCals;
    }

    /**
     * createDay
     * adds a new blank day to the database which is set as the current day
     */
    public void createDay() {
        Day newDay = new Day(user);

        days.add(newDay);
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
        Day newDay = new Day(user, null, breakfast, lunch, dinner, snack);

        days.add(newDay);
    }

    /**
     * createDay
     * adds a new day with exercises pre-filled-in which is set as the current day
     * @param exercises - the exercises for the day
     */
    public void createDay(ArrayList<Exercise> exercises) {
        Day newDay = new Day(user, exercises, null, null, null, null);

        days.add(newDay);
    }

    /**
     * getUser
     * @return - the user stored in the db
     */
    public User getUser() { return user; }

    /**
     * setUserFoodGoal
     * @param goal - the new water goal
     */
    public void setUserCalorieGoal(int goal) {
        if (goal <= 0) {
            throw new IllegalArgumentException("calorie goal must be positive");
        }
        user.setCalorieGoal(goal);
    }

    /**
     * setUserWaterGoal
     * @param goal - the new water goal
     */
    public void setUserWaterGoal(int goal) {
        if (goal <= 0) {
            throw new IllegalArgumentException("calorie goal must be positive");
        }
        user.setWaterGoal(goal);
    }

    /**
     * getNumOfDays
     * returns the number of days in the db
     * @return - the number of days in the db
     */
    public int getNumOfDays() {
        return days.size();
    }
}
