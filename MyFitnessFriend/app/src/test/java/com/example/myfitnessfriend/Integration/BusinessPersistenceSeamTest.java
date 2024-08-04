package com.example.myfitnessfriend.Integration;

import junit.framework.TestCase;

import java.util.ArrayList;

import mff.application.Main;
import mff.application.Services;
import mff.business.AccessDay;
import mff.business.AccessExercise;
import mff.business.AccessFoods;
import mff.business.AccessProfile;
import mff.objects.Exercise;
import mff.objects.Food;
import mff.objects.MealType;
import mff.objects.Meals;
import mff.objects.User;
import mff.persistence.Database;
import mff.persistence.HSQLDB;

public class BusinessPersistenceSeamTest extends TestCase {
    private Database database;

    public BusinessPersistenceSeamTest(String arg0)
    {
        super(arg0);
    }

    public void setUpRealDb() {

        Services.createDataAccess(new HSQLDB(Main.dbName));
        database = Services.getDataAccess(Main.dbName);
        ((HSQLDB)database).createSaveState();

    }

    public void tearDownRealDb() {

        ((HSQLDB)database).rollbackSaveState();
        Services.closeDataAccess();
        System.out.println("\nFinished Persistence test Database (using real)");

    }

    public void testAccessExerciseStubDB() {

        AccessExercise accessExercisee;
        Exercise exercise;
        String exerciseName;
        ArrayList<Exercise> listOfAllExercises;
        ArrayList<String> exerciseNames;
        int index = 0;

        System.out.println("\nStarting Integration test of AccessExercise to persistence (stub)");

        Services.createDataAccess(Main.dbName);

        accessExercisee = new AccessExercise();

        exercise = accessExercisee.getExercise("Skipping");
        assertEquals("Skipping",exercise.getName());
        assertEquals(37,exercise.getCaloriesPerHour());

        // testing for exercise that doesn't exist in stub db
        exercise = accessExercisee.getExercise("Jump");
        assertNull(exercise);

        exerciseName = "Burpees";
        assertEquals(8,accessExercisee.findIndex(exerciseName));

        listOfAllExercises = accessExercisee.getAllExercises();
        index = 3;
        assertTrue("Jogging on the spot".equals(listOfAllExercises.get(index).getName()));

        exerciseNames = accessExercisee.getExerciseNamesOnly(listOfAllExercises);
        assertTrue("Jogging on the spot".equals(exerciseNames.get(index)));

        Services.closeDataAccess();

        System.out.println("Finished Integration test of AccessExercise to persistence (stub)");

    }

    public void testAccessExerciseRealDB() {

        AccessExercise accessExercisee;
        Exercise exercise;
        String exerciseName;
        ArrayList<Exercise> listOfAllExercises;
        ArrayList<String> exerciseNames;
        int index = 0;

        Services.closeDataAccess();

        System.out.println("\nStarting Integration test of AccessExercise to persistence (real)");

        setUpRealDb();

        accessExercisee = new AccessExercise();

        exercise = accessExercisee.getExercise("Stair machine");
        assertEquals("Stair machine",exercise.getName());
        assertEquals(633,exercise.getCaloriesPerHour());

        // testing for exercise that doesn't exist in real db
        exercise = accessExercisee.getExercise("Jump");
        assertNull(exercise);

        exerciseName = "Ski machine";
        assertEquals(25,accessExercisee.findIndex(exerciseName));

        listOfAllExercises = accessExercisee.getAllExercises();
        index = 10;
        assertTrue("Stationary cycling light".equals(listOfAllExercises.get(index).getName()));

        exerciseNames = accessExercisee.getExerciseNamesOnly(listOfAllExercises);
        assertTrue("Stationary cycling light".equals(exerciseNames.get(index)));

        tearDownRealDb();

        System.out.println("Finished Integration test of AccessExercise to persistence (real)");

    }

    public void testAccessFoodsStubDB() {

        AccessFoods accessFoods;
        Food food1;
        Food food2;
        int foodPos;
        ArrayList<Food> listOfAllFoods;
        Meals meal;
        Meals mealFromDB;

        System.out.println("\nStarting Integration test of AccessFoods to persistence (stub)");

        Services.createDataAccess(Main.dbName);

        accessFoods = new AccessFoods();
        listOfAllFoods = accessFoods.getAllFood();
        foodPos = accessFoods.getFoodPosition("Chicken");
        food1 = listOfAllFoods.get(foodPos);
        assertEquals(5, foodPos);
        assertEquals("Chicken", food1.getName());
        assertEquals(185, food1.getCalories());
        assertEquals(0, food1.getCarbs());
        assertEquals(9, food1.getFat());
        assertEquals(23, food1.getProtein());

        foodPos = accessFoods.getFoodPosition("Chocolate Cake");
        assertEquals(-1, foodPos);

        food2 = listOfAllFoods.get(2);
        assertEquals("Butter", food2.getName());

        meal = new Meals(MealType.BREAKFAST);
        meal.addFood(food1);
        meal.addFood(food2);

        accessFoods.addMealToDay(meal, MealType.BREAKFAST);

        mealFromDB = accessFoods.getMealFromDay(MealType.BREAKFAST);

        Food food1FromMeal = meal.getFood(0);
        Food food1FromMealDB = mealFromDB.getFood(0);

        Food food2FromMeal = meal.getFood(1);
        Food food2FromMealDB = mealFromDB.getFood(1);

        assertEquals("Chicken", food1FromMeal.getName());
        assertEquals("Chicken", food1FromMealDB.getName());
        assertEquals("Butter", food2FromMeal.getName());
        assertEquals("Butter", food2FromMealDB.getName());

        Services.closeDataAccess();

        System.out.println("Finished Integration test of AccessFoods to persistence (stub)");
    }

    public void testAccessFoodsRealDB() {
        AccessFoods accessFoods;
        Food food1;
        Food food2;
        Meals meal;
        Meals mealFromDB;
        int foodPos;
        ArrayList<Food> listOfAllFoods;

        setUpRealDb();
        System.out.println("\nStarting Integration test of AccessFoods to persistence (real)");


        accessFoods = new AccessFoods();
        listOfAllFoods = accessFoods.getAllFood();

        foodPos = accessFoods.getFoodPosition("Pillsbury Cinnamon Rolls With Icing Refrigerated Dough");
        food1 = listOfAllFoods.get(foodPos);
        assertEquals(1, foodPos);
        assertEquals("Pillsbury Cinnamon Rolls With Icing Refrigerated Dough", food1.getName());
        assertEquals(330, food1.getCalories());
        assertEquals(53, food1.getCarbs());
        assertEquals(11, food1.getFat());
        assertEquals(4, food1.getProtein());

        foodPos = accessFoods.getFoodPosition("Tim Hortons Donut");
        assertEquals(-1, foodPos);

        food2 = listOfAllFoods.get(234);
        assertEquals("Lemons", food2.getName());

        meal = new Meals(MealType.BREAKFAST);
        meal.addFood(food1);
        meal.addFood(food2);

        accessFoods.addMealToDay(meal, MealType.BREAKFAST);

        mealFromDB = accessFoods.getMealFromDay(MealType.BREAKFAST);

        Food food1FromMeal = meal.getFood(0);
        Food food1FromMealDB = mealFromDB.getFood(0);

        Food food2FromMeal = meal.getFood(1);
        Food food2FromMealDB = mealFromDB.getFood(1);

        assertEquals("Pillsbury Cinnamon Rolls With Icing Refrigerated Dough", food1FromMeal.getName());
        assertEquals("Pillsbury Cinnamon Rolls With Icing Refrigerated Dough", food1FromMealDB.getName());
        assertEquals("Lemons", food2FromMeal.getName());
        assertEquals("Lemons", food2FromMealDB.getName());

        tearDownRealDb();

        System.out.println("Finished Integration test of AccessFoods to persistence (real)");
    }

    public void testAccessProfileStubDB() {
        AccessProfile accessProfile;
        User user;

        System.out.println("\nStarting Integration test of AccessProfile to persistence");

        Services.createDataAccess(Main.dbName);

        accessProfile = new AccessProfile();

        user = accessProfile.getUser();

        assertEquals("fitness foodie", accessProfile.getUserName());
        assertEquals("test@gmail.com", accessProfile.getUserEmail());
        assertEquals("3 cups", accessProfile.getUserWaterGoal());
        assertEquals("2000 calories", accessProfile.getCalorieGoal());

        Services.closeDataAccess();

        System.out.println("Finished Integration test of AccessFoods to persistence (real)");
    }

    public void testAccessProfileRealDB() {
        AccessProfile accessProfile;
        User user;

        setUpRealDb();
        System.out.println("\nStarting Integration test of AccessProfile to persistence");


        accessProfile = new AccessProfile();

        user = accessProfile.getUser();

        assertEquals("John Doe", accessProfile.getUserName());
        assertEquals("example@test.com", accessProfile.getUserEmail());
        assertEquals("12 cups", accessProfile.getUserWaterGoal());
        assertEquals("2345 calories", accessProfile.getCalorieGoal());

        tearDownRealDb();

        System.out.println("Finished Integration test of AccessFoods to persistence (real)");
    }

    public void testAccessDayStubDB() {

        AccessDay accessDay;
        int sizeBeforeDayAdded;
        int sizeAfterDayAdded;

        System.out.println("\nStarting Integration test of AccessDay to persistence (stub)");

        Services.createDataAccess(Main.dbName);

        accessDay = new AccessDay();

        sizeBeforeDayAdded = accessDay.getNumOfDays();
        accessDay.addDay();
        sizeAfterDayAdded = accessDay.getNumOfDays();
        assertEquals(sizeBeforeDayAdded + 1, sizeAfterDayAdded);

        Services.closeDataAccess();

        System.out.println("\nFinished Integration test of AccessDay to persistence (stub)");

    }

    public void testAccessDayRealDB() {

        AccessDay accessDay;
        int sizeBeforeDayAdded;
        int sizeAfterDayAdded;

        setUpRealDb();
        System.out.println("\nStarting Integration test of AccessDay to persistence (real)");


        accessDay = new AccessDay();
        sizeBeforeDayAdded = accessDay.getNumOfDays();
        accessDay.addDay();
        sizeAfterDayAdded = accessDay.getNumOfDays();
        assertEquals(sizeBeforeDayAdded + 1, sizeAfterDayAdded);

        tearDownRealDb();

        System.out.println("\nFinished Integration test of AccessDay to persistence (real)");

    }

}
