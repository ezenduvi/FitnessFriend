package mff.presentation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;

import mff.R;

public class WeeklyExerciseActivity extends AppCompatActivity {

    @Override
    /**
     * onCreate
     * sets up weekly exercise summary page to show how many
     * calories burned each day of the week
     * @param savedInstanceState - the current instance
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_exercise_view);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        createBurnedBarChart();
        createExerciseBarChart();

    }

    /**
     * backArrowOnClick
     * Takes the user back to ExercisePageActivity when they click on they back arrow
     * @param v
     */
    public void backArrowOnClick(View v) {
        Intent backHome = new Intent(WeeklyExerciseActivity.this, ExercisePageActivity.class);
        WeeklyExerciseActivity.this.startActivity(backHome);
    }

    /**
     * createBurnedBarChart
     * creates bar chart displaying calories burned each day of the week
     */
    public void createBurnedBarChart(){

        BarChart barChart = findViewById(R.id.minsChart);

        ArrayList<BarEntry> burnedPerDay = new ArrayList<>();
        ArrayList<String> daysOfWeek = new ArrayList<>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));


        burnedPerDay.add(new BarEntry(0, 400));
        burnedPerDay.add(new BarEntry(1, 520));
        burnedPerDay.add(new BarEntry(2, 200));
        burnedPerDay.add(new BarEntry(3, 300));
        burnedPerDay.add(new BarEntry(4, 400));
        burnedPerDay.add(new BarEntry(5, 500));
        burnedPerDay.add(new BarEntry(6, 450));

        BarDataSet barDataSet = new BarDataSet(burnedPerDay, "Calories burned per Day");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(daysOfWeek));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisRight().setDrawAxisLine(false);

        int purple_300 = getResources().getColor(R.color.purple_300);
        barDataSet.setColors(new int[] {purple_300});

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Calories burned per day of the week");
        barChart.animateY(2000);
        barChart.getAxisLeft().setDrawLabels(false);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getLegend().setEnabled(false);
        barChart.setTouchEnabled(false);
        barChart.setDragEnabled(false);
        barChart.setScaleEnabled(false);
        barChart.setScaleXEnabled(false);
        barChart.setScaleYEnabled(false);
        barChart.setPinchZoom(false);

        Description description = new Description();
        description.setText("");
        barChart.setDescription(description);

    }

    /**
     * createExerciseBarChart
     * creates bar chart displaying minutes exercised each day of the week
     */
    public void createExerciseBarChart(){

        BarChart barChart = findViewById(R.id.burnedChart);

        ArrayList<BarEntry> timePerDay = new ArrayList<>();
        ArrayList<String> daysOfWeek = new ArrayList<>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));

        timePerDay.add(new BarEntry(0, 40));
        timePerDay.add(new BarEntry(1, 52));
        timePerDay.add(new BarEntry(2, 20));
        timePerDay.add(new BarEntry(3, 30));
        timePerDay.add(new BarEntry(4, 40));
        timePerDay.add(new BarEntry(5, 50));
        timePerDay.add(new BarEntry(6, 45));

        BarDataSet barDataSet = new BarDataSet(timePerDay, "Exercise time per Day");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(daysOfWeek));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisRight().setDrawAxisLine(false);

        int purple_300 = getResources().getColor(R.color.purple_300);
        barDataSet.setColors(new int[] {purple_300});

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Exercise time per day of the week");
        barChart.animateY(2000);
        barChart.getAxisLeft().setDrawLabels(false);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getLegend().setEnabled(false);
        barChart.setTouchEnabled(false);
        barChart.setDragEnabled(false);
        barChart.setScaleEnabled(false);
        barChart.setScaleXEnabled(false);
        barChart.setScaleYEnabled(false);
        barChart.setPinchZoom(false);

        Description description = new Description();
        description.setText("");
        barChart.setDescription(description);

    }

}

