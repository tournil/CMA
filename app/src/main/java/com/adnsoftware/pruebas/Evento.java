package com.adnsoftware.pruebas;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Norman on 02/09/2015.
 */
public class Evento {

    private String dtmFechaEvento;
    private String strNombreEvento;
    private String strDescripcionEvento;
    private String strUrlEvento;
    private String strUrlImagenEvento;
    private String strIdEvento;
    public static String COLUMNAS_EVENTOS = "dtmFechaEvento,strNombreEvento,strDescripcionEvento,strUrlEvento,strUrlImagenEvento";
    public static String COL_FECHA_EVENTO = "dtmFechaEvento";
    public static String COL_NOMBRE_EVENTO = "strNombreEvento";
    public static String COL_DESCRIPCION_EVENTO = "strDescripcionEvento";
    public static String COL_URL_EVENTO = "strUrlEvento";
    public static String COL_IMAGEN_EVENTO = "strUrlImagenEvento";

    public String FechaEvento() {return dtmFechaEvento;}
    public String NombreEvento() {return strNombreEvento;}
    public String DescripcionEvento() {
        return strDescripcionEvento;
    }
    public String UrlEvento() {
        return strUrlEvento;
    }
    public String UrlImagenEvento() {
        return strUrlImagenEvento;
    }

    public Evento(){}

    public Evento(String _strIdEvento, String _strNombreEvento, String _strDescripcionEvento, String _strUrlEvento, String _strImagenEvento, String _strFechaEvento)
    {
           strIdEvento = _strIdEvento;
        strNombreEvento = _strNombreEvento;
        strDescripcionEvento = _strDescripcionEvento;
        strUrlEvento = _strUrlEvento;
        strUrlImagenEvento = _strImagenEvento;
        dtmFechaEvento = _strFechaEvento;
    }


    public Evento[] ObtenerEventos()
    {
        InvocarServicio objDatos = new InvocarServicio();
        String strRespuestaServicio  = objDatos.EjecutarMetodo("ListarEventos",null);
        if( strRespuestaServicio != "")
        {
            Utilidades objUtilidades = new Utilidades();
            try {
                ArrayList<HashMap<String, String>> arrInfoXml  = objUtilidades.ObtenerDatosXml(this.COLUMNAS_EVENTOS, strRespuestaServicio);
                if( arrInfoXml.toArray().length > 0 )
                {
                    Evento arrEventos[] = new Evento[arrInfoXml.toArray().length];
                    for( int i =0; i<arrInfoXml.toArray().length;i++)
                    {
                        HashMap<String, String> objXmlEvento= arrInfoXml.get(i);
                        String strDescripcionEvento = "";
                        if( objXmlEvento.get(this.COL_DESCRIPCION_EVENTO) != null) {
                            strDescripcionEvento = objXmlEvento.get(this.COL_DESCRIPCION_EVENTO).toString();
                        }

                        String strImagenEvento = "" ;
                        if( objXmlEvento.get(this.COL_IMAGEN_EVENTO) != null) {
                            strImagenEvento = objXmlEvento.get(this.COL_IMAGEN_EVENTO).toString();
                        }

                        String strNombreEvento = "";
                        if( objXmlEvento.get(this.COL_NOMBRE_EVENTO) != null) {
                            strNombreEvento = objXmlEvento.get(this.COL_NOMBRE_EVENTO).toString();
                        }


                        String strUrlEvento = "" ;
                        if( objXmlEvento.get(this.COL_URL_EVENTO) != null) {
                            strUrlEvento = objXmlEvento.get(this.COL_URL_EVENTO).toString();
                        }

                        String strFechaEvento = null ;
                        if( objXmlEvento.get(this.COL_FECHA_EVENTO) != null) {
                            strFechaEvento = objXmlEvento.get(this.COL_FECHA_EVENTO).toString();
                        }

                        Evento evento = new Evento(strIdEvento
                                , strNombreEvento
                                ,strDescripcionEvento
                                ,strUrlEvento
                                ,strImagenEvento
                                ,strFechaEvento);

                        arrEventos[i] = evento;

                    }
                    return arrEventos;


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
