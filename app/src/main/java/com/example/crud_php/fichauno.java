package com.example.crud_php;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;

public class fichauno extends AppCompatActivity {

    EditText edId, edModelo, edPatente, edComprador, edKm, edColor, edPrecioinfoautos,edCosto, edValorLista, edFichauno,edFichados,edFichatres,edFichacuatro,
            edFichacinco,edFichaseis,edFichasiete,edFichaocho,edFichanueve,edFichadiez,edPorcinfoautos;
    ImageView imagentaller;
    FloatingActionButton siguienteuno;

    TextView modelofuno, patentefuno;
    private ImageButton expandBtn;
    private int initialMaxLines;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichauno);

        edId = findViewById(R.id.id);
        edModelo = findViewById(R.id.modelo);
        edPatente = findViewById(R.id.patente);
        edPrecioinfoautos = findViewById(R.id.precioinfoautos);
        edValorLista = findViewById(R.id.valorlista);
        edComprador = findViewById(R.id.compradored);
        edPorcinfoautos = findViewById(R.id.porcinfoautosed);
        edKm = findViewById(R.id.kmed);
        edCosto = findViewById(R.id.costoed);
        edColor = findViewById(R.id.colored);
        edFichauno = findViewById(R.id.fichaunoed);
        edFichados = findViewById(R.id.edFichados);
        edFichatres = findViewById(R.id.edFichatres);
        edFichacuatro = findViewById(R.id.edFichacuatro);
        edFichacinco = findViewById(R.id.edFichacinco);
        edFichaseis = findViewById(R.id.edFichaseis);
        edFichasiete = findViewById(R.id.edFichasiete);
        edFichaocho = findViewById(R.id.edFichaocho);
        edFichanueve = findViewById(R.id.edFichanueve);
        edFichadiez = findViewById(R.id.edFichadiez);
        modelofuno = findViewById(R.id.modelofuno);
        patentefuno = findViewById(R.id.patentefuno);
        siguienteuno = findViewById(R.id.siguienteuno);
        siguienteuno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),fichados.class)
                        .putExtra("position",position));

            }
        });

        expandBtn = findViewById(R.id.fichaunoed_expand_btn);
        initialMaxLines = edFichauno.getMaxLines();

        expandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edFichauno.getMaxLines() == initialMaxLines) {
                    // Si el EditText no está expandido, lo expande y cambia el ícono del botón a contraer.
                    edFichauno.setMaxLines(Integer.MAX_VALUE);
                    expandBtn.setImageResource(R.drawable.expandir);
                } else {
                    // Si el EditText está expandido, lo contrae y cambia el ícono del botón a expandir.
                    edFichauno.setMaxLines(initialMaxLines);
                    expandBtn.setImageResource(R.drawable.expandir);
                }
            }
        });

        imagentaller = findViewById(R.id.imagenfichauno);
        // Cargar la imagen en el ImageView desde una URL almacenada en la base de datos


        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");


        edId.setText(MainActivityTaller.employeeArrayList.get(position).getId());
        edModelo.setText(MainActivityTaller.employeeArrayList.get(position).getModelo());
        edPatente.setText(MainActivityTaller.employeeArrayList.get(position).getPatente());
        edComprador.setText(MainActivityTaller.employeeArrayList.get(position).getNombre());
        edKm.setText(MainActivityTaller.employeeArrayList.get(position).getCorreo());
        edColor.setText(MainActivityTaller.employeeArrayList.get(position).getDireccion());
        edCosto.setText(MainActivityTaller.employeeArrayList.get(position).getCosto());
        edPrecioinfoautos.setText(MainActivityTaller.employeeArrayList.get(position).getPrecioinfoautos());
        edPorcinfoautos.setText(MainActivityTaller.employeeArrayList.get(position).getPorcinfoautos());
        edValorLista.setText(MainActivityTaller.employeeArrayList.get(position).getValorlista());
        edFichauno.setText(MainActivityTaller.employeeArrayList.get(position).getFichauno());
        edFichados.setText(MainActivityTaller.employeeArrayList.get(position).getFichados());
        edFichatres.setText(MainActivityTaller.employeeArrayList.get(position).getFichatres());
        edFichacuatro.setText(MainActivityTaller.employeeArrayList.get(position).getFichacuatro());
        edFichacinco.setText(MainActivityTaller.employeeArrayList.get(position).getFichacinco());
        edFichaseis.setText(MainActivityTaller.employeeArrayList.get(position).getFichaseis());
        edFichasiete.setText(MainActivityTaller.employeeArrayList.get(position).getFichasiete());
        edFichaocho.setText(MainActivityTaller.employeeArrayList.get(position).getFichaocho());
        edFichanueve.setText(MainActivityTaller.employeeArrayList.get(position).getFichanueve());
        edFichadiez.setText(MainActivityTaller.employeeArrayList.get(position).getFichadiez());
        String imageUrl = MainActivityTaller.employeeArrayList.get(position).getImagen();
        Glide.with(this).load(imageUrl).into(imagentaller);
        modelofuno.setText("Vehículo: " + MainActivityTaller.employeeArrayList.get(position).getModelo());
        patentefuno.setText("Patente: " + MainActivityTaller.employeeArrayList.get(position).getPatente());







    }


    public void actualizar(View view) {
        final String id = edId.getText().toString();
        final String comprador = edComprador.getText().toString();
        final String modelo = edModelo.getText().toString();
        final String patente = edPatente.getText().toString();
        final String km = edKm.getText().toString();
        final String color = edColor.getText().toString();
        final String precioinfoautos = edPrecioinfoautos.getText().toString();
        final String porcinfoautos = edPorcinfoautos.getText().toString();
        final String valorlista = edValorLista.getText().toString();
        final String costo = edCosto.getText().toString();
        final String fichauno = edFichauno.getText().toString();
        final String fichados = edFichados.getText().toString();
        final String fichatres = edFichatres.getText().toString();
        final String fichacuatro = edFichacuatro.getText().toString();
        final String fichacinco = edFichacinco.getText().toString();
        final String fichaseis = edFichaseis.getText().toString();
        final String fichasiete = edFichasiete.getText().toString();
        final String fichaocho = edFichaocho.getText().toString();
        final String fichanueve = edFichanueve.getText().toString();
        final String fichadiez = edFichadiez.getText().toString();



        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Actualizando....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "https://cdturnos.com.ar/actualizar.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(fichauno.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivityTaller.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(fichauno.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();

                params.put("id",id);
                params.put("comprador",comprador);
                params.put("modelo",modelo);
                params.put("patente",patente);
                params.put("km",km);
                params.put("color",color);
                params.put("precioinfoautos",precioinfoautos);
                params.put("porcinfoautos",porcinfoautos);
                params.put("costo",costo);
                params.put("valorlista",valorlista);
                params.put("fichauno",fichauno);
                params.put("fichados",fichados);
                params.put("fichatres",fichatres);
                params.put("fichacuatro",fichacuatro);
                params.put("fichacinco",fichacinco);
                params.put("fichaseis",fichaseis);
                params.put("fichasiete",fichasiete);
                params.put("fichaocho",fichaocho);
                params.put("fichanueve",fichanueve);
                params.put("fichadiez",fichadiez);



                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(fichauno.this);
        requestQueue.add(request);





    }
}