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

        for (int i = 4; i < 5; i++) {
            this.matriz[6][i] = new Peon(new int[]{6, i}, Color.BLACK, this, 'P');
            fichas.add(new int[]{6, i, Color.BLACK, 'P'});
        }

        this.matriz[4][4] = new Torre(new int[]{4, 4}, Color.BLACK, this, 'T');
        fichas.add(new int[]{4, 4, Color.BLACK, 'T'});
        this.matriz[7][7] = new Torre(new int[]{7, 7}, Color.BLACK, this, 'T');
        fichas.add(new int[]{7, 7, Color.BLACK, 'T'});

        this.matriz[7][1] = new Caballo(new int[]{7, 1}, Color.BLACK, this, 'C');
        fichas.add(new int[]{7, 1, Color.BLACK, 'C'});
        this.matriz[7][6] = new Caballo(new int[]{7, 6}, Color.BLACK, this, 'C');
        fichas.add(new int[]{7, 6, Color.BLACK, 'C'});

        this.matriz[7][2] = new Alfil(new int[]{7, 2}, Color.BLACK, this, 'A');
        fichas.add(new int[]{7, 2, Color.BLACK, 'A'});
        this.matriz[7][5] = new Alfil(new int[]{7, 5}, Color.BLACK, this, 'A');
        fichas.add(new int[]{7, 5, Color.BLACK, 'A'});

        this.matriz[7][3] = new Rey(new int[]{7, 3}, Color.BLACK, this, 'R');
        fichas.add(new int[]{7, 3, Color.BLACK, 'R'});
        this.matriz[7][4] = new Dama(new int[]{7, 4}, Color.BLACK, this, 'D');
        fichas.add(new int[]{7, 4, Color.BLACK, 'D'});

        this.matriz[1][2] = new Peon(new int[]{1, 2}, Color.WHITE, this, 'P');
        fichas.add(new int[]{1, 2, Color.WHITE, 'P'});

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

        Ficha ficha = this.matriz[coordenadas[0]][coordenadas[1]];

        if (ficha instanceof Peon && turno && !(ficha != null ? ficha.getColor() : "-1").equals(-1)) {
            Peon peon = (Peon) this.matriz[coordenadas[0]][coordenadas[1]];
            peon.posiblesMovimientos();
            this.fichaActual = peon;
            ArrayList<Object> elemento = new ArrayList<Object>();
            elemento.add(peon.movimientos);
            elemento.add(false);
            elemento.add(true);
            elemento.add(this.posicionesEnemigas());
            this.setChanged();
            this.notifyObservers(elemento);

        } else if (ficha instanceof Caballo && turno && !(ficha != null ? ficha.getColor() : "-1").equals(-1)) {
            Caballo caballo = (Caballo) this.matriz[coordenadas[0]][coordenadas[1]];
            caballo.posiblesMovimientos();
            this.fichaActual = caballo;
            ArrayList<Object> elemento = new ArrayList<Object>();
            elemento.add(caballo.movimientos);
            elemento.add(false);
            elemento.add(true);
            elemento.add(this.posicionesEnemigas());
            this.setChanged();
            this.notifyObservers(elemento);
        } else if (ficha instanceof Torre && turno && !(ficha != null ? ficha.getColor() : "-1").equals(-1)) {
            Torre torre = (Torre) this.matriz[coordenadas[0]][coordenadas[1]];
            torre.posiblesMovimientos();
            this.fichaActual = torre;
            ArrayList<Object> elemento = new ArrayList<Object>();
            elemento.add(torre.movimientos);
            elemento.add(false);
            elemento.add(true);
            elemento.add(this.posicionesEnemigas());
            this.setChanged();
            this.notifyObservers(elemento);
        } else if (ficha instanceof Alfil && turno && !(ficha != null ? ficha.getColor() : "-1").equals(-1)) {
            Alfil alfil = (Alfil) this.matriz[coordenadas[0]][coordenadas[1]];
            alfil.posiblesMovimientos();
            this.fichaActual = alfil;
            ArrayList<Object> elemento = new ArrayList<Object>();
            elemento.add(alfil.movimientos);
            elemento.add(false);
            elemento.add(true);
            elemento.add(this.posicionesEnemigas());
            this.setChanged();
            this.notifyObservers(elemento);
        } else {
            if (this.fichaActual instanceof Peon) {

                int[] coordenadaAnterior = this.fichaActual.getCoordenadas();

                if (((Peon) this.fichaActual).mover(coordenadas)) {
                    ArrayList<Object> elemento = new ArrayList<Object>();
                    elemento.add(new int[]{this.fichaActual.getCoordenadas()[0], this.fichaActual.getCoordenadas()[1], this.fichaActual.getColor(), this.fichaActual.getLetra()});
                    elemento.add(false);
                    elemento.add(true);
                    elemento.add(coordenadaAnterior);
                    elemento.add(this.posicionesEnemigas());
                    this.setChanged();
                    this.notifyObservers(elemento);
                    this.fichaActual = null;

                } else {
                    ArrayList<Object> elemento = new ArrayList<Object>();
                    elemento.add(this.fichaActual.getCoordenadas());
                    elemento.add(false);
                    elemento.add(true);
                    elemento.add(false);
                    this.setChanged();
                    this.notifyObservers(elemento);
                }

            } else if (fichaActual instanceof Caballo) {

                int[] coordenadaAnterior = this.fichaActual.getCoordenadas();

                // Si puede mover
                if (((Caballo) this.fichaActual).mover(coordenadas)) {
                    ArrayList<Object> elemento = new ArrayList<Object>();
                    elemento.add(new int[]{this.fichaActual.getCoordenadas()[0], this.fichaActual.getCoordenadas()[1], this.fichaActual.getColor(), this.fichaActual.getLetra()});
                    elemento.add(false);
                    elemento.add(true);
                    elemento.add(coordenadaAnterior);
                    elemento.add(this.posicionesEnemigas());
                    this.setChanged();
                    this.notifyObservers(elemento);
                    this.fichaActual = null;

                    // No puede mover y devuelve muestra mensaje
                } else {
                    ArrayList<Object> elemento = new ArrayList<Object>();
                    elemento.add(this.fichaActual.getCoordenadas());
                    elemento.add(false);
                    elemento.add(true);
                    elemento.add(false);
                    this.setChanged();
                    this.notifyObservers(elemento);
                }
            } else if (fichaActual instanceof Torre) {

                int[] coordenadaAnterior = this.fichaActual.getCoordenadas();

                // Si puede mover
                if (((Torre) this.fichaActual).mover(coordenadas)) {
                    ArrayList<Object> elemento = new ArrayList<Object>();
                    elemento.add(new int[]{this.fichaActual.getCoordenadas()[0], this.fichaActual.getCoordenadas()[1], this.fichaActual.getColor(), this.fichaActual.getLetra()});
                    elemento.add(false);
                    elemento.add(true);
                    elemento.add(coordenadaAnterior);
                    elemento.add(this.posicionesEnemigas());
                    this.setChanged();
                    this.notifyObservers(elemento);
                    this.fichaActual = null;

                    // No puede mover y devuelve muestra mensaje
                } else {
                    ArrayList<Object> elemento = new ArrayList<Object>();
                    elemento.add(this.fichaActual.getCoordenadas());
                    elemento.add(false);
                    elemento.add(true);
                    elemento.add(false);
                    this.setChanged();
                    this.notifyObservers(elemento);
                }
            } else if (this.fichaActual instanceof Alfil) {
                int[] coordenadaAnterior = this.fichaActual.getCoordenadas();

                // Si puede mover
                if (((Alfil) this.fichaActual).mover(coordenadas)) {
                    ArrayList<Object> elemento = new ArrayList<Object>();
                    elemento.add(new int[]{this.fichaActual.getCoordenadas()[0], this.fichaActual.getCoordenadas()[1], this.fichaActual.getColor(), this.fichaActual.getLetra()});
                    elemento.add(false);
                    elemento.add(true);
                    elemento.add(coordenadaAnterior);
                    elemento.add(this.posicionesEnemigas());
                    this.setChanged();
                    this.notifyObservers(elemento);
                    this.fichaActual = null;

                    // No puede mover y devuelve muestra mensaje
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

    public ArrayList<int[]> posicionesEnemigas() {

        ArrayList<int[]> posiciones = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.matriz[i][j] != null && this.matriz[i][j].getColor() == -1) {
                    posiciones.add(new int[]{i, j, Color.WHITE, this.matriz[i][j].getLetra()});
                }
            }
        }
        return posiciones;
    }
}
