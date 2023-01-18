package com.pe.charger;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.pe.charger.enums.SettingsEnum;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SharedPreferences settings;
    private EditText ipEditText, currentLimEditText;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        // Inflate the layout for this fragment
      View  v = inflater.inflate(R.layout.fragment_settings, container, false);
      //  settings = getSharedPreferences("charger_sets",MODE_PRIVATE);

        ipEditText =(EditText) v.findViewById(R.id.ip_edit);
        ipEditText.setText((String)App.getSetting(SettingsEnum.IP));

        currentLimEditText = (EditText) v.findViewById(R.id.currentLimSetting);

        currentLimEditText.setText(String.valueOf((Integer)App.getSetting(SettingsEnum.CURR_LIM)));

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        App.putSetting(SettingsEnum.IP,ipEditText.getText().toString() );
        //В int нормально преобразуется так как поле ввда только число
       App.putSetting(SettingsEnum.CURR_LIM,Integer.valueOf(currentLimEditText.getText().toString()));

    }
}