package com.gudkov.charger;

import android.app.Application;
import android.widget.TextView;

public class App extends Application {
    private static Current current =new Current();

    public static Current getCurrent() {
        return current;
    }

    public static void setCurrent(Current current) {
        App.current = current;
      }
}
