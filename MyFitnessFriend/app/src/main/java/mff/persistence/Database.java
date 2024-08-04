package mff.persistence;

import java.util.ArrayList;


import mff.objects.Diary;
import mff.objects.Food;
import mff.objects.MealType;
import mff.objects.Meals;
import mff.objects.User;
import mff.objects.Exercise;

public interface Database {

        /**
         * open
         * populates the stub database with different food objects
         * @param dbName - the name of the stub database
         */
        void open(String dbName);

        /**
         * close
         * prints to console that the stub database has been closed
         */
        void close();

        /**
         * return all of the diary entries
         * @return - the diary reflections
         */
        ArrayList<Diary> getAllReflections();

        /**
         * add reflection to diary
         */
        void addReflection(Diary diary);

        /**
         * getUser
         * @return - the user stored in the db
         */
        User getUser();

        /**
         * setUserFoodGoal
         * @param goal - the new water goal
         */
        void setUserCalorieGoal(int goal);

        /**
         * setUserWaterGoal
         * @param goal - the new water goal
         */
        void setUserWaterGoal(int goal);

        /**
         * return all of the food options
         * @return - the food options
         */
        ArrayList<Food> getAllFood();

        /**
         * addMealToDay
         * sets the passed meal to the the current day in the correct type
         * @param meal - the meal to be added
         * @param type - the type of the meal to be added
         */
        void addMealToDay(Meals meal, MealType type);

        /**
         * getMealFromDay
         * gets the meal of the passed type from the current day
         * @param type - the type of the meal to get from the current day
         * @return Meals - the meal from the current day of the passed type
         */
        Meals getMealFromDay(MealType type);

        /**
         * getWeeklyFoodCals
         * gets the calories gained from food by day from the past up to 7 days
         * @return ArrayList<double> - the list of food by day from the past up to 7 days
         */
        ArrayList<Double> getWeeklyFoodCals();

        /**
         * getAllExercises
         * return all of the exercises
         * @return - the exercises
         */
        ArrayList<Exercise> getAllExercises();

        /**
         * addExerciseToDay
         * adds the passed exercise to the current day
         * @param toAdd - exercise to add to the day
         */
        void addExerciseToDay(Exercise toAdd);

        /**
         * getExercisesByDay
         * gets a list of the exercises done in the current day
         * @return - the list of exercises done in the current day
         */
        ArrayList<Exercise> getExercisesByDay();

        /**
         * getWeeklyExerciseCals
         * gets the calories burned by exercise by day for the last up to 7 days
         * @return - the calories burned by exercise by day for the last up to 7 days
         */
        ArrayList<Double> getWeeklyExerciseCals();

        /**
         * createDay
         * adds a new blank day to the database which is set as the current day
         */
        void createDay();

        /**
         * createDay
         * adds a new day with meals pre-filled-in which is set as the current day
         * @param breakfast - the breakfast for the day
         * @param lunch - the lunch for the day
         * @param dinner - the dinner for the day
         * @param snack - the snack for the day
         */
        void createDay(Meals breakfast, Meals lunch, Meals dinner, Meals snack);

        /**
         * createDay
         * adds a new day with exercises pre-filled-in which is set as the current day
         * @param exercises - the exercises for the day
         */
        void createDay(ArrayList<Exercise> exercises);

        /**
         * getNumOfDays
         * returns the number of days in the db
         * @return - the number of days in the db
         */
        int getNumOfDays();

}
