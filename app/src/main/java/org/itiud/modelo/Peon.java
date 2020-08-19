package org.itiud.modelo;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class Peon extends Ficha {

    private boolean estado;

    public Peon(int[] coordenadas, int color, Ajedrez matriz, char letra) {
        super(coordenadas, color, matriz, letra);
        this.estado = false;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void posiblesMovimientos(int color) {
        this.movimientos.clear();
        movimientoNormal(color);
        movimientoDiagonal(color);
    }

    public void movimientoNormal(int color) {
        int movimiento;

        if (color == Color.rgb(255,255,240)) {
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
    }

    public void movimientoDiagonal(int color) {

        int fila = color == Color.rgb(255,255,240) ? this.coordenadas[0] + 1 : this.coordenadas[0] - 1;
        int col = this.coordenadas[1] - 1;
        if (fila >= 0 && col >= 0 && fila <= 7) {
            if (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].color != this.color) { // diagonal izquierda
                this.movimientos.add(new int[]{fila, col});
            }
        }

        col = this.coordenadas[1] + 1;
        if (fila >= 0 && col <= 7 && fila <= 7) {
            if (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].color != this.color) {
                this.movimientos.add(new int[]{fila, col});
            }
        }


    }

    public boolean mover(int[] coordenadas) {

        for (int[] m : this.movimientos) {
            if (Arrays.equals(coordenadas, m)) {
                this.matriz.getMatriz()[this.coordenadas[0]][this.coordenadas[1]] = null;
                this.setCoordenadas(coordenadas);
                this.estado = true;
                this.matriz.getMatriz()[coordenadas[0]][coordenadas[1]] = this;
                return true;
            }
        }

        return false;
    }
}
