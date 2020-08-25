package org.itiud.modelo;

public class Coordenada {

    private int fila;
    private int columna;

    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public Coordenada() {
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}
