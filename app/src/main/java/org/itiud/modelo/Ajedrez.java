package org.itiud.modelo;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Observable;

public class Ajedrez extends Observable {

    private Ficha matriz[][];
    private Ficha fichaActual;
    private int turno;

    public void iniciarJuego() {
        this.turno = Color.WHITE;
        this.matriz = new Ficha[8][8];

        ArrayList<int[]> fichas = new ArrayList<>();

        for (int i = 0; i <= 7; i += 7) {
            for (int j = 0; j < 8; j++) {
                this.matriz[i == 0 ? i + 1 : i - 1][j] = new Peon(new int[]{i == 0 ? i + 1 : i - 1, j}, i == 0 ? Color.WHITE : Color.BLACK, this, 'P');
                fichas.add(new int[]{i == 0 ? i + 1 : i - 1, j, i == 0 ? Color.WHITE : Color.BLACK, 'P'});
            }

            this.matriz[i][0] = new Torre(new int[]{i, 0}, i == 0 ? Color.WHITE : Color.BLACK, this, 'T');
            fichas.add(new int[]{i, 0, i == 0 ? Color.WHITE : Color.BLACK, 'T'});
            this.matriz[i][7] = new Torre(new int[]{i, 7}, i == 0 ? Color.WHITE : Color.BLACK, this, 'T');
            fichas.add(new int[]{i, 7, i == 0 ? Color.WHITE : Color.BLACK, 'T'});

            this.matriz[i][1] = new Caballo(new int[]{i, 1}, i == 0 ? Color.WHITE : Color.BLACK, this, 'C');
            fichas.add(new int[]{i, 1, i == 0 ? Color.WHITE : Color.BLACK, 'C'});
            this.matriz[i][6] = new Caballo(new int[]{i, 6}, i == 0 ? Color.WHITE : Color.BLACK, this, 'C');
            fichas.add(new int[]{i, 6, i == 0 ? Color.WHITE : Color.BLACK, 'C'});

            this.matriz[i][2] = new Alfil(new int[]{i, 2}, i == 0 ? Color.WHITE : Color.BLACK, this, 'A');
            fichas.add(new int[]{i, 2, i == 0 ? Color.WHITE : Color.BLACK, 'A'});
            this.matriz[i][5] = new Alfil(new int[]{i, 5}, i == 0 ? Color.WHITE : Color.BLACK, this, 'A');
            fichas.add(new int[]{i, 5, i == 0 ? Color.WHITE : Color.BLACK, 'A'});

            this.matriz[i][3] = new Rey(new int[]{i, 3}, i == 0 ? Color.WHITE : Color.BLACK, this, 'R');
            fichas.add(new int[]{i, 3, i == 0 ? Color.WHITE : Color.BLACK, 'R'});
            this.matriz[i][4] = new Dama(new int[]{i, 4}, i == 0 ? Color.WHITE : Color.BLACK, this, 'D');
            fichas.add(new int[]{i, 4, i == 0 ? Color.WHITE : Color.BLACK, 'D'});
        }


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
        ArrayList<Object> elemento = new ArrayList<Object>();

        if (ficha instanceof Peon && ficha.getColor() == turno) {
            Peon peon = (Peon) this.matriz[coordenadas[0]][coordenadas[1]];
            peon.posiblesMovimientos(peon.getColor());
            this.fichaActual = peon;
            elemento.add(peon.movimientos);

        } else if (ficha instanceof Caballo &&  ficha.getColor() == turno) {
            Caballo caballo = (Caballo) this.matriz[coordenadas[0]][coordenadas[1]];
            caballo.posiblesMovimientos();
            this.fichaActual = caballo;
            elemento.add(caballo.movimientos);

        } else if (ficha instanceof Torre && ficha.getColor() == turno) {
            Torre torre = (Torre) this.matriz[coordenadas[0]][coordenadas[1]];
            torre.posiblesMovimientos();
            this.fichaActual = torre;
            elemento.add(torre.movimientos);

        } else if (ficha instanceof Alfil && ficha.getColor() == turno) {
            Alfil alfil = (Alfil) this.matriz[coordenadas[0]][coordenadas[1]];
            alfil.posiblesMovimientos();
            this.fichaActual = alfil;
            elemento.add(alfil.movimientos);

        } else if (ficha instanceof Rey && ficha.getColor() == turno) {
            Rey rey = (Rey) this.matriz[coordenadas[0]][coordenadas[1]];
            rey.posiblesMovimientos();
            this.fichaActual = rey;
            elemento.add(rey.movimientos);

        } else if (ficha instanceof Dama && ficha.getColor() == turno) {
            Dama dama = (Dama) this.matriz[coordenadas[0]][coordenadas[1]];
            dama.posiblesMovimientos();
            this.fichaActual = dama;
            elemento.add(dama.movimientos);

        } else if (this.fichaActual instanceof Ficha) {
            boolean b = false;
            int[] coordenadaAnterior = this.fichaActual.getCoordenadas();
            if (this.fichaActual instanceof Peon && ((Peon) this.fichaActual).mover(coordenadas)) {
                b = true;
            } else if (fichaActual instanceof Caballo && ((Caballo) this.fichaActual).mover(coordenadas)) {
                b = true;

            } else if (fichaActual instanceof Torre && ((Torre) this.fichaActual).mover(coordenadas)) {
                b = true;

            } else if (this.fichaActual instanceof Alfil && ((Alfil) this.fichaActual).mover(coordenadas)) {
                b = true;

            } else if (fichaActual instanceof Rey && ((Rey) this.fichaActual).mover(coordenadas)) {
                b = true;

            } else if (fichaActual instanceof Dama && ((Dama) this.fichaActual).mover(coordenadas)) {
                b = true;

            }
            if (b) {
                turno = turno == Color.BLACK ? Color.WHITE : Color.BLACK;
                elemento.add(new int[]{this.fichaActual.getCoordenadas()[0], this.fichaActual.getCoordenadas()[1], this.fichaActual.getColor(), this.fichaActual.getLetra()});
                elemento.add(false);
                elemento.add(true);
                elemento.add(coordenadaAnterior);
                elemento.add(this.fichaActual == null ? new ArrayList<int[]>() : this.posicionesEnemigas());
                this.setChanged();
                this.notifyObservers(elemento);
                this.fichaActual = null;
            } else {
                elemento.add(this.fichaActual.getCoordenadas());
                elemento.add(false);
                elemento.add(true);
                elemento.add(false);
                this.setChanged();
                this.notifyObservers(elemento);
            }
        }
        elemento.add(false);
        elemento.add(true);
        elemento.add(this.fichaActual == null ? new ArrayList<int[]>() : this.posicionesEnemigas());
        elemento.add(this.fichaActual != null ? this.fichaActual.getColor() : 0);
        this.setChanged();
        this.notifyObservers(elemento);
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
                if (this.matriz[i][j] != null && this.matriz[i][j].getColor() == (this.fichaActual.getColor() == Color.BLACK ? Color.WHITE : Color.BLACK)) {
                    posiciones.add(new int[]{i, j, this.fichaActual.getColor() == Color.BLACK ? Color.WHITE : Color.BLACK, this.matriz[i][j].getLetra()});
                }
            }
        }
        return posiciones;
    }
}
