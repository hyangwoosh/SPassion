package com.example.spassion.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spassion.R;
import com.example.spassion.fragments.HomeFragment;
import com.example.spassion.fragments.ProfileFragment;
import com.example.spassion.fragments.SignUpFragment;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bottom nav
        BottomNavigationView bottomNav = findViewById(R.id.nav_bottom);
        bottomNav.setOnItemSelectedListener(navListener);

        // Set default fragment when MainActivity first loads i.e. HomeFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        // Camera configurations
        // Check if permission to access camera is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Request user for permission to access the camera
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA
            }, REQUEST_CAMERA_CODE);
        }
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
                        case R.id.menu_sign_up:
                            selectedFragment = new SignUpFragment();
                            break;
                        case R.id.menu_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                    };
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }; // End of onNavigationItemSelected
            }; // End of NavigationBarView.OnItemSelectedListener

    public void handleProfileLinks(View view) {
        int viewID = view.getId();

        switch (viewID) {
            case R.id.profile_link_campus_map: {
                Toast.makeText(MainActivity.this, "You are viewing the campus map",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, CampusMapActivity.class));
                break;
            }
            case R.id.profile_link_shop: {
                startActivity(new Intent(MainActivity.this, ShopActivity.class));
                break;
            }
            case R.id.profile_link_log_out: {
                LoginActivity.mAuth.signOut();
                Toast.makeText(MainActivity.this, "Successfully logged out.",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            }
        }
    }

    public void handleOCRClick(View view) {
        // Initiate crop image activity
        CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(MainActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

                Uri resultUri = result.getUri();
                try {
//                    ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), resultUri);
//                    Bitmap bitmap = ImageDecoder.decodeBitmap(source);
                    Bitmap bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                    getTextFromImage(bitmap2);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    Toast.makeText(MainActivity.this, "An error occured!",
                            Toast.LENGTH_LONG).show();
                }
        }
    }

    private void getTextFromImage(Bitmap bitmap) {
        TextRecognizer recognizer = new TextRecognizer.Builder(this).build();
        if (!recognizer.isOperational()) {
            Log.d("error", "error");
            Toast.makeText(this,"Error occurred!",Toast.LENGTH_SHORT).show();
        }
        else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> textBlockSparseArray = recognizer.detect(frame);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < textBlockSparseArray.size(); i++) {
                TextBlock textBlock = textBlockSparseArray.valueAt(i);
                stringBuilder.append(textBlock.getValue());
                stringBuilder.append("\n");
            }

            String recognizedText = stringBuilder.toString();
            if (recognizedText != "") {
                EditText atsInput = (EditText) findViewById(R.id.editTextNumber);
                atsInput.setText(recognizedText);
            }
            Log.d("ran", recognizedText + " ");
        }
    }

    public void handleSubmitATS(View view) {
        EditText editTextATS = (EditText) findViewById(R.id.editTextNumber);

        if (editTextATS.getText().toString() != "") {
            Toast.makeText(MainActivity.this, "Your ATS has been successfully submitted",
                    Toast.LENGTH_LONG).show();

            editTextATS.setText("");
        }
        else {
            Toast.makeText(MainActivity.this, "Error! Empty input",
                    Toast.LENGTH_LONG).show();
        }


    }

    public void handleCCASignUp(View view) {
        Toast.makeText(MainActivity.this, "Your CCA application has been successfully submitted",
                Toast.LENGTH_LONG).show();
    }
} // End of MainActivity