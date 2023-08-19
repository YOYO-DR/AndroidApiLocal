package com.builes.androidapilocal.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.builes.androidapilocal.R;
import com.builes.androidapilocal.functions.Funciones;


public class MainFragment extends Fragment {
    Button btnListarProdu,btnIngresarProdu,btnListarMarca,btnIngresarMarca;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_main, container, false);
        btnListarProdu=vista.findViewById(R.id.btnListar);
        btnIngresarProdu=vista.findViewById(R.id.btnIngresar);

        btnListarProdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cargar fragmento de la lista de productos
                Fragment fragListaProdu=new ListaProduFragment();
                FragmentManager fragListaProduManager=getActivity().getSupportFragmentManager();
                Funciones.crearFrag(fragListaProduManager,fragListaProdu,container.getId());
            }
        });

        return vista;
    }
}