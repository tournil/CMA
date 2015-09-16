package com.adnsoftware.pruebas;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Norman on 25/08/2015.
 */
public class Contacto {
    private String strNombreContacto;
    private double dblLongitud;
    private double dblLatitud;
    private String strDescripcion;

    public Contacto()
    {

    }

    public Contacto(String strNombreContact, double dblLatit, double dblLong, String strDescrip)
    {
        strNombreContacto = strNombreContact;
        dblLongitud = dblLong;
        dblLatitud = dblLatit;
        strDescripcion = strDescrip;
    }

    public String GetNombreContacto()
    {
        return strNombreContacto;
    }

    public String GetDescripcion()
    {
        return strDescripcion;
    }

    public double GetLongitud()
    {
        return dblLongitud;
    }


    public double GetLatitud()
    {
        return dblLatitud;
    }

    public void SetNombreContacto(String strNombre)
    {
        strNombreContacto = strNombre;
    }


    public void SetDescripcion(String strDescr)
    {
        strDescripcion = strDescr;
    }

    public void SetLongitud(double dlong)
    {
        dblLongitud = dlong;
    }

    public void SetLatitud(double dlat)
    {
        dblLatitud = dlat;
    }



}
