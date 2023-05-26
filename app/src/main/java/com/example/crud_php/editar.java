package com.example.crud_php;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class editar extends AppCompatActivity {

    EditText edId, edModelo, edPatente, edComprador, edKm, edColor, edPrecioinfoautos,edCosto, edValorLista, edFichauno, edPorcinfoautos;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

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


        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");


        edId.setText(MainActivity.employeeArrayList.get(position).getId());
        edModelo.setText(MainActivity.employeeArrayList.get(position).getModelo());
        edPatente.setText(MainActivity.employeeArrayList.get(position).getPatente());
        edComprador.setText(MainActivity.employeeArrayList.get(position).getNombre());
        edKm.setText(MainActivity.employeeArrayList.get(position).getCorreo());
        edColor.setText(MainActivity.employeeArrayList.get(position).getDireccion());
        edCosto.setText(MainActivity.employeeArrayList.get(position).getCosto());
        edPrecioinfoautos.setText(MainActivity.employeeArrayList.get(position).getPrecioinfoautos());
        edPorcinfoautos.setText(MainActivity.employeeArrayList.get(position).getPorcinfoautos());
        edValorLista.setText(MainActivity.employeeArrayList.get(position).getValorlista());
        edFichauno.setText(MainActivity.employeeArrayList.get(position).getFichauno());







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








        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Actualizando....");
        progressDialog.show();

        StringRequest request = new StringRequest(Request.Method.POST, "https://cdturnos.com.ar/actualizar.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(editar.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(editar.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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



                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(editar.this);
        requestQueue.add(request);





    }
}