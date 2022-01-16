package com.example.spassion.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.spassion.R;
import com.example.spassion.activities.LoginActivity;
import com.example.spassion.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        if (currentUser != null) {
            TextView emailDisplay = (TextView) v.findViewById(R.id.profile_user_email);
            emailDisplay.setText(currentUser.getEmail());
        }
        return v;
    } // End of onCreateView


} // End of ProfileFragment