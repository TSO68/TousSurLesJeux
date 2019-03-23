package com.example.toussurlesjeux;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ListeJeuxActivity extends AppCompatActivity {

    LinearLayout layoutJeux;
    LinearLayout unJeu;

    private List<Jeu> listJeux;
    TousSurLesJeuxDatabase db;

    public TextView firstGame;

    @Override
    protected void onStart() {
        super.onStart();
        new GetListJeuxTask().execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_jeux);
        layoutJeux = findViewById(R.id.listeJeux);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Liste des jeux");

        //firstGame = (TextView) findViewById(R.id.textView);

        db = TousSurLesJeuxDatabase.getDatabase(this);

        //new InsertTestTask().execute();
        //new GetListJeuxTask().execute();

        FloatingActionButton fabAjout = findViewById(R.id.fabAjout);
        fabAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AjouterJeuActivity.class);
                startActivity(intent);
            }
        });
    }

    private class InsertTestTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            Jeu firstGame = new Jeu();
            firstGame.Nom = "GTA San Andreas";
            firstGame.AnneeSortie = "2004";
            firstGame.Studio = "Rockstar";
            firstGame.Genre = "Action";
            firstGame.Description = "You pick the wrong house fool";
            firstGame.Note = "20/20";
            firstGame.Avis = "Meilleur jeu ever";

            db.jeuDao().insertJeu(firstGame);
            return null;
        }
    }

    /*private class GetListJeuxTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            listJeux = db.jeuDao().getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            firstGame.setText(listJeux.get(0).Nom);

        }

    }*/

    private class GetListJeuxTask extends AsyncTask<Void, Void, Void>{


        @Override
        protected Void doInBackground(Void... voids) {
            listJeux = db.jeuDao().getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            layoutJeux.removeAllViews();
            for (Jeu jeu : listJeux){

                final long id = jeu.Id;
                TextView txtNomJeu = new TextView(ListeJeuxActivity.this);
                Button btnVersFiche = new Button(ListeJeuxActivity.this);

                btnVersFiche.setText("Fiche du jeu");

                txtNomJeu.setText(jeu.Nom);
                btnVersFiche.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentFiche = new Intent(getBaseContext(), FicheJeuActivity.class);
                        intentFiche.putExtra("CONST_ID", id);
                        startActivity(intentFiche);
                    }
                });

                txtNomJeu.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                layoutJeux.addView(txtNomJeu);
                layoutJeux.addView(btnVersFiche);
            }
        }
    }
}
