package ru.job4j.todo;

import android.app.Application;

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = DaggerContext.create();
    }

    public static Context getContext() {
        return context;
    }
}
