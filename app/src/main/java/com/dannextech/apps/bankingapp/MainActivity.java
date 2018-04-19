package com.dannextech.apps.bankingapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void signUp(View v){
        startActivity(new Intent(this,SignUp.class));
    }

    public void signIn(View v){
        startActivity(new Intent(this,SignIn.class));
    }
}
