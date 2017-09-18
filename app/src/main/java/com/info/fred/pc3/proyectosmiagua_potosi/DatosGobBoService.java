package com.info.fred.pc3.proyectosmiagua_potosi;

import com.info.fred.pc3.proyectosmiagua_potosi.model.DatosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pc3 on 14/09/2017.
 */

public interface DatosGobBoService {

    //            lo q falta de la url                                       método                                                lo recibes como string y le das nomb
    @GET("action/datastore_search")
    Call<DatosResponse> establecimientosEducativos(@Query("resource_id") String resourceId, @Query("limit") int limit);

    @GET("action/datastore_search")
    Call<DatosResponse> establecimientosEducativos(@Query("resource_id") String resourceId, @Query("q") String query);

}
