package com.example.crud_php;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class agregar extends AppCompatActivity {

    private EditText txtNombre, txtModelo,txtpatente, txtCorreo, txtDireccion, txtprecioinfoautos, txtporcinfoautos, txtcosto,txtvalorlista ;
    Button btn_insert;

    private Button btnBuscar;
    private Button btnSubir;

    private ImageView imageView;

    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    private String UPLOAD_URL ="https://cdturnos.com.ar/upload.php";

    private String KEY_IMAGEN = "foto";
    private String KEY_NOMBRE = "comprador";
    private String KEY_MODELO = "modelo";
    private String KEY_PATENTE = "patente";
    private String KEY_CORREO = "km";
    private String KEY_DIRECCION = "color";
    private String KEY_PRECIOINFOAUTOS = "precioinfoautos";
    private String KEY_PORCINFOAUTOS = "porcinfoautos";
    private String KEY_COSTO = "costo";
    private String KEY_VALORLISTA = "valorlista";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        btnBuscar = (Button) findViewById(R.id.btnBuscar);
        btnSubir = (Button) findViewById(R.id.btnSubir);

        txtNombre = (EditText) findViewById(R.id.comprador);
        txtModelo = (EditText) findViewById(R.id.modelo);
        txtpatente = (EditText) findViewById(R.id.patente);
        txtprecioinfoautos = (EditText) findViewById(R.id.precioinfoautos);
        txtporcinfoautos = (EditText) findViewById(R.id.porcinfoautos);
        txtcosto = (EditText) findViewById(R.id.costo);
        txtvalorlista = (EditText) findViewById(R.id.valorlista);
        txtCorreo = (EditText) findViewById(R.id.km);
        txtDireccion = (EditText) findViewById(R.id.color);


        imageView  = (ImageView) findViewById(R.id.imageView);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooser();
            }
        });
        btnSubir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();

            }
        });
    }

    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage(){
        //Mostrar el diálogo de progreso
        final ProgressDialog loading = ProgressDialog.show(this,"Subiendo...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();
                        //Mostrando el mensaje de la respuesta
                        Toast.makeText(agregar.this, s , Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(agregar.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                String imagen = getStringImagen(bitmap);

                //Obtener el nombre de la imagen
                String comprador = txtNombre.getText().toString().trim();
                String modelo = txtModelo.getText().toString().trim();
                String patente = txtpatente.getText().toString().trim();
                String precioinfoautos = txtprecioinfoautos.getText().toString().trim();
                String porcinfoautos = txtporcinfoautos.getText().toString().trim();
                String costo = txtcosto.getText().toString().trim();
                String valorlista = txtvalorlista.getText().toString().trim();
                String km = txtCorreo.getText().toString().trim();
                String color = txtDireccion.getText().toString().trim();

                //Creación de parámetros
                Map<String,String> params = new Hashtable<String, String>();

                //Agregando de parámetros
                params.put(KEY_IMAGEN, imagen);
                params.put(KEY_NOMBRE, comprador);
                params.put(KEY_CORREO, km);
                params.put(KEY_DIRECCION, color);
                params.put(KEY_MODELO, modelo);
                params.put(KEY_PATENTE, patente);
                params.put(KEY_PRECIOINFOAUTOS, precioinfoautos);
                params.put(KEY_PORCINFOAUTOS, porcinfoautos);
                params.put(KEY_COSTO, costo);
                params.put(KEY_VALORLISTA, valorlista);





                //Parámetros de retorno
                return params;
            }
        };

        //Creación de una cola de solicitudes
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Agregar solicitud a la cola
        requestQueue.add(stringRequest);
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Cómo obtener el mapa de bits de la Galería
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Configuración del mapa de bits en ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}