package com.fdgproject.firedge.deint_205;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailViewFragment extends Fragment {

    private View v;

    public DetailViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_detail_view, container, false);

        return v;
    }

    public void setDatos(ActividadRest a){
        TextView tv = (TextView)v.findViewById(R.id.tvTipo);
        tv.setText(a.getTipo());
        tv = (TextView)v.findViewById(R.id.tvDescripcion);
        tv.setText(a.getDescripcion());
        tv = (TextView)v.findViewById(R.id.tvLugari);
        tv.setText(a.getLugari());
        tv = (TextView)v.findViewById(R.id.tvLugarf);
        tv.setText(a.getLugarf());
        tv = (TextView)v.findViewById(R.id.tvFechai);
        tv.setText(a.getFechai());
        tv = (TextView)v.findViewById(R.id.tvFechaf);
        tv.setText(a.getFechaf());
        tv = (TextView)v.findViewById(R.id.tvProfesor);
        tv.setText(MainActivity.profesores[Integer.parseInt(a.getIdprofesor())-1]);
    }


}
