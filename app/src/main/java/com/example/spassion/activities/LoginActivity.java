package com.example.spassion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.spassion.R;

public class LoginActivity extends AppCompatActivity {
    EditText tfemail,tfpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tfpassword=findViewById(R.id.tfpassword);
        tfemail=findViewById(R.id.tfemail);
    }

    public void goToSignUpScreen(View view) {

        startActivity(new Intent(this,SignUpActivity.class));
    }

    public void signIn(View view) {
        if(!(tfemail.getText().toString().equals("")&&tfpassword.getText().toString().equals(""))){
            startActivity(new Intent(LoginActivity.this,SignOutActivity.class));
        }

    }
}
