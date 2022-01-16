package com.example.spassion.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.spassion.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SignUpFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        //get the spinner from the xml.
        Spinner dropdown = view.findViewById(R.id.spinner1);

        ArrayList<String> list = new ArrayList<>();
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, list);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        // Get list of CCAs from firebase realtime database
        DatabaseReference reference = FirebaseDatabase.getInstance("https://spassion-241a9-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("CCA");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
            for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                list.add(snapshot.getValue().toString());
            }
            adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;

    } // End of onCreateView

} // End of SignUpFragment
