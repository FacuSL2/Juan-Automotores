package com.example.crud_php;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {


    private EditText mEmail, mPassword;
    private Button mLoginBtn;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;
    private CheckBox mRememberCheckbox;
    private SharedPreferences mSharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.editTextEmail);
        mPassword = findViewById(R.id.editTextPassword);
        mLoginBtn = findViewById(R.id.buttonLogin);
        mRememberCheckbox = findViewById(R.id.checkboxRemember);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (mSharedPreferences.getBoolean("remember", false)) {
            mEmail.setText(mSharedPreferences.getString("email", ""));
            mPassword.setText(mSharedPreferences.getString("password", ""));
            mRememberCheckbox.setChecked(true);
        }

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Se requiere un correo electrónico.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Se requiere una contraseña.");
                    return;
                }

                // Guardar los datos de inicio de sesión si el checkbox está marcado
                if (mRememberCheckbox.isChecked()) {
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.putBoolean("remember", true);
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.apply();
                } else {
                    // Si el checkbox no está marcado, eliminar los datos de inicio de sesión guardados
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.remove("remember");
                    editor.remove("email");
                    editor.remove("password");
                    editor.apply();
                }

                mProgressDialog = new ProgressDialog(Login.this);
                mProgressDialog.setMessage("Ingresando...");
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    checkUserType(user.getUid());
                                } else {
                                    mProgressDialog.dismiss();
                                    Toast.makeText(Login.this, "Error al iniciar sesión. Intente nuevamente.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void checkUserType(String uid) {
        DocumentReference adminRef = FirebaseFirestore.getInstance().document("usuarios/Administrador/usuarios/" + uid);
        adminRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot adminSnapshot) {
                if (adminSnapshot.exists()) {
                    mProgressDialog.dismiss();
                    startActivity(new Intent(Login.this, PrincipalAdmin.class));
                    finish();
                } else {
                    DocumentReference tallerRef = FirebaseFirestore.getInstance().document("usuarios/Taller/usuarios/" + uid);
                    tallerRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot tallerSnapshot) {
                            if (tallerSnapshot.exists()) {
                                mProgressDialog.dismiss();
                                startActivity(new Intent(Login.this, Taller.class));
                                finish();
                            } else {
                                DocumentReference vendedorRef = FirebaseFirestore.getInstance().document("usuarios/Vendedor/usuarios/" + uid);
                                vendedorRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot vendedorSnapshot) {
                                        if (vendedorSnapshot.exists()) {
                                            mProgressDialog.dismiss();
                                            startActivity(new Intent(Login.this, MainActivityVendedor.class));
                                            finish();
                                        } else {
                                            mProgressDialog.dismiss();
                                            Toast.makeText(Login.this, "No se pudo identificar el tipo de usuario.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }

}