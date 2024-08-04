package mff.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Objects;

import mff.R;
import mff.business.AccessExercise;

/**
 * ExerciseItemPageActivity
 * this Activity is the 3rd part in the exercise input flow
 */
public class ExerciseItemPageActivity extends AppCompatActivity{

    String exercise;
    ListView exerciseDurationView;
    EditText exerciseDuration;
    ArrayAdapter<Integer> adapter;
    int searchRequestCode = 1;
    Integer clickedDuration;

    /**
     * onCreate
     * this is the page where the user selects the duration of
     * their exercise after they've selected their exercise
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_item_page);
        exerciseDurationView = findViewById(R.id.layout_list);
        exerciseDuration = (EditText) findViewById(R.id.exerciseDuration);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.item_toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            exercise = extras.getString("name");
            Objects.requireNonNull(getSupportActionBar()).setTitle(exercise);

            addExerciseDuration();
        }

    }

    /**
     * addExerciseOnClick
     * after selecting duration, this ends the Activity and returns the user
     * to the ExercisePageActivity
     * @param view
     */
    public void addExerciseOnClick(View view) {

        Intent addExercise = new Intent();
        addExercise.putExtra("clickedDuration", clickedDuration);
        addExercise.putExtra("exercise", exercise);
        setResult(RESULT_OK, addExercise);
        finish();

    }

    /**
     * addExerciseDuration
     * shows a list of durations in 5 minute increments that the user selects from
     */
    public void addExerciseDuration(){
        AccessExercise ae = new AccessExercise();
        ArrayList<Integer> exerciseDurations = new ArrayList<>();
        exerciseDurations = ae.getDurationList();

        adapter = new ArrayAdapter<Integer>(this, R.layout.row_exercise_database, exerciseDurations);
        exerciseDurationView.setAdapter(adapter);

        exerciseDuration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (ExerciseItemPageActivity.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        exerciseDurationView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickedDuration = adapter.getItem(position);
            }
        });

    }
}
