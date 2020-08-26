package org.itiud.modelo;

import java.util.ArrayList;
import java.util.Arrays;

public class Ficha {

    protected int[] coordenadas = new int[2];
    protected int color;
    protected ArrayList<int[]> movimientos = new ArrayList<>();
    protected Ajedrez matriz;
    protected char letra;
    protected int[] jaque;

    public Ficha(int[] coordenadas, int color, Ajedrez matriz, char letra) {
        this.coordenadas = coordenadas;
        this.color = color;
        this.matriz = matriz;
        this.letra = letra;
        this.jaque = new int[]{-1, -1};
    }

    public Ficha() {
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

    public boolean mover(int[] coordenadas) {

        for (int[] m : this.movimientos) {
            if (Arrays.equals(coordenadas, m)) {
                if(this.matriz.getMatriz()[coordenadas[0]][coordenadas[1]] instanceof Rey) {
                    this.matriz.FinJuego();
                }
                //this.matriz.getMatriz()[this.coordenadas[0]][this.coordenadas[1]] = null;
                this.setCoordenadas(coordenadas);
                this.matriz.getMatriz()[coordenadas[0]][coordenadas[1]] = this;
                return true;
            }
        }

        return false;
    }


    public int[] getJaque() {
        return jaque;
    }

    public void setJaque(int[] jaque) {
        this.jaque = jaque;
    }
}
