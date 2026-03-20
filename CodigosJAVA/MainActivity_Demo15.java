package com.example.a2026_01_holamundoandroid;


import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
Demo de Sistesis de VOZ
http://www.tutorialspoint.com/android/android_text_to_speech.htm
 */

public class MainActivity_Demo15 extends AppCompatActivity {
    TextToSpeech t1;
    EditText ed1;
    Button b1;

    BarDataSet barDataSet;
    LineDataSet dataSet;

    LineChart chart;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo15);

        b1 = findViewById(R.id.button);
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, 10f));
        entries.add(new Entry(1f, 15f));
        entries.add(new Entry(2f, 8f));
        entries.add(new Entry(3f, 12f));

        dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK);

        chart = findViewById(R.id.chart);
        barChart = findViewById(R.id.barChart);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh the chart

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBarChart();
            }
        });
    }

    private void showBarChart() {
        ArrayList<Double> valueList = new ArrayList<Double>();
        ArrayList<BarEntry> entries = new ArrayList<>();
        String title = "Title";

        //input data
        for (int i = 0; i < 6; i++) {
            valueList.add(i * 100.1);
        }

        //fit the data into a bar
        for (int i = 0; i < valueList.size(); i++) {
            BarEntry barEntry = new BarEntry(i, valueList.get(i).floatValue());
            entries.add(barEntry);
        }

        BarDataSet barDataSet = new BarDataSet(entries, title);

        BarData data = new BarData(barDataSet);
        barChart.setData(data);
        barChart.invalidate();
    }
}
