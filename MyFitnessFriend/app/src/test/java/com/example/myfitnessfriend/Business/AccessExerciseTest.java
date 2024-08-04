package com.example.myfitnessfriend.Business;

import org.junit.Test;

import mff.application.Main;
import mff.application.Services;
import mff.business.AccessExercise;
import mff.objects.Exercise;
import mff.objects.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.util.ArrayList;

public class AccessExerciseTest {
    private String dbName = Main.dbName;
    private ArrayList<Exercise> stubExercises = addExercisesToStubList();


    // A small stub for a list of exercises for testing purposes
    private ArrayList<Exercise> addExercisesToStubList() {
        Exercise exercise;
        ArrayList<Exercise> stubExercises = new ArrayList<>();

        exercise = new Exercise("Running slow",10,100);
        stubExercises.add(exercise);
        exercise = new Exercise("Weights",1,1);
        stubExercises.add(exercise);
        exercise = new Exercise("Jogging",20,200);
        stubExercises.add(exercise);

        return stubExercises;
    }

    @Test
    public void testGetAllExercises() {

        Services.createDataAccess(Main.dbName);
        System.out.println("\nStarting test getAllExercises()");

        AccessExercise accessExercise = new AccessExercise();
        ArrayList<Exercise> allExercises = accessExercise.getAllExercises();
        assertEquals(11,allExercises.size());
        assertEquals("Weights",allExercises.get(1).getName());

        Services.closeDataAccess();
        System.out.println("\nFinished test getAllExercises()");
    }

    @Test
    public void testGetExerciseNamesOnlyExpectedValues() {
        Services.createDataAccess(Main.dbName);
        System.out.println("\nStarting test getExerciseNamesOnly()");

        AccessExercise accessExercise = new AccessExercise();
        ArrayList<Exercise> allExercises = accessExercise.getAllExercises();
        ArrayList<String> namesOnly = accessExercise.getExerciseNamesOnly(allExercises);
        assertEquals(11, allExercises.size());
        assertEquals("Jogging", namesOnly.get(2));


        namesOnly = accessExercise.getExerciseNamesOnly(stubExercises);
        assertEquals(3, namesOnly.size());
        assertEquals("Running slow", namesOnly.get(0));

        Services.closeDataAccess();
        System.out.println("\nFinished test getExerciseNamesOnly()");
    }

    @Test
    public void testGetExercises(){

        Services.createDataAccess(Main.dbName);
        System.out.println("\nStarting test getExercises()");

        AccessExercise accessExercise = new AccessExercise();
        assertEquals(accessExercise.getAllExercises().size(),11);
        assertEquals("Running slow", accessExercise.getAllExercises().get(0).getName());
        assertEquals("Sprinting", accessExercise.getAllExercises().get(accessExercise.getAllExercises().size() - 1).getName());

        Services.closeDataAccess();
        System.out.println("\nFinished test getExercises()");
    }

    @Test
    public void testGetDurationList(){

        Services.createDataAccess(Main.dbName);
        System.out.println("\nStarting test getDurationList()");

        AccessExercise accessExercise = new AccessExercise();
        assertEquals(accessExercise.getDurationList().size(),24);
        int sum = 0;
        for(int i = 0;i < accessExercise.getDurationList().size() - 1;i++){
            sum += accessExercise.getDurationList().get(i);
        }
        assertEquals(sum,1380);
        assertEquals(accessExercise.getDurationList().get(1) - accessExercise.getDurationList().get(0), 5);

        Services.closeDataAccess();
        System.out.println("\nFinished test getDurationList()");

    }
    @Test
    public void testGetTotalCaloriesBurned(){

        Services.createDataAccess(Main.dbName);
        System.out.println("\nStarting test getTotalCaloriesBurned()");

        int totalCaloriesBurned = 0;
        AccessExercise accessExercise = new AccessExercise();

        totalCaloriesBurned = accessExercise.getTotalCaloriesBurned(stubExercises);
        assertEquals(82, totalCaloriesBurned);

        Services.closeDataAccess();
        System.out.println("\nFinished test getTotalCaloriesBurned()");
    }

    @Test
    public void testGetExerciseExpectedValues() {

        Services.createDataAccess(Main.dbName);
        System.out.println("\nStarting test testGetExerciseExpectedValues()");

        AccessExercise accessExercise = new AccessExercise();
        Exercise exercise;
        String exerciseName = "Walking slow";

        exercise = accessExercise.getExercise(exerciseName);
        assertEquals("Walking slow", exercise.getName());

        Services.closeDataAccess();
        System.out.println("\nFinished test testGetExerciseExpectedValues()");

    }

    @Test
    public void testGetExerciseUnexpectedValues() {

        Services.createDataAccess(Main.dbName);
        System.out.println("\nStarting test testGetExerciseUnexpectedValues()");

        AccessExercise accessExercise = new AccessExercise();
        Exercise exercise;
        String exerciseName = "Jump";

        exercise = accessExercise.getExercise(exerciseName);
        assertEquals(null, exercise);

        Services.closeDataAccess();
        System.out.println("\nFinished test testGetExerciseUnexpectedValues()");

    }

    @Test
    public void testGetDurationsOnlyExpectedValues() {

        Services.createDataAccess(Main.dbName);
        System.out.println("\nStarting test testGetDurationsOnlyExpectedValues()");

        AccessExercise accessExercise = new AccessExercise();

        ArrayList<String> durationsPerExercise = accessExercise.getDurationsOnly(stubExercises);
        assertEquals("10 min", durationsPerExercise.get(0));
        assertEquals("20 min", durationsPerExercise.get(2));

        Services.closeDataAccess();
        System.out.println("\nFinished test testGetDurationsOnlyExpectedValues()");

    }

    @Test
    public void testGetDurationsOnlyUnexpectedValues() {

        Services.createDataAccess(Main.dbName);
        System.out.println("\nStarting test testGetDurationsOnlyUnexpectedValues()");

        AccessExercise accessExercise = new AccessExercise();

        ArrayList<String> durationsPerExercise = accessExercise.getDurationsOnly(stubExercises);
        assertEquals("1 min", durationsPerExercise.get(1));

        Services.closeDataAccess();
        System.out.println("\nFinished test testGetDurationsOnlyUnexpectedValues()");

    }

    @Test
    public void testGetCaloriesPerExerciseExpectedValues() {

        Services.createDataAccess(Main.dbName);
        System.out.println("\nStarting test testGetCaloriesPerExerciseExpectedValues()");

        AccessExercise accessExercise = new AccessExercise();

        ArrayList<String> caloriesPerExercise = accessExercise.getCaloriesPerExercise(stubExercises);
        assertEquals("16", caloriesPerExercise.get(0));
        assertEquals("66", caloriesPerExercise.get(2));

        Services.closeDataAccess();
        System.out.println("\nFinished test testGetCaloriesPerExerciseExpectedValues()");

    }

    @Test
    public void testGetCaloriesPerExerciseUnexpectedValues() {

        Services.createDataAccess(Main.dbName);
        System.out.println("\nStarting test testGetCaloriesPerExerciseUnexpectedValues()");

        AccessExercise accessExercise = new AccessExercise();

        ArrayList<String> caloriesPerExercise = accessExercise.getCaloriesPerExercise(stubExercises);
        assertEquals("0", caloriesPerExercise.get(1));

        Services.closeDataAccess();
        System.out.println("\nFinished test testGetCaloriesPerExerciseUnexpectedValues()");

    }

    @Test
    public void testAddExerciseToDayExpected() {

        Services.createDataAccess(Main.dbName);
        System.out.println("\nStarting test testAddExerciseToDayExpected()");

        AccessExercise accessExercise = new AccessExercise();
        Exercise exercise = new Exercise("Jogging",30,120);
        ArrayList<Exercise> exercises;

        accessExercise.addExerciseToDay(exercise);
        exercises = accessExercise.getExercisesByDay();
        assertEquals(1,exercises.size());
        assertEquals("Jogging", exercises.get(0).getName());

        Services.closeDataAccess();
        System.out.println("\nFinished test testAddExerciseToDayExpected()");

    }

    @Test
    public void testAddExerciseToDayUnexpected() {

        Services.createDataAccess(Main.dbName);
        System.out.println("\nStarting test testAddExerciseToDayUnexpected()");

        AccessExercise accessExercise = new AccessExercise();
        Exercise exercise = new Exercise("Run",30,120);
        ArrayList<Exercise> exercises;

        accessExercise.addExerciseToDay(exercise);
        exercises = accessExercise.getExercisesByDay();
        assertEquals(1,exercises.size());
        assertEquals("Run", exercises.get(0).getName());

        Services.closeDataAccess();
        System.out.println("\nFinished test testAddExerciseToDayUnexpected()");

    }

}
