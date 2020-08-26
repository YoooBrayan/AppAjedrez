package org.itiud.modelo;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class Actualizar {

    private Ajedrez ajedrez;
    private Database db;
    private int cont = 0;


    public Actualizar(Ajedrez ajedrez, Database db) {
        this.ajedrez = ajedrez;
        this.db = db;
    }

    public void actualizarJuego(){

        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                cont++;
                if (cont > 1) {
                    timer.cancel();
                    timer.purge();
                    return;
                }
                db.getReference().child("partida").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            int filaA = Integer.parseInt(snapshot.child("filaA").getValue().toString());
                            int columnaA = Integer.parseInt(snapshot.child("columnaA").getValue().toString());
                            int filaN = Integer.parseInt(snapshot.child("filaN").getValue().toString());
                            int columnaN = Integer.parseInt(snapshot.child("columnaN").getValue().toString());


                            System.out.println("FilaA: " + filaA);
                            System.out.println("ColumnaA: " + columnaA);
                            System.out.println("FilaN: " + filaN);
                            System.out.println("ColumnaN: " + columnaN);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);

    }
}
