package mff.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import mff.R;
import mff.business.AccessDiary;
import mff.objects.Diary;

public class ReflectionActivity extends AppCompatActivity {
    EditText refText;
    Button commit;
    ListView refViewList;
    ArrayList<Diary> refList;
    ArrayAdapter<Diary> refAdapter;
    Diary newReflection;

    private static final AccessDiary accessDiary = new AccessDiary();

    @Override
    /**
     * onCreate
     * sets up weekly exercise summary page to show how many
     * calories burned each day of the week
     * @param savedInstanceState - the current instance
     */
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.reflection_view);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        refText = (EditText) findViewById(R.id.createCommit);
        commit = (Button) findViewById(R.id.commitButton);
        refViewList = (ListView) findViewById(R.id.listReflection);
        refList = new ArrayList<Diary>();
        refAdapter = new ArrayAdapter<Diary>(this,android.R.layout.simple_list_item_activated_1,refList);
        refViewList.setAdapter(refAdapter);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refList.add(new Diary(refText.getText().toString()));
                System.out.println("Added " + new Diary(refText.getText().toString()) + " to database");
                accessDiary.addReflection(new Diary(refText.getText().toString()));
                refAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * backArrowOnClick
     * Takes the user back to HomeActivity when they click on they back arrow
     * @param v
     */
    public void backArrowOnClick(View v) {
        Intent backHome = new Intent(ReflectionActivity.this, HomeActivity.class);
        ReflectionActivity.this.startActivity(backHome);
    }

}
