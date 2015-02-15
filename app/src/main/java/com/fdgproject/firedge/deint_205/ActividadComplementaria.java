package com.fdgproject.firedge.deint_205;

/**
 * Created by Firedge on 16/12/2014.
 */
public class ActividadComplementaria extends Actividad {

    private String fecha, lugar, horaentrada, horasalida;

    public ActividadComplementaria(String profesores, String departamento, String grupos, String descripcion, String fecha, String lugar, String horaentrada, String horasalida) {
        super(profesores, departamento, grupos, descripcion);
        this.fecha = fecha;
        this.lugar = lugar;
        this.horaentrada = horaentrada;
        this.horasalida = horasalida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getHoraentrada() {
        return horaentrada;
    }

    public void setHoraentrada(String horaentrada) {
        this.horaentrada = horaentrada;
    }

    public String getHorasalida() {
        return horasalida;
    }

    public void setHorasalida(String horasalida) {
        this.horasalida = horasalida;
    }

    @Override
    public String toString() {
        return super.toString()+"ActividadComplementaria{" +
                "fecha='" + fecha + '\'' +
                ", lugar='" + lugar + '\'' +
                ", horaentrada='" + horaentrada + '\'' +
                ", horasalida='" + horasalida + '\'' +
                '}';
    }
}
