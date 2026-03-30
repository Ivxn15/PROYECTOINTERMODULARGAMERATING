package com.example.gamerating;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class InicioSesion extends AppCompatActivity {

    Button tengoCuenta;
    Button iniciasesion;
    EditText correooo;
    EditText contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.iniciosesion);

        tengoCuenta = findViewById(R.id.buttonTengoCuenta);
        iniciasesion = findViewById(R.id.buttonIniciaSesion);
        correooo = findViewById(R.id.editTextTextEmailAddress2);
        contrasena = findViewById(R.id.editTextTextPassword);

        cambioDeActividad();
        iniciarSesion();
    }

    // Ir al registro
    private void cambioDeActividad() {
        tengoCuenta.setOnClickListener(v -> {
            Intent intent = new Intent(InicioSesion.this, RegistroAPPMain.class);
            startActivity(intent);
        });
    }

    // Inicio de SESION
    private void iniciarSesion() {
        iniciasesion.setOnClickListener(v -> {

            String correo = correooo.getText().toString().trim();
            String pass = contrasena.getText().toString().trim();

            if (correo.isEmpty() || pass.isEmpty()) {
                Toast.makeText(InicioSesion.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            API_Usuarios api = new API_Usuarios();

            // Nuevo callback con idGenerado
            api.login(correo, pass, new API_Usuarios.ApiCallback() {
                @Override
                public void onSuccess(int idGenerado) {
                    // Guardamos el ID del usuario en sesión
                    Sesion.usuarioId = idGenerado;

                    Toast.makeText(InicioSesion.this, "Login correcto", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(InicioSesion.this, Principal.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onError(String mensaje) {
                    Toast.makeText(InicioSesion.this, mensaje, Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}