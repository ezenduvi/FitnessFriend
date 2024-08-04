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

import mff.R;
import mff.business.AccessExercise;

/**
 * ExerciseItemPageActivity
 * this Activity is the 2nd part in the exercise input flow
 */
public class SearchExerciseActivity extends AppCompatActivity {

    AccessExercise allExercises = new AccessExercise();
    ListView exerciseListView;
    EditText inputSearch;
    ArrayAdapter<String> adapter;
    int searchRequestCode = 1;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_exercises);
        exerciseListView = findViewById(R.id.layout_list);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        addExercises();

    } //onCreate

    /**
     * addExercises
     * Shows a list of exercises that the user can select from
     */
    public void addExercises() {

        ArrayList<String> exerciseNames = AccessExercise.getExerciseNamesOnly(AccessExercise.getAllExercises());

        adapter = new ArrayAdapter<String>(this, R.layout.row_exercise_database, exerciseNames);
        exerciseListView.setAdapter(adapter);

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (SearchExerciseActivity.this).adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent goToExercisePage = new Intent(SearchExerciseActivity.this, ExerciseItemPageActivity.class);
                String clickedExerciseName = adapter.getItem(position);
                goToExercisePage.putExtra("name", clickedExerciseName);
                startActivityForResult(goToExercisePage, searchRequestCode);
           }
        });

    }

    /**
     * onActivityResult
     * Passes the required information back to the previous Activity
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode==searchRequestCode) {
            if (resultCode == RESULT_OK) {

                String exercise = intent.getStringExtra("exercise");
                int duration = intent.getIntExtra("clickedDuration", -1);

                Intent newIntent = new Intent();
                newIntent.putExtra("exercise", exercise);
                newIntent.putExtra("clickedDuration", duration);
                setResult(RESULT_OK, newIntent);
                finish();

            }
        }
    }

    /**
     * backArrowOnClick
     * Takes the user back to HomeActivity when they click on they back arrow
     * @param view
     */
    public void backArrowOnClick(View view) {
        Intent backHome = new Intent(SearchExerciseActivity.this, HomeActivity.class);
        SearchExerciseActivity.this.startActivity(backHome);
    }

}
