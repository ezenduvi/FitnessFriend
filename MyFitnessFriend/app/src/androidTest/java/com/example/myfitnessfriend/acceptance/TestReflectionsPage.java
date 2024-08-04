package com.example.myfitnessfriend.acceptance;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import mff.R;
import mff.application.Main;
import mff.application.Services;
import mff.persistence.HSQLDB;
import mff.presentation.HomeActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class TestReflectionsPage {
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
    public void testReflectionPageDisplays() {
        onView(withText("ADD REFLECTION")).check(matches(isDisplayed())).perform(click());
        onView(withText("Reflections")).check(matches(isDisplayed()));
        onView(withId(R.id.commitButton)).check(matches(isDisplayed()));
    }


    @Test
    public void testAddReflection() {
        setUp();
        onView(withText("ADD REFLECTION")).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.createCommit)).perform(click(), typeText("Today was very productive"));
        onView(withId(R.id.commitButton)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.createCommit)).perform(clearText());
        Espresso.closeSoftKeyboard();

        onView(withText("Today was very productive")).check(matches(isDisplayed()));

        //cleanup
        tearDown();
    }


    @Test
    public void testAddMultipleReflections() {
        setUp();
        onView(withText("ADD REFLECTION")).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.createCommit)).perform(click(), typeText("Today was not as productive"));
        onView(withId(R.id.commitButton)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.createCommit)).perform(clearText());
        Espresso.closeSoftKeyboard();

        onView(withText("Today was not as productive")).check(matches(isDisplayed()));

        onView(withId(R.id.createCommit)).perform(click(), typeText("I feel very exhausted today"));
        onView(withId(R.id.commitButton)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.createCommit)).perform(clearText());
        Espresso.closeSoftKeyboard();

        onView(withText("I feel very exhausted today")).check(matches(isDisplayed()));


        //cleanup
        tearDown();
    }

    @Test
    public void testInvalidReflection() {
        setUp();
        onView(withText("ADD REFLECTION")).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.createCommit)).perform(click(), typeText(""));
        onView(withId(R.id.commitButton)).check(matches(isDisplayed())).perform(click());
        Espresso.closeSoftKeyboard();

        //cleanup
        tearDown();
    }

}
