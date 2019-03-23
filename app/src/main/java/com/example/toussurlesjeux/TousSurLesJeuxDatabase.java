package com.example.toussurlesjeux;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Jeu.class}, version = 1)
public abstract class TousSurLesJeuxDatabase extends RoomDatabase {
    public abstract JeuDao jeuDao();

    private static volatile TousSurLesJeuxDatabase INSTANCE;

    static TousSurLesJeuxDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TousSurLesJeuxDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TousSurLesJeuxDatabase.class, "jeux_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
