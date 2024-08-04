package com.example.myfitnessfriend.acceptance.FoodEdit;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import mff.R;
import mff.application.Main;
import mff.application.Services;
import mff.persistence.HSQLDB;
import mff.presentation.HomeActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class TestEditWater {
    private HSQLDB dataAccess;
    /**
     * setUp
     * creates connection to database
     */
    public void setUp() {
        Services.createDataAccess(new HSQLDB(Main.dbName));
        dataAccess = (HSQLDB) Services.getDataAccess(Main.dbName);
        dataAccess.createSaveState();
    }
    /**
     * tearDown
     * rolls back changes after testing
     */
    public void tearDown() {
        dataAccess.rollbackSaveState();
        Services.closeDataAccess();
    }

    @Rule
    public ActivityTestRule<HomeActivity> homeActivity = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void testOnScreen(){
        onView(withText("WATER")).check(matches(isDisplayed()));
        onView(withId(R.id.waterButton)).check(matches(isDisplayed()));
    }

    @Test
    public void testEditWater(){

        setUp();

        onView(withId(R.id.calorie_food_number)).check(matches(withText("0")));

        onView(withId(R.id.waterButton)).perform(click());
        onView(withId(R.id.servingSizeNumber)).check(matches(withText("1 cup")));
        onView(withId(R.id.numberOfServings)).check(matches(withText("1")));

        onView(withId(R.id.okWater)).perform(click());
        onView(withId(R.id.calorie_food_number)).check(matches(withText("0")));

        //clean up
        tearDown();

    }
}
