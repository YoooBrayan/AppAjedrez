package org.itiud.ajedrez;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import org.itiud.controlador.ControladorMatriz;

public class MainActivity extends AppCompatActivity {

    private DibujoMatriz dibujoMatriz;
    private ControladorMatriz controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.dibujoMatriz = new DibujoMatriz(this);
        this.controlador = new ControladorMatriz(this);

    }

    public DibujoMatriz getDibujoMatriz(){
        return dibujoMatriz;
    }
}
