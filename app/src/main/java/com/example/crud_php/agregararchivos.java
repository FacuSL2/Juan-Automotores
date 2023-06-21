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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class agregararchivos extends AppCompatActivity {

    private Button btnBuscarr;
    private Button btnSubirr;
    private Button btnBuscarCedula;
    private Button btnBuscarTitulo;

    private Button btnBuscarCeroocho;
    private Button btnBuscarDominio;
    private Button btnBuscarMultas;
    private Button btnBuscarMuni;
    private TextView txt_id;

    private ImageView imageView,imageViewCedula,imageViewTitulo,imageViewCeroocho,imageViewDominio,imageViewMultas,imageViewMuni;

    private Bitmap bitmap;
    private Bitmap bitmapCedula;
    private Bitmap bitmapTitulo;
    private Bitmap bitmapCeroocho;
    private Bitmap bitmapDominio;
    private Bitmap bitmapMultas;
    private Bitmap bitmapMuni;
    private int position;
    private int PICK_IMAGE_REQUEST = 1;
    private int PICK_IMAGE_REQUEST_CEDULA = 2;
    private int PICK_IMAGE_REQUEST_TITULO = 3;
    private int PICK_IMAGE_REQUEST_CEROOCHO = 4;
    private int PICK_IMAGE_REQUEST_DOMINIO = 5;
    private int PICK_IMAGE_REQUEST_MULTAS = 6;
    private int PICK_IMAGE_REQUEST_MUNI = 7;



    private String UPLOAD_URL ="https://cdturnos.com.ar/subirarchivos.php";

    private String KEY_IMAGEN = "boleto";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregararchivos);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        btnBuscarr = (Button) findViewById(R.id.btnBuscarr);
        btnSubirr = (Button) findViewById(R.id.btnSubirr);

        btnBuscarCedula = (Button) findViewById(R.id.btnBuscarCedula);
        btnBuscarCedula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooserCedula();
            }
        });

        btnBuscarTitulo = (Button) findViewById(R.id.btnBuscarTitulo);
        btnBuscarTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooserTitulo();
            }
        });

        btnBuscarCeroocho = (Button) findViewById(R.id.btnBuscarCeroocho);
        btnBuscarCeroocho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooserCeroocho();
            }
        });

        btnBuscarDominio = (Button) findViewById(R.id.btnBuscarDominio);
        btnBuscarDominio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooserDominio();
            }
        });

        btnBuscarMultas = (Button) findViewById(R.id.btnBuscarMultas);
        btnBuscarMultas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooserMultas();
            }
        });

        btnBuscarMuni = (Button) findViewById(R.id.btnBuscarMuni);
        btnBuscarMuni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooserMuni();
            }
        });



        imageView  = (ImageView) findViewById(R.id.imageVieww);
        imageViewCedula  = (ImageView) findViewById(R.id.imageViewCedula);
        imageViewTitulo  = (ImageView) findViewById(R.id.imageViewTitulo);
        imageViewCeroocho  = (ImageView) findViewById(R.id.imageViewCeroocho);
        imageViewDominio  = (ImageView) findViewById(R.id.imageViewDominio);
        imageViewMultas  = (ImageView) findViewById(R.id.imageViewMultas);
        imageViewMuni  = (ImageView) findViewById(R.id.imageViewMuni);
        txt_id = (TextView) findViewById(R.id.txt_id);
        txt_id.setText(MainActivity.employeeArrayList.get(position).getId());

        btnBuscarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showFileChooser();
            }
        });
        btnSubirr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();

            }
        });




        // private ImageView imageView,imageViewCedula,imageViewTitulo,imageViewCeroocho,imageViewDominio,imageViewMultas,imageViewMuni;


    }

    public String getStringImagen(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadImage() {
        final ProgressDialog loading = ProgressDialog.show(this, "Subiendo...", "Espere por favor...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        loading.dismiss();
                        Toast.makeText(agregararchivos.this, s, Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        loading.dismiss();
                        Toast.makeText(agregararchivos.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String boleto = getStringImagen(bitmap);
                String cedula = getStringImagen(bitmapCedula);
                String titulo = getStringImagen(bitmapTitulo);
                String ceroocho = getStringImagen(bitmapCeroocho);
                String dominio = getStringImagen(bitmapDominio);
                String multas = getStringImagen(bitmapMultas);
                String muni = getStringImagen(bitmapMuni);
                String id = MainActivity.employeeArrayList.get(position).getId();

                Map<String, String> params = new Hashtable<String, String>();
                params.put(KEY_IMAGEN, boleto);
                params.put("id", id);

                // Agrega el nuevo parámetro para la imagen de la cédula
                params.put("cedula", cedula);
                params.put("titulo", titulo);
                params.put("ceroocho", ceroocho);
                params.put("dominio", dominio);
                params.put("multas", multas);
                params.put("muni", muni);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen"), PICK_IMAGE_REQUEST);
    }

    private void showFileChooserCedula() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen de la Cédula"), PICK_IMAGE_REQUEST_CEDULA);
    }

    private void showFileChooserTitulo() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen del Titulo"), PICK_IMAGE_REQUEST_TITULO);
    }

    private void showFileChooserCeroocho() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen del Titulo"), PICK_IMAGE_REQUEST_CEROOCHO);
    }

    private void showFileChooserDominio() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen del Titulo"), PICK_IMAGE_REQUEST_DOMINIO);
    }

    private void showFileChooserMultas() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen del Titulo"), PICK_IMAGE_REQUEST_MULTAS);
    }

    private void showFileChooserMuni() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar Imagen del Titulo"), PICK_IMAGE_REQUEST_MUNI);
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
        }else if (requestCode == PICK_IMAGE_REQUEST_CEDULA && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePathCedula = data.getData();
            try {
                bitmapCedula = MediaStore.Images.Media.getBitmap(getContentResolver(), filePathCedula);
                // Puedes mostrar la imagen de la cédula en otro ImageView si lo deseas
                imageViewCedula.setImageBitmap(bitmapCedula);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (requestCode == PICK_IMAGE_REQUEST_TITULO && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePathTitulo = data.getData();
            try {
                bitmapTitulo = MediaStore.Images.Media.getBitmap(getContentResolver(), filePathTitulo);
                // Puedes mostrar la imagen del titulo en otro ImageView si lo deseas
                imageViewTitulo.setImageBitmap(bitmapTitulo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (requestCode == PICK_IMAGE_REQUEST_CEROOCHO && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePathCeroocho = data.getData();
            try {
                bitmapCeroocho = MediaStore.Images.Media.getBitmap(getContentResolver(), filePathCeroocho);
                // Puedes mostrar la imagen del titulo en otro ImageView si lo deseas
                imageViewCeroocho.setImageBitmap(bitmapCeroocho);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (requestCode == PICK_IMAGE_REQUEST_DOMINIO && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePathDominio = data.getData();
            try {
                bitmapDominio = MediaStore.Images.Media.getBitmap(getContentResolver(), filePathDominio);
                // Puedes mostrar la imagen del titulo en otro ImageView si lo deseas
                imageViewDominio.setImageBitmap(bitmapDominio);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (requestCode == PICK_IMAGE_REQUEST_MULTAS && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePathMultas = data.getData();
            try {
                bitmapMultas = MediaStore.Images.Media.getBitmap(getContentResolver(), filePathMultas);
                // Puedes mostrar la imagen del titulo en otro ImageView si lo deseas
                imageViewMultas.setImageBitmap(bitmapMultas);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (requestCode == PICK_IMAGE_REQUEST_MUNI && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePathMuni = data.getData();
            try {
                bitmapMuni = MediaStore.Images.Media.getBitmap(getContentResolver(), filePathMuni);
                // Puedes mostrar la imagen del titulo en otro ImageView si lo deseas
                imageViewMuni.setImageBitmap(bitmapMuni);
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