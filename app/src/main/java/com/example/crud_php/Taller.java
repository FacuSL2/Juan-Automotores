package com.example.crud_php;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class Taller extends AppCompatActivity {
    Button agregarficha;


    TextView tvidt,tvnamet,tvemailt,tvcontactt;

    ImageView imagentaller;
    int position;
    @SuppressLint("SetTextI18n")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taller);



        //Initializing Views
        tvidt = findViewById(R.id.txtidt);
        tvnamet = findViewById(R.id.txtnamet);
        tvemailt = findViewById(R.id.txtemailt);
        tvcontactt = findViewById(R.id.txcontactt);
        imagentaller = findViewById(R.id.imagentaller);



        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        tvidt.setText("ID: " + MainActivity.employeeArrayList.get(position).getId());
        tvnamet.setText("Modelo: " + MainActivity.employeeArrayList.get(position).getModelo());
        tvemailt.setText("Patente: " + MainActivity.employeeArrayList.get(position).getPatente());
        tvcontactt.setText("Color: " + MainActivity.employeeArrayList.get(position).getDireccion());

       /* String dato = tvcontactt.getText().toString();  //Obtienes el texto del EditText
        Intent c1 = new Intent(Taller.this, fichauno.class);
        c1.putExtra("dato", dato);
        startActivity(c1); */


        agregarficha = findViewById(R.id.agregarficha);
        agregarficha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),fichauno.class)
                        .putExtra("position",position));

            }
        });

        // Cargar la imagen en el ImageView desde una URL almacenada en la base de datos
        String imageUrl = MainActivity.employeeArrayList.get(position).getImagen();
        Glide.with(this).load(imageUrl).into(imagentaller);
    }



    private void fichauno() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_fichauno);

        Button irafichados = dialog.findViewById(R.id.irafichados);
        irafichados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(Taller.this,"Conectando con Ficha 2",Toast.LENGTH_SHORT).show();
                fichados();
            }
        });




        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

    private void fichados() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layoutfichados);

        Button irafichatres = dialog.findViewById(R.id.irafichatres);
        irafichatres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(Taller.this,"Conectando con Ficha 3",Toast.LENGTH_SHORT).show();
                fichauno();
            }
        });


        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }


}