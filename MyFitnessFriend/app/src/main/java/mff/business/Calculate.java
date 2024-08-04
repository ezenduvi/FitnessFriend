package mff.business;

public class Calculate {

    /**
     * getProteinCal
     * calculate calories from protein
     * @param proteinConsumed - amount of protein consumed
     * @param totalCaloriesConsumed - total calories consumed
     * @return float - calories from protein
     */
    public static float getProteinCal(float proteinConsumed, float totalCaloriesConsumed){
        return ((float)proteinConsumed*4)/(float)totalCaloriesConsumed;
    }

    /**
     * getCarbsCal
     * calculate calories from carbs
     * @param carbsConsumed - amount of carbs consumed
     * @param totalCaloriesConsumed - total calories consumed
     * @return float - calories from carbs
     */
    public static float getCarbsCal(float carbsConsumed, float totalCaloriesConsumed){
        return ((float)carbsConsumed*4)/(float)totalCaloriesConsumed;
    }

    /**
     * getFatCal
     * calculate calories from fat
     * @param fatConsumed - amount of fat consumed
     * @param totalCaloriesConsumed - total calories consumed
     * @return float - calories from fat
     */
    public static float getFatCal(float fatConsumed, float totalCaloriesConsumed){
        return ((float)fatConsumed*9)/(float)totalCaloriesConsumed;
    }

    /**
     * getCalBurned
     * calculate calories burned from a specific duration of an exercise
     * @param duration - duration of exercise
     * @param caloriesBurnedPerHour - calories burned per hour
     * @return float - total calories burned
     */
    public static int getCalBurned(int duration, int caloriesBurnedPerHour){
        if(duration > 0 && duration <= 120 && caloriesBurnedPerHour > 0)
            return Math.round((duration * caloriesBurnedPerHour)/60);
        else
            return 0;
    }

    /**
     * calculateRemainingCalories
     * calculate calories burned from a specific duration of an exercise
     * @param goal - maximum amount of calories to consume in a day
     * @param foodCalories - calories consumed through food
     * @param exerciseCalories - calories burned per hour
     * @return int - remaining calories that can be consumed for the day
     */
    public static int calculateRemainingCalories(int goal, int foodCalories, int exerciseCalories){
        if(goal >= 0)
            return goal - foodCalories + exerciseCalories;
        else
            return 0;
    }

}
