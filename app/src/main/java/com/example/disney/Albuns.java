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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Albuns extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private EditText nmPesquisa;
    private TextView nmAno, nmFilme, nmDiretor;

    private TextView nmNome1, nmNome2, nmNome3;
    private TextView nmTrack1, nmTrack2, nmTrack3;
    private TextView nmCompositor1, nmCompositor2, nmCompositor3;
    private TextView nmDuracao1, nmDuracao2, nmDuracao3;
    private Button btnMusica1, btnMusica2, btnMusica3;

    private ImageView nmImagem, nmCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albuns);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nmPesquisa = findViewById(R.id.Pesquisa);

        nmFilme = findViewById(R.id.txtNomeFilme);
        nmAno = findViewById(R.id.txtAno);
        nmFilme = findViewById(R.id.txtFilme);
        nmAno = findViewById(R.id.txtAnoFilme);
        nmDiretor = findViewById(R.id.txtDiretor);

        nmImagem = findViewById(R.id.imgAlbum);
        nmCover = findViewById(R.id.imgCover);

        nmNome1 = findViewById(R.id.txtNomeMusica1);
        nmTrack1 = findViewById(R.id.txtTrack1);
        nmCompositor1 = findViewById(R.id.txtCompositor1);
        nmDuracao1 = findViewById(R.id.txtDuracao1);
        btnMusica1 = findViewById(R.id.btnMusica1);

        nmNome2 = findViewById(R.id.txtNomeMusica2);
        nmTrack2 = findViewById(R.id.txtTrack2);
        nmCompositor2 = findViewById(R.id.txtCompositor2);
        nmDuracao2 = findViewById(R.id.txtDuracao2);
        btnMusica2 = findViewById(R.id.btnMusica2);

        nmNome3 = findViewById(R.id.txtNomeMusica3);
        nmTrack3 = findViewById(R.id.txtTrack3);
        nmCompositor3 = findViewById(R.id.txtCompositor3);
        nmDuracao3 = findViewById(R.id.txtDuracao3);
        btnMusica3 = findViewById(R.id.btnMusica3);

        if (LoaderManager.getInstance(this).getLoader(0) != null) {
            LoaderManager.getInstance(this).initLoader(0, null, this);
        }
    }

    public void buscaMusica(View view) {
        // Recupera a string de busca.
        String queryString = nmPesquisa.getText().toString();
        // esconde o teclado qdo o botão é clicado
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        // Verifica o status da conexão de rede
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        /* Se a rede estiver disponivel e o campo de busca não estiver vazio
         iniciar o Loader CarregaLivros */
        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            LoaderManager.getInstance(this).restartLoader(0, queryBundle, this);


        }
        // atualiza a textview para informar que não há conexão ou termo de busca
        else {
            if (queryString.length() == 0) {
                Toast.makeText(Albuns.this, "Termo inválido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Albuns.this, "Verifique a conexão", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";
        if (args != null) {
            queryString = args.getString("queryString");
        }
        return new MusicAPI(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        JSONObject musica = null;
        try {
            // Converte a resposta em Json
            JSONObject jsonObject = new JSONObject(data);
            // Obtem o JSONArray dos itens
            JSONArray itemsArray = jsonObject.getJSONArray("");
            // inicializa o contador
            int i = 0;

            String nomeFilme = null;
            String imagemAlbum = null;
            String anoFilme = null;

            String nomeMusica = null;
            String nomeCompositor = null;
            String track = null;
            String duracao = null;
            String linkMusica = null;

            String imagemCover = null;
            String nomeDiretor = null;


            /// Procura pro resultados nos itens do array
            while (i < itemsArray.length() &&
                    (nomeFilme == null)) {
                // Obtem a informação

                Log.v("ERRO APLICAÇÃO", String.valueOf(itemsArray));
                musica = itemsArray.getJSONObject(i);

                //  Obter autor e titulo para o item,
                // erro se o campo estiver vazio
                try {
                    nomeFilme = musica.getString("nameFilm");
                    imagemAlbum = musica.getString("coverURL");
                    anoFilme = musica.getString("premiere");

                    nomeMusica = musica.getString("nameSong");
                    nomeCompositor = musica.getString("nameComposer");
                    track = musica.getString("trackNumber");
                    duracao = musica.getString("duration");
                    linkMusica = musica.getString("songURL");

                    imagemCover = musica.getString("imgURL");
                    nomeDiretor = musica.getString("nameDirector");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // move para a proxima linha
                i++;
            }
            //mostra o resultado qdo possivel.
            if (nomeFilme != null) {
                nmFilme.setText(nomeFilme);
                nmAno.setText(anoFilme);
                nmDiretor.setText(nomeDiretor);

                nmNome1.setText(nomeMusica);
                nmCompositor1.setText(nomeCompositor);
                nmTrack1.setText(track);
                nmDuracao1.setText(duracao);
                btnMusica1.setText(linkMusica);

                Picasso.get().load(imagemCover).into(nmCover);
                Picasso.get().load(imagemAlbum).into(nmImagem);
            } else {
                Toast.makeText(Albuns.this, "Resultado não encontrado", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            //nmNome.setText("deu");
            //nmFilme.setText("errado");
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }


    public void Quiz(View view) {
        Intent intent = new Intent(getApplicationContext(), quiz.class);
        startActivity(intent);
    }

    public void Pesquisa(View view) {
        Intent intent = new Intent(getApplicationContext(), pesquisa.class);
        startActivity(intent);
    }

    public void Home(View view) {
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
    }
}