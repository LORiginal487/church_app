package com.example.churchapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.churchapp.utilities.Constants;
import com.example.churchapp.utilities.Post;

public class activity_View_Image extends AppCompatActivity {
    TextView text, names, church;
    ImageView image;
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        //********_-----------

        post = (Post) getIntent().getSerializableExtra(Constants.Key_Post);
        Log.d("6767575757557575765","--------------------9");

        callViews();
        setEverything();
    }
    private void callViews() {
        text = findViewById(R.id.text);
        names = findViewById(R.id.names);
        church = findViewById(R.id.churchDis);
        image = findViewById(R.id.imageShow);
    }
    private void setEverything(){
        Log.d("6767575757557575765","--------------------1");

        text.setText(post.postTxt);
        names.setText(post.posterNames);
        church.setText("---------");
        if(post.postedPic != null){
            Glide.with(image.getContext()) // Pass the activity or fragment context
                    .load(post.postedPic) // Load the image from the URL
                    .into(image);
        }else{
            image.setVisibility(View.GONE);
        }

    }
    private Bitmap getBitmap(String imageStr){
        byte[] bytes = Base64.decode(imageStr, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}