package com.fdgproject.firedge.deint_205;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class Formulario extends FragmentActivity {

    private List<Fragment> lista;
    private ViewPager pagina;
    private ExtFragment f1;
    private ComFragment f2;
    private ActividadExtraescolar ae = null;
    private ActividadComplementaria ac = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        this.lista = getFragmentos();
        Adaptador ad = new Adaptador(getSupportFragmentManager(),
                lista);
        pagina = (ViewPager)findViewById(R.id.view);
        pagina.setAdapter(ad);
        SlidingTabLayout layoutTab = (SlidingTabLayout)
                findViewById(R.id.tabsDeslizantes);
        layoutTab.setViewPager(pagina);
    }

    private List<Fragment> getFragmentos(){
        List<Fragment> lista = new ArrayList<Fragment>();
        f1 = new ExtFragment();
        f2 = new ComFragment();
        lista.add(f1);
        lista.add(f2);
        return lista;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            View view = pagina.getFocusedChild();
            Actividad a = null;
            if(f1.getView() == view){
                a = f1.getActividad();
            } else if(f2.getView() == view){
                a = f2.getActividad();
            }
            Intent i = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("actividad", a);
            i.putExtras(bundle);
            setResult(Activity.RESULT_OK, i);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
