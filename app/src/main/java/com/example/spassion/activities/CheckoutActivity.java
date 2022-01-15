package com.example.spassion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spassion.R;
import com.stripe.android.view.CardInputWidget;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Set stripe component to not collect postal code
        CardInputWidget cardInputWidget = findViewById(R.id.cardInputWidget);
        cardInputWidget.setPostalCodeEnabled(false);
    }

    public void handlePay(View view) {
        startActivity(new Intent(CheckoutActivity.this, CheckoutSuccessfulActivity.class));
    }
}
