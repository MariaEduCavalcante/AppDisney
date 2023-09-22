package com.example.disney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Playlist extends AppCompatActivity {
    private TextView nmDuracao;
    private EditText nmMusica1, nmMusica2, nmMusica3, nmMusica4, nmMusica5;
    private Button btnSalvar, btnAlterar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nmMusica1 = findViewById(R.id.txtNomeMusica1);
        nmMusica2 = findViewById(R.id.txtNomeMusica2);
        nmMusica3 = findViewById(R.id.txtMusica1);
        nmMusica4 = findViewById(R.id.txtNomeMusica4);
        nmMusica5 = findViewById(R.id.txtNomeMusica5);

        btnSalvar = findViewById(R.id.btnRegistrar4);
        btnAlterar = findViewById(R.id.btnRegistrar2);

        btnSalvar.setEnabled(false);
        btnSalvar.setVisibility(View.INVISIBLE);
    }

    public void Alterar(View view){
        nmMusica1.setEnabled(true);
        nmMusica2.setEnabled(true);
        nmMusica3.setEnabled(true);
        nmMusica4.setEnabled(true);
        nmMusica5.setEnabled(true);

        btnAlterar.setEnabled(false);
        btnAlterar.setVisibility(View.INVISIBLE);
        btnSalvar.setEnabled(true);
        btnSalvar.setVisibility(View.VISIBLE);
    }

    public void Salvar(View view){
        nmMusica1.setEnabled(false);
        nmMusica2.setEnabled(false);
        nmMusica3.setEnabled(false);
        nmMusica4.setEnabled(false);
        nmMusica5.setEnabled(false);

        btnAlterar.setEnabled(true);
        btnAlterar.setVisibility(View.VISIBLE);
        btnSalvar.setEnabled(false);
        btnSalvar.setVisibility(View.INVISIBLE);
    }


    public void Quiz(View view) {
        Intent intent = new Intent (getApplicationContext(), quiz.class);
        startActivity(intent);
    }
    public void Pesquisa(View view) {
        Intent intent = new Intent (getApplicationContext(), pesquisa.class);
        startActivity(intent);
    }
    public void Home(View view) {
        Intent intent = new Intent (getApplicationContext(), Home.class);
        startActivity(intent);
    }
}