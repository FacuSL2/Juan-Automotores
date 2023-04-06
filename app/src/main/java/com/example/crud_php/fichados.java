package com.example.crud_php;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class fichados extends AppCompatActivity {

    TextView receptordos;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichados);

        // en este TextView quiero recibir el texto
        receptordos = (TextView) findViewById(R.id.datosfichados);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        receptordos.setText(MainActivity.employeeArrayList.get(position).getId());

    }
}