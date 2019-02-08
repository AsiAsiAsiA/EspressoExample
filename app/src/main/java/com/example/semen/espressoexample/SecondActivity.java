package com.example.semen.espressoexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.semen.espressoexample.byt.A;

import java.util.Arrays;

public class SecondActivity extends AppCompatActivity {

    TextView textView;
    byte[] bytes;
    A data;

    Repository.Listener listener = new Repository.Listener() {
        @Override
        public void onDataChanged(String newData) {
            data = new A();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.tvResult);


        textView.setText(
                getIntent().getStringExtra(Constants.EXTRA_ID)
                        + ", "
                        + getIntent().getStringExtra(Constants.EXTRA_NAME));

        findViewById(R.id.startActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick();
            }
        });

        bytes = new byte[1000*1000*10];
        Arrays.fill(bytes, (byte) 1);

        ((App)getApplicationContext()).getRepository().registerListener(listener);
    }

    private void buttonClick() {
        Intent intent = new Intent();
        intent.putExtra(Constants.EXTRA_RESULT, "Result");
        setResult(RESULT_OK, intent);
        finish();
    }
}