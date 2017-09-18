package com.info.fred.pc3.proyectosmiagua_potosi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by pc3 on 16/09/2017.
 */

public class Resumen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        int index=getIntent().getExtras().getInt("index");

        String provincias_nomb[] = new String[index];
        provincias_nomb=getIntent().getExtras().getStringArray("nomb_provincia");

        int provincias_nro_proy[] = new int[index];
        provincias_nro_proy=getIntent().getExtras().getIntArray("nro_proy_provincia");

        for (int i=0;i<index;i++) {
            Log.i("Resumen", provincias_nomb[i]);
            Log.i("Resumen", provincias_nro_proy[i]+"");
        }

        // grafico de barras
        // horizontal bar
        HorizontalBarChart barChart = (HorizontalBarChart) findViewById(R.id.barchart);

        ArrayList<BarEntry> entries = new ArrayList<>();

        for(int i=0;i<index;i++){
            float cantidad =  (float)provincias_nro_proy[i];
            entries.add(new BarEntry(cantidad, i));
        }

        BarDataSet bardataset = new BarDataSet(entries, "Nro de Proyectos X Municipio - Depto POTOSI");

        ArrayList<String> labels = new ArrayList<String>();

        for(int i=0;i<index;i++){
            labels.add(provincias_nomb[i]);
        }

        BarData data = new BarData(labels, bardataset);
        barChart.setData(data); // set the data and list of labels into chart

        barChart.setDescription(" ");  // set the description

          // color de las barras
        //bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        //bardataset.setColors(ColorTemplate.VORDIPLOM_COLORS);
        bardataset.setColors(ColorTemplate.PASTEL_COLORS);

        // animacion hacer crecer las barras
        barChart.animateY(5000);

        data.setValueTextSize(12f);
        data.setValueTextColor(Color.RED);

    }
}
