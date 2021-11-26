package com.example.spassion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spassion.R;

public class SplashActivity extends AppCompatActivity {
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        // Launch the layout -> splash.xml
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.imageView2);
        logo.animate().translationY(-2000).setDuration(1500).setStartDelay(500);
        Thread splashThread = new Thread() {

            public void run() {
                try {
                    // sleep time in milliseconds (2000 = 2sec)
                    sleep(1500);
                }  catch(InterruptedException e) {
                    // Trace the error
                    e.printStackTrace();
                } finally
                {
                    // Launch the MainActivity class
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            } // End of run()
        }; // End of splashThread()

        // To Start the thread
        splashThread.start();
    }// End of onCreate()

} // End of SplashActivity