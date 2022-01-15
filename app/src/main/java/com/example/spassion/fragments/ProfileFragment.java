package com.example.spassion.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.spassion.R;
import com.example.spassion.activities.LoginActivity;
import com.example.spassion.activities.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    } // End of onCreateView


} // End of ProfileFragment