package com.example.churchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.churchapp.utilities.Database_Methods;
import com.example.churchapp.utilities.ManagePreferences;
import com.example.churchapp.utilities.User;

public class activity_SignUp extends AppCompatActivity {
    private String image;

    EditText name_in, email_in, email_in_c, passw_in, passw_in_c, surname_in;
    String name, surname, email, password;
    TextView addImg;
    Button signUp_btn;
    ProgressBar loadingProgressBar;
    ImageView imageProf;
    Database_Methods databaseMethods;
    User user;
    ManagePreferences managePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //-0-----------------------
        databaseMethods = new Database_Methods();
        user = new User();
        managePreferences = new ManagePreferences(getApplicationContext());
    }
    private void callViews(){
        name_in = findViewById(R.id.nameIn);
        email_in = findViewById(R.id.emailIn);
        email_in_c = findViewById(R.id.email_c_in);
        passw_in_c = findViewById(R.id.Password_c_in);
        passw_in = findViewById(R.id.Password_in);
        surname_in = findViewById(R.id.surname_in);
        signUp_btn = findViewById(R.id.buttonSignUp);
        loadingProgressBar = findViewById(R.id.progressBar);
        imageProf = findViewById(R.id.image_In);
        addImg = findViewById(R.id.addImageTxt);
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
        //call add to db method
        databaseMethods.addUser(user);

    }
    private boolean available(){

        return false;

    }

}