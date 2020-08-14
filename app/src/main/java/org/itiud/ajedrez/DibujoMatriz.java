package org.itiud.ajedrez;

import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DibujoMatriz {

    private GridLayout gridLayoutMatriz;
    private static TextView textViewMatriz[][];
    private LinearLayout linearLayoutJuego;
    private MainActivity mainActivity;

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
                this.textViewMatriz[i][j].setText(j == 0?(filas++)+"":"");
                this.textViewMatriz[i][j].setWidth((int) (width * 0.95 / 10));
                this.textViewMatriz[i][j].setHeight((int) (height * 0.9 / 10));
                this.textViewMatriz[i][j].setLayoutParams(params);

                this.textViewMatriz[i][j].setBackgroundColor(cont % 2 == 0 ? Color.GRAY : Color.GREEN);

                this.gridLayoutMatriz.addView(this.textViewMatriz[i][j]);
                cont++;
                if(i == 7){
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
}
