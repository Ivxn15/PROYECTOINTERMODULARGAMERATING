package com.example.gamerating;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistroAPPMain extends AppCompatActivity {
    EditText nombreSesion;
    EditText emailSesion;
    EditText contraseñaSesion;
    EditText confirmarcontraseñaSesion;
    ImageButton imageButton;
    Button button;
    ImageView fotoAvatar;
    API_Usuarios api;
    EditText imagenString;



    ActivityResultLauncher<Intent> resultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.registro);





        nombreSesion = findViewById(R.id.NombreInicio);

        emailSesion = findViewById(R.id.EmailInicio1);
        contraseñaSesion = findViewById(R.id.Contraseña1);
        confirmarcontraseñaSesion = findViewById(R.id.ConfirmaContraseña1);
        imageButton = findViewById(R.id.BotonImagen1);
        button = findViewById(R.id.registrarse);
        fotoAvatar = findViewById(R.id.Avatar);
        imagenString = findViewById(R.id.imagenString);
        api = new API_Usuarios();
        confirmarContraseña();
        ColocarAvatar();
        imageButton.setOnClickListener(v -> CogerImagen()); //LLamo a la funcion cogerImagen para que cuando presione el boton me deje escoger imagen de la galeria
        inicioApp();

    }



    private void inicioApp(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!confirmarContraseña()){
                    return;
                }
                api.subirUsuarios(nombreSesion.getText().toString(),emailSesion.getText().toString(),contraseñaSesion.getText().toString(),imagenString.getText().toString(),
                        new API_Usuarios.ApiCallback() { //Interfaz por el fallo de que la red va mas lenta que el codigo
                            @Override
                            public void onSuccess() {
                                // El servidor respondió bien. Ahora SÍ saltamos.
                                runOnUiThread(() -> {
                                    Intent intent = new Intent(RegistroAPPMain.this, Principal.class);
                                    startActivity(intent);
                                    finish(); // Cerramos registro para que no pueda volver
                                });
                            }

                            @Override
                            public void onError(String mensaje) {
                                Log.e("API_ERROR", mensaje);
                                runOnUiThread(() ->
                                        Toast.makeText(RegistroAPPMain.this, mensaje, Toast.LENGTH_LONG).show());
                            }
                        }
                );


            }
        });
    }
    //ABRIR GALERIA
    //Uso de actionGetContent que funciona para todas las versiones de movil
    private void CogerImagen() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            resultLauncher.launch(intent);

        }
        private void ColocarAvatar(){
            resultLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult o) {
                            try {
                                Uri imagenUri = o.getData().getData();
                                fotoAvatar.setImageURI(imagenUri);
                            }catch (Exception e){
                                Toast.makeText(RegistroAPPMain.this,"No se ha seleccionado ninguna imagen",Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
            );
        }
        private boolean  confirmarContraseña(){
        String contraseña = contraseñaSesion.getText().toString();
        String confirmaContraseña = confirmarcontraseñaSesion.getText().toString();
        if (!contraseña.equals(confirmaContraseña)){
            Toast.makeText(RegistroAPPMain.this,"La contraseña tiene que ser la misma",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            api.subirImagen(imageUri,this,"prueba");
            //iv.setImageURI(imageUri);
        }
    }



}