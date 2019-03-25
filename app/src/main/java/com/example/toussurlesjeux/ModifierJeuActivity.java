package com.example.toussurlesjeux;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModifierJeuActivity extends AppCompatActivity {

    TousSurLesJeuxDatabase db;

    @Override
    protected void onStart() {
        super.onStart();
        new GetTask().execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_jeu);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Modifier un jeu");

        db = TousSurLesJeuxDatabase.getDatabase(this);
        //new GetTask().execute();

        Button btnModifier = findViewById(R.id.btnModifier);
        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateTask().execute();

                /*Intent intentListe = new Intent(ModifierJeuActivity.this, ListeJeuxActivity.class);
                startActivity(intentListe);*/

                onBackPressed();
            }
        });

        Button btnAnnuler = findViewById(R.id.btnAnnuler);
        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentListe = new Intent(ModifierJeuActivity.this, ListeJeuxActivity.class);
                startActivity(intentListe);
            }
        });
    }

    private class GetTask extends AsyncTask<Void, Void, Void> {

        Jeu jeu = new Jeu();

        @Override
        protected Void doInBackground(Void... voids) {
            Intent intent = getIntent();
            db = TousSurLesJeuxDatabase.getDatabase(ModifierJeuActivity.this);

            if (intent.getExtras() != null ){
                long id = intent.getExtras().getLong("CONST_ID");
                jeu = db.jeuDao().findById(id);

                /*TextView editNom = findViewById(R.id.txtNomModif);
                TextView editAnnee = findViewById(R.id.txtAnneeModif);
                TextView editStudio = findViewById(R.id.txtStudioModif);
                TextView editGenre = findViewById(R.id.txtGenreModif);
                TextView editDescription = findViewById(R.id.txtDescriptionModif);
                TextView editNote = findViewById(R.id.txtNoteModif);
                TextView editAvis = findViewById(R.id.txtAvisModif);

                editNom.setText(jeu.Nom);
                editAnnee.setText(jeu.AnneeSortie);
                editStudio.setText(jeu.Studio);
                editGenre.setText(jeu.Genre);
                editDescription.setText(jeu.Description);
                editNote.setText(jeu.Note);
                editAvis.setText(jeu.Avis);*/
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            EditText editNom = findViewById(R.id.txtNomModif);
            EditText editAnnee = findViewById(R.id.txtAnneeModif);
            EditText editStudio = findViewById(R.id.txtStudioModif);
            EditText editGenre = findViewById(R.id.txtGenreModif);
            EditText editDescription = findViewById(R.id.txtDescriptionModif);
            EditText editNote = findViewById(R.id.txtNoteModif);
            EditText editAvis = findViewById(R.id.txtAvisModif);

            editNom.setText(jeu.Nom);
            editAnnee.setText(jeu.AnneeSortie);
            editStudio.setText(jeu.Studio);
            editGenre.setText(jeu.Genre);
            editDescription.setText(jeu.Description);
            editNote.setText(jeu.Note);
            editAvis.setText(jeu.Avis);
        }
    }

    private class UpdateTask extends AsyncTask<Void, Void, Void>{

        Jeu jeu = new Jeu();

        @Override
        protected Void doInBackground(Void... voids) {
            Intent intent = getIntent();
            db = TousSurLesJeuxDatabase.getDatabase(ModifierJeuActivity.this);

            if (intent.getExtras() != null ){
                long id = intent.getExtras().getLong("CONST_ID");
                jeu = db.jeuDao().findById(id);

                EditText editNom = findViewById(R.id.txtNomModif);
                EditText editAnnee = findViewById(R.id.txtAnneeModif);
                EditText editStudio = findViewById(R.id.txtStudioModif);
                EditText editGenre = findViewById(R.id.txtGenreModif);
                EditText editDescription = findViewById(R.id.txtDescriptionModif);
                EditText editNote = findViewById(R.id.txtNoteModif);
                EditText editAvis = findViewById(R.id.txtAvisModif);

                jeu.Nom = editNom.getText().toString();
                jeu.AnneeSortie = editAnnee.getText().toString();
                jeu.Studio = editStudio.getText().toString();
                jeu.Genre = editGenre.getText().toString();
                jeu.Description = editDescription.getText().toString();
                jeu.Note = editNote.getText().toString();
                jeu.Avis = editAvis.getText().toString();


                db.jeuDao().updateJeu(jeu);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(ModifierJeuActivity.this, "Jeu bien modifié", Toast.LENGTH_SHORT);
            finish();
        }
    }
}
