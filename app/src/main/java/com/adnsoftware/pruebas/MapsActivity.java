package com.adnsoftware.pruebas;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.kobjects.util.Util;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if( Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMyLocationEnabled(true); //Habilitamos el dispositivo para ser ubicado
        android.location.Location objUbicacion = map.getMyLocation();
        if( objUbicacion != null)
        {
            double dblLatActual = objUbicacion.getLatitude();
            double dblLongActual = objUbicacion.getLongitude();

            LatLng objUbActual = new LatLng(dblLatActual, dblLongActual);
            map.addMarker(new MarkerOptions().position(objUbActual).title("."));
            map.moveCamera(CameraUpdateFactory.newLatLng(objUbActual));
        }else
        {
                //Ubicacion actual no encontrada valide que tenga activa la ubicacion
            //Asigno una por defecto porque me dio la gana
            double dblLatActual = 4.656;
            double dblLongActual = -74.056;


            InvocarServicio objLLamadaServicio = new InvocarServicio();
            //Obtenemos la ifnormacion del servicio
            String strRespuestaServicio = objLLamadaServicio.ObtenerUbicacionAmigos("1");
            if( strRespuestaServicio != "")
            {
                LatLng objUbActual = new LatLng(dblLatActual, dblLongActual);
                map.addMarker(new MarkerOptions().position(objUbActual).title(strRespuestaServicio));
                map.moveCamera(CameraUpdateFactory.newLatLng(objUbActual));

                //Cargamos la informacion obtenida del servicio en un arreglo
                Utilidades objUtilidades = new Utilidades();
                try {
                    ArrayList<HashMap<String, String>> arrInfoXml  = objUtilidades.ObtenerDatosXml("LATITUD,LOGITUD,CONTACTO","<?xml version='1.0' encoding='UTF-8'?>" +strRespuestaServicio);
                    if( arrInfoXml.toArray().length > 0 )
                    {
                        Contacto arrContactos[] = new Contacto[arrInfoXml.toArray().length];
                        for( int i =0; i<arrInfoXml.toArray().length;i++)
                        {
                            HashMap<String, String> objContacto = arrInfoXml.get(i);
                            Contacto contacto = new Contacto(objContacto.get(Utilidades.CONTACTO_NOMBRE).toString(), Double.parseDouble( objContacto.get(Utilidades.CONTACTO_LATITUD).toString()), Double.parseDouble(objContacto.get(Utilidades.CONTACTO_LONGITUD).toString()), "") ;
                            arrContactos[i] = contacto;

                            objUbActual = new LatLng(contacto.GetLongitud(), contacto.GetLatitud());
                            map.addMarker(new MarkerOptions().position(objUbActual).title(contacto.GetNombreContacto()));
                            map.moveCamera(CameraUpdateFactory.newLatLng(objUbActual));

                        }


                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }

            }




        }




    }
/*
    public class AsynCallSoap extends AsyncTask<String, Void , String>
    {
        private final ProgressDialog dialogo = new ProgressDialog(MapsActivity.this );
        @Override
        protected String doInBackground(String... params) {
            InvocarServicio objLLamadaServicio = new InvocarServicio();
            String strRespuestaServicio = objLLamadaServicio.ObtenerUbicacionAmigos("1");
            return strRespuestaServicio;
        }

        @Override
        protected void onPostExecute(String strResultado) {
            super.onPostExecute(strResultado);
            dialogo.dismiss();

        }
    }
*/
}