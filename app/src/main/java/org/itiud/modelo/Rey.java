package org.itiud.modelo;

import java.util.Arrays;

public class Rey extends Ficha {

    public Rey(int[] coordenadas, int color, Ajedrez matriz, char letra) {
        super(coordenadas, color, matriz, letra);
    }

    public void posiblesMovimientos() {
        this.movimientos.clear();
        moverArriba();
        moverIzquierda();
        moverAbajo();
        moverDerecha();
        moverIzquierdaArriba();
        moverIzquierdaAbajo();
        moverDerechaAbajo();
        moverDerechaArriba();
    }

    private void moverDerechaArriba() {
        int fila, col;
        if (this.matriz.getMatriz()[fila = this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][col = this.coordenadas[1] + 1 > 7 ? 7 : this.coordenadas[1] + 1] == null || (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color)) {
            this.movimientos.add(new int[]{fila, col});
        }
    }

    private void moverDerechaAbajo() {
        int fila, col;
        if (this.matriz.getMatriz()[fila = this.coordenadas[0] + 1 > 7 ? 7 : this.coordenadas[0] + 1][col = this.coordenadas[1] + 1 > 7 ? 7 : this.coordenadas[1] + 1] == null || (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color)) {
            this.movimientos.add(new int[]{fila, col});
        }
    }

    private void moverIzquierdaAbajo() {
        int fila, col;
        if (this.matriz.getMatriz()[fila = this.coordenadas[0] + 1 > 7 ? 7 : this.coordenadas[0] + 1][col = this.coordenadas[1] - 1 < 0 ? 0 : this.coordenadas[1] -1] == null || (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color)) {
            this.movimientos.add(new int[]{fila, col});
        }
    }

    private void moverIzquierdaArriba() {
        int fila, col;
        if (this.matriz.getMatriz()[fila = this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] -1][col = this.coordenadas[1] - 1 < 0 ? 0 : this.coordenadas[1] -1] == null || (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color)) {
            this.movimientos.add(new int[]{fila, col});
        }
    }

    private void moverDerecha() {
        int movimiento;
        if (this.matriz.getMatriz()[this.coordenadas[0]][movimiento = this.coordenadas[1] + 1 > 7 ? 7 : this.coordenadas[1] + 1] == null || (this.matriz.getMatriz()[coordenadas[0]][movimiento] != null && this.matriz.getMatriz()[coordenadas[0]][movimiento].getColor() != this.color)) {
            this.movimientos.add(new int[]{coordenadas[0], movimiento});
        }
    }

    private void moverAbajo() {
        int movimiento;
        if (this.matriz.getMatriz()[movimiento = this.coordenadas[0] + 1 > 7 ? 7 : this.coordenadas[0] + 1][this.coordenadas[1]] == null || (this.matriz.getMatriz()[movimiento][coordenadas[1]] != null && this.matriz.getMatriz()[movimiento][coordenadas[1]].getColor() != this.color)) {
            this.movimientos.add(new int[]{movimiento, coordenadas[1]});
        }
    }

    private void moverIzquierda() {
        int movimiento;
        if (this.matriz.getMatriz()[this.coordenadas[0]][movimiento = this.coordenadas[1] - 1 < 0 ? 0 : this.coordenadas[1] - 1] == null || (this.matriz.getMatriz()[coordenadas[0]][movimiento] != null && this.matriz.getMatriz()[coordenadas[0]][movimiento].getColor() != this.color)) {
            this.movimientos.add(new int[]{coordenadas[0], movimiento});
        }
    }

    private void moverArriba() {
        int movimiento;
        if (this.matriz.getMatriz()[movimiento = this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][this.coordenadas[1]] == null || (this.matriz.getMatriz()[movimiento][coordenadas[1]] != null && this.matriz.getMatriz()[movimiento][coordenadas[1]].getColor() != this.color)) {
            this.movimientos.add(new int[]{movimiento, coordenadas[1]});
        }
    }

}
