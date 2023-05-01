
        package com.example.crud_php;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.crud_php.Adapters;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<Usuarios>implements Adapterdos, Filterable {

    Context context;
    List<Usuarios> arrayUsuarios, filterList;

    private List<Usuarios> autoList;
    private List<Usuarios> filteredAutoList;
    //private Context context;
    private LayoutInflater inflater;


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint == null || constraint.length() == 0) {
                    // si el filtro está vacío, muestra la lista completa
                    results.count = arrayUsuarios.size();
                    results.values = filterList;
                } else {
                    // filtra la lista según el criterio de búsqueda
                    List<Usuarios> filteredList = new ArrayList<>();
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (Usuarios usuarios : filterList) {
                        if (usuarios.getDireccion().toLowerCase().contains(filterPattern)
                                || usuarios.getModelo().toLowerCase().contains(filterPattern)
                                || usuarios.getCorreo().toLowerCase().contains(filterPattern)) {
                            filteredList.add(usuarios);
                        }
                    }

                    results.count = filteredList.size();
                    results.values = filteredList;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredAutoList = (List<Usuarios>) results.values;
                notifyDataSetChanged();
            }
        };
    }


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

