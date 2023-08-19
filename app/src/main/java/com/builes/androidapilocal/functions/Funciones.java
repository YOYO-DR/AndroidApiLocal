package com.builes.androidapilocal.functions;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;

import cz.msebera.android.httpclient.Header;

public class Funciones {
    public static void crearFrag(FragmentManager fragManager, Fragment frag, int container) {
        FragmentTransaction fragTransa = fragManager.beginTransaction();
        fragTransa.replace(container, frag);
        fragTransa.commit();
    }
    //creo estas 2 interfaces funcionales que van a sert como la ejecucion de las funciones que pase como parametro
    @FunctionalInterface
    public interface SuccessFunction {
        void apply(int statusCode, String respuesta) throws JSONException;
    }

    @FunctionalInterface
    public interface FailureFunction {
        void apply(int statusCode, Throwable error);
    }
    //las paso como parametro en esta funcion y las recibira como funciones lambda y asi se ejecutaran cuando las llame en el onSuccess o onFailure, solo cuando se complete la peticion
    public static void enviarPeticionPost(Context context, String url, RequestParams parametros, SuccessFunction success, FailureFunction failure) {
        AsyncHttpClient httpCliente=new AsyncHttpClient();

        httpCliente.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String respuesta = new String(responseBody);
                try {
                    success.apply(statusCode, respuesta);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                failure.apply(statusCode, error);
            }
        });
    }
    public static void enviarPeticionGet(Context context, String url, SuccessFunction success, FailureFunction failure) {
        AsyncHttpClient httpCliente=new AsyncHttpClient();
        httpCliente.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String respuesta = new String(responseBody);
                try {
                    success.apply(statusCode, respuesta);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                failure.apply(statusCode, error);
            }
        });
    }
}
