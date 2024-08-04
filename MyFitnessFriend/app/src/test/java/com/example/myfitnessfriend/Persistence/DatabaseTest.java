package com.example.myfitnessfriend.Persistence;

import junit.framework.TestCase;

import java.util.ArrayList;

import mff.application.Main;
import mff.application.Services;
import mff.business.AccessDiary;
import mff.business.AccessExercise;
import mff.business.AccessFoods;
import mff.business.AccessProfile;
import mff.objects.Diary;
import mff.objects.Exercise;
import mff.objects.Food;
import mff.objects.MealType;
import mff.objects.Meals;
import mff.objects.Unit;
import mff.objects.User;
import mff.persistence.Database;
import mff.persistence.HSQLDB;

public class DatabaseTest extends TestCase {
    private Database database;
    AccessProfile accessProfile = new AccessProfile();
    AccessDiary accessDiary = new AccessDiary();
    AccessExercise accessExercise = new AccessExercise();
    AccessFoods accessFoods = new AccessFoods();

    public DatabaseTest(String arg0) { super(arg0);}

    public void setUp() {

        Services.createDataAccess(new HSQLDB(Main.dbName));
        database = Services.getDataAccess(Main.dbName);
        ((HSQLDB)database).createSaveState();
        accessExercise = new AccessExercise();
    }

    public void tearDown() {

        ((HSQLDB)database).rollbackSaveState();
        Services.closeDataAccess();
        System.out.println("\nFinished Persistence test Database (using real)");
    }

    public static void databaseTest(Database database) {
        DatabaseTest databaseTest = new DatabaseTest("");
        databaseTest.database = database;
        databaseTest.setUp();
        databaseTest.testHSQLDB();
        databaseTest.tearDown();
    }

    public void testHSQLDB() {
        ArrayList<Food> foods;
        ArrayList<Exercise> exercises;
        ArrayList<Diary> diaries;
        Diary diary1, diary2;
        String reflection1, reflection2;
        Meals meals;
        MealType mealType;
        Food food;
        Exercise exercise1, exercise2;
        User user;

        System.out.println("\nStarting Persistence test Database (using real)");

        user = database.getUser();
        assertEquals("John Doe",user.getUserName());

        database.setUserWaterGoal(13);
        assertEquals("13 cups", accessProfile.getUserWaterGoal());

        database.setUserCalorieGoal(2500);
        assertEquals("2500 calories", accessProfile.getCalorieGoal());

        foods = database.getAllFood();
        assertNotNull(foods);
        assertEquals(14171, foods.size());
        assertEquals("Dutch Apple Pie", foods.get(10).getName());

        exercises = database.getAllExercises();
        assertNotNull(exercises);
        assertEquals(250, exercises.size());
        assertEquals("Unicycling", exercises.get(8).getName());

        // testing addReflection, getReflections
        reflection1 = "Today I lost 1 pound";
        reflection2 = "Had lots of energy";
        diary1 = new Diary(reflection1);
        diary2 = new Diary(reflection2);
        accessDiary.addReflection(diary1);
        diaries = accessDiary.getReflections();
        assertEquals(1,diaries.size());
        accessDiary.addReflection(diary2);
        diaries = accessDiary.getReflections();
        assertEquals(1,diaries.size());

        // testing addExerciseToDay, getExercisesByDay
        exercises.clear();
        exercise1 = new Exercise("Painting",15,150);
        exercise2 = new Exercise("Unicycling",30,120);
        accessExercise.addExerciseToDay(exercise1);
        exercises = accessExercise.getExercisesByDay();
        assertEquals(1,exercises.size());
        assertEquals("Painting", exercises.get(0).getName());
        accessExercise.addExerciseToDay(exercise2);
        exercises = accessExercise.getExercisesByDay();
        assertEquals(2,exercises.size());
        assertEquals("Unicycling", exercises.get(1).getName());


        // testing addMealToDay, getMealFromDay
        foods.clear();
        System.out.println("foods size after clearing is " + foods.size());
        mealType = MealType.BREAKFAST;
        food = new Food("Dutch Apple Pie",290,44,11,2,1.0, Unit.PIECES,1.0);
        foods.add(food);
        System.out.println("foods size after adding 1 food is " + foods.size());
        meals = new Meals(mealType,foods);
        System.out.println("num foods in meals is " + meals.getNumFood());
        accessFoods.addMealToDay(meals, mealType);
        assertEquals(1, accessFoods.getMealFromDay(mealType).getNumFood());
        assertEquals("Dutch Apple Pie", accessFoods.getMealFromDay(mealType).getFood(0).getName());

    }


}
