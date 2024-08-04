package mff.business;

import java.util.ArrayList;

import mff.application.Main;
import mff.application.Services;
import mff.objects.Food;
import mff.objects.MealType;
import mff.objects.Meals;
import mff.persistence.Database;

public class AccessFoods {
    private Database dataAccess;
    private ArrayList<Food> foods;

    /**
     * AccessFoods
     * gets database and sets food list
     */
    public AccessFoods() {
        dataAccess = Services.getDataAccess(Main.dbName);
        foods = null;
    }

    /**
     * getAllFood
     * returns array list with all foods
     * @return ArrayList - food list
     */
    public ArrayList<Food> getAllFood() {

        return dataAccess.getAllFood();

    }

    /**
     * getFoodPosition
     * returns position (index) of specific food object in list
     * @param name - name of object being searching for
     * @return int - index of food object
     */
    public int getFoodPosition(String name){
        ArrayList<Food> foods = this.getAllFood();

        for(int i=0; i<foods.size(); i++){

            Food currFood = foods.get(i);

            if(currFood.getName().equals(name)){
                return i;
            }
        }
        return -1;

    }

    /**
     * addMealToDay
     * adds a meal of the passed type to the current day
     * @param toAdd - the meal to be added
     * @param type - the type of meal to be added
     */
    public void addMealToDay(Meals toAdd, MealType type) {

        dataAccess.addMealToDay(toAdd, type);
        System.out.println("added meal to day accessFoods");
    }

    /**
     * getMealFromDay
     * gets the meal of the passed type from the current day
     * @param type - the type of meal to get from the current day
     * @return - the meal of the passed type from the current day
     */
    public Meals getMealFromDay(MealType type) {
        return dataAccess.getMealFromDay(type);
    }

    /**
     * getWeeklyFoodCals
     * gets the calories gained from food by day for up to the past 7 days
     * @return - the calories gained from food by day for up to the past 7 days
     */
    public ArrayList<Double> getWeeklyFoodCals() {
        return dataAccess.getWeeklyFoodCals();
    }
}
