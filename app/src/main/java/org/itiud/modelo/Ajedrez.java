package org.itiud.modelo;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.itiud.ajedrez.MainActivity;
import org.itiud.util.Constantes;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Ajedrez extends Observable {

    private Ficha matriz[][];
    private Ficha fichaActual;
    private Ficha fichaEnemiga;
    private int turno;
    Database db = new Database();
    private int cont = 0;
    private int colorJ;

    public void iniciarJuego(final String name) {

        db.getReference().child("jugadores").child("1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    Jugador jugador = new Jugador(name, "blanco");
                    db.getReference().child("jugadores").child("1").setValue(jugador);
                    colorJ = Constantes.BLANCO;
                    fichas(0);

                } else {
                    db.getReference().child("jugadores").child("2").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (!snapshot.exists()) {
                                Jugador jugador = new Jugador(name, "negro");
                                db.getReference().child("jugadores").child("2").setValue(jugador);
                                colorJ = Constantes.NEGRO;
                                fichas(1);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        actualizar(this.matriz);

    }

    public Ficha[][] getMatriz() {
        return matriz;
    }

    public void movimiento(int[] coordenadas) {

        Ficha ficha = this.matriz[coordenadas[0]][coordenadas[1]];
        ArrayList<Object> elemento = new ArrayList<Object>();

        if (ficha instanceof Peon && ficha.getColor() == turno) {
            Peon peon = (Peon) this.matriz[coordenadas[0]][coordenadas[1]];
            peon.posiblesMovimientos();
            this.fichaActual = peon;
            elemento.add(peon.movimientos);
            elemento.add(this.fichaActual != null ? this.fichaActual.getColor() : 0);

        } else if (ficha instanceof Caballo && ficha.getColor() == turno) {
            Caballo caballo = (Caballo) this.matriz[coordenadas[0]][coordenadas[1]];
            caballo.posiblesMovimientos();
            this.fichaActual = caballo;
            elemento.add(caballo.movimientos);
            elemento.add(this.fichaActual != null ? this.fichaActual.getColor() : 0);

        } else if (ficha instanceof Torre && ficha.getColor() == turno) {
            Torre torre = (Torre) this.matriz[coordenadas[0]][coordenadas[1]];
            torre.posiblesMovimientos();
            this.fichaActual = torre;
            elemento.add(torre.movimientos);
            elemento.add(this.fichaActual != null ? this.fichaActual.getColor() : 0);

        } else if (ficha instanceof Alfil && ficha.getColor() == turno) {
            Alfil alfil = (Alfil) this.matriz[coordenadas[0]][coordenadas[1]];
            alfil.posiblesMovimientos();
            this.fichaActual = alfil;
            elemento.add(alfil.movimientos);
            elemento.add(this.fichaActual != null ? this.fichaActual.getColor() : 0);

        } else if (ficha instanceof Rey && ficha.getColor() == turno) {
            Rey rey = (Rey) this.matriz[coordenadas[0]][coordenadas[1]];
            rey.posiblesMovimientos();
            this.fichaActual = rey;
            elemento.add(rey.movimientos);
            elemento.add(this.fichaActual != null ? this.fichaActual.getColor() : 0);

        } else if (ficha instanceof Dama && ficha.getColor() == turno) {
            Dama dama = (Dama) this.matriz[coordenadas[0]][coordenadas[1]];
            dama.posiblesMovimientos();
            this.fichaActual = dama;
            elemento.add(dama.movimientos);
            elemento.add(this.fichaActual != null ? this.fichaActual.getColor() : 0);

        } else if (this.fichaActual instanceof Ficha) {
            boolean b = false;
            int[] coordenadaAnterior = this.fichaActual.getCoordenadas();
            if (this.fichaActual instanceof Peon && ((Peon) this.fichaActual).mover(coordenadas)) {
                Peon peon = (Peon) this.matriz[this.fichaActual.getCoordenadas()[0]][this.fichaActual.getCoordenadas()[1]];
                peon.posiblesMovimientos();
                b = true;
            } else if (fichaActual instanceof Caballo && ((Caballo) this.fichaActual).mover(coordenadas)) {
                b = true;

            } else if (fichaActual instanceof Torre && ((Torre) this.fichaActual).mover(coordenadas)) {
                b = true;

            } else if (this.fichaActual instanceof Alfil && ((Alfil) this.fichaActual).mover(coordenadas)) {
                b = true;

            } else if (fichaActual instanceof Rey && ((Rey) this.fichaActual).mover(coordenadas)) {
                b = true;

            } else if (fichaActual instanceof Dama && ((Dama) this.fichaActual).mover(coordenadas)) {
                b = true;

            }
            if (b) {
                turno = turno == Constantes.BLANCO ? Constantes.NEGRO : Constantes.BLANCO;
                elemento.add(new int[]{this.fichaActual.getCoordenadas()[0], this.fichaActual.getCoordenadas()[1], this.fichaActual.getColor(), this.fichaActual.getLetra()});
                elemento.add(coordenadaAnterior);
                elemento.add(this.fichaActual.getJaque());
                this.fichaActual.setJaque(new int[]{-1, -1});
                //Enviar a firebase aqui
                //this.fichaActual = null;

                Coordenada coordenadasE = new Coordenada(coordenadaAnterior[0], coordenadaAnterior[1], this.fichaActual.getCoordenadas()[0], this.fichaActual.getCoordenadas()[1], this.fichaActual.getColor(), Character.toString(fichaActual.getLetra()));
                db.getReference().child("coordenadas").removeValue();
                db.getReference().child("coordenadas").setValue(coordenadasE);


            } else {
                elemento.add(this.fichaActual.getCoordenadas());
                elemento.add(false);
            }
        }
        elemento.add(false);
        elemento.add(true);
        elemento.add(this.fichaActual == null ? new ArrayList<int[]>() : this.posicionesEnemigas());
        this.setChanged();
        this.notifyObservers(elemento);
    }

    private void mover(int filaA, int columnaA, int filaN, int columnaN) {
        if (this.matriz[filaA][columnaA] instanceof Peon) {
            System.out.println("peon");
        } else if (this.matriz[filaA][columnaA] instanceof Caballo) {
            System.out.println("caballo");
        } else if (this.matriz[filaA][columnaA] instanceof Torre) {
            System.out.println("torre");
        } else if (this.matriz[filaA][columnaA] instanceof Alfil) {
            System.out.println("alfil");
        } else if (this.matriz[filaA][columnaA] instanceof Dama) {
            System.out.println("dama");
        } else if (this.matriz[filaA][columnaA] instanceof Rey) {
            System.out.println("rey");
        } else {
            System.out.println("nada");
        }

    }

    public Ficha getFichaActual() {
        return fichaActual;
    }

    public void setFichaActual(Ficha fichaActual) {
        this.fichaActual = fichaActual;
    }

    public ArrayList<int[]> posicionesEnemigas() {

        ArrayList<int[]> posiciones = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.matriz[i][j] != null && this.matriz[i][j].getColor() == (this.fichaActual.getColor() == Constantes.NEGRO ? Constantes.BLANCO : Constantes.NEGRO)) {
                    posiciones.add(new int[]{i, j, this.fichaActual.getColor() == Constantes.NEGRO ? Constantes.BLANCO : Constantes.NEGRO, this.matriz[i][j].getLetra()});
                }
            }
        }
        return posiciones;
    }

    public void FinJuego() {

        ArrayList<Object> elemento = new ArrayList<Object>();
        elemento.add(true);
        if (turno == Constantes.NEGRO) {
            elemento.add(Constantes.NEGRO);
        } else {
            elemento.add(Constantes.BLANCO);
        }
        elemento.add(false);
        setChanged();
        notifyObservers(elemento);
    }


    public void fichas(int jugador) {

        this.turno = Constantes.BLANCO;
        this.matriz = new Ficha[8][8];

        ArrayList<int[]> fichas = new ArrayList<>();

        int colorE = colorJ == Constantes.BLANCO ? Constantes.NEGRO : Constantes.BLANCO;

        if(jugador==0){

            for (int i = 0; i <= 7; i += 7) {

                for (int j = 0; j < 8; j++) {
                    this.matriz[i == 0 ? i + 1 : i - 1][j] = new Peon(new int[]{i == 0 ? i + 1 : i - 1, j}, i == 0 ? colorE : colorJ, this, 'P', i == 0 ? 0 : 1);
                    fichas.add(new int[]{i == 0 ? i + 1 : i - 1, j, i == 0 ? colorE : colorJ, 'P'});
                }

                this.matriz[i][0] = new Torre(new int[]{i, 0}, i == 0 ? colorE : colorJ, this, 'T');
                fichas.add(new int[]{i, 0, i == 0 ? colorE : colorJ, 'T'});
                this.matriz[i][7] = new Torre(new int[]{i, 7}, i == 0 ? colorE : colorJ, this, 'T');
                fichas.add(new int[]{i, 7, i == 0 ? colorE : colorJ, 'T'});

                this.matriz[i][1] = new Caballo(new int[]{i, 1}, i == 0 ? colorE : colorJ, this, 'C');
                fichas.add(new int[]{i, 1, i == 0 ? colorE : colorJ, 'C'});
                this.matriz[i][6] = new Caballo(new int[]{i, 6}, i == 0 ? colorE : colorJ, this, 'C');
                fichas.add(new int[]{i, 6, i == 0 ? colorE : colorJ, 'C'});

                this.matriz[i][2] = new Alfil(new int[]{i, 2}, i == 0 ? colorE : colorJ, this, 'A');
                fichas.add(new int[]{i, 2, i == 0 ? colorE : colorJ, 'A'});
                this.matriz[i][5] = new Alfil(new int[]{i, 5}, i == 0 ? colorE : colorJ, this, 'A');
                fichas.add(new int[]{i, 5, i == 0 ? colorE : colorJ, 'A'});

                this.matriz[i][4] = new Rey(new int[]{i, 4}, i == 0 ? colorE : colorJ, this, 'R');
                fichas.add(new int[]{i, 4, i == 0 ? colorE : colorJ, 'R'});
                this.matriz[i][3] = new Dama(new int[]{i, 3}, i == 0 ? colorE : colorJ, this, 'D');
                fichas.add(new int[]{i, 3, i == 0 ? colorE : colorJ, 'D'});
            }
        }else{
            for (int i = 7; i >= 0; i -= 7) {

                for (int j = 0; j < 8; j++) {
                    this.matriz[i == 7 ? i - 1 : i + 1][j] = new Peon(new int[]{i == 7 ? i - 1 : i + 1, j}, i == 7 ? colorE : colorJ, this, 'P', i == 7 ? 0 : 1);
                    fichas.add(new int[]{i == 7 ? i - 1 : i + 1, j, i == 7 ? colorE : colorJ, 'P'});
                }

                this.matriz[i][0] = new Torre(new int[]{i, 0}, i == 7 ? colorE : colorJ, this, 'T');
                fichas.add(new int[]{i, 0, i == 7 ? colorE : colorJ, 'T'});
                this.matriz[i][7] = new Torre(new int[]{i, 7}, i == 7 ? colorE : colorJ, this, 'T');
                fichas.add(new int[]{i, 7, i == 7 ? colorE : colorJ, 'T'});

                this.matriz[i][1] = new Caballo(new int[]{i, 1}, i == 7 ? colorE : colorJ, this, 'C');
                fichas.add(new int[]{i, 1, i == 7 ? colorE : colorJ, 'C'});
                this.matriz[i][6] = new Caballo(new int[]{i, 6}, i == 7 ? colorE : colorJ, this, 'C');
                fichas.add(new int[]{i, 6, i == 7 ? colorE : colorJ, 'C'});

                this.matriz[i][2] = new Alfil(new int[]{i, 2}, i == 7 ? colorE : colorJ, this, 'A');
                fichas.add(new int[]{i, 2, i == 7 ? colorE : colorJ, 'A'});
                this.matriz[i][5] = new Alfil(new int[]{i, 5}, i == 7 ? colorE : colorJ, this, 'A');
                fichas.add(new int[]{i, 5, i == 7 ? colorE : colorJ, 'A'});

                this.matriz[i][3] = new Rey(new int[]{i, 3}, i == 7 ? colorE : colorJ, this, 'R');
                fichas.add(new int[]{i, 3, i == 7 ? colorE : colorJ, 'R'});
                this.matriz[i][4] = new Dama(new int[]{i, 4}, i == 7 ? colorE : colorJ, this, 'D');
                fichas.add(new int[]{i, 4, i == 7 ? colorE : colorJ, 'D'});
            }
        }



        ArrayList<Object> elemento = new ArrayList<Object>();
        elemento.add(fichas);
        elemento.add(true);
        elemento.add(true);
        this.setChanged();
        this.notifyObservers(elemento);
    }

    private void inicio(final FirebaseCallback1 firebaseCallback1) {

        final boolean[] b = {false};
        db.getReference().child("jugadores").child("1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {

                    b[0] = true;
                    firebaseCallback1.onCallback1(b[0]);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private interface FirebaseCallback1 {
        void onCallback1(Boolean b);
    }

    public void actualizar(final Ficha[][] fichas) {

        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                cont++;
                if (cont > 1) {
                    timer.cancel();
                    timer.purge();
                    System.out.println("");
                    System.out.println("Cancelo");
                    return;
                }
                db.getReference().child("coordenadas").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            int filaA = Integer.parseInt(snapshot.child("filaA").getValue().toString());
                            int columnaA = Integer.parseInt(snapshot.child("columnaA").getValue().toString());
                            int filaN = Integer.parseInt(snapshot.child("filaN").getValue().toString());
                            int columnaN = Integer.parseInt(snapshot.child("columnaN").getValue().toString());
                            int color = Integer.parseInt(snapshot.child("color").getValue().toString());
                            String letra = snapshot.child("letra").getValue().toString();


                            System.out.println("FilaA: " + filaA);
                            System.out.println("ColumnaA: " + columnaA);
                            System.out.println("FilaN: " + filaN);
                            System.out.println("ColumnaN: " + columnaN);
                            System.out.println("color: " + color);
                            System.out.println("letra: " + letra);

                            matriz[filaN][columnaN] = new Ficha(new int[]{filaN, columnaA}, color, Ajedrez.this, letra.charAt(0));
                            matriz[filaA][columnaA] = null;

                            ArrayList<Object> elemento = new ArrayList<Object>();
                            elemento.add(false);
                            elemento.add(new int[]{filaN, columnaN, color, matriz[filaN][columnaN].getLetra()});
                            elemento.add(new int[]{filaA, columnaA});
                            setChanged();
                            notifyObservers(elemento);

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
