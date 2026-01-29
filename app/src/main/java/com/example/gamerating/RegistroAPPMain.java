package com.example.gamerating;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
        imageButton.setOnClickListener(v -> CogerImagen()); //LLamo a la funcion cogerImagen para que cuando presione el boton me deje escoger imagen de la galeria
        ColocarAvatar();
        inicioApp();





    }



    private void inicioApp(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroAPPMain.this,Principal.class);
                startActivity(intent);
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

}