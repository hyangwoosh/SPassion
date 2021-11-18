package com.example.spassion.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spassion.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        // Launch the layout -> splash.xml
        setContentView(R.layout.activity_splash);
        Thread splashThread = new Thread() {

            public void run() {
                try {
                    // sleep time in milliseconds (2000 = 2sec)
                    sleep(2000);
                }  catch(InterruptedException e) {
                    // Trace the error
                    e.printStackTrace();
                } finally
                {
                    // Launch the MainActivity class
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            } // End of run()
        }; // End of splashThread()

        // To Start the thread
        splashThread.start();
    }// End of onCreate()

} // End of SplashActivity