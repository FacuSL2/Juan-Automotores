package com.example.crud_php;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class fichauno extends AppCompatActivity {

    TextView receptor, datosdevehiculo;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichauno);

        FloatingActionButton irafichados = findViewById(R.id.irafichados);
        irafichados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),fichados.class)
                        .putExtra("position",position));
            }

        });
   
        // en este TextView quiero recibir el texto
        receptor = (TextView) findViewById(R.id.datosfichauno);
        datosdevehiculo = (TextView) findViewById(R.id.datosdevehiculo);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        receptor.setText(MainActivity.employeeArrayList.get(position).getDireccion());
        datosdevehiculo.setText(MainActivity.employeeArrayList.get(position).getNombre());



        /* / /Recepcion de datos.
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            String dato = parametros.getString("dato");
            receptor.setText(dato);
        }*/


    }
}