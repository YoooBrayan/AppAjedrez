package org.itiud.modelo;

import java.util.Arrays;

public class Torre extends Ficha {

    public Torre(int[] coordenadas, int color, Ajedrez matriz, char letra) {
        super(coordenadas, color, matriz, letra);
    }

    public void posiblesMovimientos() {
        this.movimientos.clear();
        moverArriba();
        moverAbajo();
        moverDerecha();
        moverIzquierda();
    }

    public void moverArriba() {
        boolean b = true;
        int fila = this.coordenadas[0]-1;

        while (fila >= 0 && b) {
            if (this.matriz.getMatriz()[fila][coordenadas[1]] == null || (this.matriz.getMatriz()[fila][coordenadas[1]] != null && this.matriz.getMatriz()[fila][coordenadas[1]].getColor() != this.color)) {
                if(this.matriz.getMatriz()[fila][coordenadas[1]] != null && this.matriz.getMatriz()[fila][coordenadas[1]].getColor() != this.color){
                    this.movimientos.add(new int[]{fila, coordenadas[1]});
                    b = false;

                }else{
                    this.movimientos.add(new int[]{fila, coordenadas[1]});
                }

            } else {
                b = false;
            }
            fila--;
        }

        System.out.println("");
        System.out.println("size: " + this.movimientos.size());
    }

    public void moverAbajo() {
        boolean b = true;
        int fila = this.coordenadas[0] + 1;

        while (fila <= 7 && b) {
            if (this.matriz.getMatriz()[fila][coordenadas[1]] == null || (this.matriz.getMatriz()[fila][coordenadas[1]] != null && this.matriz.getMatriz()[fila][coordenadas[1]].getColor() != this.color)) {
                if(this.matriz.getMatriz()[fila][coordenadas[1]] != null && this.matriz.getMatriz()[fila][coordenadas[1]].getColor() != this.color){
                    this.movimientos.add(new int[]{fila, coordenadas[1]});
                    b = false;

                }else{
                    this.movimientos.add(new int[]{fila, coordenadas[1]});
                }

            } else {
                b = false;
            }
            fila++;
        }

        System.out.println("");
        System.out.println("size: " + this.movimientos.size());
    }

    public void moverDerecha() {
        boolean b = true;
        int col = this.coordenadas[1] + 1;

        while (col <= 7 && b) {
            if (this.matriz.getMatriz()[coordenadas[0]][col] == null || (this.matriz.getMatriz()[coordenadas[0]][col] != null && this.matriz.getMatriz()[coordenadas[0]][col].getColor() != this.color)) {
                if(this.matriz.getMatriz()[coordenadas[0]][col] != null && this.matriz.getMatriz()[coordenadas[0]][col].getColor() != this.color){
                    this.movimientos.add(new int[]{coordenadas[0], col});
                    b = false;
                }else{
                    this.movimientos.add(new int[]{coordenadas[0], col});
                }

            } else {
                b = false;
            }
            col++;
        }

        System.out.println("");
        System.out.println("size: " + this.movimientos.size());
    }

    public void moverIzquierda() {
        boolean b = true;
        int col = this.coordenadas[1] - 1;

        while (col >= 0 && b) {
            if (this.matriz.getMatriz()[coordenadas[0]][col] == null || (this.matriz.getMatriz()[coordenadas[0]][col] != null && this.matriz.getMatriz()[coordenadas[0]][col].getColor() != this.color)) {
                if(this.matriz.getMatriz()[coordenadas[0]][col] != null && this.matriz.getMatriz()[coordenadas[0]][col].getColor() != this.color){
                    this.movimientos.add(new int[]{coordenadas[0], col});
                    b = false;
                }else{
                    this.movimientos.add(new int[]{coordenadas[0], col});
                }

            } else {
                b = false;
            }
            col--;
        }

        System.out.println("");
        System.out.println("size: " + this.movimientos.size());
    }

}
