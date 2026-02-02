package com.example.gamerating;

import android.util.Log;
import android.widget.ImageView;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class API_Usuarios {
    public void subirUsuarios(String nombre, String correo, String contraseña, ImageView perfil){
        new Thread(()->{
            try{
                URL url = new URL("http://192.130.0.15:8000/apismavenproyecto/rest/usuarios/android");
                HttpURLConnection con = (HttpURLConnection) url.openConnection(); //abrir conexion
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type","application/json");
                con.setDoOutput(true); //voy a escribir en el body
                JSONObject json = new JSONObject();
                json.put("nombre",nombre);
                json.put("correo",correo);
                json.put("contraseña",contraseña);
                json.put("perfil",perfil);
                System.out.println(json);

                try(OutputStream os = con.getOutputStream()){
                    os.write(json.toString().getBytes(StandardCharsets.UTF_8));//enviar el body
                }catch (IOException e){

                }
                int code = con.getResponseCode();//forzar envio
                Log.i("CODIGO APIREST","EL CODIGO RESTANTE ES " + code);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }).start();
    }
}
