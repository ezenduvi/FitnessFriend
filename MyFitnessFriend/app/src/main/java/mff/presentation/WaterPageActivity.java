package mff.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import mff.R;

public class WaterPageActivity extends AppCompatActivity {

    @Override
    /**
     * onCreate
     * sets up water page
     * @param savedInstanceState - current instance
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water_page);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

    }

    /**
     * addWaterOnClick
     * add water when button is clicked
     * @param view - the current view
     */
    public void addWaterOnClick(View view) {

        Intent addFood = new Intent();
        addFood.putExtra("meal", "Water");
        setResult(RESULT_OK, addFood);
        finish();

    }

}
