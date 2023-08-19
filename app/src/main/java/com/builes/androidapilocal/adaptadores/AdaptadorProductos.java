package com.builes.androidapilocal.adaptadores;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.builes.androidapilocal.R;
import com.builes.androidapilocal.api.ApiLocal;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolder> {
    JSONArray jsonArray;
    Context context;
    public AdaptadorProductos (Context context,JSONArray jsonArray ){
        this.context = context;
        this.jsonArray=jsonArray;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        //atributos
        ImageView imagenProdu;
        TextView tvNombreProdu,tvValorUni;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //relacion de los atributos con la vista
            imagenProdu=itemView.findViewById(R.id.ivProdu);
            tvNombreProdu=itemView.findViewById(R.id.tvNombreProdu);
            tvValorUni=itemView.findViewById(R.id.tvValorUni);
        }
    }

    @NonNull
    @Override
    public AdaptadorProductos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_productos,null);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorProductos.ViewHolder holder, int position) {
        //colocar los datos del json a cada elemento o vista
        try {
            holder.tvNombreProdu.setText(jsonArray.getJSONObject(position).get("nombre").toString());
            holder.tvValorUni.setText(jsonArray.getJSONObject(position).get("vrUnitario").toString());
            //holder.imagenUser.setImageResource(R.mipmap.ic_launcher);
            String url= ApiLocal.urlResProdu +jsonArray.getJSONObject(position).get("imagen");
            //con picasso le paso el contexto, con el load la url, y en el into el imagenView
            Log.e("image",url);
            Picasso.with(this.context).load(url).into(holder.imagenProdu);
        }catch (JSONException e){
            //error no se pudo obtener el json
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }


}
