package com.example.churchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class activity_SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void ShowTitles(View view) {
    }

    public void ShowRoles(View view) {
    }

    public void OpenHelp(View view) {
    }

    public void OpenSignIn(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_SignIn.class);
        startActivity(intent);
    }

    public void SignUp(View view) {
    }
}