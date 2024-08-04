package com.example.myfitnessfriend.Objects;

import org.junit.Test;

import mff.objects.Exercise;

import static org.junit.Assert.assertEquals;

public class ExerciseTest {

    @Test
    public void testConstructorNormal() {
        Exercise exercise;

        System.out.println("\nStarting testConstructorNormal");

        exercise = new Exercise("test", 10, 100);

        assertEquals(exercise.getName(), "test");
        assertEquals(exercise.getDuration(), 10);
        assertEquals(exercise.getCaloriesPerHour(), 100);

        System.out.println("Finished testConstructorNormal");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorNullName() {
        Exercise exercise;

        System.out.println("\nStarting testConstructorNullName");

        exercise = new Exercise(null, 10, 100);

        System.out.println("Finished testConstructorNullName");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorEmptyName() {
        Exercise exercise;

        System.out.println("\nStarting testConstructorEmptyName");

        exercise = new Exercise("", 10, 100);

        System.out.println("Finished testConstructorEmptyName");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorNegativeDuration() {
        Exercise exercise;

        System.out.println("\nStarting testConstructorNegativeDuration");

        exercise = new Exercise("test", -10, 100);

        System.out.println("Finished testConstructorNegativeDuration");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorMaxNegativeDuration() {
        Exercise exercise;

        System.out.println("\nStarting testConstructorMaxNegativeDuration");

        exercise = new Exercise("test", Integer.MIN_VALUE, 100);

        System.out.println("Finished testConstructorMaxNegativeDuration");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorZeroDuration() {
        Exercise exercise;

        System.out.println("\nStarting testConstructorZeroDuration");

        exercise = new Exercise("test", 0, 100);

        System.out.println("Finished testConstructorZeroDuration");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorNegativeCalsPerHour() {
        Exercise exercise;

        System.out.println("\nStarting testConstructorNegativeCalsPerHour");

        exercise = new Exercise("test", 10, -100);

        System.out.println("Finished testConstructorNegativeCalsPerHour");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorMaxNegativeCalsPerHour() {
        Exercise exercise;

        System.out.println("\nStarting testConstructorMaxNegativeDuration");

        exercise = new Exercise("test", 10, Integer.MIN_VALUE);

        System.out.println("Finished testConstructorMaxNegativeDuration");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorZeroCalsPerHour() {
        Exercise exercise;

        System.out.println("\nStarting testConstructorZeroCalsPerHour");

        exercise = new Exercise("test", 10, 0);

        System.out.println("Finished testConstructorZeroCalsPerHour");
    }

    @Test
    public void testSetDurationNormal() {
        Exercise exercise;

        System.out.println("\nStarting testSetDurationNormal");

        exercise = new Exercise("test", 10, 100);
        assertEquals(exercise.getDuration(), 10);

        exercise.setDuration(20);
        assertEquals(exercise.getDuration(), 20);

        System.out.println("Finished testSetDurationNormal");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetDurationNegative() {
        Exercise exercise;

        System.out.println("\nStarting testSetDurationNegative");

        exercise = new Exercise("test", 10, 100);
        assertEquals(exercise.getDuration(), 10);

        exercise.setDuration(-10);

        System.out.println("Finished testSetDurationNegative");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetDurationMaxNegative() {
        Exercise exercise;

        System.out.println("\nStarting testSetDurationMaxNegative");

        exercise = new Exercise("test", 10, 100);
        assertEquals(exercise.getDuration(), 10);

        exercise.setDuration(Integer.MIN_VALUE);

        System.out.println("Finished testSetDurationMaxNegative");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetDurationZero() {
        Exercise exercise;

        System.out.println("\nStarting testSetDurationMaxNegative");

        exercise = new Exercise("test", 10, 100);
        assertEquals(exercise.getDuration(), 10);

        exercise.setDuration(0);

        System.out.println("Finished testSetDurationMaxNegative");
    }
}
