package com.adnsoftware.pruebas;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

/**
 * Created by Norman on 27/08/2015.
 */
public class FrmPpal extends Activity {

    private ImageButton bttMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frmppal);

        bttMenu = (ImageButton) findViewById(R.id.bttMenuLogo);
        bttMenu.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        popupMenu();
                    }
                });
    }

        private void popupMenu()
        {
            //Crea instancia a PopupMenu
            PopupMenu popup = new PopupMenu(this,bttMenu);
            popup.getMenuInflater().inflate(R.menu.menu_popup_cma , popup.getMenu());
            //registra los eventos click para cada item del menu
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.action_one)
                    {
                        Toast.makeText(FrmPpal.this,
                                "Ejecutar : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                    else if (item.getItemId() == R.id.action_two)
                    {
                        Toast.makeText(FrmPpal.this,
                                "Ejecutar : " + item.getTitle() ,Toast.LENGTH_SHORT).show();
                    }
                    else if (item.getItemId() == R.id.action_three)
                    {
                        Toast.makeText(FrmPpal.this,
                                "Ejecutar : " + item.getTitle() ,Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });
            popup.show();
        }



     public void bttComunidadOnClick(View v )
    {

    }

    public void bttPQROnClick(View v )
    {


    }

    public void bttEventosOnClick(View v )
    {
        Intent pagVistaEventos = new Intent(getApplication(),EventosActividad.class);
        startActivity(pagVistaEventos);

    }

    public void bttInformacionOnClick(View v )
    {
        Intent pagVistacomunidad = new Intent(getApplication(),NoticiasActividad.class);
        startActivity(pagVistacomunidad);
    }

    public void bttInformPasaporte(View v)
    {
        Intent pagPasaporte = new Intent(getApplication(),frmPasaporte.class);
        startActivity(pagPasaporte);

    }

    /*
    public View onCreateView ( LayoutInflater inflater ,  ViewGroup container ,
                                Bundle savedInstanceState )  {
        View fragView = inflater . inflate ( R.layout.fragment_menu_desplegable , container ,  false );
        Button bttMenuLogo =  (Button) fragView.findViewById ( R . id.bttMenuLogo );
        DrawerLayout mDrawerLayout =  (DrawerLayout) getActivity (). findViewById(R.id.drawer_layout);
        ListView mDrawerList =  (ListView) getActivity (). findViewById ( R.id.navigation_drawer );
        bttMenuLogo.setOnClickListener(this);
        return fragView ;
    }*/

}
