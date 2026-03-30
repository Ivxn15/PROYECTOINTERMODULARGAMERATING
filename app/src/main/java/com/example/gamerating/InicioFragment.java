package com.example.gamerating;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InicioFragment extends Fragment {

    RecyclerView recyclerView;
    ResenaSeleccionAdapter adapter;
    Button btnEliminar;

    public InicioFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        recyclerView = view.findViewById(R.id.rv);
        btnEliminar = view.findViewById(R.id.btnEliminar);

        adapter = new ResenaSeleccionAdapter(ResenaManager.getResenas());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        btnEliminar.setOnClickListener(v -> eliminarResenas());


        //cargarResenasUsuario();
        cargarTodasResenas();

        return view;
    }

    private void cargarResenasUsuario() {
        int usuarioId = Sesion.usuarioId;
        if (usuarioId <= 0) return;

        API_Usuarios api = new API_Usuarios();
        api.getResenasUsuario(usuarioId, new API_Usuarios.ResenasCallback() {
            @Override
            public void onSuccess(List<Resena> resenas) {
                // Limpiar lista actual y agregar las reseñas de la base de datos
                ResenaManager.getResenas().clear();
                ResenaManager.getResenas().addAll(resenas);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String mensaje) {
                Toast.makeText(getContext(), "No se pudieron cargar las reseñas: " + mensaje, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void cargarTodasResenas() {

        API_Usuarios api = new API_Usuarios();

        api.getTodasResenas(new API_Usuarios.ResenasCallback() {
            @Override
            public void onSuccess(List<Resena> resenas) {
                ResenaManager.getResenas().clear();
                ResenaManager.getResenas().addAll(resenas);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String mensaje) {
                Toast.makeText(getContext(), "Error al cargar: " + mensaje, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void eliminarResenas() {

        List<Resena> seleccionadas = new ArrayList<>(adapter.getSeleccionadas());

        if (seleccionadas.isEmpty()) {
            Toast.makeText(getContext(), "No has seleccionado ninguna reseña", Toast.LENGTH_SHORT).show();
            return;
        }

        API_Usuarios api = new API_Usuarios();

        for (Resena r : seleccionadas) {
            if (r.getId() > 0) { // solo eliminar reseñas que tienen ID en BD
                api.eliminarResena(r.getId(), new API_Usuarios.ApiCallback() {
                    @Override
                    public void onSuccess(int idGenerado) {
                        // Eliminar de la lista local solo si se borró en la BD
                        ResenaManager.getResenas().remove(r);
                        adapter.limpiarSeleccion();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Reseña eliminada", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String mensaje) {
                        // Si es 403, simplemente avisamos, pero no quitamos la reseña de la lista
                        Toast.makeText(getContext(), "No puedes eliminar esta reseña", Toast.LENGTH_SHORT).show();
                        adapter.limpiarSeleccion();
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}