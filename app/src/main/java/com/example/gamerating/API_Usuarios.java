package com.example.gamerating;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class API_Usuarios {

    public interface ApiCallback {
        void onSuccess();
        void onError(String mensaje);
    }

    public void subirUsuarios(String nombre, String correo, String contrasena, String imagen, ApiCallback callback) {
        new Thread(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/usuarios");
                HttpURLConnection con = (HttpURLConnection) url.openConnection(); // abrir conexion
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true); // voy a escribir en el body

                JSONObject json = new JSONObject();
                json.put("nombre", nombre);
                json.put("correo", correo);
                json.put("contrasena", contrasena);
                json.put("imagen", imagen);
                System.out.println(json);

                // Enviar JSON
                try (OutputStream os = con.getOutputStream()) {
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                }

                // Obtener código de respuesta
                int code = con.getResponseCode();
                Log.i("CODIGO APIREST", "EL CODIGO RESTANTE ES " + code);

                // Leer respuesta del servidor para depuración
                InputStream is = (code >= 200 && code < 300) ? con.getInputStream() : con.getErrorStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                Log.i("RESPUESTA API", response.toString());

                // Ejecutar callback en hilo principal
                Handler mainHandler = new Handler(Looper.getMainLooper());
                if (code == 200 || code == 201) {
                    mainHandler.post(callback::onSuccess);
                } else {
                    String mensajeError = "Error del servidor: " + code + " - " + response.toString();
                    mainHandler.post(() -> callback.onError(mensajeError));
                }

            } catch (Exception e) {
                // Ejecutar callback en hilo principal
                new Handler(Looper.getMainLooper()).post(() -> callback.onError(e.getMessage()));
            }
        }).start();
    }
}

