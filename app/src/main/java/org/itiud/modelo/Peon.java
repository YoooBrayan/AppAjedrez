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
            this.movimientos.add(new int[]{this.coordenadas[0] - 1, this.coordenadas[1]});
            this.movimientos.add(new int[]{this.coordenadas[0] - 2, this.coordenadas[1]});
        } else {
            this.movimientos.clear();
            movimientoNormal();
            movimientoDiagonal();
        }

    }

    public void movimientoNormal() {
        int movimiento;
        if (this.matriz.getMatriz()[movimiento = this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][this.coordenadas[1]] == null) {
            this.movimientos.add(new int[]{movimiento, this.coordenadas[1]});
        }
    }

    public void movimientoDiagonal() {

        if (this.matriz.getMatriz()[this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][this.coordenadas[1] - 1 < 0 ? 0 : this.coordenadas[1] - 1] != null && this.matriz.getMatriz()[this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][this.coordenadas[1] - 1 < 0 ? 0 : this.coordenadas[1] - 1].color != this.color) { // diagonal izquierda
            this.movimientos.add(new int[]{this.coordenadas[0] - 1, this.coordenadas[1] - 1});
        }

        if (this.matriz.getMatriz()[this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][this.coordenadas[1] + 1 > 7 ? 7 : this.coordenadas[1] + 1] != null && this.matriz.getMatriz()[this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][this.coordenadas[1] + 1 > 7 ? 7 : this.coordenadas[1] + 1].color != this.color) {
            this.movimientos.add(new int[]{this.coordenadas[0] - 1, this.coordenadas[1] + 1});
        }
    }

    public boolean mover(int[] coordenadas) {
        if (!estado) {

            if (this.coordenadas[0] - coordenadas[0] == 1 && this.coordenadas[1] == coordenadas[1]) {
                this.matriz.getMatriz()[this.coordenadas[0]][this.coordenadas[1]] = null;
                this.setCoordenadas(coordenadas);
                this.estado = true;
                this.matriz.getMatriz()[coordenadas[0]][coordenadas[1]] = this;
                this.matriz.getMatriz()[coordenadas[0]+1][coordenadas[1]] = null;
                return true;
            }else if(this.coordenadas[0] - coordenadas[0] == 2 && this.coordenadas[1] == coordenadas[1]){
                this.matriz.getMatriz()[this.coordenadas[0]][this.coordenadas[1]] = null;
                this.setCoordenadas(coordenadas);
                this.estado = true;
                this.matriz.getMatriz()[coordenadas[0]][coordenadas[1]] = this;
                return true;
            }
        }else{

            int movimiento;
            if (this.coordenadas[0] - coordenadas[0] == 1 && this.coordenadas[1] == coordenadas[1] && this.matriz.getMatriz()[movimiento = this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][this.coordenadas[1]] == null) {
                this.matriz.getMatriz()[this.coordenadas[0]][this.coordenadas[1]] = null;
                this.setCoordenadas(coordenadas);
                this.matriz.getMatriz()[coordenadas[0]][coordenadas[1]] = this;
                return true;
            }

            if (this.matriz.getMatriz()[this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][this.coordenadas[1] + 1 > 7 ? 7 : this.coordenadas[1] + 1] != null && this.matriz.getMatriz()[this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][this.coordenadas[1] + 1 > 7 ? 7 : this.coordenadas[1] + 1].color != this.color && this.matriz.getMatriz()[coordenadas[0]][coordenadas[1]] != null) { //diagonal derecha
                this.matriz.getMatriz()[this.coordenadas[0]][this.coordenadas[1]] = null;
                this.setCoordenadas(coordenadas);
                this.matriz.getMatriz()[coordenadas[0]][coordenadas[1]] = this;
                return true;
            }

            if (this.matriz.getMatriz()[this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][this.coordenadas[1] - 1 < 0 ? 0 : this.coordenadas[1] - 1] != null && this.matriz.getMatriz()[this.coordenadas[0] - 1 < 0 ? 0 : this.coordenadas[0] - 1][this.coordenadas[1] - 1 < 0 ? 0 : this.coordenadas[1] - 1].color != this.color  && this.matriz.getMatriz()[coordenadas[0]][coordenadas[1]] != null) { // diagonal izquierda
                this.matriz.getMatriz()[this.coordenadas[0]][this.coordenadas[1]] = null;
                this.setCoordenadas(coordenadas);
                this.matriz.getMatriz()[coordenadas[0]][coordenadas[1]] = this;
                return true;
            }
        }
        return false;
    }
}
