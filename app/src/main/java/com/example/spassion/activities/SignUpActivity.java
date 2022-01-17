package com.example.spassion.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spassion.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    ConstraintLayout alert_layout;
    EditText email,password,confirmPassword;
    TextView timetext,textVerification,btnResend;
    Button btnSignUp;
    FirebaseAuth mAuth = LoginActivity.mAuth;

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

        // Start of validations
        if (email.getText().toString().equals("")) {
            Toast.makeText(SignUpActivity.this, "Enter your Email", Toast.LENGTH_LONG).show();
            return;
        }

        if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
            Toast.makeText(SignUpActivity.this, "The password you entered are not identical", Toast.LENGTH_LONG).show();
            return;
        }

        if (password.getText().toString().equals("") || confirmPassword.getText().toString().equals("")) {
            Toast.makeText(SignUpActivity.this, "Enter your password", Toast.LENGTH_LONG).show();
            return;
        }
        // End of validations

        String emailStr = email.getText().toString();
        String passwordStr = password.getText().toString();

        // Create account in firebase
        mAuth.createUserWithEmailAndPassword(emailStr, passwordStr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Create Account", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignUpActivity.this, "Account has been created!",
                                    Toast.LENGTH_SHORT).show();

                            alert_layout.setVisibility(View.VISIBLE);
                            btnResend.setVisibility(View.VISIBLE);
                            textVerification.setVisibility(View.VISIBLE);
                            timetext.setVisibility(View.VISIBLE);
                            btnResend.setEnabled(false);
                            btnSignUp.setText("Back to Login");
                            StartCounter();
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Create Account", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Account creation failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
    }
}