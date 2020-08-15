package org.itiud.modelo;

import java.util.ArrayList;

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

    public void posiblesMovimientos() {
        if (!estado) {
            System.out.println("");
            System.out.println("ACa Estado False");
            this.movimientos.add(new int[]{this.coordenadas[0] - 1, this.coordenadas[1]});
            this.movimientos.add(new int[]{this.coordenadas[0] - 2, this.coordenadas[1]});
        } else {
            this.movimientos.clear();
            System.out.println("");
            System.out.println("ACa Estado True");
            movimientoNormal();
            movimientoDiagonal();
        }

    }

    public void movimientoNormal() {
        if (this.matriz.getMatriz()[this.coordenadas[0] - 1][this.coordenadas[1]] == null) {
            System.out.println("Entro ACa subir 1");
            this.movimientos.add(new int[]{this.coordenadas[0] - 1, this.coordenadas[1]});
        }
    }

    public void movimientoDiagonal() {

        if (this.matriz.getMatriz()[this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][this.coordenadas[1] - 1 < 0 ? 0 : this.coordenadas[1] - 1] != null && this.matriz.getMatriz()[this.coordenadas[0] - 1][this.coordenadas[1] - 1 < 0 ? 0 : this.coordenadas[1] - 1].color != this.color) { // diagonal izquierda
            System.out.println("Entro ACa diagonal izq");
            this.movimientos.add(new int[]{this.coordenadas[0] - 1, this.coordenadas[1] - 1});
        }

        if (this.matriz.getMatriz()[this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][this.coordenadas[1] + 1] != null && this.matriz.getMatriz()[this.coordenadas[0] - 1][this.coordenadas[1] + 1 > 7 ? 7 : this.coordenadas[1] + 1].color != this.color) {
            System.out.println("Entro ACa diagonal Der");
            this.movimientos.add(new int[]{this.coordenadas[0] - 1, this.coordenadas[1] + 1});
        }
    }

    public boolean mover(int[] coordenadas) {
        if (!estado) {

            System.out.println("");
            System.out.println("Actual: " + this.coordenadas[0]);
            System.out.println("movida: " + coordenadas[0]);

            if (this.coordenadas[0] - coordenadas[0] == 1 && this.coordenadas[1] == coordenadas[1]) {
                this.setCoordenadas(coordenadas);
                this.estado = true;
                this.matriz.getMatriz()[coordenadas[0]][coordenadas[1]] = this;
                this.matriz.getMatriz()[coordenadas[0]+1][coordenadas[1]] = null;
                System.out.println("");
                System.out.println("Estado a cambiado a: " + this.estado);
                return true;
            }else if(this.coordenadas[0] - coordenadas[0] == 2 && this.coordenadas[1] == coordenadas[1]){
                this.setCoordenadas(coordenadas);
                this.estado = true;
                this.matriz.getMatriz()[coordenadas[0]][coordenadas[1]] = this;
                this.matriz.getMatriz()[coordenadas[0]+2][coordenadas[1]] = null;
                System.out.println("");
                System.out.println("Estado a cambiado a: " + this.estado);
                return true;
            }
        }else{

            if (this.coordenadas[0] - coordenadas[0] == 1 && this.coordenadas[1] == coordenadas[1]) {
                this.setCoordenadas(coordenadas);
                this.matriz.getMatriz()[coordenadas[0]][coordenadas[1]] = this;
                this.matriz.getMatriz()[coordenadas[0]+1][coordenadas[1]] = null;
                return true;
            }

            if (this.matriz.getMatriz()[this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][this.coordenadas[1] + 1] != null && this.matriz.getMatriz()[this.coordenadas[0] - 1][this.coordenadas[1] + 1 > 7 ? 7 : this.coordenadas[1] + 1].color != this.color) {
                this.setCoordenadas(coordenadas);
                this.matriz.getMatriz()[coordenadas[0]][coordenadas[1]] = this;
                this.matriz.getMatriz()[coordenadas[0]+1][coordenadas[1]-1] = null;
                return true;
            }

            if (this.matriz.getMatriz()[this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][this.coordenadas[1] - 1 < 0 ? 0 : this.coordenadas[1] - 1] != null && this.matriz.getMatriz()[this.coordenadas[0] - 1][this.coordenadas[1] - 1 < 0 ? 0 : this.coordenadas[1] - 1].color != this.color) { // diagonal izquierda
                this.setCoordenadas(coordenadas);
                this.matriz.getMatriz()[coordenadas[0]][coordenadas[1]] = this;
                this.matriz.getMatriz()[coordenadas[0]+1][coordenadas[1]+1] = null;
                return true;
            }
        }
        return false;
    }
}
