package com.example.toussurlesjeux;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.FileHandler;

public class FicheJeuActivity extends AppCompatActivity {

    TousSurLesJeuxDatabase db;

    private Jeu unJeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_jeu);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Fiche du jeu");

        db = TousSurLesJeuxDatabase.getDatabase(this);
        new GetFicheTask().execute();

        Button btnModifier = findViewById(R.id.btnModifier);
        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentModifier = new Intent(getBaseContext(), ModifierJeuActivity.class);
                startActivity(intentModifier);
            }
        });

        Button btnSupprimer = findViewById(R.id.btnSupprimer);
        btnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteTask().execute();

                Intent intentListe = new Intent(FicheJeuActivity.this, ListeJeuxActivity.class);
                startActivity(intentListe);
            }
        });
    }

    private class GetFicheTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Intent intent = getIntent();
            if (intent.getExtras() != null ){
                long id = intent.getExtras().getLong("CONST_ID");
                Jeu jeu = db.jeuDao().findById(id);

                TextView txtNom = findViewById(R.id.txtPresNom);
                TextView txtAnnee = findViewById(R.id.txtPresAnnee);
                TextView txtStudio = findViewById(R.id.txtPresStudio);
                TextView txtGenre = findViewById(R.id.txtPresGenre);
                TextView txtDescription = findViewById(R.id.txtPresDescription);
                TextView txtNote = findViewById(R.id.txtPresNote);
                TextView txtAvis = findViewById(R.id.txtPresAvis);

                txtNom.setText(jeu.Nom);
                txtAnnee.setText(jeu.AnneeSortie);
                txtStudio.setText(jeu.Studio);
                txtGenre.setText(jeu.Genre);
                txtDescription.setText(jeu.Description);
                txtNote.setText(jeu.Note);
                txtAvis.setText(jeu.Avis);
            }
            return null;
        }
    }

    private class DeleteTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            Intent intent = getIntent();
            if (intent.getExtras() != null ){
                long id = intent.getExtras().getLong("CONST_ID");
                Jeu jeu = db.jeuDao().findById(id);
                db.jeuDao().deleteJeu(jeu);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(FicheJeuActivity.this, "Le jeu a été supprimé", Toast.LENGTH_SHORT);
            finish();
        }
    }
}
