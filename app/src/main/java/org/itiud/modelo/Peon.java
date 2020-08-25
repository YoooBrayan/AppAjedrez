package org.itiud.modelo;

import android.graphics.Color;

import org.itiud.util.Constantes;

import java.util.ArrayList;
import java.util.Arrays;

public class Peon extends Ficha {

    private boolean estado;
    private int posicion;

    public Peon(int[] coordenadas, int color, Ajedrez matriz, char letra, int posicion) {
        super(coordenadas, color, matriz, letra);
        this.estado = false;
        this.posicion = posicion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void posiblesMovimientos() {
        //this.movimientos.clear();
        movimientoNormal();
        movimientoDiagonal();
    }

    public void movimientoNormal() {
        int movimiento;

        if (this.posicion == 0) {
            if (this.matriz.getMatriz()[movimiento = this.coordenadas[0] + 1 > 7 ? 7 : this.coordenadas[0] + 1][this.coordenadas[1]] == null) {
                this.movimientos.add(new int[]{movimiento, this.coordenadas[1]});
                if (!this.estado && this.matriz.getMatriz()[movimiento = this.coordenadas[0] + 2 > 7 ? 7 : this.coordenadas[0] + 2][this.coordenadas[1]] == null) {
                    this.movimientos.add(new int[]{movimiento, this.coordenadas[1]});
                }
            }
        } else {
            if (this.matriz.getMatriz()[movimiento = this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][this.coordenadas[1]] == null) {
                this.movimientos.add(new int[]{movimiento, this.coordenadas[1]});
                if (!this.estado && this.matriz.getMatriz()[movimiento = this.coordenadas[0] - 2 < 0 ? 0 : this.coordenadas[0] - 2][this.coordenadas[1]] == null) {
                    this.movimientos.add(new int[]{this.coordenadas[0] - 2, this.coordenadas[1]});
                }
            }
        }
        this.estado = true;
    }

    public void movimientoDiagonal() {

        int fila = this.posicion == 0 ? this.coordenadas[0] + 1 : this.coordenadas[0] - 1;
        int col = this.coordenadas[1] - 1;
        if (fila >= 0 && col >= 0 && fila <= 7) {
            if (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].color != this.color) { // diagonal izquierda
                this.movimientos.add(new int[]{fila, col});
                if(this.matriz.getMatriz()[fila][col] instanceof Rey){
                    this.jaque[0] = fila;
                    this.jaque[1] = col;
                }
            }
        }

        col = this.coordenadas[1] + 1;
        if (fila >= 0 && col <= 7 && fila <= 7) {
            if (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].color != this.color) {
                this.movimientos.add(new int[]{fila, col});
                if(this.matriz.getMatriz()[fila][col] instanceof Rey){
                    this.jaque[0] = fila;
                    this.jaque[1] = col;
                }
            }
        }
        this.estado = true;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
