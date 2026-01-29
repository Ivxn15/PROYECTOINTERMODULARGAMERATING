package com.example.gamerating;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
}