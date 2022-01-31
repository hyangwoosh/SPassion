package com.example.spassion.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spassion.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ShopActivity extends AppCompatActivity {
    private AdView mAdView;
    Button b1;
    ImageView im;

    private Bitmap bmp;
    private Bitmap operation;
    double red, green, blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        b1 = (Button) findViewById(R.id.button7);
        im = (ImageView) findViewById(R.id.imageView4);

        BitmapDrawable abmp = (BitmapDrawable) im.getDrawable();
        bmp = abmp.getBitmap();
    }

    public void handlePurchase(View view) {
        startActivity(new Intent(ShopActivity.this, CheckoutActivity.class));
    }

    public void handleBackToHomeFromPurchase(View view) {
        startActivity(new Intent(ShopActivity.this, MainActivity.class));
    }

    public void sharpen() {
        double[][] SharpConfig = new double[][] {
                { 0 , -2    , 0  },
                { -2, 11, -2 },
                { 0 , -2    , 0  }
        };
        ConvolutionMatrix convMatrix = new ConvolutionMatrix(3);
        convMatrix.applyConfig(SharpConfig);
        convMatrix.Factor = 3;
        Bitmap bmOut = ConvolutionMatrix.computeConvolution3x3(bmp, convMatrix);
        im.setImageBitmap(bmOut);
    }

    public void doGamma(View view) {
        // define rgb
        red = 1.8;
        green = 1.8;
        blue = 1.8;

        // create output image
        Bitmap bmOut = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        // get image size
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        // color information
        int A, R, G, B;
        int pixel;
        // constant value curve
        final int    MAX_SIZE = 256;
        final double MAX_VALUE_DBL = 255.0;
        final int    MAX_VALUE_INT = 255;
        final double REVERSE = 1.0;

        // gamma arrays
        int[] gammaR = new int[MAX_SIZE];
        int[] gammaG = new int[MAX_SIZE];
        int[] gammaB = new int[MAX_SIZE];

        // setting values for every gamma channels
        for(int i = 0; i < MAX_SIZE; ++i) {
            gammaR[i] = (int)Math.min(MAX_VALUE_INT,
                    (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / red)) + 0.5));
            gammaG[i] = (int)Math.min(MAX_VALUE_INT,
                    (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / green)) + 0.5));
            gammaB[i] = (int)Math.min(MAX_VALUE_INT,
                    (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / blue)) + 0.5));
        }

        // apply gamma table
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get pixel color
                pixel = bmp.getPixel(x, y);
                A = Color.alpha(pixel);
                // look up gamma
                R = gammaR[Color.red(pixel)];
                G = gammaG[Color.green(pixel)];
                B = gammaB[Color.blue(pixel)];
                // set new color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        // return final image
        im.setImageBitmap(bmOut);
    }
    public void doNormal(View view) {
        setContentView(R.layout.activity_shop);
//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
        b1 = (Button) findViewById(R.id.button7);
        im = (ImageView) findViewById(R.id.imageView4);

        BitmapDrawable abmp = (BitmapDrawable) im.getDrawable();
        bmp = abmp.getBitmap();
    }

    public void doNormal2(View view) {
        // define rgb
        red = 1;
        green = 1;
        blue = 1;

        // create output image
        Bitmap bmOut = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        // get image size
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        // color information
        int A, R, G, B;
        int pixel;
        // constant value curve
        final int    MAX_SIZE = 256;
        final double MAX_VALUE_DBL = 255.0;
        final int    MAX_VALUE_INT = 255;
        final double REVERSE = 1.0;

        // gamma arrays
        int[] gammaR = new int[MAX_SIZE];
        int[] gammaG = new int[MAX_SIZE];
        int[] gammaB = new int[MAX_SIZE];

        // setting values for every gamma channels
        for(int i = 0; i < MAX_SIZE; ++i) {
            gammaR[i] = (int)Math.min(MAX_VALUE_INT,
                    (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / red)) + 0.5));
            gammaG[i] = (int)Math.min(MAX_VALUE_INT,
                    (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / green)) + 0.5));
            gammaB[i] = (int)Math.min(MAX_VALUE_INT,
                    (int)((MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / blue)) + 0.5));
        }

        // apply gamma table
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                // get pixel color
                pixel = bmp.getPixel(x, y);
                A = Color.alpha(pixel);
                // look up gamma
                R = gammaR[Color.red(pixel)];
                G = gammaG[Color.green(pixel)];
                B = gammaB[Color.blue(pixel)];
                // set new color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

        // return final image
        im.setImageBitmap(bmOut);
    }

    public void normal(View view) {
        operation = Bitmap.createBitmap(bmp.getWidth(),bmp.getHeight(), bmp.getConfig());

        im.setImageBitmap(operation);
    }
}