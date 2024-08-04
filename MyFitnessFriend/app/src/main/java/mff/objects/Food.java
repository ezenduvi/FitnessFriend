package mff.objects;

public class Food {

    private String name;
    private int calories;
    private int carbs;
    private int fat;
    private int protein;
    private double servingSize;
    private Unit unit;
    private double numberOfServings;

    /**
     * Food
     * creates a food using the values passed (used mainly by the programmer to populate the database...
     * with a list of foods to pick from, in future food might be able to be entered by user and include more features)
     * if negative values are entered mistakenly, it makes it positive.
     * @param foodName - the name of the food
     * @param calories - macro for food
     * @param carbs - macro for food
     * @param fat - macro for food
     * @param protein - macro for food
     * @param servingSize - servingSize of food (2 tablespoons of ...)
     * @param unit - unit for food(pieces, tablespoon, oz)
     * @param numberOfServings - could be half a serving, two or more
     */

    public Food(String foodName, int calories, int carbs, int fat, int protein, double servingSize, Unit unit, double numberOfServings){
//        foodID = newID;
        if(foodName == null) {
            this.name = "no-name";
        }
        else {
            name = foodName;
        }

        if(calories<0) {
            this.calories = calories * -1;
        }
        else {
            this.calories = calories;
        }

        if(carbs<0) {
            this.carbs = carbs * -1;
        }
        else {
            this.carbs = carbs;
        }

        if(fat<0) {
            this.fat = fat * -1;
        }
        else {
            this.fat = fat;
        }

        if(protein<0) {
            this.protein = protein * -1;
        }
        else {
            this.protein = protein;
        }

        if(servingSize<0) {
            this.servingSize = servingSize * -1;
        }
        else {
            this.servingSize = servingSize;
        }

        if(unit == null) {
            this.unit = Unit.NONE;
        }
        else {
            this.unit = unit;
        }

        if(numberOfServings<0) {
            this.numberOfServings = numberOfServings * -1;
        }
        else {
            this.numberOfServings = numberOfServings;
        }

    }

    /**
     * Getter methods for food macros and remaining instance variables
     */

    public String getName(){
        return (name);
    }

    public int getCalories(){
        return (calories);
    }

    public int getCarbs(){
        return (carbs);
    }

    public int getFat(){
        return (fat);
    }

    public int getProtein(){
        return (protein);
    }

    public double getServingSize(){
        return (servingSize);
    }

    public Unit getUnit(){
        return (unit);
    }

    public double getNumberOfServings(){
        return (numberOfServings);
    }

}