package com.example.spassion.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.spassion.R;
import com.example.spassion.fragments.EventsFragment;
import com.example.spassion.fragments.ExploreFragment;
import com.example.spassion.fragments.HomeFragment;
import com.example.spassion.fragments.ProfileFragment;
import com.example.spassion.fragments.SignUpFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bottom nav
        BottomNavigationView bottomNav = findViewById(R.id.nav_bottom);
        bottomNav.setOnItemSelectedListener(navListener);

        // Set default fragment when MainActivity first loads i.e. HomeFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    } // End of onCreate

    // This method is called in onCreate
    private NavigationBarView.OnItemSelectedListener navListener =
            new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.menu_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.menu_explore:
                            selectedFragment = new ExploreFragment();
                            break;
                        case R.id.menu_sign_up:
                            selectedFragment = new SignUpFragment();
                            break;
                        case R.id.menu_events:
                            selectedFragment = new EventsFragment();
                            break;
                        case R.id.menu_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                    };
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }; // End of onNavigationItemSelected
            }; // End of NavigationBarView.OnItemSelectedListener

} // End of MainActivity