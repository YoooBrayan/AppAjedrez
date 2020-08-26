package org.itiud.ajedrez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.itiud.controlador.ControladorMatriz;
import org.itiud.modelo.Database;

public class MainActivity extends AppCompatActivity {

    private DibujoMatriz dibujoMatriz;
    private ControladorMatriz controlador;
    private TextView textNamePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textNamePlayer = (TextView) findViewById(R.id.textNamePlayer);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            String name = user.getDisplayName();
            this.dibujoMatriz = new DibujoMatriz(this);
            this.controlador = new ControladorMatriz(this, name);

            textNamePlayer.setText(name);

        }else{
            goLogin();
        }

        /*if(AccessToken.getCurrentAccessToken() == null){
            goLogin();
        }else{
            this.dibujoMatriz = new DibujoMatriz(this);
            this.controlador = new ControladorMatriz(this);
        }*/


    }

    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public DibujoMatriz getDibujoMatriz(){
        return dibujoMatriz;
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        AccessToken.setCurrentAccessToken(null);

        Database db = new Database();
        db.getReference().child("jugadores").removeValue();
        db.getReference().child("coordenadas").removeValue();

        goLogin();
    }
}