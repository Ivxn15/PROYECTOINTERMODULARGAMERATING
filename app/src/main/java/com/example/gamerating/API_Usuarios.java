package com.example.gamerating;

import android.os.Handler;
import android.os.Looper;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class API_Usuarios {

    public interface ApiCallback {
        void onSuccess(int idGenerado);
        void onError(String mensaje);
    }

    public interface ResenasCallback {
        void onSuccess(List<Resena> resenas);
        void onError(String mensaje);
    }

    // INICIO DE seSION
    public void login(String correo, String contrasena, ApiCallback callback) {
        new Thread(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/usuarios/login");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);

                JSONObject json = new JSONObject();
                json.put("correo", correo);
                json.put("contrasena", contrasena);

                OutputStream os = con.getOutputStream();
                os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                os.close();

                int code = con.getResponseCode();
                InputStream is = (code >= 200 && code < 300) ? con.getInputStream() : con.getErrorStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) response.append(line);

                Handler main = new Handler(Looper.getMainLooper());
                if (code == 200) {
                    JSONObject obj = new JSONObject(response.toString());
                    int id = obj.getInt("id");
                    Sesion.usuarioId = id;
                    main.post(() -> callback.onSuccess(id));
                } else main.post(() -> callback.onError("Login incorrecto"));
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> callback.onError(e.getMessage()));
            }
        }).start();
    }

    // REGISTRo
    public void subirUsuarios(String nombre, String correo, String contrasena, ApiCallback callback) {
        new Thread(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/usuarios");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);

                JSONObject json = new JSONObject();
                json.put("nombre", nombre);
                json.put("correo", correo);
                json.put("contrasena", contrasena);

                OutputStream os = con.getOutputStream();
                os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                os.close();

                int code = con.getResponseCode();
                InputStream is = (code >= 200 && code < 300) ? con.getInputStream() : con.getErrorStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) response.append(line);

                Handler main = new Handler(Looper.getMainLooper());

                if (code == 200 || code == 201) {
                    JSONObject obj = new JSONObject(response.toString());
                    int idGenerado = obj.getInt("id");  // ESTE ES EL ID REAL
                    main.post(() -> callback.onSuccess(idGenerado));
                } else {
                    main.post(() -> callback.onError("Error registro: " + code));
                }

            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> callback.onError(e.getMessage()));
            }
        }).start();
    }

    // CREAR RESENA

    public void crearResena(int usuarioId, String juego, int puntuacion, String comentario, String fecha, ApiCallback callback) {
        new Thread(() -> {
            try {

                URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/usuarios/resenas");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);

                JSONObject json = new JSONObject();
                json.put("usuario_id", usuarioId);
                json.put("juego", juego);
                json.put("puntuacion", puntuacion);
                json.put("comentario", comentario);
                json.put("fecha", fecha);

                OutputStream os = con.getOutputStream();
                os.write(json.toString().getBytes(StandardCharsets.UTF_8));
                os.close();

                int code = con.getResponseCode();

                InputStream is = (code >= 200 && code < 300) ? con.getInputStream() : con.getErrorStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String response = br.readLine();

                Handler main = new Handler(Looper.getMainLooper());

                if (code == 200 || code == 201) {
                    int idGenerado = Integer.parseInt(response);
                    main.post(() -> callback.onSuccess(idGenerado));
                } else {
                    main.post(() -> callback.onError("Error crear reseña: " + code + " - " + response));
                }

            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> callback.onError(e.getMessage()));
            }
        }).start();
    }

    // ELIMINAR RESENA
    public void eliminarResena(int id, ApiCallback callback) {
        new Thread(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/usuarios/resenas/" + id + "?usuarioId=" + Sesion.usuarioId);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("DELETE");

                int code = con.getResponseCode();
                Handler main = new Handler(Looper.getMainLooper());
                if (code == 200) main.post(() -> callback.onSuccess(id));
                else main.post(() -> callback.onError("Error eliminar: " + code));
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> callback.onError(e.getMessage()));
            }
        }).start();
    }

    // OBTENER RESENAS
    public void getResenasUsuario(int usuarioId, ResenasCallback callback) {
        new Thread(() -> {
            try {

                URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/usuarios/usuarios/" + usuarioId + "/resenas");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                int code = con.getResponseCode();
                InputStream is = (code >= 200 && code < 300) ? con.getInputStream() : con.getErrorStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) response.append(line);

                Handler main = new Handler(Looper.getMainLooper());
                if (code == 200) {
                    JSONArray array = new JSONArray(response.toString());
                    List<Resena> resenas = new ArrayList<>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        int id = obj.getInt("id");
                        String juego = obj.getString("juego");
                        int puntuacion = obj.getInt("puntuacion");
                        String comentario = obj.getString("comentario");
                        String fecha = obj.getString("fecha");
                        String nombre = obj.getString("nombre");
                        resenas.add(new Resena(id, usuarioId, juego, puntuacion, comentario, fecha,nombre));
                    }
                    main.post(() -> callback.onSuccess(resenas));
                } else main.post(() -> callback.onError("Error cargar reseñas: " + code));
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> callback.onError(e.getMessage()));
            }
        }).start();
    }
    //Obtener todas las reseñas para mostrarlas a todos los usuarios
    public void getTodasResenas(ResenasCallback callback) {
        new Thread(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8080/tema5maven/rest/usuarios/resenas/todas");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                int code = con.getResponseCode();

                InputStream is = (code >= 200 && code < 300) ? con.getInputStream() : con.getErrorStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) response.append(line);

                Handler main = new Handler(Looper.getMainLooper());

                if (code == 200) {
                    JSONArray array = new JSONArray(response.toString());
                    List<Resena> resenas = new ArrayList<>();

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);

                        int id = obj.getInt("id");
                        int usuarioId = obj.getInt("usuario_id");
                        String juego = obj.getString("juego");
                        int puntuacion = obj.getInt("puntuacion");
                        String comentario = obj.getString("comentario");
                        String fecha = obj.getString("fecha");
                        String nombre = obj.getString("nombre");

                        resenas.add(new Resena(id, usuarioId, juego, puntuacion, comentario, fecha, nombre));
                    }

                    main.post(() -> callback.onSuccess(resenas));
                } else {
                    main.post(() -> callback.onError("Error: " + code));
                }

            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> callback.onError(e.getMessage()));
            }
        }).start();
    }
}
