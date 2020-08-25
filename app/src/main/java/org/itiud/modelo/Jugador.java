package org.itiud.modelo;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class Jugador implements Serializable {

    private String name;
    private String color;
    private Database db = new Database();

    public Jugador(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Jugador() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
