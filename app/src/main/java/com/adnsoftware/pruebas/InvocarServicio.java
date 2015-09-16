package com.adnsoftware.pruebas;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Norman on 24/08/2015.
 */
public class InvocarServicio {
    public String ObtenerUbicacionAmigos(String strIdMovil)
    {
        String SOAP_ACCION = "http://adnsoftware.com.co/webservices/ObtenerUbicaciones";
        String NOMBRE_OPERACION = "ObtenerUbicaciones";
        String WSDL_NAMESPACE ="http://adnsoftware.com.co/webservices/";

        String DIRECCION_SERVICIO= "http://200.116.47.232/wsPruebaAndroid/Service1.asmx";

        //Declaramos el servicio usando la libreria de conexion a servicios Web
        SoapObject objSolicitud = new SoapObject(WSDL_NAMESPACE,NOMBRE_OPERACION);

        //Declaramos la propiedad para enviar parametros
        PropertyInfo propiedadesServicio = new PropertyInfo();
        propiedadesServicio.setName("strIdMovil");
        propiedadesServicio.setValue(strIdMovil);

        //Adicionamos propuedades al servicio
        objSolicitud.addProperty(propiedadesServicio);

        SoapSerializationEnvelope wsEntorno =new SoapSerializationEnvelope(SoapEnvelope.VER11);
        wsEntorno.dotNet = true;
        wsEntorno.setOutputSoapObject(objSolicitud);

        String strRespuesta = null;
        try
        {
            HttpTransportSE objServicio = new HttpTransportSE(DIRECCION_SERVICIO);
           // objServicio.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?\>");
            objServicio.call(SOAP_ACCION, wsEntorno);
            SoapObject result = (SoapObject)wsEntorno.bodyIn;

            if(result != null)
            {
                //Get the first property and change the label text
                strRespuesta = result.getProperty(0).toString();
            }
            else
            {
                strRespuesta = "El servicio no responde";
            }


        } catch(Exception ex)
        {
            strRespuesta = "Falla en la conexion "+ ex.toString();

        }
        return strRespuesta;

    }


    public String EjecutarMetodo(String ACCION_METODO,PropertyInfo []arrParametrosMetodo)
    {
        String SOAP_ACCION = "http://adnsoftware.com.co/webservices/"+ACCION_METODO;
        String NOMBRE_OPERACION = ACCION_METODO;
        String WSDL_NAMESPACE ="http://adnsoftware.com.co/webservices/";

        String DIRECCION_SERVICIO= "http://200.116.47.232/wsPruebaAndroid/Service1.asmx";

        //Declaramos el servicio usando la libreria de conexion a servicios Web
        SoapObject objSolicitud = new SoapObject(WSDL_NAMESPACE,NOMBRE_OPERACION);

        //Adicionamos las propiedades del metodo en caso de requerirlas
        if( arrParametrosMetodo != null)
        {
            if(arrParametrosMetodo.length > 0)
            {
                for (PropertyInfo objParametro: arrParametrosMetodo) {
                    //Adicionamos propuedades al servicio
                    objSolicitud.addProperty(objParametro);
                }

            }

        }



        SoapSerializationEnvelope wsEntorno =new SoapSerializationEnvelope(SoapEnvelope.VER11);
        wsEntorno.dotNet = true;
        wsEntorno.setOutputSoapObject(objSolicitud);

        String strRespuesta = null;
        try
        {
            HttpTransportSE objServicio = new HttpTransportSE(DIRECCION_SERVICIO);
            // objServicio.setXmlVersionTag("<?xml version=\"1.0\" encoding=\"utf-8\"?\>");
            objServicio.call(SOAP_ACCION, wsEntorno);
            SoapObject result = (SoapObject)wsEntorno.bodyIn;

            if(result != null)
            {
                //Get the first property and change the label text
                strRespuesta = "<?xml version='1.0' encoding='UTF-8'?>"+result.getProperty(0).toString();
            }
            else
            {
                strRespuesta = "El servicio no responde";
            }


        } catch(Exception ex)
        {
            strRespuesta = "Falla en la conexion "+ ex.toString();

        }
        return strRespuesta;

    }
}
