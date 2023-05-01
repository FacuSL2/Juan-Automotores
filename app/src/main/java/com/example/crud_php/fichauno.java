package com.example.crud_php;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;

public class fichauno extends AppCompatActivity {

    EditText edId, edFichauno;
    private ImageButton expandBtn;
    private int initialMaxLines;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fichauno);

        edId = findViewById(R.id.id);
        edFichauno = findViewById(R.id.fichaunoed);
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



        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");


        edId.setText(MainActivityTaller.employeeArrayList.get(position).getId());

        edFichauno.setText(MainActivityTaller.employeeArrayList.get(position).getFichauno());







    }

    public void expandirTexto(View view) {
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

    public void actualizar(View view) {
        final String id = edId.getText().toString();

        final String fichauno = edFichauno.getText().toString();








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
                params.put("fichauno",fichauno);



                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(fichauno.this);
        requestQueue.add(request);





    }
}