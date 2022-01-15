package com.example.spassion.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spassion.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText tfemail,tfpassword;
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static FirebaseUser currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tfpassword=findViewById(R.id.tfpassword);
        tfemail=findViewById(R.id.tfemail);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth != null) {
            mAuth.signOut();
            currentUser = mAuth.getCurrentUser();
        }

        // User is already signed in
        if (currentUser != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    public void goToSignUpScreen(View view) {

        startActivity(new Intent(this,SignUpActivity.class));
    }

    public void signIn(View view) {

        String email = tfemail.getText().toString();
        String password = tfpassword.getText().toString();

        Log.d("login: ", email);
        Log.d("login: ", password);

        if (email.equals("") || password.equals("")) {
            Toast.makeText(LoginActivity.this, "Login failed.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

//        Firebase login
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("login: ", "success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Login failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//        // Allow full access to app for now
//        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }


}
