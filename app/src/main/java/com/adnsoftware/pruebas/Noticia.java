package com.adnsoftware.pruebas;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Norman on 31/08/2015.
 */
public class Noticia {


    private String strTituloNoticia;
    private String strImagenNoticia;
    private String strDetalleNoticiaResumen;
    private String strDetalleNoticia;
    private String strFechaPublicacion;
    private String strIdNoticia;
    public static String COLUMNAS_NOTICIAS = "IdNoticia, strImagenNoticia, strTituloNoticia,strDetalleNoticia,dtmFechaPublicacion";
    public static String COL_ID_NOTICIA = "IdNoticia";
    public static String COL_IMAGEN_NOTICIA = "strImagenNoticia";
    public static String COL_DETALLE_NOTICIA = "strDetalleNoticia";
    public static String COL_FECHA_PUB_NOTICIA = "dtmFechaPublicacion";
    public static String COL_TITULO_NOTICIA = "strTituloNoticia";



    public String TituloNoticia() {
        return strTituloNoticia;
    }

    public String ImagenNoticia() {
        return strImagenNoticia;
    }

    public String DetalleResumenNoticia() {
        return strDetalleNoticiaResumen;
    }

    public String DetalleNoticia() {
        return strDetalleNoticia;
    }

    public String FechaPublicacion() {
        return strFechaPublicacion;
    }

    public String IdNoticia() {
        return strIdNoticia;
    }


    public Noticia(){}

    public Noticia(String _strIdNoticia, String _strImagenNoticia, String _strTitulo, String _strDetalle, String _strFechaPublicacion)
    {
        strIdNoticia = _strIdNoticia;
        strImagenNoticia = _strImagenNoticia;
        strDetalleNoticia= _strDetalle;
        strDetalleNoticiaResumen = _strDetalle;
        if( _strDetalle.length() > 70) {
               strDetalleNoticiaResumen = _strDetalle.substring(0,70);
        }
        strTituloNoticia = _strTitulo;
        if( _strTitulo.length() > 40) {
            strTituloNoticia = _strTitulo.substring(0,40);
        }

    }



    public Noticia[] ObtenerListadoNoticiasActivas()
    {
        InvocarServicio objDatos = new InvocarServicio();
        String strRespuestaServicio  = objDatos.EjecutarMetodo("ListarNoticias",null);
        if( strRespuestaServicio != "")
        {
            Utilidades objUtilidades = new Utilidades();
            try {
                ArrayList<HashMap<String, String>> arrInfoXml  = objUtilidades.ObtenerDatosXml(this.COLUMNAS_NOTICIAS, strRespuestaServicio);
                if( arrInfoXml.toArray().length > 0 )
                {
                    Noticia arrNoticias[] = new Noticia[arrInfoXml.toArray().length];
                    for( int i =0; i<arrInfoXml.toArray().length;i++)
                    {
                        HashMap<String, String> objxmlNoticia = arrInfoXml.get(i);
                        String strIdNoticia = objxmlNoticia.get(this.COL_ID_NOTICIA).toString();
                        String strTituloNoticia = "";
                        if( objxmlNoticia.get(this.COL_TITULO_NOTICIA) != null) {
                            strTituloNoticia = objxmlNoticia.get(this.COL_TITULO_NOTICIA).toString();
                        }
                        String strImagenNoticia = "" ;
                        if( objxmlNoticia.get(this.COL_IMAGEN_NOTICIA) != null) {
                            strImagenNoticia = objxmlNoticia.get(this.COL_IMAGEN_NOTICIA).toString();
                        }

                        String strDetalleNoticia = "";
                        if( objxmlNoticia.get(this.COL_DETALLE_NOTICIA) != null) {
                            strDetalleNoticia = objxmlNoticia.get(this.COL_DETALLE_NOTICIA).toString();
                        }

                        Noticia noticia = new Noticia(strIdNoticia
                                                    , strImagenNoticia
                                                    ,strTituloNoticia
                                                    ,strDetalleNoticia
                                                    ,objxmlNoticia.get(this.COL_FECHA_PUB_NOTICIA).toString());

                        arrNoticias[i] = noticia;

                    }
                    return arrNoticias;


                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }

        }

        return null;

    }

}