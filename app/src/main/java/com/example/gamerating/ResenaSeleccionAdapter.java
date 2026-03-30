package com.example.gamerating;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ResenaSeleccionAdapter extends RecyclerView.Adapter<ResenaSeleccionAdapter.ViewHolder> {

    private final List<Resena> resenas;
    private final List<Resena> seleccionadas = new ArrayList<>();

    public ResenaSeleccionAdapter(List<Resena> resenas) {
        this.resenas = resenas;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJuego, tvPuntuacion, tvComentario, tvFecha, tvnombre;
        EditText et1;
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            tvJuego = itemView.findViewById(R.id.tvJuego);
            tvPuntuacion = itemView.findViewById(R.id.tvPuntuacion);
            tvComentario = itemView.findViewById(R.id.tvComentario);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvnombre = itemView.findViewById(R.id.tvNombreUsuario);
            checkBox = itemView.findViewById(R.id.checkBox1);

        }
    }

    @NonNull
    @Override
    public ResenaSeleccionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.celda, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResenaSeleccionAdapter.ViewHolder holder, int position) {
        Resena r = resenas.get(position);

        holder.tvJuego.setText(r.getJuego());
        holder.tvPuntuacion.setText(String.valueOf(r.getPuntuacion()));
        holder.tvComentario.setText(r.getComentario());
        holder.tvFecha.setText(r.getFecha());
        holder.tvnombre.setText("El usuario "+r.getNombre() + " publicó: ");

        // Checkbox
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(seleccionadas.contains(r));

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (!seleccionadas.contains(r)) seleccionadas.add(r);
            } else {
                seleccionadas.remove(r);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resenas.size();
    }

    // Devuelve las reseñas seleccionadas
    public List<Resena> getSeleccionadas() {
        return new ArrayList<>(seleccionadas);
    }

    // Limpiar la selección después de eliminar
    public void limpiarSeleccion() {
        seleccionadas.clear();
    }
}
