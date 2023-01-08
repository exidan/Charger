package com.pe.charger;

import retrofit2.Call;
import retrofit2.http.POST;

public interface RestApi {

    @POST("/")
    Call<Current> loadRepo();
}
