package mff.business;

import java.util.ArrayList;

import mff.application.Main;
import mff.application.Services;
import mff.objects.Exercise;
import mff.persistence.Database;
import mff.business.Calculate;


public class AccessExercise {

    private static Database dataAccess;
    private static ArrayList<Exercise> exercises;

    /**
     * AccessExercise
     * gets database and sets exercise list
     */
    public AccessExercise() {
        dataAccess = Services.getDataAccess(Main.dbName);
        exercises = dataAccess.getAllExercises();
    }

    /**
     * getAllExercises
     * returns array list with all exercises
     * @return ArrayList - exercise list
     */
    public static ArrayList<Exercise> getAllExercises() {
        return exercises;
    }

    /**
     * getExerciseNamesOnly
     * returns array list with the names (strings) of all exercises
     * @return ArrayList - list of exercise names as Strings
     */
    public static ArrayList<String> getExerciseNamesOnly(ArrayList<Exercise> exercises) {
        ArrayList<String> exerciseNamesOnly = new ArrayList<>();
        Exercise currExercise;
        String exercise;

        for(int i = 0; i < exercises.size(); i++) {
            currExercise = exercises.get(i);
            exercise = currExercise.getName();
            exerciseNamesOnly.add(exercise);
        }

        return exerciseNamesOnly;
    }

    /**
     * getDurationsOnly
     * extracts the durations and appends "min" to each exercise in the list passed to it.
     * @param exercisesDone - list of exercises done
     * @return ArrayList - list of durations for exercises done
     */
    public static ArrayList<String> getDurationsOnly(ArrayList<Exercise> exercisesDone) {
        ArrayList<String> durationsOnly = new ArrayList<>();
        Exercise currExercise;
        String duration;

        for(int i = 0; i < exercisesDone.size(); i++) {
            currExercise = exercisesDone.get(i);
            duration = String.valueOf(currExercise.getDuration());
            duration += " min";
            durationsOnly.add(duration);
        }

        return durationsOnly;
    }

    /**
     * getCaloriesPerExercise
     * calls method from Calculate class to calculate the calories burned
     * for each of the exercises in the array passed to it.
     * @param exercisesDone - list of exercises done
     * @return ArrayList - list of the calories burned per exercise done
     */
    public ArrayList<String> getCaloriesPerExercise(ArrayList<Exercise> exercisesDone) {
        ArrayList<String> caloriesPerExercise = new ArrayList<>();
        Exercise currExercise;
        int duration = 0;
        int caloriesBurnedPerHour = 0;
        String calories;

        for(int i = 0; i < exercisesDone.size(); i++) {
            currExercise = exercisesDone.get(i);
            duration = currExercise.getDuration();
            caloriesBurnedPerHour = currExercise.getCaloriesPerHour();
            calories = String.valueOf(Calculate.getCalBurned(duration, caloriesBurnedPerHour));
            caloriesPerExercise.add(calories);
        }

        return caloriesPerExercise;
    }

    /**
     * getTotalCaloriesBurned
     * sums the calories burned by the exercises done in the passed list
     * @return int - number of total calories burned
     */
    public int getTotalCaloriesBurned(ArrayList<Exercise> exercisesDone) {
        ArrayList<String> individualCalories = getCaloriesPerExercise(exercisesDone);

        int sum = 0;

        for(int i = 0; i < individualCalories.size(); i++) {
            sum += Integer.valueOf(individualCalories.get(i));
        }

        return sum;
    }

    /**
     * getDurationList
     * returns array list with the duration of all exercises
     * @return ArrayList - list of duration of all exercises
     */
    public static ArrayList<Integer> getDurationList() {
        ArrayList<Integer> exerciseDurations = new ArrayList<>();
        int duration;

        for(duration = 5; duration <= 120; duration += 5) {
            exerciseDurations.add(duration);
        }

        return exerciseDurations;
    }

    /**
     * getExercise
     * returns Exercise object given an exercise name
     * @param exercise - name of object to be returned
     * @return Exercise - exercise object that matches name
     */
    public static Exercise getExercise(String exercise) {
        int index = findIndex(exercise);
        if(index < exercises.size())
            return exercises.get(index);
        else
            return null;
    }

    /**
     * findIndex
     * returns position (index) of specific exercise object in list
     * @param exercise - name of object being searching for
     * @return int - index of exercise object
     */
    public static int findIndex(String exercise) {
        boolean exerciseFound = false;
        int index = 0;

        while(index < exercises.size() && !exerciseFound) {
            Exercise currExercise = exercises.get(index);
            if(currExercise.getName().equals(exercise)) {
                exerciseFound = true;
            } else {
                index++;
            }
        }

        return index;
    }

    /**
     * addExerciseToDay
     * adds the passed exercise to the current day
     * @param toAdd - the exercise to be added
     */
    public static void addExerciseToDay(Exercise toAdd) {
        dataAccess.addExerciseToDay(toAdd);
    }

    /**
     * getExercisesFromDay
     * gets all exercises from the current day
     * @return - all exercises from the current day
     */
    public static ArrayList<Exercise> getExercisesByDay() {
        return dataAccess.getExercisesByDay();
    }

    /**
     * getWeeklyExerciseCals
     * gets a list of the calories burned by exercise by day for up to 7 days
     * @return - a list of the calories burned by exercise by day for up to 7 days
     */
    public ArrayList<Double> getWeeklyExerciseCals() {
        return dataAccess.getWeeklyExerciseCals();
    }
}
