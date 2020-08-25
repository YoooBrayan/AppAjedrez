package org.itiud.modelo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Database {

    private DatabaseReference mDatabase;

    public Database(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getReference(){
        return this.mDatabase;
    }

}
