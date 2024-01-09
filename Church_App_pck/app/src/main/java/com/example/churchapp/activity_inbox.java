package com.example.churchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_inbox extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
    }

    public void OpenSideMenu(View view) {
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
}