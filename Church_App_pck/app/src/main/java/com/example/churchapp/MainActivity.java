package com.example.churchapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.churchapp.Listeners.LikesAndCommentListener;
import com.example.churchapp.utilities.Constants;
import com.example.churchapp.utilities.Database_Methods;
import com.example.churchapp.utilities.Post;
import com.example.churchapp.utilities.User;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements LikesAndCommentListener {
    TextView churchName;
    Database_Methods databaseMethods;
    RecyclerView recents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //---------------------------------------
        callViews();
        databaseMethods = new Database_Methods(getApplicationContext());
        setRecents();
    }

    private void callViews() {
        recents = findViewById(R.id.rvLatestUpdate);

    }
    private void setRecents(){
        databaseMethods.letsTestAdapter(recents);
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

    public void AddPost(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_addApost.class);
        startActivity(intent);
    }

    @Override
    public void onCommentClicked(Post post) {

    }

    @Override
    public void onLikeClicked(Post post) {

    }

    @Override
    public void onPictureClick(Post post) {
        Log.d("6767575757557575765","--------------------2");

        Intent intent = new Intent(this, activity_View_Image.class);
        Log.d("6767575757557575765","--------------------3");
        if(post != null) {
            intent.putExtra(Constants.Key_Post, post);
            Log.d("6767575757557575765","--------------------8");
        }

        startActivity(intent);
        Log.d("6767575757557575765","--------------------10");


    }

    @Override
    public void onPersonClicked(User user) {

    }

    @Override
    public void onThreeDotsClick(String type, String postId) {

    }
}