package com.example.churchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.churchapp.utilities.Constants;
import com.example.churchapp.utilities.Database_Methods;
import com.example.churchapp.utilities.ManagePreferences;
import com.example.churchapp.utilities.User;
import com.makeramen.roundedimageview.RoundedImageView;

public class activity_profile extends AppCompatActivity {
    TextView names;
    RoundedImageView pp;
    Database_Methods databaseMethods;
    User user;
    ManagePreferences managePreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //*-----------------
        callViews();
        managePreferences = new ManagePreferences(getApplicationContext());
        ownUserDetails();
    }
    private void callViews() {
        names = findViewById(R.id.names);
        pp = findViewById(R.id.PP);
    }
    private void ownUserDetails(){
        names.setText(managePreferences.getString(Constants.Key_Name)+" "+managePreferences.getString(Constants.Key_Surname));
        Glide.with(pp.getContext()) // Pass the activity or fragment context
                .load(managePreferences.getString(Constants.Key_Image)) // Load the image from the URL
                .into(pp);
    }

    public void OpenHome(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void OpenInbox(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_inbox.class);
        startActivity(intent);
    }

    public void OpenProfile(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_profile.class);
        startActivity(intent);
    }

    public void OpenApps(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_menu.class);
        startActivity(intent);
    }

    public void addBackGround(View view) {
    }
}