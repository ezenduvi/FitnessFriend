package com.example.myfitnessfriend;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

import com.example.myfitnessfriend.acceptance.TestExercisePage;
import com.example.myfitnessfriend.acceptance.FoodEdit.TestEditBreakfast;
import com.example.myfitnessfriend.acceptance.FoodEdit.TestEditDinner;
import com.example.myfitnessfriend.acceptance.FoodEdit.TestEditLunch;
import com.example.myfitnessfriend.acceptance.FoodEdit.TestEditSnack;
import com.example.myfitnessfriend.acceptance.ProfileEdit.TestEditGoal;
import com.example.myfitnessfriend.acceptance.TestReflectionsPage;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestEditBreakfast.class,
        TestEditLunch.class,
        TestEditDinner.class,
        TestEditSnack.class,
        TestEditGoal.class,
        TestExercisePage.class,
        TestReflectionsPage.class})
public class RunAcceptanceTests
{
    public RunAcceptanceTests()
    {
        System.out.println("Acceptance tests");
    }
}
