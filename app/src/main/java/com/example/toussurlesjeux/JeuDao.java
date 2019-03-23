package com.example.toussurlesjeux;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface JeuDao {

    @Query("SELECT * FROM jeu")
    List<Jeu> getAll();

    @Query("SELECT * FROM jeu WHERE id = :jeuid")
    Jeu findById(long jeuid);

    @Insert
    void insertJeu(Jeu jeu);

    @Update
    void updateJeu(Jeu jeu);

    @Delete
    void deleteJeu(Jeu jeu);
}
