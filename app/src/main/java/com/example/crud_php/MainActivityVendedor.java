package com.example.crud_php;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
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

public class MainActivityVendedor extends AppCompatActivity {

    ListView listView;
    Adapter adapter;
    FloatingActionButton catalogo;

    SearchView buscarven;


    public static ArrayList<Usuarios> employeeArrayList = new ArrayList<>();
    String url = "https://cdturnos.com.ar/mostrar.php";
    Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vendedor);



        listView = findViewById(R.id.myListView);
        adapter = new Adapter(this,employeeArrayList);
        catalogo = findViewById(R.id.catalogo);
        listView.setAdapter(adapter);
        buscarven = findViewById(R.id.buscarven);
        buscarven.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });





        catalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Taller.class));
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogItem = {"Ver Datos"};
                builder.setTitle(employeeArrayList.get(position).getModelo());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        switch (i){

                            case 0:

                                startActivity(new Intent(getApplicationContext(),detallesvendedor.class)
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

    private void deleteData(final String id) {

        StringRequest request = new StringRequest(Request.Method.POST, "https://cdturnos.com.ar/eliminar.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("datos eliminados")){
                            Toast.makeText(MainActivityVendedor.this, "eliminado correctamente", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                        else{
                            Toast.makeText(MainActivityVendedor.this, "no se puedo eliminar", Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityVendedor.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String,String>();
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


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
                                    String boleto = object.getString("boleto");
                                    String cedula = object.getString("cedula");
                                    String titulo = object.getString("titulo");
                                    String ceroocho = object.getString("ceroocho");
                                    String dominio = object.getString("dominio");
                                    String multas = object.getString("multas");
                                    String muni = object.getString("muni");
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


                                    usuarios = new Usuarios(id,comprador,modelo,patente, km,color, precioinfoautos, porcinfoautos, costo, valorlista, foto, boleto, cedula, titulo, ceroocho, dominio, multas, muni, fichauno, fichados,
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
                Toast.makeText(MainActivityVendedor.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);




    }


    public void agregar(View view) {
        startActivity(new Intent(getApplicationContext(),agregar.class));
    }
}
