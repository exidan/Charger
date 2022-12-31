package com.gudkov.charger;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {

    @POST("/")
    Call<Current> loadRepo();
}
