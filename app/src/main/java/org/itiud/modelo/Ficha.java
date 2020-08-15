package org.itiud.modelo;

import java.util.ArrayList;

public class Ficha {

    protected int[] coordenadas = new int[2];
    protected int color;
    protected ArrayList<int[]> movimientos= new ArrayList<>();
    protected Ajedrez matriz;
    protected char letra;

    public Ficha(int[] coordenadas, int color, Ajedrez matriz, char letra) {
        this.coordenadas = coordenadas;
        this.color = color;
        this.matriz = matriz;
        this.letra = letra;
    }

    public int[] getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(int[] coordenadas) {
        this.coordenadas = coordenadas;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public ArrayList<int[]> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<int[]> movimientos) {
        this.movimientos = movimientos;
    }

    public Ajedrez getMatriz() {
        return matriz;
    }

    public void setMatriz(Ajedrez matriz) {
        this.matriz = matriz;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }
}
