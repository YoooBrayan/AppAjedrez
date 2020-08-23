package org.itiud.modelo;

import java.util.Arrays;

public class Caballo extends Ficha {

    public Caballo(int[] coordenadas, int color, Ajedrez matriz, char letra) {
        super(coordenadas, color, matriz, letra);
    }

    public void posiblesMovimientos() {
        this.movimientos.clear();
        moverArribaIzquierda();
        moverArribaDerecha();
        moverIzquierdaArriba();
        moverIzquierdaAbajo();
        moverAbajoIzquierda();
        moverAbajoDerecha();
        moverDerechaAbajo();
        moverDerechaArriba();
    }

    public void moverArribaIzquierda() {
        int fila = this.coordenadas[0] - 2, col = this.coordenadas[1] - 1;
        if (fila >= 0 && col >= 0) {
            if(this.matriz.getMatriz()[fila][col] == null || (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color)){
                this.movimientos.add(new int[]{fila, col});
            }
        }
    }

    public void moverArribaDerecha() {
        int fila = this.coordenadas[0] - 2, col = this.coordenadas[1] + 1;
        if (fila >= 0 && col <= 7) {
            if(this.matriz.getMatriz()[fila][col] == null || (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color)){
                this.movimientos.add(new int[]{fila, col});
            }
        }
    }

    public void moverIzquierdaArriba() {
        int fila = this.coordenadas[0] - 1, col = this.coordenadas[1] - 2;
        if (fila >= 0 && col >= 0) {
            if(this.matriz.getMatriz()[fila][col] == null || (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color)){
                this.movimientos.add(new int[]{fila, col});
            }
        }
    }

    public void moverIzquierdaAbajo() {
        int fila = this.coordenadas[0] + 1, col = this.coordenadas[1] - 2;
        if (fila <= 7 && col >= 0) {
            if(this.matriz.getMatriz()[fila][col] == null || (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color)){
                this.movimientos.add(new int[]{fila, col});
            }
        }
    }

    public void moverAbajoIzquierda() {
        int fila = this.coordenadas[0] + 2, col = this.coordenadas[1] - 1;
        if (fila <= 7 && col >= 0) {
            if(this.matriz.getMatriz()[fila][col] == null || (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color)){
                this.movimientos.add(new int[]{fila, col});
            }
        }
    }

    public void moverAbajoDerecha() {
        int fila = this.coordenadas[0] + 2, col = this.coordenadas[1] + 1;
        if (fila <= 7 && col <= 7) {
            if(this.matriz.getMatriz()[fila][col] == null || (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color)){
                this.movimientos.add(new int[]{fila, col});
            }
        }
    }

    public void moverDerechaAbajo() {
        int fila = this.coordenadas[0] + 1, col = this.coordenadas[1] + 2;
        if (fila <= 7 && col <= 7) {
            if(this.matriz.getMatriz()[fila][col] == null || (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color)){
                this.movimientos.add(new int[]{fila, col});
            }
        }
    }

    public void moverDerechaArriba() {
        int fila = this.coordenadas[0] - 1, col = this.coordenadas[1] + 2;
        if (fila >= 0 && col <= 7) {
            if(this.matriz.getMatriz()[fila][col] == null || (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color)){
                this.movimientos.add(new int[]{fila, col});
            }
        }
    }

}
