package com.example.semen.espressoexample;

import android.app.Application;

public class App extends Application {

    private Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        repository = new Repository();
    }

    public Repository getRepository() {
        return repository;
    }
}