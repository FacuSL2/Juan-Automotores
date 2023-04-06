
        package com.example.crud_php;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.crud_php.Adapters;

import java.util.List;

public class Adapter extends ArrayAdapter<Usuarios> implements Adapterdos, Filterable {

    Context context;
    List<Usuarios> arrayUsuarios, filterList;


    public Adapter(@NonNull Context context, List<Usuarios> arrayUsuarios) {
        super(context, R.layout.list_usuarios,arrayUsuarios);
        this.context =context;
        this.arrayUsuarios =arrayUsuarios;
        this.filterList = arrayUsuarios;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_usuarios, parent, false);
            holder = new ViewHolder();
            holder.tvKilometraje = convertView.findViewById(R.id.txt_id);
            holder.tvModelo = convertView.findViewById(R.id.txt_name);
            holder.tvColor = convertView.findViewById(R.id.txt_color);
            holder.img = convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Usuarios usuario = arrayUsuarios.get(position);
        holder.tvKilometraje.setText("Kilometraje: "+usuario.getCorreo());
        holder.tvModelo.setText(usuario.getModelo());
        holder.tvColor.setText("Color: "+usuario.getDireccion());

        Glide.with(context)
                .load(usuario.getImagen())
                .into(holder.img);

        return convertView;
    }

    static class ViewHolder {
        TextView tvKilometraje;
        TextView tvModelo;
        TextView tvColor;
        ImageView img;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapters.PlayerViewnHolder holder, int position) {
        // No necesitas esta implementación aquí, ya que no estás usando un RecyclerView
    }
}

