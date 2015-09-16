package com.adnsoftware.pruebas;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Norman on 02/09/2015.
 */
public class EventosActividad extends Activity {
        CalendarView calendar;
        private List<Evento> arrEventos = new ArrayList<Evento>();
        private List<Evento> arrEventosMostrar=new ArrayList<Evento>();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            if( Build.VERSION.SDK_INT > 9)
            {
                StrictMode.ThreadPolicy politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(politica);
            }
            super.onCreate(savedInstanceState);
            setContentView(R.layout.act_frm_eventos_cma);

            CargarEventos();

            // this can be add to make calendar
            calendar = (CalendarView) findViewById(R.id.calendar);
            calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                @Override
                public void onSelectedDayChange(CalendarView view,
                                                int year, int month, int dayOfMonth) {
                    arrEventosMostrar.clear();
                    String strDia=String.valueOf(dayOfMonth);
                    String strMes=String.valueOf(month);
                    if(dayOfMonth<10)
                    {
                        strDia="0"+strDia;
                    }
                    if(month<10)
                    {
                        strMes="0"+strMes;
                    }

                    String strFechaSeleccionada = strDia + "/" + strMes + "/" + year;

                    for(Evento evento: arrEventos) {
                        String strFechaEventoEvento = "";
                        strFechaEventoEvento = evento.FechaEvento().substring(0, 10);

                        if (strFechaEventoEvento.trim().equals(strFechaSeleccionada.trim())) {
                            arrEventosMostrar.add(evento);
                            Boolean blnGuardado = true;
                        }
                    }
                    CargarEventosSeleccionados();

                }
            });
        }


        private void CargarEventos() {

            long Fecha=System.currentTimeMillis();
            Date FechaExacta= new Date(Fecha);
            DateFormat FechaSistema = new SimpleDateFormat("dd/MM/yyyy");
            String strFecha=FechaSistema.format(FechaExacta);

            //Nos conectamos al servicio web
            Evento objEvento = new Evento();
            Evento[]arrObjEvento = objEvento.ObtenerEventos();
            // Cargamos el objeto de noticias
            for (Evento evento:arrObjEvento)
            {
                arrEventos.add(evento);
            }


        }
        private void CargarEventosSeleccionados() {

            ArrayAdapter<Evento> adapEventos = new ListarEventos();
            ListView lvwNoticia = (ListView) findViewById(R.id.lvwNoticias);
            lvwNoticia.setAdapter(adapEventos);


        }
        private class ListarEventos extends ArrayAdapter<Evento>
        {
            public ListarEventos()
            {
                super(EventosActividad.this , R.layout.itvw_eventos, arrEventosMostrar);

            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View itemEvento = convertView;
                if(itemEvento == null)
                {
                    itemEvento = getLayoutInflater().inflate(R.layout.itvw_eventos, parent,false);
                }

                //Obtenemos eventos selecionados
                Evento evento = arrEventosMostrar.get(position);


                TextView lblNombreEvento = (TextView) itemEvento.findViewById(R.id.lblTituloEvento);
                TextView lblDescripcion = (TextView) itemEvento.findViewById(R.id.lblDetalleEvento);
                TextView lblFechaEvento = (TextView) itemEvento.findViewById(R.id.lblFecha);
                ImageView imgEvento = (ImageView) itemEvento.findViewById(R.id.imgEvento);

                lblNombreEvento.setText(evento.NombreEvento());
                lblDescripcion.setText(evento.DescripcionEvento());
                lblFechaEvento.setText(evento.FechaEvento());

                imgEvento.setImageBitmap( new Utilidades().ObtenerImagenUrl(evento.UrlImagenEvento()));

                return itemEvento;
            }
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            // Inflate the menu; this adds items to the action bar if it is present.
            //getMenuInflater().inflate(R.menu, menu);
            return true;
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
}


