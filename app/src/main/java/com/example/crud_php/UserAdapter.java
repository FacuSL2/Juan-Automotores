package com.example.crud_php;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

public class UserAdapter extends FirestoreRecyclerAdapter<User, UserAdapter.UserViewHolder> {

    private static final String TAG = "UserAdapter";

    private RadioButton radioButtonAdmin;
    private RadioButton radioButtonTaller;
    private FirestoreRecyclerOptions<User> options;
    private FirestoreRecyclerOptions<User> optionsAdmin;
    private FirestoreRecyclerOptions<User> optionsTaller;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference adminRef = db.collection("usuarios").document("Administrador").collection("usuarios");
    private CollectionReference tallerRef = db.collection("usuarios").document("Taller").collection("usuarios");
    private String userType = "Administrador";
    private String searchText = "";

    public UserAdapter(@NonNull FirestoreRecyclerOptions<User> options, RadioButton radioButtonAdmin, RadioButton radioButtonTaller) {
        super(options);
        this.radioButtonAdmin = radioButtonAdmin;
        this.radioButtonTaller = radioButtonTaller;
        this.options = options;
        this.optionsAdmin = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(adminRef.orderBy("nombre").startAt(searchText).endAt(searchText + "\uf8ff"), User.class)
                .build();
        this.optionsTaller = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(tallerRef.orderBy("nombre").startAt(searchText).endAt(searchText + "\uf8ff"), User.class)
                .build();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView emailTextView;
        TextView typeTextView;

        public UserViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewNombre);
            emailTextView = itemView.findViewById(R.id.textViewCorreo);
            typeTextView = itemView.findViewById(R.id.textViewType);
        }
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_de_usuarios, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull User model) {
        if (userType.equals("Administrador")) {
            DocumentReference docRef = FirebaseFirestore.getInstance()
                    .collection("usuarios")
                    .document("Administrador")
                    .collection("usuarios")
                    .document(model.getUid());
            docRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    holder.nameTextView.setText(documentSnapshot.getString("nombre"));
                    holder.emailTextView.setText(documentSnapshot.getString("correo"));
                    holder.typeTextView.setText("Administrador");
                }
            });
        } else {
            DocumentReference docRef = FirebaseFirestore.getInstance()
                    .collection("usuarios")
                    .document("Taller")
                    .collection("usuarios")
                    .document(model.getUid());
            docRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    holder.nameTextView.setText(documentSnapshot.getString("nombre"));
                    holder.emailTextView.setText(documentSnapshot.getString("correo"));
                    holder.typeTextView.setText("Taller");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public void filter(String text) {
        searchText = text;
        if (userType.equals("Administrador")) {
            optionsAdmin = new FirestoreRecyclerOptions.Builder<User>()
                    .setQuery(adminRef.orderBy("nombre").startAt(searchText).endAt(searchText + "\uf8ff"), User.class)
                    .build();
            updateOptions(optionsAdmin);
        } else {
            optionsTaller = new FirestoreRecyclerOptions.Builder<User>()
                    .setQuery(tallerRef.orderBy("nombre").startAt(searchText).endAt(searchText + "\uf8ff"), User.class)
                    .build();
            updateOptions(optionsTaller);
        }
    }

    public void filterAdmin() {
        userType = "Administrador";
        updateOptions(optionsAdmin);
    }

    public void filterTaller() {
        userType = "Taller";
        updateOptions(optionsTaller);
    }
}
