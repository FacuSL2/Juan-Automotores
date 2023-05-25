package com.example.crud_php;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class editar extends AppCompatActivity {

    EditText edId, edModelo, edPatente, edComprador, edKm, edColor, edPrecioinfoautos,edCosto, edValorLista, edFichauno, edPorcinfoautos;
    private ImageView ImgBoleto;
    private int position;
    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    private String UPLOAD_URL ="https://cdturnos.com.ar/editararchivos.php";

    private String KEY_BOLETO = "boleto";

    private Button BtnBoleto;
    private Button btnsubiredit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        BtnBoleto = (Button) findViewById(R.id.BtnBoleto);
        btnsubiredit = (Button) findViewById(R.id.btnsubiredit);
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
        ImgBoleto  = (ImageView) findViewById(R.id.ImgBoleto);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");


        BtnBoleto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooser();
            }
        });

        btnsubiredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();

            }
        });

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
                        Toast.makeText(editar.this, s , Toast.LENGTH_LONG).show();
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
                        Toast.makeText(editar.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Convertir bits a cadena
                String boleto = getStringImagen(bitmap);

                //Creación de parámetros
                Map<String,String> params = new Hashtable<String, String>();

                //Agregando de parámetros
                params.put(KEY_BOLETO, boleto);





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
        startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            /*try {
                //Cómo obtener el mapa de bits de la Galería
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Configuración del mapa de bits en ImageView
                ImgBoleto.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                ImgBoleto.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}