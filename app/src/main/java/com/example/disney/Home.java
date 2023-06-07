package com.example.disney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    public void Quiz(View view) {
        Intent intent = new Intent (getApplicationContext(), quiz.class);
        startActivity(intent);
    }
    public void Exibicao(View view) {
        Intent intent = new Intent (getApplicationContext(), exibicao.class);
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
    public void Favoritos(View view) {
        Intent intent = new Intent (getApplicationContext(), Favoritos.class);
        startActivity(intent);
    }
}