package com.example.gamerating;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class InicioAnadirJuegosFragment extends Fragment {



    public InicioAnadirJuegosFragment() {
        // Required empty public constructor
    }



    public static InicioAnadirJuegosFragment newInstance(String param1, String param2) {
        InicioAnadirJuegosFragment fragment = new InicioAnadirJuegosFragment();

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
        return inflater.inflate(R.layout.fragment_inicio_anadir_juegos, container, false);
    }
}