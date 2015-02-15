package com.fdgproject.firedge.deint_205;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class DetailView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        Bundle b = getIntent().getExtras();
        ActividadRest a = (ActividadRest)b.getSerializable("actividad");
        TextView tv = (TextView)findViewById(R.id.tvTipo);
        tv.setText(a.getTipo());
        tv = (TextView)findViewById(R.id.tvDescripcion);
        tv.setText(a.getDescripcion());
        tv = (TextView)findViewById(R.id.tvLugari);
        tv.setText(a.getLugari());
        tv = (TextView)findViewById(R.id.tvLugarf);
        tv.setText(a.getLugarf());
        tv = (TextView)findViewById(R.id.tvFechai);
        tv.setText(a.getFechai());
        tv = (TextView)findViewById(R.id.tvFechaf);
        tv.setText(a.getFechaf());
        tv = (TextView)findViewById(R.id.tvProfesor);
        tv.setText(MainActivity.profesores[Integer.parseInt(a.getIdprofesor())-1]);
    }
}
