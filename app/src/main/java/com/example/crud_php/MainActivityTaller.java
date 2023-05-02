package com.example.crud_php;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivityTaller extends AppCompatActivity {

    ListView listView;
    Adapter adapter;
    FloatingActionButton catalogo;

    SearchView buscar;


    public static ArrayList<Usuarios> employeeArrayList = new ArrayList<>();
    String url = "https://cdturnos.com.ar/mostrar.php";
    Usuarios usuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listView = findViewById(R.id.myListView);
        adapter = new Adapter(this,employeeArrayList);
        catalogo = findViewById(R.id.catalogo);
        listView.setAdapter(adapter);






        catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),fichauno.class));
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"Fichas Técnicas"};
                builder.setTitle(employeeArrayList.get(position).getModelo());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(),fichauno.class)
                                        .putExtra("position",position));

                                break;


                        }



                    }
                });


                builder.create().show();


            }
        });

        retrieveData();


    }


    public void retrieveData(){

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        employeeArrayList.clear();
                        try{

                            JSONObject jsonObject = new JSONObject(response);
                            String exito = jsonObject.getString("exito");
                            JSONArray jsonArray = jsonObject.getJSONArray("datos");

                            if(exito.equals("1")){


                                for(int i=0;i<jsonArray.length();i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("id");
                                    String comprador = object.getString("comprador");
                                    String modelo = object.getString("modelo");
                                    String patente = object.getString("patente");
                                    String km = object.getString("km");
                                    String color = object.getString("color");
                                    String precioinfoautos = object.getString("precioinfoautos");
                                    String porcinfoautos = object.getString("porcinfoautos");
                                    String costo = object.getString("costo");
                                    String valorlista = object.getString("valorlista");
                                    String foto = object.getString("foto");
                                    String fichauno = object.getString("fichauno");
                                    String fichados = object.getString("fichados");
                                    String fichatres = object.getString("fichatres");
                                    String fichacuatro = object.getString("fichacuatro");
                                    String fichacinco = object.getString("fichacinco");
                                    String fichaseis = object.getString("fichaseis");
                                    String fichasiete = object.getString("fichasiete");
                                    String fichaocho = object.getString("fichaocho");
                                    String fichanueve = object.getString("fichanueve");
                                    String fichadiez = object.getString("fichadiez");


                                    usuarios = new Usuarios(id,comprador,modelo,patente, km,color, precioinfoautos, porcinfoautos, costo, valorlista, foto, fichauno, fichados,
                                            fichatres,fichacuatro,fichacinco,fichaseis,fichasiete,fichaocho,fichanueve,fichadiez);
                                    employeeArrayList.add(usuarios);
                                    adapter.notifyDataSetChanged();



                                }



                            }




                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }






                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityTaller.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);




    }


    public void agregar(View view) {
        startActivity(new Intent(getApplicationContext(),agregar.class));
    }
}