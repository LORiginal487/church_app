package com.example.churchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.churchapp.utilities.Constants;
import com.example.churchapp.utilities.Database_Methods;
import com.example.churchapp.utilities.ManagePreferences;
import com.example.churchapp.utilities.User;

public class activity_SignIn extends AppCompatActivity {
    EditText phone_in, passw_in;
    TextView errorSi;
    Database_Methods databaseMethods;
    User user;
    ManagePreferences managePreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //----------------------**
        databaseMethods = new Database_Methods(getApplicationContext());
        Log.d("l4 1111111", "_______________");
        user = new User();
        Log.d("l4 1111111", "_______________2");
        managePreferences = new ManagePreferences(getApplicationContext());
        callViews();

    }

    private void callViews() {
        phone_in = findViewById(R.id.phoneNoIn);
        passw_in = findViewById(R.id.passwordIn);
        errorSi = findViewById(R.id.errorSi);
    }

    public void SignIn(View view) {
        validation();
    }

    public void OpenSignUp(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_SignUp.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void validation(){
        User user2;
        databaseMethods.getUserByString(Constants.Key_Phone, phone_in.getText().toString(), new Database_Methods.FirestoreListenerUser() {
            @Override
            public void onSuccess(User user) {
                if(user.password.equals(passw_in.getText().toString())){
                    openMain(user);
                }else{
                    errorSi.setText("password incorrect");
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                errorSi.setText(errorMessage);

            }
        });
    }
    private void openMain(User user){
        managePreferences.putString(Constants.Key_Name, user.name);
        managePreferences.putString(Constants.Key_Phone, user.phone);
        managePreferences.putString(Constants.Key_Surname, user.surname);
        managePreferences.putString(Constants.Key_Title, user.title);
        managePreferences.putString(Constants.Key_Gender, user.gender);
        managePreferences.putString(Constants.Key_Email, user.email);
        managePreferences.putString(Constants.Key_Church, user.church);
        managePreferences.putString(Constants.Key_BackgroundPic, user.bckGndP);
        managePreferences.putString(Constants.Key_Bio, user.bio);
        managePreferences.putString(Constants.Key_Id, user.idNum);
        managePreferences.putString(Constants.Key_Role, user.role);
        managePreferences.putString(Constants.Key_Image, user.image);


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}