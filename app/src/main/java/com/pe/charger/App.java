package com.pe.charger;

import android.app.Application;
import android.content.SharedPreferences;

import com.pe.charger.enums.SettingsEnum;

public class App extends Application {

    private static Current current =new Current(); //Текущий ток
    private static Boolean stateOn = false; //Состояние станции вкл true
    private static SharedPreferences settings;


    public static Current getCurrent() {
        return current;
    }

    public static void setCurrent(Current current) {
        App.current = current;
      }


    @Override
    public void onCreate() {
        super.onCreate();
        settings =  getSharedPreferences("charger_sets",MODE_PRIVATE);
    }


    public static Boolean getStateOn() {
        return stateOn;
    }

    public static void setStateOn(Boolean stateOn) {
        App.stateOn = stateOn;
    }

    //Функции работы с SharedPreferences
      public static Object getSetting(SettingsEnum setting){
        switch (setting) {

            case IP: {
                return settings.getString("ip","192.168.0.1") ;
            }

            case CURR_LIM:{
                return settings.getInt("currentLim",32);
            }
            case CURR_CHOSEN:{
                return settings.getInt("currentLast",0);
            }
        } ;
        return null;
    }

    public static String getSettingString(SettingsEnum setting){
        switch (setting) {

            case IP: {
                return settings.getString("ip","192.168.0.1") ;
            }

        } ;
        return "";
    }
    public static Integer getSettingInt(SettingsEnum setting){
        switch (setting) {

            case CURR_LIM:{
                return settings.getInt("currentLim",32);
            }
            case CURR_CHOSEN:{
                return settings.getInt("currentChosen",6);
            }
        } ;
        return 0;
    }
    public static void putSetting(SettingsEnum setting,Object value){
        switch (setting) {
//TODO Сделать проверку что нормально откастовалось может быть даже свой эксепшен сделать
            case IP: {

                settings.edit().putString("ip",(String)value).commit();
                break;
            }

            case CURR_LIM:{
                settings.edit().putInt("currentLim",(Integer)value).commit();
                break;
            }
            case CURR_CHOSEN:{
                settings.edit().putInt("currentChosen",(Integer)value).commit();
                break;

            }
        } ;

    }
}
