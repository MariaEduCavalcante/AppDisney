package com.example.disney;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class quiz extends AppCompatActivity {

    private ImageView nmImagem;
    private ImageView nmCoberta3;
    private ImageView nmCoberta1;
    private ImageView nmCoberta2;

    private EditText nmResposta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz);
        nmImagem = findViewById(R.id.imgAdivinha);
        nmResposta = findViewById(R.id.editTxtQuiz);

        //final ImageView nmCoberta = findViewById(R.id.imgCoberta);
        //LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) nmCoberta.getLayoutParams();
        //layoutParams.width = 214;
        //layoutParams.height = 219; // Pode usar uma variável
        //nmCoberta.setLayoutParams(layoutParams);

    }

    //define os personagens possiveis
    String[] personagens = {"Ariel", "Rapunzel", "Mickey", "Mulan", "Tiana", "Mirabel", "Bela", "Moana"};
    String[] personagensIMG = {"https://static.wikia.nocookie.net/disneyprincesas/images/1/1c/Ariel_pose.png/revision/latest?cb=20140215012320&path-prefix=pt-br", "https://static.wikia.nocookie.net/disney/images/8/82/Rapunzel_pose.png/revision/latest?cb=20160324225337&path-prefix=pt-br", "https://upload.wikimedia.org/wikipedia/pt/d/d4/Mickey_Mouse.png", "https://i.pinimg.com/originals/0f/05/60/0f05601cdc3deff64252497f462b1687.png", "https://i.pinimg.com/originals/67/20/33/672033ccd2bea6e4212c94a2659a5e02.png", "https://i.pinimg.com/originals/24/f4/cd/24f4cd71533d587638bd0b77581caee4.png", "https://i0.wp.com/imagensemoldes.com.br/wp-content/uploads/2021/04/Bela-Beauty-And-The-Beast-PNG.png?fit=860%2C1214&ssl=1", "https://www.imagenspng.com.br/wp-content/uploads/2017/03/moana-01-920x1024.png"};
    //cria uma variavel random
    int x = new Random().nextInt(8);
    //atribui o valor aleatorio
    String NomePersonagem = personagens[x];
    String ImgPersonagem = personagensIMG[x];
    // esconde o teclado qdo o botão é clicado

    public void Iniciar(View view){
        nmCoberta2 = findViewById(R.id.imgCoberta2);
        Picasso.get().load(ImgPersonagem).into(nmImagem);
        nmCoberta2.setVisibility(View.INVISIBLE);
    }

    public void Adivinhar(View view){
        nmCoberta3 = findViewById(R.id.imgCoberta3);
        nmCoberta1 = findViewById(R.id.imgCoberta1);

        String resposta = nmResposta.getText().toString();

        if(resposta.equals(NomePersonagem)){
            nmCoberta3.setVisibility(View.INVISIBLE);
            nmCoberta1.setVisibility(View.INVISIBLE);
            Toast.makeText(quiz.this, "Parabéns!!", Toast.LENGTH_SHORT).show();
        } else{
            nmCoberta3.setVisibility(View.VISIBLE);
            nmCoberta1.setVisibility(View.INVISIBLE);
            Toast.makeText(quiz.this, "Tente novamente!!", Toast.LENGTH_SHORT).show();
        }

    }




    public void Pesquisa(View view) {
        Intent intent = new Intent (getApplicationContext(), pesquisa.class);
        startActivity(intent);
    }
    public void QuizNovo(View view) {
        Intent intent = new Intent (getApplicationContext(), quiz.class);
        startActivity(intent);
    }
    public void Home(View view) {
        Intent intent = new Intent (getApplicationContext(), Home.class);
        startActivity(intent);
    }
}