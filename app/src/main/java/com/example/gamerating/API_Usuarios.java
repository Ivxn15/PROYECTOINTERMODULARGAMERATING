package com.example.gamerating;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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


                try (OutputStream os = con.getOutputStream()) {
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                }


                int code = con.getResponseCode();
                Log.i("CODIGO APIREST", "EL CODIGO RESTANTE ES " + code);


                InputStream is = (code >= 200 && code < 300) ? con.getInputStream() : con.getErrorStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                Log.i("RESPUESTA API", response.toString());


                Handler mainHandler = new Handler(Looper.getMainLooper());
                if (code == 200 || code == 201) {
                    mainHandler.post(callback::onSuccess);
                } else {
                    String mensajeError = "Error del servidor: " + code + " - " + response.toString();
                    mainHandler.post(() -> callback.onError(mensajeError));
                }

            } catch (Exception e) {

                new Handler(Looper.getMainLooper()).post(() -> callback.onError(e.getMessage()));
            }
        }).start();

    }
    public void subirImagen(Uri imageUri, Context context, String nombre) {
        new Thread(() -> {
            try {
                // Leer la imagen
                InputStream is = context.getContentResolver().openInputStream(imageUri);
                if (is == null) {
                    Log.e("UPLOAD", "InputStream nulo");
                    return;
                }
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, bytesRead);
                }
                is.close();
                String base64Imagen = Base64.encodeToString(
                        baos.toByteArray(),
                        Base64.NO_WRAP
                );
                // Crear JSON
                JSONObject json = new JSONObject();
                json.put("nombre", nombre);
                json.put("imagen", base64Imagen);
                // Conexión HTTP
                URL url = new URL("http://172.16.0.79:8080/tema5maven/rest/deportistas/imagen");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setConnectTimeout(15000);
                con.setReadTimeout(15000);
                con.setRequestProperty(
                        "Content-Type",
                        "application/json; charset=UTF-8"
                );
                con.connect();
                System.out.println("1");

                // Enviar JSON
                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = json.toString().getBytes(StandardCharsets.UTF_8);
                    os.write(input);
                    os.flush();
                }
                System.out.println("2");

                // Leer respuesta
                int code = con.getResponseCode();
                Log.i("API", "Código respuesta: " + code);
            } catch (Exception e) {
                Log.e("UPLOAD", "Error al subir imagen", e);
            }
        }).start();
    }

}

