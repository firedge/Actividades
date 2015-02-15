package com.fdgproject.firedge.deint_205;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {

    private static final String URLBASE = "http://ieszv.x10.bz/restful/api/";
    private final static int FORMULARIO = 1;
    private ArrayList<ActividadRest> actividades;
    public static String [] profesores, grupos;
    private ListView lv;
    private AdaptadorListView ad;
    private boolean tablet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FragmentManager fragmentManager = getSupportFragmentManager();
        //Fragment f = fragmentManager.findFragmentById(R.id.fg_detalle);
        View detalle = findViewById(R.id.fg_detalle);
        if(detalle != null && detalle.getVisibility() == View.VISIBLE){
        //if(f!= null && f.isInLayout()){
            tablet = true;
        }
        actividades = new ArrayList<ActividadRest>();
        lv = (ListView) findViewById(R.id.lv_actividades);
        ad = new AdaptadorListView(this, R.layout.detalle, actividades);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(!tablet) {
                    Intent intent = new Intent(MainActivity.this, DetailView.class);
                    Bundle b = new Bundle();
                    b.putSerializable("actividad", actividades.get(i));
                    intent.putExtras(b);
                    startActivity(intent);
                } else {
                    ActividadRest a = actividades.get(i);
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
        });
        registerForContextMenu(lv);
        cargarActividades();
    }

    private void cargarActividades(){
        String[] peticiones = new String[3];
        peticiones[0] = "actividad/gamarra";
        peticiones[1] = "profesor";
        peticiones[2] = "grupo";
        GetRestFul get = new GetRestFul();
        get.execute(peticiones);
    }

    private class GetRestFul extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... s) {
            String[] r = new String[s.length];
            int i = 0;
            for(String a: s){
                r[i] = ClientRestFul.get(URLBASE+a);
                Log.v("URL", URLBASE+a);
                i++;
            }
            return r;
        }

        @Override
        protected void onPostExecute(String... s) {
            super.onPostExecute(s);
            JSONTokener token = new JSONTokener(s[0]);
            //Actividades
            try {
                //JSONObject raiz = new JSONObject(tokener);
                JSONArray array = new JSONArray(token);
                for(int i=0;  i<array.length(); i++){
                    JSONObject object = array.getJSONObject(i);
                    ActividadRest a = new ActividadRest(object);
                    Log.v("ActividadRest", a.toString());
                    actividades.add(a);
                }
            }catch(Exception ex){}
            //Profesores
            token = new JSONTokener(s[1]);
            try {
                JSONArray array = new JSONArray(token);
                profesores = new String[array.length()];
                for(int i=0;  i<array.length(); i++){
                    JSONObject object = array.getJSONObject(i);
                    profesores[i] = object.getString("nombre")+" "+object.getString("apellidos");
                }
            }catch(Exception ex){}
            //Grupos
            token = new JSONTokener(s[2]);
            try {
                JSONArray array = new JSONArray(token);
                grupos = new String[array.length()];
                for(int i=0;  i<array.length(); i++){
                    JSONObject object = array.getJSONObject(i);
                    grupos[i] = object.getString("grupo");
                }
            }catch(Exception ex){}
            RelativeLayout rl = (RelativeLayout)findViewById(R.id.rlCarga);
            rl.setVisibility(View.GONE);
            ad.notifyDataSetChanged();
        }
    }

    static class ParametrosPost{
        String url;
        JSONObject object;
        String grupo;
    }

    private class PostRestFul extends AsyncTask<ParametrosPost, Void, String>{

        @Override
        protected String doInBackground(ParametrosPost... s) {
            String id = ClientRestFul.post(s[0].url, s[0].object);
            Log.v("ID", id);
            JSONObject object = new JSONObject();
            try {
                JSONObject obj = new JSONObject(new JSONTokener(id));
                id = obj.getString("id");
                object.put("idactividad", id);
                object.put("idgrupo", s[0].grupo);
            } catch (Exception ex) {}
            String r1 = ClientRestFul.post(URLBASE+"actividadgrupo", object);
            //Toast.makeText(MainActivity.this, id, Toast.LENGTH_SHORT).show();
            return id;
        }

    }

    private class DeleteRestFul extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... s) {
            String id = ClientRestFul.delete(s[0]);
            return id;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent i = new Intent(this, Formulario.class);
            startActivityForResult(i, FORMULARIO);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = item.getItemId();
        int index = info.position;
        Object o = info.targetView.getTag();
        if(id == R.id.action_delete){
            DeleteRestFul delete = new DeleteRestFul();
            delete.execute(URLBASE + "actividad/" + actividades.get(index).getId());
            actividades.remove(index);
            ad.notifyDataSetChanged();
        } else if(id == R.id.action_modify){
            //edit(index);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data){
        if (resultCode == Activity.RESULT_OK && requestCode == FORMULARIO) {
            Actividad a = (Actividad) data.getSerializableExtra("actividad");
            ActividadRest actividad = null;
            if(a instanceof ActividadComplementaria){
                actividad = new ActividadRest((ActividadComplementaria)a);
            } else if(a instanceof ActividadExtraescolar){
                actividad = new ActividadRest((ActividadExtraescolar)a);
            }
            if(actividad!=null) {
                actividades.add(actividad);
                ad.notifyDataSetChanged();
                ParametrosPost p = new ParametrosPost();
                p.url = URLBASE + "actividad";
                p.object = actividad.getJSON();
                p.grupo = a.getGrupos();
                PostRestFul pr = new PostRestFul();
                pr.execute(p);
            }
        }
    }

}
