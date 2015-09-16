package com.adnsoftware.pruebas;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.*;

/**
 * Created by Norman on 25/08/2015.
 */
public class Utilidades {

    static final String DATO_GEN_SERVICIO = "Table";

    static final String CONTACTO_LATITUD = "LATITUD";
    static final String CONTACTO_LONGITUD = "LOGITUD";
    static final String CONTACTO_NOMBRE = "CONTACTO";


    /**
     * Obtiene el valor contenido en un tag XML
     * @return el valor contenido en el tag especificado
     */
    public String getValue(Element item, String str) {
        NodeList n = item.getElementsByTagName(str);
        return this.getElementValue(n.item(0));
    }

    /**
     * Obtiene el el nodo completo de un elmento xml enviado
     * @return el nodo
     */
    public final String getElementValue( Node elem ) {
        Node child;
        if( elem != null){
            if (elem.hasChildNodes()){
                for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
                    if( child.getNodeType() == Node.TEXT_NODE  ){
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }

    /**
     * Obtiene un arreglo con cada uno de los elementos recibidos en el servicio estos son almacenados en un hasmap con el nombre de la columba
     * La tabla principal es "table"
     * los parametros configuracion columnas deben enviarse las columnas que son requeridas leer del xml separadas por ,
     * @return el valor contenido en el tag especificado
     */
    public  ArrayList<HashMap<String, String>> ObtenerDatosXml(String strConfiguracionColumnas, String strInfoXml) throws IOException, SAXException
    {
        //Las columnas se encuentran separadas por , para cargar de manera dinamica en el objeto los resultados
        String arrColumnasServicio[] = strConfiguracionColumnas.split(",");

        ArrayList<HashMap<String, String>> arrContactos = new ArrayList<HashMap<String, String>>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document docRecibido = builder.parse(new InputSource(new StringReader(strInfoXml)));

            NodeList nl = docRecibido.getElementsByTagName(DATO_GEN_SERVICIO);
            // Recorremos el arreglo de cada uno de los nodos contenidos en Table
            for (int i = 0; i < nl.getLength(); i++) {

                //Creamos hasmap donde van a ser ubicados los resultados del servicio
                HashMap<String, String> hmap = new HashMap<String, String>();
                Element e = (Element) nl.item(i);
                for(String strCOLUMNA : arrColumnasServicio )
                {
                    hmap.put(strCOLUMNA.trim(), this.getValue(e, strCOLUMNA.trim()));
                }
                //Adicionamos los datos respuesta del servicio
                arrContactos.add(hmap);
            }

            return arrContactos;


        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
            return null;
        }

    }


    public Bitmap ObtenerImagenUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return  BitmapFactory.decodeStream(input);

        } catch (IOException e) {
            e.printStackTrace();

            return null;
        }
    }

}
