package com.example.semen.espressoexample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.semen.espressoexample.byt.A;
import com.example.semen.espressoexample.byt.B;
import com.example.semen.espressoexample.byt.C;
import com.example.semen.espressoexample.byt.D;
import com.example.semen.espressoexample.byt.E;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataAdapter.ItemClickListener {
    TextView textView;
    Button button, idleButton, startActivity, createMyObjects, clearMyObjects;
    EditText editText;
    RecyclerView recyclerView;
    List<Phone> phones = new ArrayList<>();
    List<MyObject> myObjects = new ArrayList<>();

    A a;
    C c;

    TextView selection;
    ListView listView;

    public static final int REQUEST_CODE = 1;


    CustomIdlingResource customIdlingResource = new CustomIdlingResource();


    CountingIdlingResource countingIdlingResource = new CountingIdlingResource("Name");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialData();

        textView = findViewById(R.id.tvResult);
        editText = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.recyclerView);

        DataAdapter dataAdapter = new DataAdapter(this, phones);
        dataAdapter.setItemClickListener(this);
        recyclerView.setAdapter(dataAdapter);

        button = findViewById(R.id.button);
        idleButton = findViewById(R.id.idleButton);
        startActivity = findViewById(R.id.startActivity);
        createMyObjects = findViewById(R.id.createMyObjects);
        clearMyObjects = findViewById(R.id.clearMyObjects);

        initArrayListMyObject();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                if (TextUtils.isEmpty(text)) {
                    text = "Default";
                }
                textView.setText(text);
            }
        });

        idleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customIdlingResource.setmIsIdleNow(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        selection.setText("Idle change");
                        customIdlingResource.setmIsIdleNow(true);
                    }
                }, 5000);
            }
        });

        startActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick();
            }
        });
        createMyObjects.setOnClickListener((view) -> initArrayListMyObject());
        clearMyObjects.setOnClickListener((view) -> clearArrayListMyObject());


        selection = findViewById(R.id.tvResult);
        listView = findViewById(R.id.listView);

        //createCountriesListView();
        createItemListView();

        create();
    }

    private void create() {
        a = new A();
        a.b = new B();
        a.b.e = new E();

        c = new C();
        c.d = new D();
        c.d.e = new E();
    }

    private void initArrayListMyObject() {
        for (int i = 0; i <100 ; i++) {
            myObjects.add(new MyObject());
        }
    }

    private void clearArrayListMyObject() {
        myObjects.clear();
    }

    private void buttonClick() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(Constants.EXTRA_ID, "Str");
        intent.putExtra(Constants.EXTRA_NAME, "Name 1");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                textView.setText(data.getStringExtra(Constants.EXTRA_RESULT));
            } else {
                textView.setText("Cancel");
            }
        }
    }

    private void createItemListView() {
        final Item[] items = {
                new Item(),
                new Item(),
                new Item(),
                new Item(),
                new Item(),
                new Item(),
                new Item(),
        };

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        // устанавливаем для списка адаптер
        listView.setAdapter(adapter);
        // добавляем для списка слушатель
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // по позиции получаем выбранный элемент
                Item selectedItem = items[position];
                // установка текста элемента TextView
                selection.setText(selectedItem.getName());
            }
        });
    }

    private void setInitialData() {

        phones.add(new Phone("Huawei P10", "Huawei"));
        phones.add(new Phone("Elite z3", "HP"));
        phones.add(new Phone("Galaxy S8", "Samsung"));
        phones.add(new Phone("LG G 5", "LG"));
    }

    private void createCountriesListView() {
        final String[] countries = {
                "Бразилия",
                "Аргентина",
                "Колумбия",
                "Чили",
                "Уругвай"};

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, countries);
        // устанавливаем для списка адаптер
        listView.setAdapter(adapter);
        // добавляем для списка слушатель
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // по позиции получаем выбранный элемент
                String selectedItem = countries[position];
                // установка текста элемента TextView
                selection.setText(selectedItem);
            }
        });
    }

    @Override
    public void onClick(String name) {
        selection.setText(name);
    }

    public CountingIdlingResource getCountingIdlingResource() {
        return countingIdlingResource;
    }

    public CustomIdlingResource getCustomIdlingResource() {
        return customIdlingResource;
    }
}
