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

public class WeeklySummaryActivity extends AppCompatActivity {

    @Override
    /**
     * onCreate
     * sets up weekly summary page
     * @param savedInstanceState - current instance
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly_view);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        createBarChart();

    }

    /**
     * backArrowOnClick
     * allows you to go back to home page using back arrow
     * @param v - the current view
     */
    public void backArrowOnClick(View v) {
        Intent backHome = new Intent(WeeklySummaryActivity.this, HomeActivity.class);
        WeeklySummaryActivity.this.startActivity(backHome);
    }

    /**
     * createBarChart
     * show bar chart with calories consumed each day
     */
    public void createBarChart(){

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> caloriesPerDay = new ArrayList<>();
        ArrayList<String> daysOfWeek = new ArrayList<>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));

        caloriesPerDay.add(new BarEntry(0, 1400));
        caloriesPerDay.add(new BarEntry(1, 1520));
        caloriesPerDay.add(new BarEntry(2, 1200));
        caloriesPerDay.add(new BarEntry(3, 1300));
        caloriesPerDay.add(new BarEntry(4, 1400));
        caloriesPerDay.add(new BarEntry(5, 1500));
        caloriesPerDay.add(new BarEntry(6, 1450));

        BarDataSet barDataSet = new BarDataSet(caloriesPerDay, "Calories per Day");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        int purple_300 = getResources().getColor(R.color.purple_300);
        barDataSet.setColors(new int[] {purple_300});

        BarData barData = new BarData(barDataSet);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(daysOfWeek));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);

        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisRight().setDrawAxisLine(false);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Calories eaten per day of the week");
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
