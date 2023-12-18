package ar.edu.utn.frba.dds.modelo.entidades.georefapi.services;
import ar.edu.utn.frba.dds.modelo.entidades.georefapi.entities.ListadoDeLocalidades;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoRefService {

    //cada metodo de la interfaz representa una posible llamada a la api
    //esta debe tener una annotation que especifique el tipo de solicitud y la dir relativa
    //T es la clase molde
    @GET("localidades") //1.mete lo de aca
    Call<ListadoDeLocalidades> localidades(); //2.aca (matchea)

    @GET("localidades")
    Call<ListadoDeLocalidades> localidades(@Query("campos")String campos); //TODO ver que queryParam puedo necesitar

}
