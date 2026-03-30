package com.example.gamerating;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroAPPMain extends AppCompatActivity {

    EditText nombreSesion, emailSesion, contraseñaSesion, confirmarcontraseñaSesion;
    Button button;

    API_Usuarios api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.registro);

        // UI
        nombreSesion = findViewById(R.id.NombreInicio);
        emailSesion = findViewById(R.id.EmailInicio1);
        contraseñaSesion = findViewById(R.id.Contraseña1);
        confirmarcontraseñaSesion = findViewById(R.id.ConfirmaContraseña1);
        button = findViewById(R.id.registrarse);

        api = new API_Usuarios();

        configurarBoton();
    }

    // Validar que las contraseñas coincidan
    private boolean validarContraseña() {
        String pass = contraseñaSesion.getText().toString();
        String confirm = confirmarcontraseñaSesion.getText().toString();

        if (!pass.equals(confirm)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Configurar botón de registro
    private void configurarBoton() {
        button.setOnClickListener(v -> {
            Log.d("CLICK", "BOTÓN PULSADO");

            String nombre = nombreSesion.getText().toString().trim();
            String email = emailSesion.getText().toString().trim();
            String pass = contraseñaSesion.getText().toString().trim();

            // Validaciones básicas
            if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!validarContraseña()) return;


            api.subirUsuarios(nombre, email, pass, new API_Usuarios.ApiCallback() {

                @Override
                public void onSuccess(int idGenerado) {
                    // Guardamos el ID del usuario en sesión
                    Sesion.usuarioId = idGenerado;

                    runOnUiThread(() -> {
                        Toast.makeText(RegistroAPPMain.this, "Registro correcto", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistroAPPMain.this, Principal.class);
                        startActivity(intent);
                        finish();
                    });
                }

                @Override
                public void onError(String mensaje) {
                    Log.e("API_ERROR", mensaje);
                    runOnUiThread(() ->
                            Toast.makeText(RegistroAPPMain.this, mensaje, Toast.LENGTH_LONG).show());
                }
            });
        });
    }
}
