package com.pe.charger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {
    private SharedPreferences settings;
    private EditText ipEditText, currentLimEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings = getSharedPreferences("charger_sets",MODE_PRIVATE);

        ipEditText =(EditText) findViewById(R.id.ip_edit);
        ipEditText.setText(settings.getString("ip","192.168.0.1"));

        currentLimEditText = (EditText) findViewById(R.id.currentLimSetting);

        currentLimEditText.setText(String.valueOf(settings.getInt("currentLim",32)));

    }
//TODO Сделать проверку на максимальный ток 32А и что число
// TODO Сделать проверку или маску IP


    @Override
    protected void onPause() {
        super.onPause();
        settings.edit().putString("ip",ipEditText.getText().toString()).commit();
        settings.edit().putInt("currentLim",Integer.valueOf(currentLimEditText.getText().toString())).commit();
      //  Intent intent = new Intent();
      //  intent.putExtra("currLim",Integer.valueOf(currentLimEditText.getText().toString()));
       // setResult(RESULT_OK, intent);
    //   finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}