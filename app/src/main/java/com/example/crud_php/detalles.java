package com.example.crud_php;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class detalles extends AppCompatActivity {

    TextView tvid,tvname,txmodelo,txpatente,tvemail,tvcontact,txprecioinfoautos,txporcinfoautos,txcosto, txvalorlista;
    ImageView imagen;
    int position;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);


        //Initializing Views
        tvid = findViewById(R.id.txtid);
        tvname = findViewById(R.id.txtname);
        txmodelo = findViewById(R.id.txmodelo);
        txpatente = findViewById(R.id.txpatente);
        tvemail = findViewById(R.id.txtemail);
        tvcontact = findViewById(R.id.txcontact);
        txprecioinfoautos = findViewById(R.id.txprecioinfoautos);
        txporcinfoautos = findViewById(R.id.txporcinfoautos);
        txcosto = findViewById(R.id.txcosto);
        txvalorlista = findViewById(R.id.txvalorlista);

        imagen = findViewById(R.id.imagen);


        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText(MainActivity.employeeArrayList.get(position).getId());
        tvname.setText(MainActivity.employeeArrayList.get(position).getNombre());
        txmodelo.setText(MainActivity.employeeArrayList.get(position).getModelo());
        txpatente.setText(MainActivity.employeeArrayList.get(position).getPatente());
        tvemail.setText(MainActivity.employeeArrayList.get(position).getCorreo());
        tvcontact.setText(MainActivity.employeeArrayList.get(position).getDireccion());
        txprecioinfoautos.setText(MainActivity.employeeArrayList.get(position).getPrecioinfoautos());
        txporcinfoautos.setText(MainActivity.employeeArrayList.get(position).getPorcinfoautos());
        txcosto.setText(MainActivity.employeeArrayList.get(position).getCosto());
        txvalorlista.setText(MainActivity.employeeArrayList.get(position).getValorlista());



        // Cargar la imagen en el ImageView desde una URL almacenada en la base de datos
        String imageUrl = MainActivity.employeeArrayList.get(position).getImagen();
        Glide.with(this).load(imageUrl).into(imagen);

    }


}