  package com.example.crud_php;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapters extends RecyclerView.Adapter<Adapters.PlayerViewnHolder> {

    private Context mCtx;
    private List<Vehiculos>productosList;

    private AdapterView.OnItemClickListener onItemClickListener;
    public Adapters(Context mCtx, List<Vehiculos>productosList){
        this.mCtx=mCtx;
        this.productosList=productosList;
        this.onItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public PlayerViewnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(mCtx);
        // View view=inflater.inflate(R.layout.lista,null);

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista, parent, false);

        return new PlayerViewnHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewnHolder holder, int position) {
        Vehiculos productos=productosList.get(position);
        Glide.with(mCtx)
                .load(productos.getImagen())
                .into(holder.img);
        holder.tv1.setText(productos.getTitulo());
        holder.tv2.setText(productos.getPrecio());
    }



    @Override
    public int getItemCount() {
        return productosList.size();
    }


    public interface OnItemClickListener {
        void onVerClick(int position);
        void onEditarClick(int position);
        void onTallerClick(int position);
        void onEliminarClick(int position);
    }


    static class PlayerViewnHolder extends  RecyclerView.ViewHolder{
        TextView tv1,tv2;
        ImageView img;
        public PlayerViewnHolder(@NonNull View itemView) {
            super(itemView);

            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
            img=itemView.findViewById(R.id.img);
        }
    }
}
