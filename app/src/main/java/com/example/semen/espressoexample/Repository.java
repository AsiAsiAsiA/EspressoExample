package com.example.semen.espressoexample;

import java.util.HashSet;
import java.util.Set;

public class Repository {

    interface Listener {
        void onDataChanged(String newData);
    }

    Set<Listener> listeners = new HashSet<>();

    public void registerListener(Listener listener) {
        listeners.add(listener);
    }
}