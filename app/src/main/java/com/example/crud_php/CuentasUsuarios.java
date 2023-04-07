package com.example.crud_php;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class CuentasUsuarios extends AppCompatActivity {
    private FirebaseFirestore db;
    private ListView listViewAdmins, listViewTalleres;
    private ArrayList<Usuario> adminsList, talleresList;

    public class Usuario {
        public String nombre, correo;
        public Usuario(String nombre, String correo) {
            this.nombre = nombre;
            this.correo = correo;
        }
        // Agregamos el método toString para que el ArrayAdapter pueda mostrar correctamente los datos
        @Override
        public String toString() {
            return nombre + "\n" + correo;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuentas_usuarios);

        db = FirebaseFirestore.getInstance();
        listViewAdmins = findViewById(R.id.listViewAdmins);
        listViewTalleres = findViewById(R.id.listViewTalleres);

        adminsList = new ArrayList<>();
        talleresList = new ArrayList<>();

        // Obtener los usuarios de tipo Administrador y agregarlos a la lista de admins
        db.collection("usuarios").document("Administrador").collection("usuarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String nombre = document.getString("nombre");
                                String correo = document.getString("correo");
                                Usuario usuario = new Usuario(nombre, correo);
                                adminsList.add(usuario);
                            }
                            // Crear un ArrayAdapter para mostrar la lista de admins en el listViewAdmins
                            ArrayAdapter<Usuario> adapterAdmins = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, adminsList);
                            listViewAdmins.setAdapter(adapterAdmins);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        // Obtener los usuarios de tipo Taller y agregarlos a la lista de talleres
        db.collection("usuarios").document("Taller").collection("usuarios")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String nombre = document.getString("nombre");
                                String correo = document.getString("correo");
                                Usuario usuario = new Usuario(nombre, correo);
                                talleresList.add(usuario);
                            }
                            // Crear un ArrayAdapter para mostrar la lista de talleres en el listViewTalleres
                            ArrayAdapter<Usuario> adapterTalleres = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, talleresList);
                            listViewTalleres.setAdapter(adapterTalleres);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}