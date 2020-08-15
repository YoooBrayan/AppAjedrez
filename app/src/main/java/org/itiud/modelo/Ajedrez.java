package org.itiud.modelo;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Observable;

public class Ajedrez extends Observable {

    private Ficha matriz[][];
    private Ficha fichaActual;
    private boolean turno;

    public void iniciarJuego() {
        this.turno = true;
        this.matriz = new Ficha[8][8];

        ArrayList<int[]> fichas = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            this.matriz[6][i] = new Peon(new int[]{6, i}, Color.BLACK, this, 'P');
            fichas.add(new int[]{6, i, Color.BLACK, 'P'});
        }

        this.matriz[3][4] = new Peon(new int[]{3, 4}, Color.WHITE, this, 'P');
        fichas.add(new int[]{3, 4, Color.WHITE, 'P'});

        ArrayList<Object> elemento = new ArrayList<Object>();
        elemento.add(fichas);
        elemento.add(true);
        elemento.add(false);
        this.setChanged();
        this.notifyObservers(elemento);
    }

    public Ficha[][] getMatriz() {
        return matriz;
    }

    public void movimiento(int[] coordenadas) {

        System.out.println("");
        System.out.println("Color de ficha: " + (this.matriz[coordenadas[0]][coordenadas[1]]!=null?this.matriz[coordenadas[0]][coordenadas[1]].getColor():"null"));

        if (this.matriz[coordenadas[0]][coordenadas[1]] instanceof Peon && turno && !(this.matriz[coordenadas[0]][coordenadas[1]]!=null?this.matriz[coordenadas[0]][coordenadas[1]].getColor():"-1").equals(-1)) {
            Peon peon = (Peon) this.matriz[coordenadas[0]][coordenadas[1]];
            peon.posiblesMovimientos();
            this.fichaActual = peon;
            System.out.println("");
            System.out.println("ActualF: " + this.fichaActual.getCoordenadas()[0]);
            ArrayList<Object> elemento = new ArrayList<Object>();

            elemento.add(peon.movimientos);
            elemento.add(false);
            elemento.add(true);
            this.setChanged();
            this.notifyObservers(elemento);

        } else {
            if (this.fichaActual instanceof Peon) {

                int[] coordenadaAnterior = this.fichaActual.getCoordenadas();

                if (((Peon) this.fichaActual).mover(coordenadas)) {
                    ArrayList<Object> elemento = new ArrayList<Object>();
                    elemento.add(this.fichaActual.getCoordenadas());
                    elemento.add(false);
                    elemento.add(true);
                    elemento.add(coordenadaAnterior);
                    this.setChanged();
                    this.notifyObservers(elemento);
                    this.fichaActual = null;

                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (this.matriz[i][j] != null) {
                                System.out.println("");
                                if (this.matriz[i][j] instanceof Peon) {
                                    Peon p = (Peon) this.matriz[i][j];
                                    System.out.println(p.getCoordenadas()[0] + "" + p.getCoordenadas()[1] + " Estado: " + p.getEstado());
                                }
                            }

                        }
                    }

                } else {
                    ArrayList<Object> elemento = new ArrayList<Object>();
                    elemento.add(this.fichaActual.getCoordenadas());
                    elemento.add(false);
                    elemento.add(true);
                    elemento.add(false);
                    this.setChanged();
                    this.notifyObservers(elemento);
                }

            }
        }


    }

    public Ficha getFichaActual() {
        return fichaActual;
    }

    public void setFichaActual(Ficha fichaActual) {
        this.fichaActual = fichaActual;
    }
}
