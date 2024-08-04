package mff.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

import mff.R;
import mff.application.Main;
import mff.business.AccessExercise;
import mff.business.AccessProfile;
import mff.business.Calculate;
import mff.objects.Day;
import mff.objects.Exercise;
import mff.objects.User;

/**
 * ExercisePageActivity
 * this Activity is the main page of the exercise input flow
 */
public class ExercisePageActivity extends AppCompatActivity {

    int searchRequestCode = 0;
    int caloriesBurned = 0;
    int totalCaloriesBurned = 0;
    int totalCaloriesConsumed = 0;

    ArrayList<String> exerciseNames = new ArrayList<>();
    ArrayList<String> exerciseDuration = new ArrayList<>();
    ArrayList<String> caloriesBurnedByExercise = new ArrayList<>();

    User user;
    Date date;
    Day today;
    ArrayList<Exercise> exercisesDone = new ArrayList<>();
    AccessExercise accessExercise = new AccessExercise();
    AccessProfile accessProfile;

    RecyclerView exerciseListView;
    TextView textViewToChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.exercise_ui);
        exerciseListView = findViewById(R.id.exerciseList);
        accessProfile = new AccessProfile();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            totalCaloriesConsumed = extras.getInt("totalCaloriesConsumed");
        }

        user = accessProfile.getUser();

        exercisesDone = accessExercise.getExercisesByDay();
        exerciseDuration = accessExercise.getDurationsOnly(exercisesDone);
        caloriesBurnedByExercise = accessExercise.getCaloriesPerExercise(exercisesDone);

        System.out.println("size of exercisesDone is " + exercisesDone.size());
        System.out.println("size of exerciseDuration is " + exerciseDuration.size());
        System.out.println("size of caloriesBurnedByExercise is " + caloriesBurnedByExercise.size());
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, accessExercise.getExerciseNamesOnly(exercisesDone),
                exerciseDuration, caloriesBurnedByExercise);
        exerciseListView.setAdapter(adapter);
        exerciseListView.setLayoutManager(new LinearLayoutManager(this));

        displayCalculationNumbers(exercisesDone);

    }

    /**
     * addExerciseOnClick
     * when user clicks 'view exercise' button, this starts the SearchExerciseActivity
     * @param view
     */
    public void addExerciseOnClick(View view) {
        Intent exercisePage = new Intent(ExercisePageActivity.this, SearchExerciseActivity.class);
        startActivityForResult(exercisePage, searchRequestCode);
    }

    /**
     * backArrowOnClick
     * when user clicks the back arrow this takes them back to HomeActivity
     * @param view
     */
    public void backArrowOnClick(View view) {
        Intent backHome = new Intent(ExercisePageActivity.this, HomeActivity.class);
        backHome.putExtra("caloriesBurned",totalCaloriesBurned);
        ExercisePageActivity.this.startActivity(backHome);
    }

    /**
     * onActivityResult
     * adds the selected exercise and duration to a list of exercises
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        String exercise = intent.getStringExtra("exercise");
        int duration = intent.getIntExtra("clickedDuration",-1);

        System.out.println("exercise is " + exercise);
        System.out.println("clickedDuration is " + duration);

        addExerciseToList(exercise, duration);

        displayCalculationNumbers(accessExercise.getExercisesByDay());

    }

    /**
     * addExerciseToList
     * adds the selected exercise and duration to a list of exercises.
     * Called by onActivityResult.
     * @param exercise the selected exercise
     * @param duration the selected duration that the exercise was performed
     */
    private void addExerciseToList(String exercise, int duration) {
        System.out.println("Add " + exercise + " to list");

        int caloriesBurnedPerHour;
        Exercise exerciseToAdd = accessExercise.getExercise(exercise); //returns the exercise object

        exerciseToAdd.setDuration(duration); //sets the duration for the exercise object
        caloriesBurnedPerHour = exerciseToAdd.getCaloriesPerHour();

        caloriesBurned = Calculate.getCalBurned(duration,caloriesBurnedPerHour);

        accessExercise.addExerciseToDay(exerciseToAdd); //adds the exercise to db
        caloriesBurnedByExercise.add(caloriesBurned + " calories");

        System.out.println("calories burned = " + caloriesBurned);

        exercisesDone = accessExercise.getExercisesByDay(); // get the days exercises
        exerciseDuration = accessExercise.getDurationsOnly(exercisesDone);
        caloriesBurnedByExercise = accessExercise.getCaloriesPerExercise(exercisesDone);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, accessExercise.getExerciseNamesOnly(exercisesDone),
                exerciseDuration, caloriesBurnedByExercise);
        exerciseListView.setAdapter(adapter);
        exerciseListView.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * displayCalculationNumbers
     * populates the TestView fields with the correct calories numbers and
     * calls a method to calculate the remaining calories
     */
    public void displayCalculationNumbers(ArrayList<Exercise> exercisesDone) {
        totalCaloriesBurned = accessExercise.getTotalCaloriesBurned(exercisesDone);

        textViewToChange = (TextView) findViewById(R.id.calorie_goal_number);
        textViewToChange.setText(String.valueOf(user.getCalorieGoal()));

        textViewToChange = (TextView) findViewById(R.id.calorie_food_number);
        textViewToChange.setText(String.valueOf(totalCaloriesConsumed));

        textViewToChange = (TextView) findViewById(R.id.calorie_exercise_number);
        textViewToChange.setText(String.valueOf(totalCaloriesBurned));

        textViewToChange = (TextView) findViewById(R.id.calories_remaining_number);
        textViewToChange.setText(String.valueOf(Calculate.calculateRemainingCalories(user.getCalorieGoal(),
                totalCaloriesConsumed,totalCaloriesBurned)));

    }

    /**
     * exerciseSummaryOnClick
     * when user clicks the button it takes them to the summary page
     * @param v
     */
    public void exerciseSummaryOnClick(View v){
        Intent exerciseSummary = new Intent(ExercisePageActivity.this, WeeklyExerciseActivity.class);
        ExercisePageActivity.this.startActivity(exerciseSummary);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Main.shutDown();
    }
}
