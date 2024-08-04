package com.example.myfitnessfriend.Objects;

import org.junit.Test;
import mff.objects.Diary;
import static org.junit.Assert.assertEquals;

public class DiaryTest {

    @Test
    public void testValidReflection(){
        System.out.println("Starting testValidReflection");

        Diary diary = new Diary("I stayed within my calorie Limit for the day");
        assertEquals("I stayed within my calorie Limit for the day", diary.getReflection());

        System.out.println("Finished testValidReflection");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullReflection() {
       Diary diary;

        System.out.println("\nStarting testNullReflection");

        diary = new Diary(null);

        System.out.println("Finished testNullReflection");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyReflection() {
        Diary diary;

        System.out.println("\nStarting testEmptyReflection");

        diary = new Diary("");

        System.out.println("Finished testEmptyReflection");
    }
}
