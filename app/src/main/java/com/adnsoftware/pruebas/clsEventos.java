package com.adnsoftware.pruebas;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SergioSoto on 01/09/2015.
 */
public class clsEventos extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        if( Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);
        }
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.);


    }



}
