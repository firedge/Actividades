package com.fdgproject.firedge.deint_205;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ExtFragment extends Fragment implements View.OnClickListener {

    private View v;
    private DatePickerDialog fromDatePickerDialogInicio;
    private DatePickerDialog fromDatePickerDialogFin;
    private TimePickerDialog fromTimePickerDialogInicio;
    private TimePickerDialog fromTimePickerDialogFin;
    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat timeFormatter;

    //Comunes
    private Spinner spProfesor;
    private EditText etDepartamento;
    private Spinner spGrupo;
    private EditText etDescripcion;
    private EditText etLugSalida;
    private EditText etLugRegreso;

    //Especificas
    private EditText etFecInicio;
    private EditText etFecFin;
    private EditText etHorInicio;
    private EditText etHorFin;

    public ExtFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_ext, container, false);
        Log.v("Fragment", "onCreateView");

        //EditText de texto
        etDepartamento = (EditText)v.findViewById(R.id.et_departamento);
        etDescripcion = (EditText)v.findViewById(R.id.et_descripcion);
        etLugSalida = (EditText)v.findViewById(R.id.et_lugsalida);
        etLugRegreso = (EditText)v.findViewById(R.id.et_lugregreso);

        //Spinner
        spProfesor = (Spinner) v.findViewById(R.id.sp_profesor);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, MainActivity.profesores);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProfesor.setAdapter(adapter);

        spGrupo = (Spinner) v.findViewById(R.id.sp_grupo);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, MainActivity.grupos);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGrupo.setAdapter(adapter2);

        //Formato
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        timeFormatter = new SimpleDateFormat("hh:mm");

        //Fecha inicio
        etFecInicio = (EditText) v.findViewById(R.id.et_fecinicio);
        etFecInicio.setInputType(InputType.TYPE_NULL);
        etFecInicio.requestFocus();
        etFecInicio.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialogInicio = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etFecInicio.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        //Fecha fin
        etFecFin = (EditText) v.findViewById(R.id.et_fecfin);
        etFecFin.setInputType(InputType.TYPE_NULL);
        etFecFin.setOnClickListener(this);

        newCalendar = Calendar.getInstance();
        fromDatePickerDialogFin = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etFecFin.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        //Hora Inicio
        etHorInicio = (EditText) v.findViewById(R.id.et_horinicio);
        etHorInicio.setInputType(InputType.TYPE_NULL);
        etHorInicio.setOnClickListener(this);

        newCalendar = Calendar.getInstance();
        fromTimePickerDialogInicio = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(0, 0, 0, selectedHour, selectedMinute);
                etHorInicio.setText(timeFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.HOUR_OF_DAY), newCalendar.get(Calendar.MINUTE), true);

        //Hora Fin
        etHorFin = (EditText) v.findViewById(R.id.et_horfin);
        etHorFin.setInputType(InputType.TYPE_NULL);
        etHorFin.setOnClickListener(this);

        newCalendar = Calendar.getInstance();
        fromTimePickerDialogFin = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(0, 0, 0, selectedHour, selectedMinute);
                etHorFin.setText(timeFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.HOUR_OF_DAY), newCalendar.get(Calendar.MINUTE), true);

        return v;
    }

    public ActividadExtraescolar getActividad(){
        String profesor = spProfesor.getSelectedItemPosition()+1+"";
        String departamento = etDepartamento.getText().toString();
        String grupo = spGrupo.getSelectedItemPosition()+1+"";
        String descripcion = etDescripcion.getText().toString();
        String fechainicio = etFecInicio.getText().toString();
        String fechafin = etFecFin.getText().toString();
        String lugarsalida = etLugSalida.getText().toString();
        String lugarregreso = etLugRegreso.getText().toString();
        String horainicio = etHorInicio.getText().toString();
        String horafin = etHorFin.getText().toString();
        ActividadExtraescolar ae = new ActividadExtraescolar(profesor, departamento, grupo, descripcion, fechainicio, fechafin, lugarsalida, lugarregreso, horainicio, horafin);
        return ae;
    }

    @Override
    public void onClick(View view) {
        if(view == etFecInicio) {
            fromDatePickerDialogInicio.show();
        } else if(view == etFecFin) {
            fromDatePickerDialogFin.show();
        } else if(view == etHorInicio) {
            fromTimePickerDialogInicio.show();
        } else if(view == etHorFin) {
            fromTimePickerDialogFin.show();
        }
    }
}