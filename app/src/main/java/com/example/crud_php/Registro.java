package com.example.crud_php;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextCorreo;
    private EditText editTextContrasena;
    private RadioGroup radioGroupTipoUsuario;
    private Button buttonRegistrar;
    private FirebaseAuth mAuth;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usuariosRef = db.collection("usuarios");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextContrasena = findViewById(R.id.editTextContrasena);
        radioGroupTipoUsuario = findViewById(R.id.radioGroupTipoUsuario);
        buttonRegistrar = findViewById(R.id.buttonRegistrar);
        mAuth = FirebaseAuth.getInstance();


        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        String nombre = editTextNombre.getText().toString();
        String correo = editTextCorreo.getText().toString();
        String contrasena = editTextContrasena.getText().toString();
        int tipoUsuarioId = radioGroupTipoUsuario.getCheckedRadioButtonId();
        RadioButton tipoUsuarioRadioButton = findViewById(tipoUsuarioId);
        String tipoUsuario = tipoUsuarioRadioButton.getText().toString();

        mAuth.createUserWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            Map<String, Object> usuario = new HashMap<>();
                            usuario.put("nombre", nombre);
                            usuario.put("correo", correo);
                            usuario.put("contrasena", contrasena);

                            usuariosRef.document(tipoUsuario).collection("usuarios").document(user.getUid()).set(usuario)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {


                                            if (task.isSuccessful()) {
                                                Toast.makeText(Registro.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();


                                            } else {
                                                Toast.makeText(Registro.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                                            }


                                        }
                                    });
                        } else {
                            Toast.makeText(Registro.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }
}