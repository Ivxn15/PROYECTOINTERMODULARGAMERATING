package com.example.gamerating;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InicioAnadirJuegosFragment extends Fragment {

    EditText etJuego, etPuntuacion, etComentario;
    Button btnAgregar, btnCerrarSesion;

    public InicioAnadirJuegosFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio_anadir_juegos, container, false);

        etJuego = view.findViewById(R.id.etJuego);
        etPuntuacion = view.findViewById(R.id.etPuntuacion);
        etComentario = view.findViewById(R.id.etComentario);
        btnAgregar = view.findViewById(R.id.btnAgregar);
        btnCerrarSesion = view.findViewById(R.id.btnCerrarSesion);

        btnAgregar.setOnClickListener(v -> agregarResena());
        btnCerrarSesion.setOnClickListener(v -> cerrarSesion());

        return view;
    }

    private void agregarResena() {

        String juego = etJuego.getText().toString().trim();
        String comentario = etComentario.getText().toString().trim();
        int puntuacion;

        try {
            puntuacion = Integer.parseInt(etPuntuacion.getText().toString().trim());
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Puntuación inválida", Toast.LENGTH_SHORT).show();
            return;
        }

        if (puntuacion < 0 || puntuacion > 10) {
            Toast.makeText(getContext(), "La puntuación debe estar entre 0 y 10", Toast.LENGTH_SHORT).show();
            return;
        }

        if (juego.isEmpty() || comentario.isEmpty()) {
            Toast.makeText(getContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        API_Usuarios api = new API_Usuarios();

        api.crearResena(
                Sesion.usuarioId,
                juego,
                puntuacion,
                comentario,
                fecha,
                new API_Usuarios.ApiCallback() {

                    @Override
                    public void onSuccess(int idGenerado) {


                        String nombreUsuario = "Usuario " + Sesion.usuarioId;

                        Resena resena = new Resena(
                                idGenerado,
                                Sesion.usuarioId,
                                juego,
                                puntuacion,
                                comentario,
                                fecha,
                                nombreUsuario
                        );

                        ResenaManager.agregarResena(resena);

                        Toast.makeText(getContext(), "Reseña guardada", Toast.LENGTH_SHORT).show();

                        etJuego.setText("");
                        etPuntuacion.setText("");
                        etComentario.setText("");
                    }

                    @Override
                    public void onError(String mensaje) {
                        Toast.makeText(getContext(), mensaje, Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private void cerrarSesion() {
        // Limpiar sesión
        //Sesion.usuarioId = 0;
        // Volver a pantalla de inicio de sesion
        Intent intent = new Intent(getContext(), InicioSesion.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(getContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show();
    }
}