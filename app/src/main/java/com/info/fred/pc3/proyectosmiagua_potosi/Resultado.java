package com.info.fred.pc3.proyectosmiagua_potosi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * Created by pc3 on 15/09/2017.
 */

public class Resultado extends AppCompatActivity {

    TextView nombreProyTextView;;
    TextView fpsTextView;
    TextView contraparteTextView;
    TextView totalTextview;

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        nombreProyTextView =(TextView)findViewById(R.id.nombreProyTextView);
        fpsTextView = (TextView)findViewById(R.id.res_fpsTextview);
        contraparteTextView = (TextView)findViewById(R.id.res_contraparteTextview);
        totalTextview = (TextView)findViewById(R.id.res_totalTextview);

        imageView = (ImageView)findViewById(R.id.imagenTipoProy);
        String imagen_agua[] = new String[5];
        String imagen_riego[] = new String[5];

        String nombre_proy = getIntent().getExtras().getString("nomb_proy");
        String tipo_proy = getIntent().getExtras().getString("t_proy");
        double fps = Double.parseDouble(getIntent().getExtras().getString("m_fps"));
        double contraparte = Double.parseDouble(getIntent().getExtras().getString("m_contra"));
        double monto_total =fps+contraparte;

        float monto_fps = Float.parseFloat(getIntent().getExtras().getString("m_fps"));
        float monto_contraparte = Float.parseFloat(getIntent().getExtras().getString("m_contra"));

        nombreProyTextView.setText(nombre_proy);
        fpsTextView.setText(""+fps);
        contraparteTextView.setText(""+contraparte);
        totalTextview.setText(""+monto_total);

        imagen_agua[0]="http://3.bp.blogspot.com/-3XI67FvyfCU/Th2cKlSdrdI/AAAAAAAAALA/54uLpbOi2pk/s1600/cosecha-agua.jpg";
        imagen_agua[1]="http://coquimbo.mop.cl/noticias/PublishingImages/apr-ovalle-punilla.jpg";
        imagen_agua[2]="http://www.energetica.org.bo/energetica/bbdd/v4_n02_archivos/image004.jpg";
        imagen_agua[3]="http://elpotosi.net/img/notas/20170622/nota38754_imagen0_x5.jpg";
        imagen_agua[4]="https://3.bp.blogspot.com/-qHCI7Eh6yB8/WCCDF4Hk-GI/AAAAAAACX-M/CviVj3Mb8vcWJL3ar4b509vTvabIrdu5QCLcB/s1600/Cuatro%2Bmunicipios%2Bde%2BLa%2BPaz%2Bsufren%2Bescasez.jpg";

        imagen_riego[0]="http://codexverde.cl/wp-content/uploads/2017/02/Riego-CNR.jpg";
        imagen_riego[1]="http://correodelsur.com/img/notas/20151125/nota20537_imagen0_x5.jpg";
        imagen_riego[2]="http://servicesaws.iadb.org/wmsfiles/images/0x400/-9898.jpg";
        imagen_riego[3]="http://www.notiboliviarural.com/images/stories/2014/enero/13/riego-agua.jpg";
        imagen_riego[4]="http://agraria.pe/uploads/images/2015/08/riego-tec.jpg";

        int numero_aleatorio = (int) (Math.random() * 4);  // gen aleatorio

        //Toast.makeText(Resultado.this, "Nro aleatorio: "+tipo_proy, Toast.LENGTH_SHORT).show();
        // asignando imagen de proyecto
        if(tipo_proy.equals("AGUA")) {
            Glide.with(this)
                    .load(imagen_agua[numero_aleatorio])
                    .into(imageView);
        }else{
            Glide.with(this)
                    .load(imagen_riego[numero_aleatorio])
                    .into(imageView);
        }


        // graficando la torta
        PieChart inversionGrafica = (PieChart) findViewById(R.id.piechart);
        inversionGrafica.setUsePercentValues(true);

        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(monto_fps,0));
        yvalues.add(new Entry(monto_contraparte,1));
        PieDataSet dataSet = new PieDataSet(yvalues, "");

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("Monto fps");
        xVals.add("Contraparte");
        PieData data = new PieData(xVals,dataSet);

        data.setValueFormatter(new PercentFormatter());

        inversionGrafica.setData(data);

        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        inversionGrafica.setDescription("Inversion del Proyecto en (Bs)");

        inversionGrafica.setDrawHoleEnabled(false);

        data.setValueTextSize(12f);

    }

}
