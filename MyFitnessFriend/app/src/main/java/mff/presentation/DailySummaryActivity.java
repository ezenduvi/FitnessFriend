package mff.presentation;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import mff.R;
import mff.business.Calculate;

public class DailySummaryActivity extends AppCompatActivity {
    private PieChart pieChart;
    int waterConsumed;
    float proteinConsumed;
    float carbsConsumed;
    float fatConsumed;
    int totalCaloriesConsumed;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    @Override
    /**
     * onCreate
     * sets up daily summary page to display summary of calories consumed
     * as well as macros consumed
     * @param savedInstanceState - the current instance
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dailyview);
        calendar = Calendar.getInstance();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Bundle extras = getIntent().getExtras();
        waterConsumed = extras.getInt("water");
        totalCaloriesConsumed = extras.getInt("totalCalories");
        proteinConsumed = (float) extras.getDouble("protein");
        carbsConsumed =  (float)  extras.getDouble("carbs");
        fatConsumed =  (float)  extras.getDouble("fat");

        TextView dateView = findViewById(R.id.today);
        dateFormat = new SimpleDateFormat("EEEE, MMMM d yyyy", Locale.CANADA);
        date = dateFormat.format(calendar.getTime());
        dateView.setText(date);

        TextView calorieView = findViewById(R.id.dailyCalorieNumber);
        calorieView.setText(String.valueOf(totalCaloriesConsumed));

        TextView waterValue = findViewById(R.id.dailyWaterNumber);
        String waterString = waterConsumed + " cups";
        waterValue.setText(waterString);

        TextView proteinValue = findViewById(R.id.dailyProteinGrams);
        String proteinString = proteinConsumed + "g";
        proteinValue.setText(proteinString);

        TextView fatValue = findViewById(R.id.dailyFatGrams);
        String fatString = fatConsumed + "g";
        fatValue.setText(fatString);

        TextView carbValue = findViewById(R.id.dailyCarbGrams);
        String carbString = carbsConsumed + "g";
        carbValue.setText(carbString);

        float proteinPer = Calculate.getProteinCal(proteinConsumed, totalCaloriesConsumed);
        float carbsPer = Calculate.getCarbsCal(carbsConsumed, totalCaloriesConsumed);
        float fatPer = Calculate.getFatCal(fatConsumed, totalCaloriesConsumed);

        pieChart = findViewById(R.id.pieChart);
        List<PieEntry> items= new ArrayList<>();
        items.add(new PieEntry(proteinPer,"Protein"));
        items.add(new PieEntry(carbsPer,"Carbohydrates"));
        items.add(new PieEntry(fatPer,"Fat"));

        pieChart.setDrawHoleEnabled(false);
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#FF4C4C"));
        colors.add(Color.parseColor("#34BF49"));
        colors.add(Color.parseColor("#0099E5"));

        PieDataSet pieDataSet = new PieDataSet(items,"");
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextColor(Color.parseColor("#FFFFFF"));
        pieData.setValueTextSize(20);
        pieData.setValueFormatter(new PercentFormatter(pieChart));

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setHighlightPerTapEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setDrawEntryLabels(false);
        pieChart.setData(pieData);

    }

}
