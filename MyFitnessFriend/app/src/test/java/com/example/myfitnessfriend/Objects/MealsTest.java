package com.example.myfitnessfriend.Objects;

import org.junit.Test;

import java.util.ArrayList;

import mff.objects.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MealsTest {
    private static final double ELIPSON = 1E-5;
    @Test
    public void testTypeConstructor() {
        Meals meal;

        System.out.println("\nStarting testTypeConstructor");

        meal = new Meals(MealType.BREAKFAST);

        assertEquals(meal.getType(), MealType.BREAKFAST);
        assertEquals(meal.getNumFood(), 0);

        System.out.println("Finished testTypeConstructor");
    }

    @Test
    public void testEmptyArrayConstructor() {
        Meals meal;
        ArrayList<Food> foods;

        System.out.println("\nStarting testEmptyArrayConstructor");

        foods = new ArrayList<>();
        meal = new Meals(MealType.LUNCH, foods);

        assertEquals(meal.getType(), MealType.LUNCH);
        assertEquals(meal.getNumFood(), 0);

        System.out.println("Finished testEmptyArrayConstructor");
    }

    @Test
    public void testFullArrayConstructor(){
        Meals meal;
        ArrayList<Food> foods;
        Food food1;

        System.out.println("\nStarting testFullArrayConstructor");

        food1 = new Food("test", 100, 100, 100, 100, 1,
                Unit.CUP, 1);

        foods = new ArrayList<>();
        foods.add(food1);

        meal = new Meals(MealType.DINNER, foods);

        assertEquals(meal.getType(), MealType.DINNER);
        assertEquals(meal.getNumFood(), 1);
        assertNotNull(meal.getFood(0));
        assertTrue(meal.getFood(0).equals(food1));

        System.out.println("Finished testFullArrayConstructor");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testFailTypeConstructor() {
        Meals meal;

        System.out.println("\nStarting testFailTypeConstructor");

        meal = new Meals(null);

        System.out.println("Finished testTypeConstructor");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testFailArrayConstructor() {
        Meals meal;
        ArrayList<Food> foods;

        System.out.println("\nStarting testFailArrayConstructor");

        foods = new ArrayList<>();
        meal = new Meals(null, foods);

        System.out.println("Finished testFailArrayConstructor");
    }

    @Test
    public void testGetType() {
        Meals meal;
        ArrayList<Food> foods;

        System.out.println("\nStarting testGetType");

        foods = new ArrayList<>();
        meal = new Meals(MealType.DINNER, foods);

        assertEquals(meal.getType(), MealType.DINNER);

        System.out.println("Finished testGetType");
    }

    @Test
    public void testGetCaloriesEmpty() {
        Meals meal;
        ArrayList<Food> foods;

        System.out.println("\nStarting testGetCaloriesEmpty");

        foods = new ArrayList<>();

        meal = new Meals(MealType.DINNER, foods);

        assertEquals(meal.getCalories(), 0, ELIPSON);

        System.out.println("Finished testGetCaloriesEmpty");
    }

    @Test
    public void testGetCaloriesOne() {
        Meals meal;
        Food food1;
        ArrayList<Food> foods;

        System.out.println("\nStarting testGetCaloriesOne");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);

        foods = new ArrayList<>();
        foods.add(food1);

        meal = new Meals(MealType.DINNER, foods);

        assertEquals(meal.getCalories(), food1.getCalories(), ELIPSON);

        System.out.println("Finished testGetCaloriesOne");
    }

    @Test
    public void testGetCaloriesTwo() {
        Meals meal;
        Food food1;
        Food food2;
        ArrayList<Food> foods;

        System.out.println("\nStarting testGetCaloriesTwo");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        food2 = new Food("test2", 1000, 1000, 1000, 1000, 1000,
                Unit.CUP, 1000);

        foods = new ArrayList<>();
        foods.add(food1);
        foods.add(food2);

        meal = new Meals(MealType.DINNER, foods);

        assertEquals(meal.getCalories(), food1.getCalories() + food2.getCalories(), ELIPSON);

        System.out.println("Finished testGetCaloriesTwo");
    }


    @Test
    public void testGetCarbsEmpty() {
        Meals meal;
        ArrayList<Food> foods;

        System.out.println("\nStarting testGetCarbsEmpty");

        foods = new ArrayList<>();

        meal = new Meals(MealType.DINNER, foods);

        assertEquals(meal.getCarbs(), 0, ELIPSON);

        System.out.println("Finished testGetCarbsEmpty");
    }

    @Test
    public void testGetCarbsOne() {
        Meals meal;
        Food food1;
        ArrayList<Food> foods;

        System.out.println("\nStarting testGetCarbsOne");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);

        foods = new ArrayList<>();

        foods.add(food1);

        meal = new Meals(MealType.DINNER, foods);

        assertEquals(meal.getCarbs(), food1.getCarbs(), ELIPSON);

        System.out.println("Finished testGetCarbsOne");
    }

    @Test
    public void testGetCarbsTwo() {
        Meals meal;
        Food food1;
        Food food2;
        ArrayList<Food> foods;

        System.out.println("\nStarting testGetCarbsTwo");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        food2 = new Food("test2", 1000, 1000, 1000, 1000, 1000,
                Unit.CUP, 1000);

        foods = new ArrayList<>();

        foods.add(food1);
        foods.add(food2);

        meal = new Meals(MealType.DINNER, foods);

        assertEquals(meal.getCarbs(), food1.getCarbs() + food2.getCarbs(), ELIPSON);

        System.out.println("Finished testGetCarbsTwo");
    }

    @Test
    public void testGetFatEmpty() {
        Meals meal;
        ArrayList<Food> foods;

        System.out.println("\nStarting testGetFatEmpty");

        foods = new ArrayList<>();

        meal = new Meals(MealType.DINNER, foods);

        assertEquals(meal.getFat(), 0, ELIPSON);

        System.out.println("Finished testGetFatEmpty");
    }

    @Test
    public void testGetFatOne() {
        Meals meal;
        Food food1;
        ArrayList<Food> foods;

        System.out.println("\nStarting testGetFatOne");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);

        foods = new ArrayList<>();

        foods.add(food1);

        meal = new Meals(MealType.DINNER, foods);

        assertEquals(meal.getFat(), food1.getFat(), ELIPSON);

        System.out.println("Finished testGetFatOne");
    }

    @Test
    public void testGetFatTwo() {
        Meals meal;
        Food food1;
        Food food2;
        ArrayList<Food> foods;

        System.out.println("\nStarting testGetFatTwo");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        food2 = new Food("test2", 1000, 1000, 1000, 1000, 1000,
                Unit.CUP, 1000);

        foods = new ArrayList<>();

        foods.add(food1);
        foods.add(food2);

        meal = new Meals(MealType.DINNER, foods);

        assertEquals(meal.getFat(), food1.getFat() + food2.getFat(), ELIPSON);

        System.out.println("Finished testGetFatTwo");
    }


    @Test
    public void testGetProteinEmpty() {
        Meals meal;
        ArrayList<Food> foods;

        System.out.println("\nStarting testGetProteinEmpty");

        foods = new ArrayList<>();

        meal = new Meals(MealType.DINNER, foods);

        assertEquals(meal.getProtein(), 0, ELIPSON);

        System.out.println("Finished testGetProteinEmpty");
    }

    @Test
    public void testGetProteinOne() {
        Meals meal;
        Food food1;
        ArrayList<Food> foods;

        System.out.println("\nStarting testGetProteinOne");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);

        foods = new ArrayList<>();

        foods.add(food1);

        meal = new Meals(MealType.DINNER, foods);

        assertEquals(meal.getProtein(), food1.getProtein(), ELIPSON);

        System.out.println("Finished testGetProteinOne");
    }

    @Test
    public void testGetProteinTwo() {
        Meals meal;
        Food food1;
        Food food2;
        ArrayList<Food> foods;

        System.out.println("\nStarting testGetProteinTwo");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        food2 = new Food("test2", 1000, 1000, 1000, 1000, 1000,
                Unit.CUP, 1000);

        foods = new ArrayList<>();

        foods.add(food1);
        foods.add(food2);

        meal = new Meals(MealType.DINNER, foods);

        assertEquals(meal.getProtein(), food1.getProtein() + food2.getProtein(), ELIPSON);

        System.out.println("Finished testGetProteinTwo");
    }

    @Test
    public void testAddOneFood(){
        Meals meal;
        Food food1;

        System.out.println("\nStarting testAddOneFood");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);

        meal = new Meals(MealType.DINNER);

        meal.addFood(food1);

        assertEquals(meal.getProtein(), food1.getProtein(), ELIPSON);

        System.out.println("Finished testAddOneFood");
    }

    @Test
    public void testAddMultipleFoods(){
        Meals meal;
        Food food1;
        Food food2;
        Food food3;

        System.out.println("\nStarting testAddMultipleFoods");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        food2 = new Food("test2", 100, 100, 100, 100, 100,
                Unit.CUP, 100);
        food3 = new Food("test3", 1000, 1000, 1000, 1000, 1000,
                Unit.CUP, 1000);

        meal = new Meals(MealType.DINNER);

        meal.addFood(food1);
        meal.addFood(food2);
        meal.addFood(food3);

        assertEquals(meal.getNumFood(), 3);
        assertEquals(meal.getProtein(), food1.getProtein() + food2.getProtein() + food3.getProtein(), ELIPSON);

        System.out.println("Finished testAddMultipleFoods");
    }

    @Test (expected = NullPointerException.class)
    public void testFailAddFood(){
        Meals meal;

        System.out.println("\nStarting testFailAddFood");

        meal = new Meals(MealType.DINNER);

        meal.addFood(null);

        System.out.println("Finished testFailAddFood");
    }

    @Test
    public void testRemoveOneFoodIndex() {
        Meals meal;
        Food food1;
        Food resultFood;

        System.out.println("\nStarting testRemoveOneFoodIndex");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);

        meal = new Meals(MealType.DINNER);

        meal.addFood(food1);
        resultFood = meal.removeFood(0);

        assertEquals(meal.getNumFood(), 0);
        assertTrue(food1.equals(resultFood));

        System.out.println("Finished testRemoveOneFoodIndex");
    }

    @Test
    public void testRemoveMultipleFoodIndex() {
        Meals meal;
        Food food1;
        Food food2;
        Food food3;
        Food otherFood;

        System.out.println("\nStarting testRemoveMultipleFoodIndex");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        food2 = new Food("test2", 100, 100, 100, 100, 100,
                Unit.CUP, 100);
        food3 = new Food("test2", 1000, 1000, 1000, 1000, 1000,
                Unit.CUP, 1000);

        meal = new Meals(MealType.DINNER);

        meal.addFood(food1);
        meal.addFood(food2);
        meal.addFood(food3);
        otherFood = meal.removeFood(1);

        assertEquals(meal.getNumFood(), 2);
        assertTrue(meal.getFood(0).equals(food1));
        assertTrue(meal.getFood(1).equals(food3));
        assertTrue(otherFood.equals(food2));

        otherFood = meal.removeFood(1);

        assertEquals(meal.getNumFood(), 1);
        assertTrue(meal.getFood(0).equals(food1));
        assertTrue(otherFood.equals(food3));

        otherFood = meal.removeFood(0);

        assertEquals(meal.getNumFood(), 0);
        assertTrue(otherFood.equals(food1));

        System.out.println("Finished testRemoveMultipleFoodIndex");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testFailRemoveEmptyFoodIndex() {
        Meals meal;

        System.out.println("\nStarting testFailRemoveEmptyFoodIndex");

        meal = new Meals(MealType.DINNER);

        meal.removeFood(0);

        System.out.println("Finished testFailRemoveEmptyFoodIndex");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testFailRemoveNegativeFoodIndex() {
        Meals meal;
        Food food1;

        System.out.println("\nStarting testFailRemoveNegativeFoodIndex");

        meal = new Meals(MealType.DINNER);

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        meal.addFood(food1);

        meal.removeFood(-1);

        System.out.println("Finished testFailRemoveNegativeFoodIndex");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testFailRemoveFarNegativeFoodIndex() {
        Meals meal;
        Food food1;

        System.out.println("\nStarting testFailRemoveFarNegativeFoodIndex");

        meal = new Meals(MealType.DINNER);

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        meal.addFood(food1);

        meal.removeFood(Integer.MIN_VALUE);

        System.out.println("Finished testFailRemoveFarNegativeFoodIndex");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testFailRemoveOverMaxFoodIndex() {
        Meals meal;
        Food food1;

        System.out.println("\nStarting testRemoveMultipleFoodIndex");

        meal = new Meals(MealType.DINNER);

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        meal.addFood(food1);

        meal.removeFood(1);

        System.out.println("Finished testRemoveMultipleFoodIndex");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testFailRemoveFarOverMaxFoodIndex() {
        Meals meal;
        Food food1;

        System.out.println("\nStarting testRemoveMultipleFoodIndex");

        meal = new Meals(MealType.DINNER);

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        meal.addFood(food1);

        meal.removeFood(Integer.MAX_VALUE);

        System.out.println("Finished testRemoveMultipleFoodIndex");
    }

    @Test
    public void testRemoveOneFoodObject() {
        Meals meal;
        Food food1;
        boolean result;

        System.out.println("\nStarting testRemoveOneFoodObject");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);

        meal = new Meals(MealType.DINNER);

        meal.addFood(food1);
        result = meal.removeFood(food1);

        assertEquals(meal.getNumFood(), 0);
        assertTrue(result);

        System.out.println("Finished testRemoveOneFoodObject");
    }

    @Test
    public void testRemoveMultipleFoodObject() {
        Meals meal;
        Food food1;
        Food food2;
        Food food3;
        boolean result;

        System.out.println("\nStarting testRemoveMultipleFoodObject");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        food2 = new Food("test2", 100, 100, 100, 100, 100,
                Unit.CUP, 100);
        food3 = new Food("test2", 1000, 1000, 1000, 1000, 1000,
                Unit.CUP, 1000);

        meal = new Meals(MealType.DINNER);

        meal.addFood(food1);
        meal.addFood(food2);
        meal.addFood(food3);
        result = meal.removeFood(food2);

        assertEquals(meal.getNumFood(), 2);
        assertTrue(meal.getFood(0).equals(food1));
        assertTrue(meal.getFood(1).equals(food3));
        assertTrue(result);

        result = meal.removeFood(food3);

        assertEquals(meal.getNumFood(), 1);
        assertTrue(meal.getFood(0).equals(food1));
        assertTrue(result);

        result = meal.removeFood(food1);

        assertEquals(meal.getNumFood(), 0);
        assertTrue(result);

        System.out.println("Finished testRemoveMultipleFoodObject");
    }

    @Test (expected = NullPointerException.class)
    public void testFailRemoveNullFoodObject() {
        Meals meal;

        System.out.println("\nStarting testFailRemoveNullFoodObject");

        meal = new Meals(MealType.DINNER);

        meal.removeFood(null);

        System.out.println("Finished testFailRemoveNullFoodObject");
    }

    @Test
    public void testFailRemoveWrongFoodObject() {
        Meals meal;
        Food food1;
        Food food2;
        boolean result;

        System.out.println("\nStarting testFailRemoveWrongFoodObject");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        food2 = new Food("test2", 100, 100, 100, 100, 100,
                Unit.CUP, 100);

        meal = new Meals(MealType.DINNER);

        meal.addFood(food1);

        result = meal.removeFood(food2);

        assertFalse(result);
        assertEquals(meal.getNumFood(), 1);

        System.out.println("Finished testFailRemoveWrongFoodObject");
    }

    @Test
    public void testFailRemoveWrongFoodObjectEmpty() {
        Meals meal;
        Food food1;
        boolean result;

        System.out.println("\nStarting testFailRemoveWrongFoodObjectEmpty");

        meal = new Meals(MealType.DINNER);

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);

        result = meal.removeFood(food1);

        assertFalse(result);
        assertEquals(meal.getNumFood(), 0);

        System.out.println("Finished testFailRemoveWrongFoodObjectEmpty");
    }

    @Test
    public void testGetFoodOne(){
        Meals meal;
        Food food1;

        System.out.println("\nStarting testGetFoodOne");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);

        meal = new Meals(MealType.DINNER);
        meal.addFood(food1);

        assertTrue(meal.getFood(0).equals(food1));

        System.out.println("Finished testGetFoodOne");
    }

    @Test
    public void testGetFoodMany(){
        Meals meal;
        Food food1;
        Food food2;
        Food food3;

        System.out.println("\nStarting testGetFoodMany");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        food2 = new Food("test2", 100, 100, 100, 100, 100,
                Unit.CUP, 100);
        food3 = new Food("test2", 1000, 1000, 1000, 1000, 1000,
                Unit.CUP, 1000);


        meal = new Meals(MealType.DINNER);
        meal.addFood(food1);
        meal.addFood(food2);
        meal.addFood(food3);

        assertTrue(meal.getFood(0).equals(food1));
        assertTrue(meal.getFood(1).equals(food2));
        assertTrue(meal.getFood(2).equals(food3));

        System.out.println("Finished testGetFoodMany");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testFailGetFoodNegative(){
        Meals meal;
        Food food1;

        System.out.println("\nStarting testFailGetFoodNegative");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);

        meal = new Meals(MealType.DINNER);
        meal.addFood(food1);

        meal.getFood(-1);

        System.out.println("Finished testFailGetFoodNegative");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testFailGetFoodLargeNegative(){
        Meals meal;
        Food food1;

        System.out.println("\nStarting testFailGetFoodLargeNegative");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);

        meal = new Meals(MealType.DINNER);
        meal.addFood(food1);

        meal.getFood(Integer.MIN_VALUE);

        System.out.println("Finished testFailGetFoodLargeNegative");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testFailGetFoodTooLarge(){
        Meals meal;
        Food food1;

        System.out.println("\nStarting testFailGetFoodTooLarge");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);

        meal = new Meals(MealType.DINNER);
        meal.addFood(food1);

        meal.getFood(1);

        System.out.println("Finished testFailGetFoodTooLarge");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testFailGetFoodFarTooLarge(){
        Meals meal;
        Food food1;

        System.out.println("\nStarting testFailGetFoodFarTooLarge");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);

        meal = new Meals(MealType.DINNER);
        meal.addFood(food1);

        meal.getFood(Integer.MAX_VALUE);

        System.out.println("Finished testFailGetFoodFarTooLarge");
    }

    @Test
    public void testGetNumFoodNone(){
        Meals meal;

        System.out.println("\nStarting testGetNumFoodNone");

        meal = new Meals(MealType.DINNER);

        assertEquals(meal.getNumFood(), 0);

        System.out.println("Finished testGetNumFoodNone");
    }

    @Test
    public void testGetNumFoodOne(){
        Meals meal;
        Food food1;

        System.out.println("\nStarting testGetNumFoodOne");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);

        meal = new Meals(MealType.DINNER);
        meal.addFood(food1);

        assertEquals(meal.getNumFood(), 1);

        System.out.println("Finished testGetNumFoodOne");
    }

    @Test
    public void testGetNumFoodMany(){
        Meals meal;
        Food food1;
        Food food2;
        Food food3;

        System.out.println("\nStarting testGetNumFoodMany");

        food1 = new Food("test1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        food2 = new Food("test2", 100, 100, 100, 100, 100,
                Unit.CUP, 100);
        food3 = new Food("test2", 1000, 1000, 1000, 1000, 1000,
                Unit.CUP, 1000);

        meal = new Meals(MealType.DINNER);
        meal.addFood(food1);
        meal.addFood(food2);
        meal.addFood(food3);

        assertEquals(meal.getNumFood(), 3);

        System.out.println("Finished testGetNumFoodMany");
    }
}
