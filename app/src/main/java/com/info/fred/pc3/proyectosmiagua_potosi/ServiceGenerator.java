package com.info.fred.pc3.proyectosmiagua_potosi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pc3 on 14/09/2017.
 */

public class ServiceGenerator {

    private static final String BASE_URL = "https://datos.gob.bo/api/";

    //   convierte a objetos java
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();  // crea una var Retrofit

    //  para querys = servicios
    //  met-generico
    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);

    }
}
