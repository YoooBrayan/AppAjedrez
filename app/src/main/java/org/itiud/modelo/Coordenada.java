package org.itiud.modelo;

import java.io.Serializable;

public class Coordenada {

    private int filaA;
    private int columnaA;
    private int filaN;
    private int columnaN;
    private int color;
    private String letra;

    public Coordenada(int filaA, int columnaA, int filaN, int columnaN, int color, String letra) {
        this.filaA = filaA;
        this.columnaA = columnaA;
        this.filaN = filaN;
        this.columnaN = columnaN;
        this.color = color;
        this.letra = letra;
    }

    public Coordenada() {

    }

    public int getFilaA() {
        return filaA;
    }

    public void setFilaA(int filaA) {
        this.filaA = filaA;
    }

    public int getColumnaA() {
        return columnaA;
    }

    public void setColumnaA(int columnaA) {
        this.columnaA = columnaA;
    }

    public int getFilaN() {
        return filaN;
    }

    public void setFilaN(int filaN) {
        this.filaN = filaN;
    }

    public int getColumnaN() {
        return columnaN;
    }

    public void setColumnaN(int columnaN) {
        this.columnaN = columnaN;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
}
