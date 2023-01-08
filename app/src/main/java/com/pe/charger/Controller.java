package com.pe.charger;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller extends TimerTask implements Callback<Current> {

    static final String BASE_URL = "https://13bdfc1c-1d65-49ef-bd16-70546c8d28c1.mock.pstmn.io";

    Listner listner;

    public Controller(Listner listner) {
        this.listner = listner;
    }

    public void start() {
      //  this.listner = listner;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RestApi gerritAPI = retrofit.create(RestApi.class);

        Call<Current> call = gerritAPI.loadRepo();
        call.enqueue(this);

    }


    @Override
    public void onResponse(Call<Current> call, Response<Current> response) {
        try {
            Log.i("TAG_", "onResponse: "+response.body().toString());

            App.setCurrent(response.body());
            listner.SendCurrent(response.body());
            Log.i("TAG_", "onResponse: ");
        }catch (NullPointerException e){

            listner.SendCurrent(new Current());

        }


    }

    @Override
    public void onFailure(Call<Current> call, Throwable t) {
        Log.i("TAG_", "onResponse: FAIL");
    }

    @Override
    public void run() {
        start();
    }

    interface Listner{
        void SendCurrent(Current current);
    }

}
