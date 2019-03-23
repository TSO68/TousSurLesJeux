package com.example.toussurlesjeux;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Jeu {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long Id;

    @NonNull
    public String Nom;

    @NonNull
    public String AnneeSortie;

    @NonNull
    public String Studio;

    @NonNull
    public String Genre;

    @NonNull
    public String Description;

    @NonNull
    public String Note;

    @NonNull
    public String Avis;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    @NonNull
    public String getNom() {
        return Nom;
    }

    public void setNom(@NonNull String nom) {
        Nom = nom;
    }

    public String getAnneeSortie() {
        return AnneeSortie;
    }

    public void setAnneeSortie(String anneeSortie) {
        AnneeSortie = anneeSortie;
    }

    @NonNull
    public String getStudio() {
        return Studio;
    }

    public void setStudio(@NonNull String studio) {
        Studio = studio;
    }

    @NonNull
    public String getGenre() {
        return Genre;
    }

    public void setGenre(@NonNull String genre) {
        Genre = genre;
    }

    @NonNull
    public String getDescription() {
        return Description;
    }

    public void setDescription(@NonNull String description) {
        Description = description;
    }

    public String  getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    @NonNull
    public String getAvis() {
        return Avis;
    }

    public void setAvis(@NonNull String avis) {
        Avis = avis;
    }
}
