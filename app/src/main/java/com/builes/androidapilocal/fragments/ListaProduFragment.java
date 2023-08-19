package com.builes.androidapilocal.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.builes.androidapilocal.R;
import com.builes.androidapilocal.adaptadores.AdaptadorProductos;
import com.builes.androidapilocal.api.ApiLocal;
import com.builes.androidapilocal.functions.Funciones;

import org.json.JSONArray;

public class ListaProduFragment extends Fragment {
    Button btnVolverListaProdu;
    RecyclerView recyListProdu;
    public ListaProduFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_lista_produ, container, false);
        btnVolverListaProdu=vista.findViewById(R.id.btnVolverListaProdu);
        recyListProdu=vista.findViewById(R.id.recyListProdu);
        recyListProdu.setLayoutManager(new LinearLayoutManager(getContext()));
        //cargar productos
        Funciones.enviarPeticionGet(getContext(), ApiLocal.urlListProdu,
        (statusCode, respuesta) -> {
            JSONArray array= new JSONArray(respuesta);
            AdaptadorProductos adapter = new AdaptadorProductos(getContext(),array);
            recyListProdu.setAdapter(adapter);
       },(statusCode, error) -> {
            Toast.makeText(getContext(), ""+error.toString(), Toast.LENGTH_SHORT).show();
        });
        //evento para volver al inicio
        btnVolverListaProdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return vista;
    }
}