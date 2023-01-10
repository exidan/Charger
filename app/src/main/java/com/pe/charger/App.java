package com.pe.charger;

import android.app.Application;
import android.content.SharedPreferences;

import com.pe.charger.enums.SettingsEnum;

public class App extends Application {
    private static Current current =new Current();
    public static SharedPreferences settings;

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

      public static Object getSetting(SettingsEnum setting){
          switch (setting) {

              case IP: {
                  return settings.getString("ip","192.168.0.1") ;
              }

              case CURR_LIM:{
                  return settings.getInt("currentLim",32);
              }
              case CURR_LAST:{
                  return settings.getInt("currentLast",0);
              }
          } ;
          return null;
      }

}
