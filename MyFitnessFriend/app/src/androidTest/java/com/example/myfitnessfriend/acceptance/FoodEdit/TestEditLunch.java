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

public class TestEditLunch {
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
        onView(withText("LUNCH")).check(matches(isDisplayed()));
        onView(withId(R.id.totalLunchCalories)).check(matches(withText("0")));
        onView(withId(R.id.lunchButton)).check(matches(isDisplayed()));
    }

    @Test
    public void testEditLunch(){

        setUp();

        onView(withId(R.id.totalLunchCalories)).check(matches(withText("0")));
        onView(withId(R.id.calorie_food_number)).check(matches(withText("0")));

        onView(withId(R.id.lunchButton)).perform(click());
        onView(withText("Dutch Apple Pie")).perform(click());

        onView(withId(R.id.numberOfCalories)).check(matches(withText("290")));
        onView(withId(R.id.proteinGrams)).check(matches(withText("2")));
        onView(withId(R.id.carbGrams)).check((matches(withText("44"))));
        onView(withId(R.id.fatGrams)).check(matches(withText("11")));

        onView(withId(R.id.addButton)).perform(click());
        onView(withId(R.id.totalLunchCalories)).check(matches(withText("290")));
        onView(withId(R.id.calorie_food_number)).check(matches(withText("290")));

        //add second food
        onView(withId(R.id.lunchButton)).perform(click());
        onView(withText("Dutch Apple Pie")).perform(click());

        onView(withId(R.id.addButton)).perform(click());
        onView(withId(R.id.totalLunchCalories)).check(matches(withText("580")));
        onView(withId(R.id.calorie_food_number)).check(matches(withText("580")));

        //clean up
        tearDown();

    }
}
