package com.fdgproject.firedge.deint_205;

/**
 * Created by Firedge on 16/12/2014.
 */
public class ActividadExtraescolar extends Actividad{

    private String fechasalida, fechallegada, lugarsalida, lugarllegada, horasalida, horallegada;

    public ActividadExtraescolar(String profesores, String departamento, String grupos, String descripcion, String fechasalida, String fechallegada, String lugarsalida, String lugarllegada, String horasalida, String horallegada) {
        super(profesores, departamento, grupos, descripcion);
        this.fechasalida = fechasalida;
        this.fechallegada = fechallegada;
        this.lugarsalida = lugarsalida;
        this.lugarllegada = lugarllegada;
        this.horasalida = horasalida;
        this.horallegada = horallegada;
    }

    public String getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(String fechasalida) {
        this.fechasalida = fechasalida;
    }

    public String getFechallegada() {
        return fechallegada;
    }

    public void setFechallegada(String fechallegada) {
        this.fechallegada = fechallegada;
    }

    public String getLugarsalida() {
        return lugarsalida;
    }

    public void setLugarsalida(String lugarsalida) {
        this.lugarsalida = lugarsalida;
    }

    public String getLugarllegada() {
        return lugarllegada;
    }

    public void setLugarllegada(String lugarllegada) {
        this.lugarllegada = lugarllegada;
    }

    public String getHorasalida() {
        return horasalida;
    }

    public void setHorasalida(String horasalida) {
        this.horasalida = horasalida;
    }

    public String getHorallegada() {
        return horallegada;
    }

    public void setHorallegada(String horallegada) {
        this.horallegada = horallegada;
    }

    @Override
    public String toString() {
        return super.toString()+"ActividadExtraescolar{" +
                "fechasalida='" + fechasalida + '\'' +
                ", fechallegada='" + fechallegada + '\'' +
                ", lugarsalida='" + lugarsalida + '\'' +
                ", lugarllegada='" + lugarllegada + '\'' +
                ", horasalida='" + horasalida + '\'' +
                ", horallegada='" + horallegada + '\'' +
                '}';
    }
}
