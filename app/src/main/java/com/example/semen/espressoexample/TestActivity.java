package com.example.semen.espressoexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;

public class TestActivity extends AppCompatActivity {

    byte[] bytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        bytes = new byte[100*100*10];
        Arrays.fill(bytes, (byte) 1);
    }
}