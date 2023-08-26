package com.builes.androidapilocal.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.builes.androidapilocal.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgregarProductoFragment extends Fragment {
    EditText etNombre,etPrecio,etDesc,etCodigo;
    Button btnCargaImagen,btnEnviar;
    ImageView imgFoto;
    Spinner spnMarcas;
    String directorioFoto;
    Bitmap bitmap;

    public AgregarProductoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_agregar_producto, container, false);
        etNombre=v.findViewById(R.id.etNombre);
        etPrecio=v.findViewById(R.id.etPrecio);
        etDesc=v.findViewById(R.id.etDesc);
        etCodigo=v.findViewById(R.id.etCodigo);
        btnCargaImagen=v.findViewById(R.id.btnCargaImagen);
        btnEnviar=v.findViewById(R.id.btnEnviarProdu);
        spnMarcas=v.findViewById(R.id.spnMarca);
        imgFoto=v.findViewById(R.id.ivAgreProdu);

        //tomar foto
        btnCargaImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCamara();
            }
        });
        return v;
    }
    //enviar peticion

    private void abrirCamara() {
        Intent intento= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imgFile=null;
        try {
            imgFile=crearImagen();
        }catch (IOException e){
            Log.e("Eror file",e.getMessage());
            Toast.makeText(getContext(), "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if (imgFile !=null){
            Uri uri = FileProvider.getUriForFile(getContext(),"com.builes.androidapilocal",imgFile);
            intento.putExtra(MediaStore.EXTRA_OUTPUT,uri);
            camaralauncher.launch(intento);
        }

    }
    ActivityResultLauncher<Intent> camaralauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            bitmap = BitmapFactory.decodeFile(directorioFoto);
            imgFoto.setImageBitmap(bitmap);
        }
    });

    private File crearImagen() throws IOException{
        File dirAlmacena=getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        String imgFileFecha=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imgNameFile="JPG_"+imgFileFecha;
        File imagen= File.createTempFile(imgNameFile,".jpg",dirAlmacena);
        directorioFoto=imagen.getAbsolutePath();
        return imagen;
    }
}