package com.example.churchapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.churchapp.utilities.Constants;
import com.example.churchapp.utilities.ManagePreferences;
import com.example.churchapp.utilities.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CommentsLayout extends AppCompatActivity  {
    TextView names, time,   text, likesCount, commentsCount;
    Post post;
    EditText writeComm;
    ImageView ImagePosted;
    ProgressBar progressBar;
    RecyclerView commentsRV;
    RoundedImageView imagePP;
    ManagePreferences managePreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_layout);
        Log.d("111111111111111111111111111","-------------------");


        //**---------------
        callViews();
        Log.d("111111111111111111111111111","-------");

        setEverything();
        Log.d("111111111111111111111111111","post.postID+");

    }
    private void callViews(){
        imagePP = findViewById(R.id.posterPP);
        names = findViewById(R.id.posterName);
        time = findViewById(R.id.timePosted);
        ImagePosted = findViewById(R.id.picture);
        text = findViewById(R.id.txtPOSTED);

    }
    private void setEverything(){
        //getIntent().getStringExtra(Constants.Key_Email)
        Log.d("111111111111111111111111111",post.postID+"0");
        post = (Post) getIntent().getSerializableExtra(Constants.Key_Post);
        Log.d("111111111111111111111111111",post.postID+"1");
        names.setText(post.posterNames);
        Log.d("111111111111111111111111111",post.postID+"2");
        text.setText(post.postTxt);
        Log.d("111111111111111111111111111",post.postID+"3");
        time.setText(post.postTime);
        Log.d("111111111111111111111111111",post.postID+"4");
        Glide.with(imagePP.getContext()) // Pass the activity or fragment context
                .load(post.posterPP) // Load the image from the URL
                .into(imagePP);
        Log.d("111111111111111111111111111",post.postID+"5");
        if(post.postedPic != null){
            Glide.with(ImagePosted.getContext()) // Pass the activity or fragment context
                    .load(post.postedPic) // Load the image from the URL
                    .into(ImagePosted);
            Log.d("111111111111111111111111111",post.postID+"6");
        }else{
            ImagePosted.setVisibility(View.GONE);
            Log.d("111111111111111111111111111",post.postID+"7");
        }


    }

}