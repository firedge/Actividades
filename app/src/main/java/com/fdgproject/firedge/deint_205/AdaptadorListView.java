package com.fdgproject.firedge.deint_205;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Firedge on 28/01/2015.
 */
public class AdaptadorListView extends ArrayAdapter<ActividadRest> {

    private Context cnt;
    private ArrayList<ActividadRest> list;
    private int rec;
    private static LayoutInflater lin;

    static class ViewHolder{
        public TextView tv_descripcion, tv_fechai, tv_fechaf;
        public ImageView iv_tipo;
        public int posicion;
    }

    public AdaptadorListView(Context context, int resource, ArrayList<ActividadRest> objects) {
        super(context, resource, objects);
        this.cnt = context;
        this.list = objects;
        this.rec = resource;
        this.lin = (LayoutInflater)cnt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView == null) {
            convertView = lin.inflate(rec, null);
            vh =new ViewHolder();
            vh.tv_descripcion = (TextView)convertView.findViewById(R.id.tv_descripcion);
            vh.tv_fechai = (TextView)convertView.findViewById(R.id.tv_fechai);
            vh.tv_fechaf = (TextView)convertView.findViewById(R.id.tv_fechaf);
            vh.iv_tipo = (ImageView)convertView.findViewById(R.id.iv_tipo);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder)convertView.getTag();
        }
        vh.posicion = position;
        vh.tv_descripcion.setText(list.get(position).getDescripcion());
        vh.tv_fechai.setText(list.get(position).getFechai());
        vh.tv_fechaf.setText(list.get(position).getFechaf());
        if(list.get(position).getTipo().equals("complementaria")){
            vh.iv_tipo.setBackground(convertView.getResources().getDrawable(R.drawable.complementaria));
        } else if(list.get(position).getTipo().equals("extraescolar")){
            vh.iv_tipo.setBackground(convertView.getResources().getDrawable(R.drawable.extraescolar));
        }
        return convertView;
    }

}
