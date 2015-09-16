package com.adnsoftware.pruebas;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.kobjects.util.Util;

/**
 * Created by Norman on 31/08/2015.
 */
public class NoticiasActividad extends Activity
{
    private List<Noticia> arrNoticias = new ArrayList<Noticia>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if( Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_frmnoticias_cma);
        CargarNoticias();
        CargarControlNoticias();
    }


    private void CargarNoticias() {
        //Nos conectamos al servicio web
        Noticia objNoticia = new Noticia();
        Noticia[]arrObjNoticia = objNoticia.ObtenerListadoNoticiasActivas();
        // Cargamos el objeto de noticias
       for (Noticia noticia:arrObjNoticia)
       {
           arrNoticias.add(noticia);
       }

    }


    private void CargarControlNoticias() {
        ArrayAdapter<Noticia> adapNoticias = new ListaNoticias();
        ListView lvwNoticia = (ListView) findViewById(R.id.lvwNoticias);
        lvwNoticia.setAdapter(adapNoticias);
    }


    private class ListaNoticias extends ArrayAdapter<Noticia>
    {
        public ListaNoticias()
        {
            super(NoticiasActividad.this,R.layout.itvw_noticia, arrNoticias );

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemNoticia = convertView;
            if(itemNoticia == null)
            {
                itemNoticia = getLayoutInflater().inflate(R.layout.itvw_noticia, parent,false);
            }

            Noticia objNoticiaActual = arrNoticias.get(position);

            //Llenamos la vista con el objeto
            ImageView imgNoticia = (ImageView)itemNoticia.findViewById(R.id.imgNoticia);
            Bitmap bmpImg = new Utilidades().ObtenerImagenUrl(objNoticiaActual.ImagenNoticia());
            imgNoticia.setImageBitmap(bmpImg);

            TextView lblTitulo = (TextView) itemNoticia.findViewById(R.id.lblTituloNoticia);
            lblTitulo.setText(objNoticiaActual.TituloNoticia());

            TextView lblDescripcion = (TextView) itemNoticia.findViewById(R.id.lblDetalleNoticia);
            lblDescripcion.setText(objNoticiaActual.DetalleResumenNoticia());

            return itemNoticia;
        }
    }
}
