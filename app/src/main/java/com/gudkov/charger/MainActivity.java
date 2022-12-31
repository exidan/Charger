package com.gudkov.charger;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, Controller.Listner {
    private ImageButton settingsButton;
    private TextView currTextView, currSeekText,currMaxSeekBar;
    private Controller controller;
    private SeekBar currentSeekBar;
    private SharedPreferences settings;

    ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
        //           Integer currLim = Integer.valueOf(result.getData().getIntExtra("currLim",32));
                    Integer currLim = Integer.valueOf(settings.getInt("currentLim",32));
                    currentSeekBar.setMax(currLim);

             //       currMaxSeekBar.setText(String.valueOf(settings.getInt("currentLim",32))+"A");
                    currMaxSeekBar.setText(currLim.toString()+"A");
                    Log.i("TAG_", "onActivityResult: " +currentSeekBar.getMax());

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

        settings = getSharedPreferences("charger_sets",MODE_PRIVATE);

        settingsButton =(ImageButton) findViewById(R.id.settings_butt);
        settingsButton.setOnClickListener(this);

        currTextView = (TextView) findViewById(R.id.current_txt);

        currSeekText = (TextView) findViewById(R.id.currentSeekText);
        currMaxSeekBar = (TextView) findViewById(R.id.currMaxSeekBar);
        currMaxSeekBar.setText(String.valueOf(settings.getInt("currentLim",32))+"A");

        controller = new Controller(this::SendCurrent); //Запуск запросов по таймеры
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(controller, 0, 5*1000);

        currentSeekBar = (SeekBar) findViewById(R.id.currentSeekBar);
        currentSeekBar.setMin(0);
        currentSeekBar.setMax(settings.getInt("currentLim",32));
        currentSeekBar.setOnSeekBarChangeListener(this);
        currSeekText.setText(String.valueOf(currentSeekBar.getProgress())+" A");

    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.settings_butt:{
                Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
           //     startActivity(intent);
                activityResultLaunch.launch(intent);
                break;
            }

        }

    }


    @Override
    public void SendCurrent(Current current) {
        currTextView.setText(current.getCur().toString()+ " A");
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        currSeekText.setText(String.valueOf(progress) +" A");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


}