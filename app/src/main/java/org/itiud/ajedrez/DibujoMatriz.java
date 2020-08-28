package org.itiud.ajedrez;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.Point;
import android.graphics.Typeface;
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
    private int[] jaque = new int[]{-1, -1};

    public DibujoMatriz(final MainActivity mainActivity, int orden) {

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

        if(orden == 0){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    this.textViewMatriz[i][j] = new TextView(this.mainActivity);
                    //this.textViewMatriz[i][j].setText(i+""+j);
                    this.textViewMatriz[i][j].setWidth((int) (width * 0.99 / 10));
                    this.textViewMatriz[i][j].setHeight((int) (height * 0.68 / 10));
                    this.textViewMatriz[i][j].setLayoutParams(params);

                    this.textViewMatriz[i][j].setBackgroundColor(cont % 2 == 0 ? Color.rgb(245, 222, 179) : Color.rgb(139, 69, 19));

                    this.gridLayoutMatriz.addView(this.textViewMatriz[i][j]);
                    cont++;
                    if (i == 7) {
                        //this.textViewMatriz[7][j].setText(this.textViewMatriz[7][j].getText() + Character.toString((char) col++));
                    }
                }
                cont--;

            }
            this.linearLayoutJuego.addView(this.gridLayoutMatriz);
        }else{
            for (int i = 7; i >= 0; i--) {
                for (int j = 7; j >= 0; j--) {
                    this.textViewMatriz[i][j] = new TextView(this.mainActivity);
                    //this.textViewMatriz[i][j].setText(i+""+j);
                    this.textViewMatriz[i][j].setWidth((int) (width * 0.99 / 10));
                    this.textViewMatriz[i][j].setHeight((int) (height * 0.68 / 10));
                    this.textViewMatriz[i][j].setLayoutParams(params);

                    this.textViewMatriz[i][j].setBackgroundColor(cont % 2 == 0 ? Color.rgb(245, 222, 179) : Color.rgb(139, 69, 19));

                    this.gridLayoutMatriz.addView(this.textViewMatriz[i][j]);
                    cont++;
                    if (i == 7) {
                        //this.textViewMatriz[7][j].setText(this.textViewMatriz[7][j].getText() + Character.toString((char) col++));
                    }
                }
                cont--;

            }
            this.linearLayoutJuego.addView(this.gridLayoutMatriz);
        }

    }

    public TextView[][] getTextViewMatriz() {
        return textViewMatriz;
    }

    @Override
    public void update(Observable o, Object arg) {

        final ArrayList<Object> elemento = (ArrayList<Object>) arg;


        //inicio
        if (elemento.get(0) != null && elemento.get(2).equals(true)) {
            ArrayList<int[]> fichas = (ArrayList<int[]>) elemento.get(0);

            for (int[] f : fichas) {
                textViewMatriz[f[0]][f[1]].setText(Character.toString((char) f[3]));
                textViewMatriz[f[0]][f[1]].setGravity(Gravity.CENTER);
                textViewMatriz[f[0]][f[1]].setTextColor(f[2]);
            }
            // Posibles movimientos
        } else if (elemento.get(0) instanceof List && elemento.get(2).equals(false)) {
            ArrayList<int[]> movimientos = (ArrayList<int[]>) elemento.get(0);

            for (int[] b : borrar) {

                if (!Arrays.equals(this.fichaMovida, b)) {
                    textViewMatriz[b[0]][b[1]].setText("");
                }
            }

            ArrayList<int[]> posicionesE = (ArrayList<int[]>) elemento.get(4);
            int color = (int) elemento.get(1);

            for (int[] f : posicionesE) {
                if (!Arrays.equals(new int[]{f[0], f[1]}, jaque)) {
                    textViewMatriz[f[0]][f[1]].setText(Character.toString((char) f[3]));
                    textViewMatriz[f[0]][f[1]].setTextColor(f[2]);
                } else {
                    textViewMatriz[f[0]][f[1]].setText(Character.toString((char) f[3]));
                }
            }

            this.borrar = movimientos;

            for (int[] m : movimientos) {
                textViewMatriz[m[0]][m[1]].setText("*");
                textViewMatriz[m[0]][m[1]].setTextColor(color);
                textViewMatriz[m[0]][m[1]].setGravity(Gravity.CENTER);
            }

            // Mover Ficha
        } else if (elemento.get(0) instanceof int[]) {

            if (elemento.get(1) instanceof int[]) {

                int[] ficha = (int[]) elemento.get(0);
                int[] coordenadaAnterior = (int[]) elemento.get(1);
                this.jaque = (int[]) elemento.get(2);

                this.fichaMovida = new int[]{ficha[0], ficha[1]};

                for (int[] b : borrar) {
                    if (!Arrays.equals(this.fichaMovida, b)) {
                        textViewMatriz[b[0]][b[1]].setText("");
                    }
                }

                this.borrar.clear();
                ArrayList<int[]> posicionesE = (ArrayList<int[]>) elemento.get(5);

                for (int[] f : posicionesE) {
                    if (!Arrays.equals(new int[]{f[0], f[1]}, jaque)) {
                        textViewMatriz[f[0]][f[1]].setText(Character.toString((char) f[3]));
                        textViewMatriz[f[0]][f[1]].setTextColor(f[2]);
                    } else {
                        textViewMatriz[f[0]][f[1]].setText(Character.toString((char) f[3]));
                    }
                }

                textViewMatriz[ficha[0]][ficha[1]].setText(Character.toString((char) ficha[3]));
                textViewMatriz[ficha[0]][ficha[1]].setTextColor(ficha[2]);
                textViewMatriz[coordenadaAnterior[0]][coordenadaAnterior[1]].setText("");

                if (!Arrays.equals(jaque, new int[]{-1, -1})) {
                    textViewMatriz[jaque[0]][jaque[1]].setTextColor(Color.RED);
                }
            } else {
                Toast.makeText(this.mainActivity, "Movimiento Invalido", Toast.LENGTH_SHORT).show();
            }

        }else if(elemento.get(0).equals(true)){
            Intent alert = new Intent(this.mainActivity, AlertActivity.class);
            alert.putExtra("ganador", elemento.get(1).toString());
            mainActivity.startActivity(alert);
        }else if(elemento.get(0).equals(false) && !elemento.get(1).equals(true)){

            int[] ficha = (int[]) elemento.get(1);
            int[] coordenadaAnterior = (int[]) elemento.get(2);
            this.jaque = (int[]) elemento.get(3);

            textViewMatriz[ficha[0]][ficha[1]].setText(Character.toString((char) ficha[3]));
            textViewMatriz[ficha[0]][ficha[1]].setTextColor(ficha[2]);
            textViewMatriz[ficha[0]][ficha[1]].setGravity(Gravity.CENTER);
            textViewMatriz[coordenadaAnterior[0]][coordenadaAnterior[1]].setText("");

            if (!Arrays.equals(jaque, new int[]{-1, -1})) {
                textViewMatriz[jaque[0]][jaque[1]].setTextColor(Color.RED);
            }

        }
        else{
            Toast.makeText(this.mainActivity, "Espera tu turno", Toast.LENGTH_SHORT).show();
        }


    }
}
