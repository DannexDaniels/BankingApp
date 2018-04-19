package com.dannextech.apps.bankingapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    EditText etname,etid,etphone,etemail,etrealpass,etconfpass,etage;
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final BankQueries query = new BankQueries(this);

        etname = (EditText) findViewById(R.id.etName);
        etid = (EditText) findViewById(R.id.etIdNumber);
        etphone = (EditText) findViewById(R.id.etPhone);
        etage = (EditText) findViewById(R.id.etAge);
        etemail = (EditText) findViewById(R.id.etEmail);
        etrealpass = (EditText) findViewById(R.id.etPassReal);
        etconfpass = (EditText) findViewById(R.id.etPassConf);
        btSubmit = (Button) findViewById(R.id.btCreateAccount);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etrealpass.getText().toString().equals(etconfpass.getText().toString())){
                    if (query.createAccount(etname.getText().toString(),etid.getText().toString(),etage.getText().toString(),etemail.getText().toString(),etphone.getText().toString(),etrealpass.getText().toString())){
                        Snackbar.make(v,"Account Created Successfully!",Snackbar.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),SignIn.class));
                    }else {
                        Snackbar.make(v,"Something went wrong. Please try again.",Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Snackbar.make(v,"Passwords should match",Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}
