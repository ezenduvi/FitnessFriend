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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class TestExercisePage {
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
    public void testExercisePageDisplays() {
        onView(withText("VIEW EXERCISE")).check(matches(isDisplayed())).perform(click());
        onView(withText("GOAL")).check(matches(isDisplayed()));
        onView(withId(R.id.exerciseButton)).check(matches(isDisplayed()));
    }

    @Test
    public void testSearchExercisePageDisplays() {
        setUp();
        onView(withText("VIEW EXERCISE")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.exerciseButton)).check(matches(isDisplayed())).perform(click());
        onView(withText("Running stairs down")).check(matches(isDisplayed()));

        //cleanup
        tearDown();
    }

    @Test
    public void testSelectExerciseDurationPageDisplays() {
        onView(withText("VIEW EXERCISE")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.exerciseButton)).check(matches(isDisplayed())).perform(click());
        onView(withText("Running stairs down")).check(matches(isDisplayed()));
        onView(withText("Unicycling")).check(matches(isDisplayed())).perform(click());
        onView(withText("Unicycling")).check(matches(isDisplayed()));
    }

    @Test
    public void testAddExercise() {
        setUp();
        onView(withText("VIEW EXERCISE")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.exerciseButton)).check(matches(isDisplayed())).perform(click());
        onView(withText("Cycling mountain bike bmx")).check(matches(isDisplayed())).perform(click());
        onView(withText("10")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.addExercise)).perform(click());
        onView(withText("Cycling mountain bike bmx")).check(matches(isDisplayed()));
        onView(withId(R.id.calorie_exercise_number)).check(matches(withText("99")));

        //cleanup
        tearDown();
    }

    @Test
    public void testSearchExercise() {
        setUp();
        onView(withText("VIEW EXERCISE")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.exerciseButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.inputSearch)).perform(click(), typeText("Unicyc"));
        Espresso.closeSoftKeyboard();

        onView(withText("Unicycling")).check(matches(isDisplayed())).perform(click());
        onView(withText("10")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.addExercise)).perform(click());
        onView(withText("Unicycling")).check(matches(isDisplayed()));
        onView(withId(R.id.calorie_exercise_number)).check(matches(withText("58")));

        //cleanup
        tearDown();
    }

    @Test
    public void testSearchMinutes() {
        setUp();
        onView(withText("VIEW EXERCISE")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.exerciseButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.inputSearch)).perform(click(), typeText("Stair m"));
        Espresso.closeSoftKeyboard();

        onView(withText("Stair machine")).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.exerciseDuration)).perform(click(), typeText("1"));
        Espresso.closeSoftKeyboard();

        onView(withText("100")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.addExercise)).perform(click());
        onView(withText("Stair machine")).check(matches(isDisplayed()));
        onView(withId(R.id.calorie_exercise_number)).check(matches(withText("1055")));

        //cleanup
        tearDown();
    }

    @Test
    public void testExerciseNotFound() {
        setUp();
        onView(withText("VIEW EXERCISE")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.exerciseButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.inputSearch)).perform(click(), typeText("gvfjfhasjbkrfgiureufgbeyvg"));
        Espresso.closeSoftKeyboard();

        //cleanup
        tearDown();
    }

    @Test
    public void testMinutesNotFound() {
        setUp();

        onView(withText("VIEW EXERCISE")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.exerciseButton)).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.inputSearch)).perform(click(), typeText("Stair m"));
        Espresso.closeSoftKeyboard();

        onView(withText("Stair machine")).check(matches(isDisplayed())).perform(click());

        onView(withId(R.id.exerciseDuration)).perform(click(), typeText("10000000"));
        Espresso.closeSoftKeyboard();

        //cleanup
        tearDown();
    }

    @Test
    public void testAddMultipleExercises() {
        setUp();

        onView(withText("VIEW EXERCISE")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.exerciseButton)).check(matches(isDisplayed())).perform(click());
        onView(withText("Cycling mountain bike bmx")).check(matches(isDisplayed())).perform(click());
        onView(withText("10")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.addExercise)).perform(click());
        onView(withText("Cycling mountain bike bmx")).check(matches(isDisplayed()));
        onView(withId(R.id.calorie_exercise_number)).check(matches(withText("99")));
        onView(withId(R.id.exerciseButton)).check(matches(isDisplayed())).perform(click());
        onView(withText("Unicycling")).check(matches(isDisplayed())).perform(click());
        onView(withText("10")).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.addExercise)).perform(click());
        onView(withId(R.id.calorie_exercise_number)).check(matches(withText("157")));

        //cleanup
        tearDown();
    }

}
