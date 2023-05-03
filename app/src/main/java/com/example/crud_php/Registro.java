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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

    private EditText mEditTextNombre;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private RadioGroup mRadioGroupTipoUsuario;
    private Button mButtonRegistrar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mEditTextNombre = findViewById(R.id.editTextNombre);
        mEditTextEmail = findViewById(R.id.editTextCorreo);
        mEditTextPassword = findViewById(R.id.editTextContrasena);
        mRadioGroupTipoUsuario = findViewById(R.id.radioGroupTipoUsuario);
        mButtonRegistrar = findViewById(R.id.buttonRegistrar);

        mAuth = FirebaseAuth.getInstance();

        mButtonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        final String nombre = mEditTextNombre.getText().toString().trim();
        final String email = mEditTextEmail.getText().toString().trim();
        final String password = mEditTextPassword.getText().toString().trim();

        int radioButtonId = mRadioGroupTipoUsuario.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioButtonId);
        final String tipoUsuario = radioButton.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            Map<String, Object> usuario = new HashMap<>();
                            usuario.put("nombre", nombre);
                            usuario.put("email", email);
                            usuario.put("tipoUsuario", tipoUsuario);
                            db.collection("usuarios").document(user.getUid())
                                    .set(usuario)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(Registro.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                                            if (tipoUsuario.equals("Administrador")) {
                                                Intent intent = new Intent(Registro.this, PrincipalAdmin.class);
                                                startActivity(intent);
                                            } else if (tipoUsuario.equals("Taller")) {
                                                Intent intent = new Intent(Registro.this, Taller.class);
                                                startActivity(intent);
                                            }else if (tipoUsuario.equals("Vendedor")) {
                                                Intent intent = new Intent(Registro.this, MainActivityVendedor.class);
                                                startActivity(intent);
                                            }
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Registro.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(Registro.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
