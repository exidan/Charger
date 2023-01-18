package com.pe.charger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pe.charger.enums.SettingsEnum;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestFasade {

    private static RestFasade instanse;

    private void RestFasade() {

    }

    public RestFasade getInstanse(){
        if (instanse!=null){
            return instanse;

        } else {
           return instanse = new RestFasade();
        }
    }

    public void setCurrent(Integer progress){
        final String BASE_URL = "http://"+(String) App.getSetting(SettingsEnum.IP);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RestApi restApi = retrofit.create(RestApi.class);

        Call<Current> call = restApi.setCur(new Current(progress));
        call.enqueue(new Callback<Current>() {
            @Override
            public void onResponse(Call<Current> call, Response<Current> response) {

            }

            @Override
            public void onFailure(Call<Current> call, Throwable t) {

            }
        });
    }

}
