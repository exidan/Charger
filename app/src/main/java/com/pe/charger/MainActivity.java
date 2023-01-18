package com.pe.charger;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pe.charger.enums.SettingsEnum;

import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener,
        Controller.Listner, NavigationBarView.OnItemSelectedListener {
    private ImageButton settingsButton;
    private TextView currTextView, currSeekText,currMaxSeekBar;
    private Controller controller;
    private SeekBar currentSeekBar;
    private SharedPreferences settings;
    private BottomNavigationView bottomNavigationView;

    ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

//                    Integer currLim = Integer.valueOf(settings.getInt("currentLim",32));
//                    currentSeekBar.setMax(currLim);
//
//                       currMaxSeekBar.setText(currLim.toString()+"A");
//                    Log.i("TAG_", "onActivityResult: " +currentSeekBar.getMax());

                }
            });


    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fraime_container, new MainFragment());
        fragmentTransaction.commit();
        //TODO подумать как выключать таймер и в какой момент

//        controller = new Controller(this::SendCurrent); //Запуск запросов по таймеры
//        Timer timer = new Timer(true);
//        timer.scheduleAtFixedRate(controller, 0, 2*1000);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
//            case R.id.settings_butt:{
//                Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
//           //     startActivity(intent);
//                activityResultLaunch.launch(intent);
//                break;
//            }
        }
    }


    @Override
    public void SendCurrent(Current current) {
   //     currTextView.setText(current.getCur().toString()+ " A");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        currSeekText.setText(String.valueOf(progress) +" A");
//        final String BASE_URL = "http://"+(String) App.getSetting(SettingsEnum.IP);
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//
//        RestApi restApi = retrofit.create(RestApi.class);
//
//        Call<Current> call = restApi.setCur(new Current(progress));
//        call.enqueue(new Callback<Current>() {
//            @Override
//            public void onResponse(Call<Current> call, Response<Current> response) {
//                App.setCurrent(response.body());
//                SendCurrent(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<Current> call, Throwable t) {
//
//            }
//        });


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (item.getItemId()){
            case  R.id.bottom_menu_settings:{

                fragmentTransaction.replace(R.id.fraime_container, new SettingsFragment());

                 break;
            }
            case  R.id.bottom_menu_home:{
                fragmentTransaction.replace(R.id.fraime_container,new MainFragment() );
                 break;
            }
        }
        fragmentTransaction.commit();

        item.setChecked(true);

        return false;
    }
}