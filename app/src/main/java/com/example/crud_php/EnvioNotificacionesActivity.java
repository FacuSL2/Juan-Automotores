package com.example.crud_php;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnvioNotificacionesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerTipoUsuario;
    private Button btnEnviarNotificacion;
    private String tipoUsuario;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usuariosRef = db.collection("usuarios");
    private ArrayList<String> tokens = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envio_notificaciones);

        spinnerTipoUsuario = findViewById(R.id.spinnerTipoUsuario);
        btnEnviarNotificacion = findViewById(R.id.btnEnviar);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipos_usuario, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoUsuario.setAdapter(adapter);
        spinnerTipoUsuario.setOnItemSelectedListener(this);

        btnEnviarNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tokens.size() == 0) {
                    Toast.makeText(EnvioNotificacionesActivity.this, "No hay usuarios registrados de este tipo", Toast.LENGTH_SHORT).show();
                } else {
                    enviarNotificacion();
                }
            }
        });
    }
    private void obtenerTokens() {
        usuariosRef.document(tipoUsuario).collection("usuarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                tokens.add(document.getString("token"));
                            }
                        } else {
                            Toast.makeText(EnvioNotificacionesActivity.this, "Error al obtener usuarios", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void enviarNotificacion() {
        Map<String, String> data = new HashMap<>();
        data.put("tipo_usuario", tipoUsuario);
        FirebaseMessaging.getInstance().send(new RemoteMessage.Builder("")
                .setMessageId(Integer.toString((int) System.currentTimeMillis()))
                .addData("data", data.toString())
                .addAllTokens(tokens)
                .build());
        Toast.makeText(this, "Notificación enviada correctamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tipoUsuario = parent.getItemAtPosition(position).toString();
        tokens.clear();
        obtenerTokens();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}