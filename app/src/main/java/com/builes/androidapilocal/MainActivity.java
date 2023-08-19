package com.builes.androidapilocal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.builes.androidapilocal.fragments.MainFragment;
import com.builes.androidapilocal.functions.Funciones;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragMain=new MainFragment();
        FragmentManager fragMainManager = getSupportFragmentManager();
        Funciones.crearFrag(fragMainManager,fragMain,R.id.fragMain);
    }

}