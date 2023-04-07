package com.example.crud_php;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.auth.User;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class CuentasUsuarios extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserListAdapter adapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference usersRef = db.collection("users");
    private Query adminQuery, tallerQuery;
    private Button filterAdminButton, filterTallerButton;
    private boolean adminSelected = false, tallerSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuentas_usuarios);

        filterAdminButton = findViewById(R.id.adminRadioButton);
        filterTallerButton = findViewById(R.id.tallerRadioButton);

        adminQuery = usersRef.whereEqualTo("type", "Administrador");
        tallerQuery = usersRef.whereEqualTo("type", "Taller");

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.userListRecyclerView);
        recyclerView.setHasFixedSize(true);

        Query query = usersRef.orderBy("name");

        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();

        adapter = new UserListAdapter(options);
        recyclerView.setAdapter(adapter);
    }

    private void setupFilteredRecyclerView(Query query) {
        recyclerView = findViewById(R.id.userListRecyclerView);
        recyclerView.setHasFixedSize(true);

        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();

        adapter = new UserListAdapter(options);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public class UserListAdapter extends FirestoreRecyclerAdapter<User, UserListAdapter.UserViewHolder> {

        public UserListAdapter(@NonNull FirestoreRecyclerOptions<User> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull User model) {
            // Bind data to ViewHolder
            holder.nameTextView.setText(model.getName());
            holder.emailTextView.setText(model.getEmail());
            holder.typeTextView.setText(model.getType());
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Inflate ViewHolder layout
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cuentas_usuarios, parent, false);
            return new UserViewHolder(view);
        }

        class UserViewHolder extends RecyclerView.ViewHolder {

            TextView nameTextView;
            TextView emailTextView;
            TextView typeTextView;

            public UserViewHolder(@NonNull View itemView) {
                super(itemView);
                nameTextView = itemView.findViewById(R.id.textview_name);
                emailTextView = itemView.findViewById(R.id.textview_email);
                typeTextView = itemView.findViewById(R.id.textview_type);
            }
        }
    }
}