package com.example.disney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = ".MESSAGE";

    EditText username, password;
    Button btnlogin;
    DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        username = findViewById(R.id.txtUsuarioL);
        password = findViewById(R.id.txtSenhaL);
        btnlogin = findViewById(R.id.btnLogin);

        DB = new DBhelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals(""))
                    Toast.makeText(Login.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass == true){
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        String message = user;
                        intent.putExtra(EXTRA_MESSAGE, message);
                        Toast.makeText(Login.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                    }else {
                        Toast.makeText(Login.this, "Nome ou senha inválidos", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }


    public void mostrarCadastro(View view) {
        Intent intent = new Intent(Login.this, Cadastro.class);
        startActivity(intent);
    }


}