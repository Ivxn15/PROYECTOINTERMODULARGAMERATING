package com.example.gamerating;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class InicioActividadAmigosFragment extends Fragment {



    public InicioActividadAmigosFragment() {
        // Required empty public constructor
    }


    public static InicioActividadAmigosFragment newInstance(String param1, String param2) {
        InicioActividadAmigosFragment fragment = new InicioActividadAmigosFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio_actividad_amigos, container, false);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) { //En fragments el onCreateOptionsMenu se hace asi
        inflater.inflate(R.menu.menuactividad, menu);
    }

    private MenuInflater getMenuInflater() {
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.yo){

            return true;
        }else if (id==R.id.amigos){

            return true;

        };
        return super.onOptionsItemSelected(item);
    }
}
