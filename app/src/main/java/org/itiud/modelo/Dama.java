package org.itiud.modelo;

public class Dama extends Torre{

    public Dama(int[] coordenadas, int color, Ajedrez matriz, char letra) {
        super(coordenadas, color, matriz, letra);
    }

    public void posiblesMovimientos() {
        this.movimientos.clear();
        moverArriba();
        moverAbajo();
        moverDerecha();
        moverIzquierda();
        moverDiagonalIzquierdaAbajo();
        moverDiagonalIzquierdaArriba();
        moverDiagonalDerechaAbajo();
        moverDiagonalDerechaArriba();
    }

    public void moverDiagonalIzquierdaArriba() {
        boolean b = true;
        int fila = this.coordenadas[0] - 1;
        int col = this.coordenadas[1] - 1;

        while (fila >= 0 && col >= 0 && b) {
            if (this.matriz.getMatriz()[fila][col] == null || (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color)) {
                this.movimientos.add(new int[]{fila, col});
                if(this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color){
                    b = false;
                }
            } else {
                b = false;
            }
            fila--;
            col--;
        }

        System.out.println("");
        System.out.println("size: " + this.movimientos.size());
    }

    public void moverDiagonalDerechaArriba() {
        boolean b = true;
        int fila = this.coordenadas[0] - 1;
        int col = this.coordenadas[1] + 1;

        while (fila >= 0 && col <= 7 && b) {
            if (this.matriz.getMatriz()[fila][col] == null || (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color)) {
                this.movimientos.add(new int[]{fila, col});
                if(this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color){
                    b = false;
                }
            } else {
                b = false;
            }
            fila--;
            col++;
        }

        System.out.println("");
        System.out.println("size: " + this.movimientos.size());
    }

    public void moverDiagonalDerechaAbajo() {
        boolean b = true;
        int fila = this.coordenadas[0] + 1;
        int col = this.coordenadas[1] + 1;

        while (fila <= 7 && col <= 7 && b) {
            if (this.matriz.getMatriz()[fila][col] == null || (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color)) {
                this.movimientos.add(new int[]{fila, col});
                if(this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color){
                    b = false;
                }
            } else {
                b = false;
            }
            fila++;
            col++;
        }

        System.out.println("");
        System.out.println("size: " + this.movimientos.size());
    }

    public void moverDiagonalIzquierdaAbajo() {
        boolean b = true;
        int fila = this.coordenadas[0] + 1;
        int col = this.coordenadas[1] - 1;

        while (fila <= 7 && col >= 0 && b) {
            if (this.matriz.getMatriz()[fila][col] == null || (this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color)) {
                this.movimientos.add(new int[]{fila, col});
                if(this.matriz.getMatriz()[fila][col] != null && this.matriz.getMatriz()[fila][col].getColor() != this.color){
                    b = false;
                }
            } else {
                b = false;
            }
            fila++;
            col--;
        }

        System.out.println("");
        System.out.println("size: " + this.movimientos.size());
    }

}
