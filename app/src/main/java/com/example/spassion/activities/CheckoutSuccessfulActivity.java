package com.example.spassion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spassion.R;
import com.stripe.android.view.CardInputWidget;

public class CheckoutSuccessfulActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_successful);
    }

    public void handleBackToHome(View view) {
        startActivity(new Intent(CheckoutSuccessfulActivity.this, MainActivity.class));
    }
}