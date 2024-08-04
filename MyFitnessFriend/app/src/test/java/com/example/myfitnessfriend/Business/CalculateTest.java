package com.example.myfitnessfriend.Business;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import mff.business.Calculate;

public class CalculateTest {
    //test methods for DailySummaryActivity
    @Test
    public void testGetProteinCal(){
        System.out.println("\nStarting testGetProteinCal");
        assertEquals(2.0, Calculate.getProteinCal(1.0f, 2.0f), 0.0);
        System.out.println("Finished testGetProteinCal");
    }
    @Test
    public void testGetCarbsCal(){
        System.out.println("\nStarting testGetCabsCal");
        assertEquals(2.0, Calculate.getCarbsCal(1.0f, 2.0f), 0.0);
        System.out.println("Finished testGetCabsCal");
    }
    @Test
    public void testGetFatCal(){
        System.out.println("\nStarting testGetFatCal");
        assertEquals(4.5, Calculate.getFatCal(1.0f, 2.0f), 0.0);
        System.out.println("Finished testGetFatCal");
    }

    //test methods for ExercisePageActivity
    @Test
    public void testValidInputsGetCalBurned(){
        System.out.println("\nStarting testValidInputsGetCalBurned");
        int duration = 1;
        int caloriesBurnedPerHour = 1;
        assertEquals(0,Calculate.getCalBurned(duration,caloriesBurnedPerHour));

        duration = 1;
        caloriesBurnedPerHour = 100;
        assertEquals(1,Calculate.getCalBurned(duration,caloriesBurnedPerHour));

        duration = 20;
        caloriesBurnedPerHour = 100;
        assertEquals(33,Calculate.getCalBurned(duration,caloriesBurnedPerHour));

        duration = 60;
        caloriesBurnedPerHour = 225;
        assertEquals(225,Calculate.getCalBurned(duration,caloriesBurnedPerHour));

        duration = 120;
        caloriesBurnedPerHour = 250;
        assertEquals(500,Calculate.getCalBurned(duration,caloriesBurnedPerHour));

        System.out.println("Finished testValidInputsGetCalBurned");
    }

    @Test
    public void testInvalidInputsGetCalBurned(){
        System.out.println("\nStarting testInvalidInputsGetCalBurned");
        int duration = 0;
        int caloriesBurnedPerHour = 0;
        assertEquals(0,Calculate.getCalBurned(duration,caloriesBurnedPerHour));

        duration = -1;
        caloriesBurnedPerHour = 0;
        assertEquals(0,Calculate.getCalBurned(duration,caloriesBurnedPerHour));

        duration = -1;
        caloriesBurnedPerHour = -5;
        assertEquals(0,Calculate.getCalBurned(duration,caloriesBurnedPerHour));

        duration = 121;
        caloriesBurnedPerHour = 250;
        assertEquals(0,Calculate.getCalBurned(duration,caloriesBurnedPerHour));

        duration = 100;
        caloriesBurnedPerHour = 0;
        assertEquals(0,Calculate.getCalBurned(duration,caloriesBurnedPerHour));

        System.out.println("Finished testInvalidInputsGetCalBurned");
    }

    @Test
    public void testValidCalculateRemainingCalories() {
        System.out.println("\nStarting testValidCalculateRemainingCalories");
        int goal = 1500;
        int foodCalories = 300;
        int exerciseCalories = 100;
        assertEquals(1300, Calculate.calculateRemainingCalories(goal,foodCalories,exerciseCalories));

        goal = 1500;
        foodCalories = 0;
        exerciseCalories = 0;
        assertEquals(1500, Calculate.calculateRemainingCalories(goal,foodCalories,exerciseCalories));

        goal = 0;
        foodCalories = 0;
        exerciseCalories = 0;
        assertEquals(0, Calculate.calculateRemainingCalories(goal,foodCalories,exerciseCalories));

        goal = 0;
        foodCalories = 200;
        exerciseCalories = 75;
        assertEquals(-125, Calculate.calculateRemainingCalories(goal,foodCalories,exerciseCalories));

        goal = 1;
        foodCalories = 200;
        exerciseCalories = 75;
        assertEquals(-124, Calculate.calculateRemainingCalories(goal,foodCalories,exerciseCalories));

        System.out.println("Finished testValidCalculateRemainingCalories");
    }

    @Test
    public void testInvalidCalculateRemainingCalories() {
        System.out.println("\nStarting testInvalidCalculateRemainingCalories");
        int goal = -1;
        int foodCalories = 300;
        int exerciseCalories = 100;
        assertEquals(0, Calculate.calculateRemainingCalories(goal,foodCalories,exerciseCalories));

        goal = -100;
        foodCalories = 200;
        exerciseCalories = 75;
        assertEquals(0, Calculate.calculateRemainingCalories(goal,foodCalories,exerciseCalories));

        System.out.println("Finished testInvalidCalculateRemainingCalories");
    }

}
