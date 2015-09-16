package com.adnsoftware.pruebas;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Norman on 02/09/2015.
 */
public class frmPasaporte extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if( Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);
        }
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_frm_pasaporte_cma);
        TextView lblNombreUsuario = (TextView) findViewById( R.id.txtUsuario);
        lblNombreUsuario.setText("SUPERTIENDA MERKOPOLIS");
    }

    public void bttMapaOnClick(View v )
    {
        Intent pagVistaMapa = new Intent(getApplication(),MapsActivity.class);
        startActivity(pagVistaMapa);

    }

    public void prueba(){}



}
