package com.example.spassion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spassion.R;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
    }

    public void handlePurchase(View view) {
        startActivity(new Intent(ShopActivity.this, CheckoutActivity.class));
    }

    public void handleBackToHomeFromPurchase(View view) {
        startActivity(new Intent(ShopActivity.this, MainActivity.class));
    }
}