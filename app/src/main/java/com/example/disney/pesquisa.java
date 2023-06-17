package com.example.disney;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class pesquisa extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{
    private EditText nmPesquisa;

    Button bSetWallpaper;
    private TextView nmNome;private TextView nmFilme;private TextView nmShow;private ImageView nmImagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        nmPesquisa = findViewById(R.id.txtPesquisa);
        nmNome = findViewById(R.id.txtNome);
        nmFilme = findViewById(R.id.txtFilme);
        nmShow = findViewById(R.id.txtShow);
        nmImagem = findViewById(R.id.imgPersona);
        if (LoaderManager.getInstance(this).getLoader(0) != null) {
            LoaderManager.getInstance(this).initLoader(0, null, this);
        }

        pesquisa buscaPersonagem = new pesquisa();

    }

    public void buscaPersonagem(View view) {
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
                nmFilme.setText(R.string.str_empty);
                nmNome.setText(R.string.no_search_term);
                nmShow.setText(R.string.no_search_term);
            } else {
                nmFilme.setText(" ");
                nmNome.setText(R.string.no_network);
                nmShow.setText(R.string.no_network);
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
        return new DisneyAPI(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            // Converte a resposta em Json
            JSONObject jsonObject = new JSONObject(data);
            // Obtem o JSONArray dos itens de livros
            JSONArray itemsArray = jsonObject.getJSONArray("data");
            // inicializa o contador
            int i = 0;
            String nome = null;
            String filme = null;
            String show = null;
            String imagem = null;
            // Procura pro resultados nos itens do array
            while (i < itemsArray.length() &&
                    (filme == null)) {
                // Obtem a informação

                Log.v("ERRO APLICAÇÃO", String.valueOf(itemsArray));
                JSONObject persona = itemsArray.getJSONObject(i);

                JSONArray volumeInfo = persona.getJSONArray("films");
                JSONArray showInfo = persona.getJSONArray("tvShows");//


                //  Obter autor e titulo para o item,
                // erro se o campo estiver vazio
                try {
                   nome = persona.getString("name");
                   filme =  volumeInfo.getString(0);
                   show =  showInfo.getString(0);
                   imagem = persona.getString("imageUrl");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // move para a proxima linha
                i++;
            }
            //mostra o resultado qdo possivel.
            if (filme != null) {
                nmShow.setText("TvShow: " + show);
                nmNome.setText(nome);
                nmFilme.setText("Filme: " + filme);
                //picasso serve carregar a imagem
                Picasso.get().load(imagem).into(nmImagem);

            } else {
                // If none are found, update the UI to show failed results.
                //nmNome.setText("resultado");
                //nmFilme.setText("falho");
            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            //nmNome.setText("deu");
            //nmFilme.setText("errado");
        }

        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        bSetWallpaper = findViewById(R.id.btnDefinir);
        bSetWallpaper.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {

               // try {
                   // wallpaperManager.setResource(Picasso.get().load());
                //} catch (IOException e) {
                    //e.printStackTrace();
               // }
            }
        });
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada
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