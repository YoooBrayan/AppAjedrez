package org.itiud.ajedrez;

import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class DibujoMatriz implements Observer {

    private GridLayout gridLayoutMatriz;
    private static TextView textViewMatriz[][];
    private LinearLayout linearLayoutJuego;
    private MainActivity mainActivity;
    private ArrayList<int[]> borrar = new ArrayList<>();
    private int[] fichaMovida = new int[2];

    public DibujoMatriz(final MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.textViewMatriz = new TextView[8][8];
        this.gridLayoutMatriz = new GridLayout(this.mainActivity);
        this.gridLayoutMatriz.setColumnCount(8);
        this.gridLayoutMatriz.setRowCount(8);
        this.linearLayoutJuego = this.mainActivity.findViewById(R.id.linearLayoutAjedrez);
        Display display = this.mainActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(2, 2, 2, 2);

        int cont = 0;
        int filas = 1;
        int col = 97;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.textViewMatriz[i][j] = new TextView(this.mainActivity);
                this.textViewMatriz[i][j].setText(j == 0 ? (filas++) + "" : "");
                this.textViewMatriz[i][j].setWidth((int) (width * 0.95 / 10));
                this.textViewMatriz[i][j].setHeight((int) (height * 0.9 / 10));
                this.textViewMatriz[i][j].setLayoutParams(params);


                this.textViewMatriz[i][j].setBackgroundColor(cont % 2 == 0 ? Color.GRAY : Color.GREEN);

                this.gridLayoutMatriz.addView(this.textViewMatriz[i][j]);
                cont++;
                if (i == 7) {
                    this.textViewMatriz[7][j].setText(this.textViewMatriz[7][j].getText() + Character.toString((char) col++));
                }
            }
            cont--;

        }
        this.linearLayoutJuego.addView(this.gridLayoutMatriz);
    }

    public TextView[][] getTextViewMatriz() {
        return textViewMatriz;
    }

    @Override
    public void update(Observable o, Object arg) {

        final ArrayList<Object> elemento = (ArrayList<Object>) arg;

        if (elemento.get(0) instanceof List && elemento.get(1).equals(false)) { //Posibles mov
            ArrayList<int[]> movimientos = (ArrayList<int[]>) elemento.get(0);


            for (int[] b : borrar) {
                if(!Arrays.equals(this.fichaMovida, b)){
                    textViewMatriz[b[0]][b[1]].setText("");
                }
            }

            this.borrar = movimientos;

            for (int[] m : movimientos) {
                textViewMatriz[m[0]][m[1]].setText("*");
                textViewMatriz[m[0]][m[1]].setGravity(Gravity.CENTER);
            }

        } else if (elemento.get(0) != null && elemento.get(2).equals(false)) { // mostrar incio de juego
            ArrayList<int[]> fichas = (ArrayList<int[]>) elemento.get(0);

            for(int[] f: fichas){
                textViewMatriz[f[0]][f[1]].setText(Character.toString((char) f[3]));
                textViewMatriz[f[0]][f[1]].setTextColor(f[2]);
            }

        }else if(elemento.get(2).equals(true)){

            if(elemento.get(3) instanceof int[]){

                int[] nuevasCoordenadas = (int[]) elemento.get(0);
                int[] coordenadaAnterior = (int[]) elemento.get(3);

                this.fichaMovida = nuevasCoordenadas;

                for (int[] b : borrar) {
                    if(!Arrays.equals(nuevasCoordenadas, b)){
                        textViewMatriz[b[0]][b[1]].setText("");
                    }
                }

                textViewMatriz[nuevasCoordenadas[0]][nuevasCoordenadas[1]].setText("P");
                textViewMatriz[coordenadaAnterior[0]][coordenadaAnterior[1]].setText("");
            }else{
                Toast.makeText(this.mainActivity, "Movimiento Invalido", Toast.LENGTH_SHORT).show();
                System.out.println("");
                System.out.println("Invalido");
            }

        }


    }
}
