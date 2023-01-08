package com.pe.charger;

import android.app.Application;

public class App extends Application {
    private static Current current =new Current();

    public static Current getCurrent() {
        return current;
    }

    public static void setCurrent(Current current) {
        App.current = current;
      }
}
