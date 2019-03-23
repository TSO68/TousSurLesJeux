package com.example.toussurlesjeux;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AjouterJeuActivity extends AppCompatActivity {

    EditText editNom;
    EditText editAnnee;
    EditText editStudio;
    EditText editGenre;
    EditText editDescription;
    EditText editNote;
    EditText editAvis;

    TousSurLesJeuxDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_jeu);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Ajouter un jeu");

        db = TousSurLesJeuxDatabase.getDatabase(this);

        editNom = findViewById(R.id.txtNom);
        editAnnee = findViewById(R.id.txtAnnee);
        editStudio = findViewById(R.id.txtStudio);
        editGenre = findViewById(R.id.txtGenre);
        editDescription = findViewById(R.id.txtDescription);
        editNote = findViewById(R.id.txtNote);
        editAvis = findViewById(R.id.txtAvis);

        Button btnAjouter = findViewById(R.id.btnAjout);
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new InsertGameTask().execute();

                Intent intentListe = new Intent(AjouterJeuActivity.this, ListeJeuxActivity.class);
                startActivity(intentListe);
            }
        });

        Button btnAnnuler = findViewById(R.id.btnAnnuler);
        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentAccueil = new Intent(AjouterJeuActivity.this, MainActivity.class);
                startActivity(intentAccueil);
            }
        });
    }

    private class InsertGameTask extends AsyncTask<Void, Void, Void > {

        @Override
        protected Void doInBackground(Void... voids){

            Jeu new_jeu = new Jeu();

            new_jeu.Nom = editNom.getText().toString();
            new_jeu.AnneeSortie = editAnnee.getText().toString();
            new_jeu.Studio = editStudio.getText().toString();
            new_jeu.Genre = editGenre.getText().toString();
            new_jeu.Description = editDescription.getText().toString();
            new_jeu.Note = editNote.getText().toString();
            new_jeu.Avis = editAvis.getText().toString();

            db.jeuDao().insertJeu(new_jeu);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(AjouterJeuActivity.this ,"Jeu ajouté avec succès", Toast.LENGTH_SHORT).show();
            finish();

        }
    }
}
