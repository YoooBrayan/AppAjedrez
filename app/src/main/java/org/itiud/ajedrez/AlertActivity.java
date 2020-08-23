package org.itiud.ajedrez;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AlertActivity extends AppCompatActivity {

    private TextView ganador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        ganador = (TextView) findViewById(R.id.ganador);

        String dato = getIntent().getStringExtra("ganador");
        if(dato.equals("-16777216")){
            ganador.setText("Fichas Negras");
        }else{
            ganador.setText("Fichas Blancas");
        }




        final CharSequence opciones[] = {"Jugar de Nuevo", "Salir"};
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Seleccione una opcion");
        alert.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(opciones[i].equals("Jugar de Nuevo")){
                    Intent main = new Intent(AlertActivity.this, MainActivity.class);
                    startActivity(main);
                }else{
                    if(opciones[i].equals("Salir")){
                        System.exit(1);
                        System.out.println("Salio");
                    }else{
                        dialog.dismiss();
                    }
                }
            }
        });
        alert.show();
    }

}
