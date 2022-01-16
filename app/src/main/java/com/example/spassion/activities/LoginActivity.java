package com.example.spassion.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

    // Variable declarations
    EditText tfemail,tfpassword;
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static FirebaseUser currentUser = null;
    public static final String loginPref = "LoginPref";
    public static final String loginPrefEmail = "loginPrefEmail";
    public static final String loginPrefPassword = "loginPrefPassword";
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tfpassword=findViewById(R.id.tfpassword);
        tfemail=findViewById(R.id.tfemail);

        // Shared preference
        prefs = getSharedPreferences(loginPref, MODE_PRIVATE);
        String email = prefs.getString(loginPrefEmail, "");
        String password = prefs.getString(loginPrefPassword, "");

        tfemail.setText(email);
        tfpassword.setText(password);

        // Firebase authentication
        mAuth = FirebaseAuth.getInstance();
        if (mAuth != null) {
            mAuth.signOut();
            currentUser = mAuth.getCurrentUser();
        }

        // Check if user is already signed in
        if (currentUser != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    } // End of onCreate


    @Override
    public void onPause(){
        super.onPause();

        tfpassword=findViewById(R.id.tfpassword);
        tfemail=findViewById(R.id.tfemail);

        prefs = getSharedPreferences(loginPref, MODE_PRIVATE);
        String email = tfemail.getText().toString();
        String password = tfpassword.getText().toString();

        // Get the text from the EditText field
        SharedPreferences.Editor editor = prefs.edit();

        // Store into the SharedPreferences
        editor.putString(loginPrefEmail,email);
        editor.putString(loginPrefPassword,password);
        editor.commit();

    } // End of onPause


    public void goToSignUpScreen(View view) {

        startActivity(new Intent(this,SignUpActivity.class));
    } // End of goToSignUpScreen

    public void signIn(View view) {

        String email = tfemail.getText().toString();
        String password = tfpassword.getText().toString();


        if (email.equals("") || password.equals("")) {
            Toast.makeText(LoginActivity.this, "Login failed.",
                    Toast.LENGTH_SHORT).show();
            return;
        } // End of if

//        Firebase login
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("login: ", "success");
                            prefs = getSharedPreferences(loginPref, MODE_PRIVATE);

                            // Get the text from the EditText field
                            SharedPreferences.Editor editor = prefs.edit();

                            // Store into the SharedPreferences
                            editor.putString(loginPrefEmail,email);
                            editor.putString(loginPrefPassword,password);
                            editor.commit();

                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Login failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    } // End of signIn
} // End of class
