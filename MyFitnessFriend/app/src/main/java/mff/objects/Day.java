package mff.objects;

import java.util.ArrayList;
import java.util.HashMap;

public class Day {
    private User user;
    private ArrayList<Exercise> exercises;
    private double water;
    private HashMap<String, Meals> meals;
    private final String breakfast = MealType.BREAKFAST.toString();
    private final String lunch = MealType.LUNCH.toString();
    private final String dinner = MealType.DINNER.toString();
    private final String snack = MealType.SNACK.toString();

    /**
     * Day
     * creates a new empty Day object
     * @param user - user associated with day
     * @param exercises - list of the exercises
     * @param breakfast - meal
     * @param lunch - meal
     * @param dinner - meal
     * @param snack - meal
     */
    public Day(User user, ArrayList<Exercise> exercises, Meals breakfast, Meals lunch, Meals dinner, Meals snack){
        this.user = user;
        this.exercises = exercises;
        this.meals = new HashMap<>();
        this.meals.put(this.breakfast, breakfast);
        this.meals.put(this.lunch, lunch);
        this.meals.put(this.dinner, dinner);
        this.meals.put(this.snack, snack);
        water = 0.0;
    }

    /**
     * Day
     * creates a new empty Day object
     * @param user - user associated with day
     */
    public Day(User user){
        this.user = user;
        this.exercises = new ArrayList<>();
        this.meals = new HashMap<>();
        this.meals.put(this.breakfast, new Meals(MealType.BREAKFAST));
        this.meals.put(this.lunch, new Meals(MealType.LUNCH));
        this.meals.put(this.dinner, new Meals(MealType.DINNER));
        this.meals.put(this.snack, new Meals(MealType.SNACK));
        water = 0.0;
    }

    /**
     * getDailyCalories
     * returns total calories from each meal
     * @return int - calories consumed in the day
     * @throws NullPointerException - if any of its meals have the value null
     */
    public double getDailyCalories(){
        double calories = 0.0;
        Meals breakfast = meals.get(this.breakfast);
        Meals lunch = meals.get(this.lunch);
        Meals dinner = meals.get(this.dinner);
        Meals snack = meals.get(this.snack);

        if (breakfast == null || lunch == null || dinner == null || snack == null) {
            throw new NullPointerException("Day has a null meal");
        }

        calories = breakfast.getCalories()+lunch.getCalories()+dinner.getCalories()+snack.getCalories();
        return calories;
    }

    /**
     * getDailyWater
     * returns total water consumed
     * @return int - water consumed in the day
     */
    public double getDailyWater() {
        return water;
    }

    /**
     * addWater
     * add water to water consumed
     * @param add - amount of water to be added
     */
    public void addWater(double add){
        water += add;
    }

    /**
     * getRemaining
     * returns remaining calories
     * @return double - calories remaining
     */
    public double getRemaining(){
        return user.getCalorieGoal() - this.getDailyCalories() + getExerciseCals();
    }

    /**
     * getProtein
     * returns total amount of protein consumed from each meal
     * @return double - protein consumed in the day
     */
    public double getProtein(){
        double protein = 0.0;
        Meals breakfast = meals.get(this.breakfast);
        Meals lunch = meals.get(this.lunch);
        Meals dinner = meals.get(this.dinner);
        Meals snack = meals.get(this.snack);

        if (breakfast == null || lunch == null || dinner == null || snack == null) {
            throw new NullPointerException("Day has a null meal");
        }

        protein = breakfast.getProtein() + lunch.getProtein() + dinner.getProtein() + snack.getProtein();
        return  protein;
    }

    /**
     * getCarb
     * returns total amount of carbs consumed from each meal
     * @return double - carbs consumed in the day
     */
    public double getCarb(){
        double carb = 0.0;
        Meals breakfast = meals.get(this.breakfast);
        Meals lunch = meals.get(this.lunch);
        Meals dinner = meals.get(this.dinner);
        Meals snack = meals.get(this.snack);

        if (breakfast == null || lunch == null || dinner == null || snack == null) {
            throw new NullPointerException("Day has a null meal");
        }

        carb = breakfast.getCarbs() + lunch.getCarbs() + dinner.getCarbs() + snack.getCarbs();
        return  carb;
    }

    /**
     * getFat
     * returns total amount of fat consumed from each meal
     * @return double - fat consumed in the day
     */
    public double getFat(){
        double fat = 0.0;
        Meals breakfast = meals.get(this.breakfast);
        Meals lunch = meals.get(this.lunch);
        Meals dinner = meals.get(this.dinner);
        Meals snack = meals.get(this.snack);

        if (breakfast == null || lunch == null || dinner == null || snack == null) {
            throw new NullPointerException("Day has a null meal");
        }

        fat = breakfast.getFat() + lunch.getFat() + dinner.getFat() + snack.getFat();
        return fat;
    }

    /**
     * getProteinPer
     * returns number of calories from protein
     * @return double - calories from protein consumed in the day
     */
    public double getProteinPer(){
        double result = 0.0;
        result = (this.getProtein() * 9)/this.getDailyCalories();
        return result;
    }

    /**
     * getCarbPer
     * returns number of calories from carbs
     * @return double - calories from carbs consumed in the day
     */
    public double getCarbPer(){
        double result = 0.0;
        result = (this.getCarb() * 4)/this.getDailyCalories();
        return result;
    }

    /**
     * getFatPer
     * returns number of calories from fat
     * @return double - calories from fat consumed in the day
     */
    public double getFatPer(){
        double result = 0.0;
        result = (this.getFat() * 4)/this.getDailyCalories();
        return result;
    }

    /**
     * getUser
     * @return User - returns the contained user object
     */
    public User getUser(){ return user; }

    /**
     * getExercises
     * @return ArrayList<Exercise> - all the exercises in this day
     */
    public ArrayList<Exercise> getExercises() { return exercises; }

    /**
     * addExercise
     * @param newExercise - adds this exercise to the list of exercises in this day
     * @throws IllegalArgumentException - if null is passed is the new exercise
     */
    public void addExercise(Exercise newExercise) {
        if (newExercise == null) {
            throw new IllegalArgumentException("null exercise can not be added to day");
        } else {
            exercises.add(newExercise);
        }
    }

    /**
     * getExerciseCals
     * @return double - returns the number of class burned by all of the exercises performed this day
     */
    public double getExerciseCals() {
        double sumCals = 0;

        for (Exercise exercise : exercises) {
            sumCals += exercise.getCaloriesPerHour() * (double)exercise.getDuration() / 60;
        }

        return sumCals;
    }

    /**
     * addMeal
     * @param meal - the meal to be added to this day
     * @param type - what meal of the day this should be set as
     * @throws IllegalArgumentException - if meal or type is null
     */
    public void addMeal(Meals meal, MealType type) {
        if (meal == null || type == null) {
            throw new IllegalArgumentException("neither meal or type can be null");
        }

        meals.remove(type.toString());
        meals.put(type.toString(), meal);
    }

    /**
     * getMeal
     * @param type - the type of meal to get from this day
     * @return Meals - the meal in this day of the type passed
     * @throws IllegalArgumentException - if type is null
     */
    public Meals getMeal(MealType type) {
        if (type == null) {
            throw new IllegalArgumentException("type can not be null");
        }

        return meals.get(type.toString());
    }

} //Day class
