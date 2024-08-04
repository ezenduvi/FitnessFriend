package mff.objects;

import java.util.ArrayList;

public class Meals {
    private ArrayList<Food> foods;
    private MealType type;

    /**
     * Meals
     * creates a new empty meal of the passed type
     * @param type - the type of the meal
     * @throws IllegalArgumentException - if we try to make this object with a type of null
     */
    public Meals(MealType type) {
        if (type != null) {
            this.type = type;
            foods = new ArrayList<>();
        } else {
            throw new IllegalArgumentException("Can not create meal with type null");
        }
    }

    /**
     * Meals
     * creates a new meal with the passed food and type
     * @param type - the type of the meal
     * @param foods - a list of foods to put in the meal initially
     * @throws IllegalArgumentException - if we try to make this object with a type of null
     */
    public Meals(MealType type, ArrayList<Food> foods) {
        if (type != null) {
            this.type = type;

            if (foods != null) {
                this.foods = foods;
            } else {
                this.foods = new ArrayList<>();
            }
        } else {
            throw new IllegalArgumentException("Can not create meal with type null");
        }
    }

    /**
     * getType
     * the type of this meal
     * @return - the type of this meal
     */
    public MealType getType() {
        return type;
    }

    /**
     * getCalories()
     * returns the total calories of all foods in the meal
     * @return - the total calories of all foods in the meal
     */
    public double getCalories() {
        double sumCals = 0;

        for (int i = 0; i < foods.size(); i++) {
            sumCals += foods.get(i).getCalories();
        }

        return sumCals;
    }

    /**
     * getCarbs()
     * returns the total carbs of all foods in the meal
     * @return - the total carbs of all foods in the meal
     */
    public double getCarbs() {
        double sumCarbs = 0;

        for (int i = 0; i < foods.size(); i++) {
            sumCarbs += foods.get(i).getCarbs();
        }

        return sumCarbs;
    }

    /**
     * getFat()
     * returns the total fat of all foods in the meal
     * @return - the total fat of all foods in the meal
     */
    public double getFat() {
        double sumFat = 0;

        for (int i = 0; i < foods.size(); i++) {
            sumFat += foods.get(i).getFat();
        }

        return sumFat;
    }

    /**
     * getProtein()
     * returns the total protein of all foods in the meal
     * @return - the total protein of all foods in the meal
     */
    public double getProtein() {
        double sumProtein = 0;

        for (int i = 0; i < foods.size(); i++) {
            sumProtein += foods.get(i).getProtein();
        }

        return sumProtein;
    }

    /**
     * addFood
     * adds a food to the meal
     * @param newFood - the food to be added
     * @throws NullPointerException - Can not add null to the meal
     */
    public void addFood(Food newFood) {
        if (newFood != null) {
            foods.add(newFood);
        } else {
            throw new NullPointerException("Can not add null to the meal");
        }
    }

    /**
     * removeFood
     * removes a food at the given index from the meal
     * @param index - the index of the food to be removed
     * @return - the object that was removed
     * @throws IndexOutOfBoundsException - if we try to remove a food that is not in the meal
     */
    public Food removeFood(int index) {
        Food toReturn;
        if (index >= 0 && index < foods.size()){
            toReturn = foods.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Given index out of bounds");
        }
        return toReturn;
    }

    /**
     * removeFood
     * removes a specified food from the meal
     * @param food - the food to be removed
     * @throws NullPointerException - if we try to remove food of type null
     */
    public boolean removeFood(Food food) {
        if (food == null) {
            throw new NullPointerException("Can not use null as object to find in list");
        }

        return foods.remove(food);
    }

    /**
     * getFood
     * @param index - the index of the food to be returned
     * @return - the food to be returned
     * @throws IndexOutOfBoundsException - if the meal does not contain a food at the given index
     */
    public Food getFood(int index) {
        Food toReturn;
        System.out.println("\nindex: " + index);
        System.out.println("\nfoods size: " + foods.size());
        if (index >= 0 && index < foods.size()){
            toReturn = foods.get(index);
        } else {
            throw new IndexOutOfBoundsException("Given index out of bounds");
        }

        return toReturn;
    }

    /**
     * getNumFood
     * @return - the number of foods in the meal
     */
    public int getNumFood() { return foods.size(); }

    public void printMeal() {
        System.out.println("Printing meal " + getType());
        for(int i=0; i<foods.size(); i++){
            Food food = foods.get(i);
            System.out.println(i + ": " + food.getName()+"\n");
        }
    }
}
