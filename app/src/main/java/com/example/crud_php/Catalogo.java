package com.example.crud_php;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalogo extends AppCompatActivity {
    private  static  String url="https://cdturnos.com.ar/imagen.php";
    List<Vehiculos>productosList;
    private List<Vehiculos> productosLists;
    RecyclerView  recyclerView;


    private RecyclerView.Adapter adapters;
    private RecyclerView.LayoutManager layoutManager;

    Adapter adapter;
    public static ArrayList<Usuarios> employeeArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productosList=new ArrayList<>();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        productosList = new ArrayList<>();
        Adapters adapter = new Adapters(Catalogo.this, productosList);
        recyclerView.setAdapter(adapters);

        cargargarImagen();



    }



    private void cargargarImagen() {
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject Vehiculos = array.getJSONObject(i);

                                productosList.add(new Vehiculos(
                                        Vehiculos.getInt("id"),
                                        Vehiculos.getString("comprador"),
                                        Vehiculos.getString("km"),
                                        Vehiculos.getString("foto")
                                ));
                            }
                            Adapters adapter = new Adapters(Catalogo.this, productosList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);



    }



}