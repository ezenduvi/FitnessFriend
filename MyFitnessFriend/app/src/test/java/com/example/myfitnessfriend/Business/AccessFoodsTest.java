package com.example.myfitnessfriend.Business;

import org.junit.Test;

import java.util.ArrayList;

import mff.application.Main;
import mff.application.Services;
import mff.business.AccessFoods;
import mff.objects.Food;
import mff.objects.MealType;
import mff.objects.Meals;
import mff.objects.Unit;
import mff.persistence.HSQLDB;

import static org.junit.Assert.assertEquals;

public class AccessFoodsTest {
    private HSQLDB dataAccess;
    private AccessFoods accessFoods;

    public void setUp() {
        Services.createDataAccess(new HSQLDB(Main.dbName));
        dataAccess = (HSQLDB) Services.getDataAccess(Main.dbName);
        dataAccess.createSaveState();
        accessFoods = new AccessFoods();
    }

    public void tearDown() {
        dataAccess.rollbackSaveState();
        Services.closeDataAccess();
    }

    @Test
    public void testGetAllFood() {
        ArrayList<Food> foods;
        System.out.println("\nStarting test testGetAllFood");
        setUp();

        foods = accessFoods.getAllFood();

        assertEquals(14171, foods.size());
        assertEquals("Pillsbury Golden Layer Buttermilk Biscuits Artificial Flavor Refrigerated Dough", foods.get(0).getName());
        assertEquals("French Bread", foods.get(5163).getName());
        assertEquals("Industrial Oil As Ingredient In Food", foods.get(14163).getName());

        tearDown();
        System.out.println("Finished test testGetAllFood");
    }

    @Test
    public void testAddMealToDay() {
        Meals mealDb;
        Meals mealAdd1 = new Meals(MealType.BREAKFAST, new ArrayList<Food>());
        Meals mealAdd2 = new Meals(MealType.LUNCH, new ArrayList<Food>());
        System.out.println("\nStarting test testGetAllFood");
        setUp();

        mealDb = accessFoods.getMealFromDay(MealType.BREAKFAST);
        assertEquals(0, mealDb.getNumFood());

        mealAdd1.addFood(new Food("French Bread", 1, 2, 3, 4, 5, Unit.CUP, 1));
        accessFoods.addMealToDay(mealAdd1, MealType.BREAKFAST);
        mealDb = accessFoods.getMealFromDay(MealType.BREAKFAST);

        assertEquals(1, mealDb.getNumFood());
        assertEquals("French Bread", mealDb.getFood(0).getName());

        mealAdd2.addFood(new Food("Iced Coffee Brewed", 1, 2, 3, 4, 5, Unit.CUP, 1));
        accessFoods.addMealToDay(mealAdd2, MealType.LUNCH);
        mealDb = accessFoods.getMealFromDay(MealType.LUNCH);

        assertEquals(1, mealDb.getNumFood());
        assertEquals("Iced Coffee Brewed", mealDb.getFood(0).getName());

        mealDb = accessFoods.getMealFromDay(MealType.BREAKFAST);

        assertEquals(1, mealDb.getNumFood());
        assertEquals("French Bread", mealDb.getFood(0).getName());

        tearDown();
        System.out.println("Finished test testGetAllFood");
    }
}