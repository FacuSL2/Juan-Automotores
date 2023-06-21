package com.example.crud_php;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class detallesvendedor extends AppCompatActivity {


    TextView tvid,tvname,txmodelo,txpatente,tvemail,tvcontact,txprecioinfoautos,txporcinfoautos,txcosto, txvalorlista;
    ImageView imagen,boleto,cedula, titulo, ceroocho, dominio, multas, muni;
    int position;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallesvendedor);


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
        boleto = findViewById(R.id.boleto);
        cedula = findViewById(R.id.cedula);
        titulo = findViewById(R.id.titulo);
        ceroocho = findViewById(R.id.ceroocho);
        dominio = findViewById(R.id.dominio);
        multas = findViewById(R.id.multas);
        muni = findViewById(R.id.muni);


        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        tvid.setText(MainActivityVendedor.employeeArrayList.get(position).getId());
        tvname.setText(MainActivityVendedor.employeeArrayList.get(position).getNombre());
        txmodelo.setText(MainActivityVendedor.employeeArrayList.get(position).getModelo());
        txpatente.setText(MainActivityVendedor.employeeArrayList.get(position).getPatente());
        tvemail.setText(MainActivityVendedor.employeeArrayList.get(position).getCorreo());
        tvcontact.setText(MainActivityVendedor.employeeArrayList.get(position).getDireccion());
        txprecioinfoautos.setText(MainActivityVendedor.employeeArrayList.get(position).getPrecioinfoautos());
        txporcinfoautos.setText(MainActivityVendedor.employeeArrayList.get(position).getPorcinfoautos());
        txcosto.setText(MainActivityVendedor.employeeArrayList.get(position).getCosto());
        txvalorlista.setText(MainActivityVendedor.employeeArrayList.get(position).getValorlista());



        // Cargar la imagen en el ImageView desde una URL almacenada en la base de datos
        String imageUrl = MainActivityVendedor.employeeArrayList.get(position).getImagen();
        Glide.with(this).load(imageUrl).into(imagen);

        String imageUrlboleto = MainActivityVendedor.employeeArrayList.get(position).getBoleto();
        Glide.with(this).load(imageUrlboleto).into(boleto);

        String imageUrlcedula = MainActivityVendedor.employeeArrayList.get(position).getCedula();
        Glide.with(this).load(imageUrlcedula).into(cedula);

        String imageUrltitulo = MainActivityVendedor.employeeArrayList.get(position).getTitulo();
        Glide.with(this).load(imageUrltitulo).into(titulo);

        String imageUrlceroocho = MainActivityVendedor.employeeArrayList.get(position).getCeroocho();
        Glide.with(this).load(imageUrlceroocho).into(ceroocho);

        String imageUrldominio = MainActivityVendedor.employeeArrayList.get(position).getDominio();
        Glide.with(this).load(imageUrldominio).into(dominio);

        String imageUrlmultas = MainActivityVendedor.employeeArrayList.get(position).getMultas();
        Glide.with(this).load(imageUrlmultas).into(multas);

        String imageUrlmuni = MainActivityVendedor.employeeArrayList.get(position).getMuni();
        Glide.with(this).load(imageUrlmuni).into(muni);

    }

    public void onClickImage(View view) {
        ImageView image = (ImageView) view;
        ViewGroup.LayoutParams layoutParams = image.getLayoutParams();
        layoutParams.width = layoutParams.width * 2; // Duplica el ancho
        layoutParams.height = layoutParams.height * 2; // Duplica la altura
        image.setLayoutParams(layoutParams);
    }


}