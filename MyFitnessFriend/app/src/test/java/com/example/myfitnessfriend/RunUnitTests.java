package com.example.myfitnessfriend;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.myfitnessfriend.Business.AccessDayTests;
import com.example.myfitnessfriend.Business.AccessExerciseTest;
import com.example.myfitnessfriend.Business.AccessFoodsTest;
import com.example.myfitnessfriend.Business.AccessProfileTest;
import com.example.myfitnessfriend.Business.CalculateTest;
import com.example.myfitnessfriend.Objects.ExerciseTest;
import com.example.myfitnessfriend.Objects.FoodTest;
import com.example.myfitnessfriend.Objects.UserTest;
import com.example.myfitnessfriend.Objects.DayTest;
import com.example.myfitnessfriend.Objects.MealsTest;

import mff.business.AccessDay;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        UserTest.class,
        DayTest.class,
        MealsTest.class,
        FoodTest.class,
        CalculateTest.class,
        ExerciseTest.class,
        AccessExerciseTest.class,
        AccessProfileTest.class,
        AccessFoodsTest.class,
        AccessDayTests.class
})

public class RunUnitTests {
}
