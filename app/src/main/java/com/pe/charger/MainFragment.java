package com.pe.charger;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener,
        Controller.Listner, NavigationBarView.OnItemSelectedListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ImageButton settingsButton;
    private TextView currTextView, currSeekText,currMaxSeekBar;
    private Controller controller;
    private SeekBar currentSeekBar;
    private SharedPreferences settings;

    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
     //   settings = v.getSharedPreferences("charger_sets",getActivity().MODE_PRIVATE);

//        settingsButton =(ImageButton) findViewById(R.id.settings_butt);
//        settingsButton.setOnClickListener(this);

        currTextView = (TextView) getActivity().findViewById(R.id.current_txt);

        currSeekText = (TextView)v.findViewById(R.id.currentSeekText);
        currMaxSeekBar = (TextView) v.findViewById(R.id.currMaxSeekBar);
        currMaxSeekBar.setText(String.valueOf((Integer)App.getSetting(SettingsEnum.CURR_LIM)+"A"));

        controller = new Controller(this::SendCurrent); //Запуск запросов по таймеры
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(controller, 0, 2*1000);

        currentSeekBar = (SeekBar) v.findViewById(R.id.currentSeekBar);
        currentSeekBar.setMin(0);
        currentSeekBar.setMax((Integer)App.getSetting(SettingsEnum.CURR_LIM));
        currentSeekBar.setOnSeekBarChangeListener(this);
        currSeekText.setText(String.valueOf(currentSeekBar.getProgress())+" A");

        return v;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        currSeekText.setText(String.valueOf(progress) +" A");
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
                App.setCurrent(response.body());
                SendCurrent(response.body());
            }

            @Override
            public void onFailure(Call<Current> call, Throwable t) {

            }
        });
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void SendCurrent(Current current) {
        currTextView.setText(current.getCur().toString()+ " A");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


}