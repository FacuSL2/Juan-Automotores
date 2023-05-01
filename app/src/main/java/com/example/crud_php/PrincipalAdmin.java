package com.example.crud_php;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PrincipalAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_admin);

        CardView agregarvehiculo = findViewById(R.id.agregarvehiculo);
        agregarvehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), agregar.class);
                startActivity(intent);
            }
        });

        CardView listavehiculos = findViewById(R.id.listavehiculos);
        listavehiculos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        CardView agregarusuarios = findViewById(R.id.agregarusuarioss);
        agregarusuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GeneralUsuarios.class);
                startActivity(intent);
            }
        });

        CardView tallercard = findViewById(R.id.tallercard);
        tallercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivityTaller.class);
                startActivity(intent);
            }
        });


    }
}