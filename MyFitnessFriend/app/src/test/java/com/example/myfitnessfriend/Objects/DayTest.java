package com.example.myfitnessfriend.Objects;

import org.junit.Test;

import java.util.ArrayList;

import mff.objects.Day;
import mff.objects.Exercise;
import mff.objects.Food;
import mff.objects.MealType;
import mff.objects.Meals;
import mff.objects.Unit;
import mff.objects.User;

import static org.junit.Assert.assertEquals;

public class DayTest {

    private static final double ERROR = 1E-5;

    @Test
    public void testEmptyGetWater(){
        System.out.println("\nStarting testEmptyGetWater");
        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test", 10, 100);
        exercises.add(exercise);
        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);
        assertEquals(0, day1.getDailyWater(), 0.0);
        System.out.println("Finished testEmptyGetWater");
    }
    @Test
    public void testEmptyGetFat(){
        System.out.println("\nStarting testEmptyGetFat");
        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test", 10, 100);
        exercises.add(exercise);
        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);
        assertEquals(0, day1.getFat(), 0.0);
        System.out.println("Finished testEmptyGetFat");
    }
    @Test
    public void testEmptyGetProtein(){
        System.out.println("\nStarting testEmptyGetProtein");
        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test", 10, 100);
        exercises.add(exercise);
        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);
        assertEquals(0, day1.getProtein(), 0.0);
        System.out.println("Finished testEmptyGetProtein");
    }
    @Test
    public void testEmptyGetCarb(){
        System.out.println("\nStarting testEmptyGetCarb");
        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test", 10, 100);
        exercises.add(exercise);
        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);
        assertEquals(0,day1.getCarb(),0.0);
        System.out.println("Finished testEmptyGetCarb");
    }
    @Test
    public void testEmptyGetCalories(){
        System.out.println("\nStarting testEmptyGetCalories");
        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test", 60, 1);
        exercises.add(exercise);
        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);
        assertEquals(0.0, day1.getDailyCalories(),0.0);
        System.out.println("Finished testEmptyGetCalories");
    }
    @Test
    public void testEmptyGetRemaining(){
        System.out.println("\nStarting testEmptyGetRemaining");
        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test", 60, 1);
        exercises.add(exercise);
        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);
        assertEquals(1501, day1.getRemaining(),0.0);
        System.out.println("Finished testEmptyGetRemaining");
    }
    @Test
    public void testAddWater(){
        System.out.println("\nStarting testAddWater");
        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test", 10, 100);
        exercises.add(exercise);
        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);
        day1.addWater(0.1);
        assertEquals(0.1, day1.getDailyWater(),0.0);
        System.out.println("Finished testAddWater");
    }
    @Test
    public void testGetFat(){
        System.out.println("\nStarting testGetFat");
        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test", 10, 100);
        exercises.add(exercise);

        Food food1;
        food1 = new Food("food1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        breakfast.addFood(food1);
        Food food2;
        food2 = new Food("food2", 2, 2, 2, 2, 1,
                Unit.CUP, 1);
        lunch.addFood(food2);
        Food food3;
        food3 = new Food("food3", 3, 3, 3, 3, 1,
                Unit.CUP, 1);
        dinner.addFood(food3);
        Food food4;
        food4 = new Food("food4", 4, 4, 4, 4, 1,
                Unit.CUP, 1);
        snack.addFood(food4);

        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);
        assertEquals(10, day1.getFat(),0.0);
        System.out.println("Finished testGetFat");
    }
    @Test
    public void testGetProtein(){
        System.out.println("\nStarting testGetProtein");
        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test", 10, 100);
        exercises.add(exercise);

        Food food1;
        food1 = new Food("food1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        breakfast.addFood(food1);
        Food food2;
        food2 = new Food("food2", 2, 2, 2, 2, 1,
                Unit.CUP, 1);
        lunch.addFood(food2);
        Food food3;
        food3 = new Food("food3", 3, 3, 3, 3, 1,
                Unit.CUP, 1);
        dinner.addFood(food3);
        Food food4;
        food4 = new Food("food4", 4, 4, 4, 4, 1,
                Unit.CUP, 1);
        snack.addFood(food4);

        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);
        assertEquals(10, day1.getFat(),0.0);
        System.out.println("Finished testGetProtein");
    }
    @Test
    public void testGetCarb(){
        System.out.println("\nStarting testGetCarb");
        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test", 10, 100);
        exercises.add(exercise);

        Food food1;
        food1 = new Food("food1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        breakfast.addFood(food1);
        Food food2;
        food2 = new Food("food2", 2, 2, 2, 2, 1,
                Unit.CUP, 1);
        lunch.addFood(food2);
        Food food3;
        food3 = new Food("food3", 3, 3, 3, 3, 1,
                Unit.CUP, 1);
        dinner.addFood(food3);
        Food food4;
        food4 = new Food("food4", 4, 4, 4, 4, 1,
                Unit.CUP, 1);
        snack.addFood(food4);

        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);
        assertEquals(10, day1.getFat(),0.0);
        System.out.println("Finished testGetCarb");
    }
    @Test
    public void testGetRemaining(){
        System.out.println("\nStarting testGetRemaining");
        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test", 60, 100);
        exercises.add(exercise);

        Food food1;
        food1 = new Food("food1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        breakfast.addFood(food1);
        Food food2;
        food2 = new Food("food2", 2, 2, 2, 2, 1,
                Unit.CUP, 1);
        lunch.addFood(food2);
        Food food3;
        food3 = new Food("food3", 3, 3, 3, 3, 1,
                Unit.CUP, 1);
        dinner.addFood(food3);
        Food food4;
        food4 = new Food("food4", 4, 4, 4, 4, 1,
                Unit.CUP, 1);
        snack.addFood(food4);

        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);
        assertEquals(1590, day1.getRemaining(),0.0);
        System.out.println("Finished testGetRemaining");
    }
    @Test
    public void testGetFatPer(){
        System.out.println("\nStarting testGetFatPer");
        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test", 10, 100);
        exercises.add(exercise);

        Food food1;
        food1 = new Food("food1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        breakfast.addFood(food1);
        Food food2;
        food2 = new Food("food2", 2, 2, 2, 2, 1,
                Unit.CUP, 1);
        lunch.addFood(food2);
        Food food3;
        food3 = new Food("food3", 3, 3, 3, 3, 1,
                Unit.CUP, 1);
        dinner.addFood(food3);
        Food food4;
        food4 = new Food("food4", 4, 4, 4, 4, 1,
                Unit.CUP, 1);
        snack.addFood(food4);

        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);
        assertEquals(4.0, day1.getFatPer(),0.0);
        System.out.println("Finished testGetFatPer");
    }
    @Test
    public void testGetCarbPer(){
        System.out.println("\nStarting testGetCarbPer");
        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test", 10, 100);
        exercises.add(exercise);

        Food food1;
        food1 = new Food("food1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        breakfast.addFood(food1);
        Food food2;
        food2 = new Food("food2", 2, 2, 2, 2, 1,
                Unit.CUP, 1);
        lunch.addFood(food2);
        Food food3;
        food3 = new Food("food3", 3, 3, 3, 3, 1,
                Unit.CUP, 1);
        dinner.addFood(food3);
        Food food4;
        food4 = new Food("food4", 4, 4, 4, 4, 1,
                Unit.CUP, 1);
        snack.addFood(food4);

        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);
        assertEquals(4.0, day1.getCarbPer(),0.0);
        System.out.println("Finished testGetCarbPer");
    }
    @Test
    public void testGetProteinPer(){
        System.out.println("\nStarting testGetProteinPer");
        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);
        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test", 10, 100);
        exercises.add(exercise);

        Food food1;
        food1 = new Food("food1", 1, 1, 1, 1, 1,
                Unit.CUP, 1);
        breakfast.addFood(food1);
        Food food2;
        food2 = new Food("food2", 2, 2, 2, 2, 1,
                Unit.CUP, 1);
        lunch.addFood(food2);
        Food food3;
        food3 = new Food("food3", 3, 3, 3, 3, 1,
                Unit.CUP, 1);
        dinner.addFood(food3);
        Food food4;
        food4 = new Food("food4", 4, 4, 4, 4, 1,
                Unit.CUP, 1);
        snack.addFood(food4);

        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);
        assertEquals(9.0, day1.getProteinPer(),0.0);
        System.out.println("Finished testGetProteinPer");
    }

    @Test
    public void testGetUser() {
        System.out.println("\nStarting testGetUser");

        User user1 = new User("user1", "@MyFitnessFriend.com", 1500, 2);
        Day day = new Day(user1);
        assertEquals(user1, day.getUser());

        System.out.println("Finished testGetUser");
    }

    @Test
    public void testGetExercisesEmpty() {
        System.out.println("\nStarting testGetExercisesEmpty");

        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);

        ArrayList<Exercise> exercises = new ArrayList<>();

        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);

        assertEquals(day1.getExercises().size(), 0);

        System.out.println("Finished testGetExercisesEmpty");
    }

    @Test
    public void testGetExercisesOne() {
        System.out.println("\nStarting testGetExercisesOne");

        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);

        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test", 60, 100);
        exercises.add(exercise);

        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);

        assertEquals(day1.getExercises().size(), 1);
        assertEquals(day1.getExercises().get(0).getName(), "test");
        assertEquals(day1.getExercises().get(0).getDuration(), 60);
        assertEquals(day1.getExercises().get(0).getCaloriesPerHour(), 100);

        System.out.println("Finished testGetExercisesOne");
    }

    @Test
    public void testGetExercisesMany() {
        System.out.println("\nStarting testGetExercisesMany");

        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);

        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test1", 60, 100);
        exercises.add(exercise);
        exercise = new Exercise("test2", 120, 200);
        exercises.add(exercise);
        exercise = new Exercise("test3", 180, 300);
        exercises.add(exercise);

        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);

        assertEquals(day1.getExercises().size(), 3);
        assertEquals(day1.getExercises().get(0).getName(), "test1");
        assertEquals(day1.getExercises().get(0).getDuration(), 60);
        assertEquals(day1.getExercises().get(0).getCaloriesPerHour(), 100);
        assertEquals(day1.getExercises().get(1).getName(), "test2");
        assertEquals(day1.getExercises().get(1).getDuration(), 120);
        assertEquals(day1.getExercises().get(1).getCaloriesPerHour(), 200);
        assertEquals(day1.getExercises().get(2).getName(), "test3");
        assertEquals(day1.getExercises().get(2).getDuration(), 180);
        assertEquals(day1.getExercises().get(2).getCaloriesPerHour(), 300);

        System.out.println("Finished testGetExercisesMany");
    }

    @Test
    public void testAddExercisesEmpty() {
        System.out.println("\nStarting testAddExercisesEmpty");

        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);

        ArrayList<Exercise> exercises = new ArrayList<>();

        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);
        day1.addExercise(new Exercise("test", 60, 100));

        assertEquals(day1.getExercises().size(), 1);
        assertEquals(day1.getExercises().get(0).getName(), "test");
        assertEquals(day1.getExercises().get(0).getDuration(), 60);
        assertEquals(day1.getExercises().get(0).getCaloriesPerHour(), 100);

        System.out.println("Finished testAddExercisesEmpty");
    }

    @Test
    public void testAddExercisesOne() {
        System.out.println("\nStarting testAddExercisesOne");

        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);

        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test1", 60, 100);
        exercises.add(exercise);

        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);
        day1.addExercise(new Exercise("test2", 120, 200));

        assertEquals(day1.getExercises().size(), 2);
        assertEquals(day1.getExercises().get(0).getName(), "test1");
        assertEquals(day1.getExercises().get(0).getDuration(), 60);
        assertEquals(day1.getExercises().get(0).getCaloriesPerHour(), 100);
        assertEquals(day1.getExercises().get(1).getName(), "test2");
        assertEquals(day1.getExercises().get(1).getDuration(), 120);
        assertEquals(day1.getExercises().get(1).getCaloriesPerHour(), 200);

        System.out.println("Finished testAddExercisesOne");
    }

    @Test
    public void testGetExerciseCalsEmpty() {
        System.out.println("\nStarting testGetExerciseCalsEmpty");

        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);

        ArrayList<Exercise> exercises = new ArrayList<>();

        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);

        assertEquals(day1.getExerciseCals(), 0, ERROR);

        System.out.println("Finished testGetExerciseCalsEmpty");
    }

    @Test
    public void testGetExerciseCalsOne() {
        System.out.println("\nStarting testGetExerciseCalsOne");

        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);

        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test1", 60, 100);
        exercises.add(exercise);

        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);

        assertEquals(day1.getExerciseCals(), 100, ERROR);

        System.out.println("Finished testGetExerciseCalsOne");
    }

    @Test
    public void testGetExerciseCalsMany() {
        System.out.println("\nStarting testGetExerciseCalsMany");

        User user1 = new User("user1", "@MyFitnessFriend,com", 1500, 2);

        Meals breakfast = new Meals(MealType.BREAKFAST);
        Meals lunch = new Meals(MealType.LUNCH);
        Meals dinner = new Meals((MealType.DINNER));
        Meals snack = new Meals(MealType.SNACK);

        ArrayList<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise("test1", 60, 100);
        exercises.add(exercise);
        exercise = new Exercise("test2", 120, 200);
        exercises.add(exercise);

        Day day1 = new Day(user1, exercises, breakfast, lunch, dinner, snack);

        assertEquals(day1.getExerciseCals(), 500, ERROR);

        System.out.println("Finished testGetExerciseCalsMany");
    }

}
