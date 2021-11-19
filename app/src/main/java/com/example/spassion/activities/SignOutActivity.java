package com.example.spassion.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;

import com.example.spassion.R;

public class SignOutActivity extends AppCompatActivity {
      ConstraintLayout alert_msg_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_out);
        alert_msg_layout=findViewById(R.id.alert_msg_layout);
        alert_msg_layout.setVisibility(View.GONE);
    }

    public void signOut(View view) {
        alert_msg_layout.setVisibility(View.VISIBLE);
    }

    public void closeAlert(View view) {
        alert_msg_layout.setVisibility(View.GONE);
    }
}