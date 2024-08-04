package com.example.myfitnessfriend.Objects;

import org.junit.Test;
import mff.objects.*;
import static org.junit.Assert.*;

public class FoodTest {
    @Test
    public void testEmptyFoodName(){
        System.out.println("Starting testEmptyFoodName");

        Food food1 = new Food("",200, 13, 5,8,1,Unit.PIECES,1);
        assertEquals("", food1.getName());

        System.out.println("Finished testEmptyFoodName");
    }

    @Test
    public void testValidFoodName(){
        System.out.println("Starting testValidFoodName");

        Food food1 = new Food("potatoes",200, 13, 5,8,1,Unit.PIECES,1);
        assertEquals("potatoes", food1.getName());

        System.out.println("Finished testValidFoodName");
    }

    @Test
    public void testNullFoodName(){
        System.out.println("Starting testNullFoodName");

        Food food1 = new Food(null,200, 13, 5,8,1,Unit.PIECES,1);
        assertEquals("no-name", food1.getName());

        System.out.println("Finished testNullFoodName");
    }

    @Test
    public void testInvalidCalories(){
        System.out.println("Starting testInvalidCalories");

        Food food1 = new Food("chicken",-200, 13, 5,8,1,Unit.PIECES,1);
        assertEquals(200, food1.getCalories());

        System.out.println("Finished testInvalidCalories");
    }

    @Test
    public void testValidCalories(){
        System.out.println("Starting testValidCalories");

        Food food1 = new Food("chicken",200, 13, 5,8,1,Unit.PIECES,1);
        assertEquals(200, food1.getCalories());

        System.out.println("Finished testValidCalories");
    }

    @Test
    public void testInvalidCarbs(){
        System.out.println("Starting testInvalidCarbs");

        Food food1 = new Food("butter",-200, -13, 5,8,1,Unit.TBSP,1);
        assertEquals(13, food1.getCarbs());

        System.out.println("Finished testInvalidCarbs");
    }

    @Test
    public void testValidCarbs(){
        System.out.println("Starting testValidCarbs");

        Food food1 = new Food("butter",-200, 13, 5,8,1,Unit.TBSP,1);
        assertEquals(13, food1.getCarbs());

        System.out.println("Finished testValidCarbs");
    }

    @Test
    public void testInvalidFat(){
        System.out.println("Starting testInvalidFat");

        Food food1 = new Food("egg",-200, -13, -5,8,1,Unit.PIECES,1);
        assertEquals(5, food1.getFat());

        System.out.println("Finished testInvalidFat");
    }

    @Test
    public void testValidFat(){
        System.out.println("Starting testValidFat");

        Food food1 = new Food("egg",-200, -13, 5,8,1,Unit.PIECES,1);
        assertEquals(5, food1.getFat());

        System.out.println("Finished testValidFat");
    }

    @Test
    public void testInvalidProtein(){
        System.out.println("Starting testInvalidProtein");

        Food food1 = new Food("egg",-200, -13, 5,-8,1,Unit.PIECES,1);
        assertEquals(8, food1.getProtein());

        System.out.println("Finished testInvalidProtein");
    }

    @Test
    public void testValidProtein(){
        System.out.println("Starting testValidProtein");

        Food food1 = new Food("egg",-200, -13, 5,0,1,Unit.PIECES,1);
        assertEquals(0, food1.getProtein());

        System.out.println("Finished testValidProtein");
    }

    @Test
    public void testInvalidServingSize(){
        System.out.println("Starting testInvalidServingSize");

        Food food1 = new Food("egg",-200, -13, 5,-8,-1,Unit.PIECES,1);
        assertEquals(1, food1.getServingSize(), 0.0);

        System.out.println("Finished testInvalidServingSize");
    }

    @Test
    public void testValidServingSize(){
        System.out.println("Starting testValidServingSize");

        Food food1 = new Food("egg",-200, -13, 5,-8,0.5,Unit.PIECES,1);
        assertEquals(0.5, food1.getServingSize(), 0.0);

        System.out.println("Finished testValidServingSize");
    }

    @Test
    public void testInvalidNumberOfServings(){
        System.out.println("Starting testInvalidNumberOfServings");

        Food food1 = new Food("egg",-200, -13, 5,-8,1,Unit.PIECES,-1);
        assertEquals(1, food1.getNumberOfServings(), 0.0);

        System.out.println("Finished testInvalidNumberOfServings");
    }

    @Test
    public void testValidNumberOfServings(){
        System.out.println("Starting testValidNumberOfServings");

        Food food1 = new Food("egg",-200, -13, 5,-8,1,Unit.PIECES,2);
        assertEquals(2, food1.getNumberOfServings(), 0.0);

        System.out.println("Finished testValidNumberOfServings");
    }

    @Test
    public void testNullUnit(){
        System.out.println("Starting testNullUnit");

        Food food1 = new Food("egg",-200, -13, 5,-8,1,null,2);
        assertEquals(Unit.NONE, food1.getUnit());

        System.out.println("Finished testNullUnit");
    }

    @Test
    public void testValidUnit(){
        System.out.println("Starting testValidUnit");

        Food food1 = new Food("egg",-200, -13, 5,-8,1,Unit.PIECES,2);
        assertEquals(Unit.PIECES, food1.getUnit());

        System.out.println("Finished testValidUnit");
    }
}
