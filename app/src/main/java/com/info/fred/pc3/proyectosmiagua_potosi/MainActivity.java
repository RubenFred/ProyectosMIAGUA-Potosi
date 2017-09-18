package com.info.fred.pc3.proyectosmiagua_potosi;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.info.fred.pc3.proyectosmiagua_potosi.adapters.UnidadesProyectosAdapter;
import com.info.fred.pc3.proyectosmiagua_potosi.model.DatosResponse;
import com.info.fred.pc3.proyectosmiagua_potosi.model.UnidadProyecto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements UnidadesProyectosAdapter.OnUnidadProyectoSelectedListener {

    private RecyclerView unidadesProyectosRecyclerview;
    private UnidadesProyectosAdapter unidadesProyectosAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    // para obtener resultados
    String provincias_nomb[] = new String[30];
    int provincias_nro=0;
    int provincias_nro_proy[] = new int[30];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // layout activityMain
        unidadesProyectosRecyclerview = (RecyclerView) findViewById(R.id.unidadesProyectosRecyclerView);
        unidadesProyectosRecyclerview.setHasFixedSize(true);
        unidadesProyectosRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        unidadesProyectosAdapter = new UnidadesProyectosAdapter(this, this);

        unidadesProyectosRecyclerview.setAdapter(unidadesProyectosAdapter);

        swipeRefreshLayout =(SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        //swipeRefreshLayout.setProgressBackgroundColorSchemeColor(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                cargarDatos();
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        swipeRefreshLayout.setRefreshing(true);
        // array 1
       // nro_tipoProyecto[0]=0;
       // nro_tipoProyecto[1]=0;

        cargarDatos();
    }

    private void cargarDatos() {

        // obtiene servicio
        DatosGobBoService service = ServiceGenerator.createService(DatosGobBoService.class);

        //  consulta query   para proy-Agua
        Call<DatosResponse> call = service.establecimientosEducativos("cfaac208-f853-4607-907b-249887235c37", "POTOSI");

        call.enqueue(new Callback<DatosResponse>() {
            @Override
            public void onResponse(Call<DatosResponse> call, Response<DatosResponse> response) {
                // datos ontenidos con exito
                swipeRefreshLayout.setRefreshing(false);

                if(response.isSuccessful()) {
                    List<UnidadProyecto> lista = response.body().getResult().getRecords();
                    unidadesProyectosAdapter.setDataset(lista);

                    // obt nomb de provincias
                    String v_aux[] = new String[lista.size()];
                    String nom_prov[] = new String[lista.size()];
                    int cont,j=0,z=0;

                    for (int i=0;i<lista.size();i++) {
                        cont=0;
                        //num=v_original[i];
                        String prov = lista.get(i).getMunicipio()+"";
                        Log.i("MiApp","prov: "+prov);

                        v_aux[j]=prov;
                        j++;
                        Log.i("MiApp","j: "+j);

                        for (int k=0;k<lista.size();k++) {
                            if(prov.equals(v_aux[k])) {  //v_aux[k] == prov
                                cont++;
                            }
                        }

                        if (cont == 1) {
                            nom_prov[z] = prov;
                            z++;
                        }
                    }

                    Log.i("MiApp","*****************");
                    // Nombre de provincias
                    for (int i=0;i<z;i++) {
                        //printf ("%d \n",v_final[i]);
                        Log.i("MiApp", nom_prov[i]);
                        provincias_nomb[i]=nom_prov[i];
                    }
                    Log.i("MiApp","************");

                    // obtiene nro de Proyect x Provincia
                    provincias_nro=z;
                    int nro_proy[] = new int[z];
                    int a=0;

                    for (int i=0;i<z;i++) {
                        cont = 0;

                        for(j=0;j<lista.size();j++){
                            String prov = lista.get(j).getMunicipio();

                            if(prov.equals(nom_prov[i])){
                                cont++;
                            }

                        }
                        nro_proy[a]=cont;
                        a++;
                    }

                    for (int i=0;i<z;i++) {
                        Log.i("MiApp", nro_proy[i]+"");
                        provincias_nro_proy[i]=nro_proy[i];
                    }


                }else{

                }
            }

            @Override
            public void onFailure(Call<DatosResponse> call, Throwable t) {
                // si hay un error en la obtencion de datos
                swipeRefreshLayout.setRefreshing(false);
            }
        });



    }


    @Override
    public void onUnidadProyectoSelected(UnidadProyecto proyecto) {  //departamento

       // Toast.makeText(MainActivity.this,"cambiando de pantalla: ", Toast.LENGTH_SHORT).show();
        String nombre_proy = proyecto.getNombreproy();
        String tipo_proy = proyecto.getTipoproy();
        String monto_fps = proyecto.getMontofps();
        String monto_contraparte = proyecto.getMontocontraparte();

        Intent intent = new Intent(this, Resultado.class);
        intent.putExtra("nomb_proy",nombre_proy);
        intent.putExtra("t_proy",tipo_proy);
        intent.putExtra("m_fps",monto_fps);
        intent.putExtra("m_contra",monto_contraparte);
        startActivity(intent);

    }


    public void resumenGeneral(View vista){
        //Toast.makeText(MainActivity.this, "Pantalla de RESUMEN ", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, Resumen.class);
        intent.putExtra("index", provincias_nro);
        intent.putExtra("nomb_provincia",provincias_nomb);
        intent.putExtra("nro_proy_provincia",provincias_nro_proy);
        startActivity(intent);


    }
}
