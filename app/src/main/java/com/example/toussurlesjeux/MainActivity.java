package com.example.toussurlesjeux;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle(null);

        Button btnListeJeux = findViewById(R.id.btnVersListe);
        btnListeJeux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentListe = new Intent(MainActivity.this, ListeJeuxActivity.class);
                startActivity(intentListe);
            }
        });

        Button btnAjouterJeu = findViewById(R.id.btnVersAjout);
        btnAjouterJeu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentAjout = new Intent(MainActivity.this, AjouterJeuActivity.class);
                startActivity(intentAjout);
            }
        });
    }
}
