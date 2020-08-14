package org.itiud.controlador;

import android.view.View;
import android.widget.Toast;

import org.itiud.ajedrez.MainActivity;

public class ControladorMatriz {

    private final MainActivity mainActivityC;

    public ControladorMatriz(MainActivity mainActivity){

        this.mainActivityC = mainActivity;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                final int f = i;
                final int c = j;

                this.mainActivityC.getDibujoMatriz().getTextViewMatriz()[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mainActivityC, f+""+c, Toast.LENGTH_SHORT).show();

                        //evento(f,c);
                    }
                });

            }
        }

    }
}

