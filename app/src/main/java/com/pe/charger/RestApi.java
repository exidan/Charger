package com.pe.charger;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestApi {

    @POST("/cur")
    Call<Current> getCur();

    @POST("/setCur")
    Call<Current> setCur(@Body Current current);

}
