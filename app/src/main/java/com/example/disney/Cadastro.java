package com.example.disney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {
    EditText username, password, repassword;
    Button signup ;
    DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        username = findViewById (R.id.txtUsuarioR);
        password = findViewById(R.id.txtSenhaR);
        repassword = findViewById(R.id.txtConfirmSenhaR);
        signup = findViewById( R.id.btnRegistrar);

        DB = new DBhelper(this);
    }


        public void Registrar(View view) {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();

            if(user.equals("") ||pass.equals("") || repass.equals(""))
                Toast.makeText(Cadastro.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            else{
                if(pass.equals(repass)){
                    Boolean checkuser = DB.checkusername(user);
                    if(checkuser==false){
                        Boolean insert = DB.insertData(user, pass);
                        if(insert==true){
                            Toast.makeText(Cadastro.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Cadastro.this, "Erro ao cadastrar", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else{
                        Toast.makeText(Cadastro.this, "Usuário já existente", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(Cadastro.this, "Senhas não correspondem", Toast.LENGTH_SHORT).show();

                }

            }

        }

    public void mostrarLogin(View view) {
        Intent intent = new Intent(Cadastro.this, Login.class);
        startActivity(intent);
    }
}