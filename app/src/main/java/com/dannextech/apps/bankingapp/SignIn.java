package com.dannextech.apps.bankingapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignIn extends AppCompatActivity {

    Button btLogin;
    EditText etUser, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btLogin = (Button) findViewById(R.id.btnLogin);
        etPass = (EditText) findViewById(R.id.etPassword);
        etUser = (EditText) findViewById(R.id.etUserName);

        final BankQueries query = new BankQueries(this);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (query.authenticateUser(etUser.getText().toString(),etPass.getText().toString())){
                    Snackbar.make(v,"Login Successfull",Snackbar.LENGTH_LONG).setAction("action",null).show();
                    startActivity(new Intent(getApplicationContext(),Home.class));
                }else {
                    Snackbar.make(v,"Enter the correct password",Snackbar.LENGTH_LONG).setAction("action",null).show();
                }
            }
        });
    }
}
