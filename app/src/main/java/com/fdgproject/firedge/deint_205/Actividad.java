package com.fdgproject.firedge.deint_205;

import java.io.Serializable;

/**
 * Created by Firedge on 16/12/2014.
 */
public class Actividad implements Serializable{

    private String profesores, departamento, grupos, descripcion;

    public Actividad(String profesores, String departamento, String grupos, String descripcion) {
        this.profesores = profesores;
        this.departamento = departamento;
        this.grupos = grupos;
        this.descripcion = descripcion;
    }

    public String getProfesores(){
        return profesores;
    }

    public void setProfesores(String profesores) {
        this.profesores = profesores;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getGrupos() {
        return grupos;
    }

    public void setGrupos(String grupos) {
        this.grupos = grupos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "profesores='" + profesores + '\'' +
                ", departamento='" + departamento + '\'' +
                ", grupos='" + grupos + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
