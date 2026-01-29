package com.example.gamerating;
//https://rawg.io/login?forward=developer
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.principal);
        setupNavegacion();

        }
    private void setupNavegacion(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.botomNavigation);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_hostfragment);
        NavigationUI.setupWithNavController(
                bottomNavigationView,
                navHostFragment.getNavController()
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menuinicio,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.mjuegos){
            Intent intent = new Intent(Principal.this,Principal.class);
            startActivity(intent);
            return true;
        }else if (id==R.id.mreseñas){

            return true;
        }else if (id==R.id.mactividad){
            Toast.makeText(this, "Otro", Toast.LENGTH_SHORT).show();
            return true;
        }else if (id==R.id.mlistas){
            Toast.makeText(this, "Submenú 1", Toast.LENGTH_SHORT).show();
            return true;
        };
        return super.onOptionsItemSelected(item);
    }
}


