package com.example.spassion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spassion.R;

public class SignUpActivity extends AppCompatActivity {
    ConstraintLayout alert_layout;
    EditText email,password,confirmPassword;
    TextView timetext,textVerification,btnResend;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        initView();
    }
    public void initView(){
        alert_layout=findViewById(R.id.alert_layout);
        confirmPassword=findViewById(R.id.tfcpassword);
        password=findViewById(R.id.tfpassword);
        email=findViewById(R.id.email);
        timetext=findViewById(R.id.timetext);
        btnResend=findViewById(R.id.btnResend);
        btnSignUp=findViewById(R.id.btnSignUp);
        textVerification=findViewById(R.id.textVerification);
        btnResend.setVisibility(View.GONE);
        textVerification.setVisibility(View.GONE);
        timetext.setVisibility(View.GONE);
        alert_layout.setVisibility(View.GONE);
        btnSignUp.setText("Sign Up");
        btnResend.setEnabled(false);
    }

    public void signUp(View view) {
        if(btnSignUp.getText().toString().equals("Sign Up")) {
            if (email.getText().toString().equals("")) {
                Toast.makeText(SignUpActivity.this, "Enter your Email", Toast.LENGTH_LONG).show();
            } else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                Toast.makeText(SignUpActivity.this, "The password you entered are not identical", Toast.LENGTH_LONG).show();
            } else if (password.getText().toString().equals("") || confirmPassword.getText().toString().equals("")) {
                Toast.makeText(SignUpActivity.this, "Enter your password", Toast.LENGTH_LONG).show();
            } else {
                alert_layout.setVisibility(View.VISIBLE);
                btnResend.setVisibility(View.VISIBLE);
                textVerification.setVisibility(View.VISIBLE);
                timetext.setVisibility(View.VISIBLE);
                btnResend.setEnabled(false);
                btnSignUp.setText("Back to Login");
                StartCounter();
            }
        }
        else{
            startActivity(new Intent(SignUpActivity.this,MainActivity.class));
            finish();
        }

    }

    public void resendEmail(View view) {

    }

    public void closeAlert(View view) {
        alert_layout.setVisibility(View.GONE);
    }
    public void StartCounter(){
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timetext.setText("You will be able to resend a confirmation email in " + millisUntilFinished / 1000+" second");
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                btnResend.setEnabled(true);
            }

        }.start();
    }

    public void back(View view) {
        startActivity(new Intent(SignUpActivity.this,MainActivity.class));
    }
}