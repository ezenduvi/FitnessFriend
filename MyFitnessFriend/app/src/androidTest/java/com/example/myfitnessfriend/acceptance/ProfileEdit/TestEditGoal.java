package com.example.myfitnessfriend.acceptance.ProfileEdit;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mff.R;
import mff.application.Main;
import mff.application.Services;
import mff.persistence.HSQLDB;
import mff.presentation.HomeActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class TestEditGoal {

    @Rule
    public ActivityTestRule<HomeActivity> homeActivity = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void testProfileScreen(){
        onView(withText("VIEW PROFILE")).check(matches(isDisplayed()));
    }

    @Test
    public void testEditGoal(){

        onView(withId(R.id.profileButton)).perform(click());

        onView((withId(R.id.calorieGoalEdit))).perform(click());
        onView(withId(R.id.newCalorieGoal)).perform(click(),typeText("1500"));
        onView(withId(R.id.okButton)).perform(click());

        onView((withId(R.id.waterGoalEdit))).perform(click());
        onView(withId(R.id.newWaterGoal)).perform(click(),typeText("10"));
        onView(withId(R.id.okButton)).perform(click());

        onView((withId(R.id.waterGoalAmount))).check((matches(withText("10 cups"))));

        onView(withId(R.id.headerBackButton)).perform(click());
        onView((withId(R.id.calorie_goal_number))).check(matches(withText("1500")));


        //clean up

        onView(withId(R.id.profileButton)).perform(click());

        onView((withId(R.id.calorieGoalEdit))).perform(click());
        onView(withId(R.id.newCalorieGoal)).perform(click(),typeText("2000"));
        onView(withId(R.id.okButton)).perform(click());

        onView((withId(R.id.waterGoalEdit))).perform(click());
        onView(withId(R.id.newWaterGoal)).perform(click(),typeText("7"));
        onView(withId(R.id.okButton)).perform(click());

    }
}
