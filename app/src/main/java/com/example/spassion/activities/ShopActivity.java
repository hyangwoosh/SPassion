package com.example.spassion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spassion.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ShopActivity extends AppCompatActivity {
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void handlePurchase(View view) {
        startActivity(new Intent(ShopActivity.this, CheckoutActivity.class));
    }

    public void handleBackToHomeFromPurchase(View view) {
        startActivity(new Intent(ShopActivity.this, MainActivity.class));
    }
}