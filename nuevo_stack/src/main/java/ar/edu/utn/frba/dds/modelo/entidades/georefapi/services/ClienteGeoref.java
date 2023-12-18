package ar.edu.utn.frba.dds.modelo.entidades.georefapi.services;

import ar.edu.utn.frba.dds.modelo.entidades.georefapi.entities.ListadoDeLocalidades;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import java.io.IOException;

public class ClienteGeoref {
    private static ClienteGeoref instance = null;
    private static String urlAPI = "https://apis.datos.gob.ar/georef/api/";
    private Retrofit retrofit;

    private GeoRefService geoRefService;

    private ClienteGeoref() {
        this.retrofit = new Retrofit.Builder().
            baseUrl(urlAPI).
            addConverterFactory(GsonConverterFactory.create()).
            build();
    }

    public static ClienteGeoref getInstance() {
        if (instance == null) {
            instance = new ClienteGeoref();
        }
        return instance;
    }

    public String getUrlAPI() {
        return urlAPI;
    }

    public ListadoDeLocalidades listadoDeLocalidades() throws IOException {
        GeoRefService geoRefService = this.retrofit.create(GeoRefService.class);
        Call<ListadoDeLocalidades> requestLocalidadesArg = geoRefService.localidades();
        Response<ListadoDeLocalidades> responseListadoDeLocalidades = requestLocalidadesArg.execute();
        return responseListadoDeLocalidades.body();
    }
}