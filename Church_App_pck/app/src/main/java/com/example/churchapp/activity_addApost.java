package com.example.churchapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.churchapp.utilities.Constants;
import com.example.churchapp.utilities.Database_Methods;
import com.example.churchapp.utilities.Post;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class activity_addApost extends AppCompatActivity {
    TextView names,errortxt;
    EditText text2post;
    LinearLayout defaultAd,churchAd,publicAd;
    String fullName, pp,imagePosted, postText, postaID, audience = null;
    RoundedImageView imageViewPP;
    Database_Methods databaseMethods;
    ImageView pic2post;
    Uri imageUri2;
    Boolean addImage = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_apost);

        //_____------
        callViews();
        databaseMethods = new Database_Methods(getApplicationContext());
    }
    private void callViews(){
        names = findViewById(R.id.namedis);
        text2post = findViewById(R.id.inText);
        defaultAd = findViewById(R.id.defaultAd);
        churchAd = findViewById(R.id.churchAd);
        publicAd = findViewById(R.id.publicAd);
        pic2post = findViewById(R.id.image2p);
        errortxt = findViewById(R.id.errorAp);

    }
    public void openAudience(View view) {
        defaultAd.setVisibility(View.GONE);
        churchAd.setVisibility(View.VISIBLE);
        publicAd.setVisibility(View.VISIBLE);
    }

    public void setPublic(View view) {
        churchAd.setVisibility(View.GONE);
        publicAd.setVisibility(View.VISIBLE);
        audience = Constants.Key_Audience_Public;
    }

    public void setChurch(View view) {
        churchAd.setVisibility(View.VISIBLE);
        publicAd.setVisibility(View.GONE);
        audience = Constants.Key_Audience_Church;
    }

    public void addPhoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        pickImage.launch(intent);
    }
    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->{
                if(result.getResultCode()==RESULT_OK){
                    if(result.getData() != null){
                        Uri imgUri = result.getData().getData();
                        Log.d("11111111111111111", "-----"+imgUri.toString());
                        imageUri2 =imgUri;
                        addImage = true;
                        databaseMethods.uploadAnImage(imageUri2, Constants.Key_Type_Image_PI);


                        try {
                            InputStream inputStream = getContentResolver().openInputStream(imgUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                            pic2post.setVisibility(View.VISIBLE);
                            pic2post.setImageBitmap(bitmap);
                            errortxt.setVisibility(View.GONE);
                        }catch (FileNotFoundException e){
                            errortxt.setText("please try adding picture again");
                            errortxt.setVisibility(View.VISIBLE);
                        }
//
                    }
                }
            });

    public void OpenHome(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void post(View view) {
        if(audienceChecked()){
            if(writtenSomething()){
                errortxt.setVisibility(View.GONE);
                Post post = new Post();
                post.posterNames = fullName;
                post.posterPP = pp;
                post.posterId = postaID;
                post.audience = audience;
                post.postTxt = postText;
                if(addImage){
                    post.postedPic = imageUri2.toString();
                }else{
                    post.postedPic = null;
                }
                databaseMethods.addPost(post);
                OpenHome(view);
            }else{
                errortxt.setVisibility(View.VISIBLE);
                errortxt.setText("Please write Something");
            }
        }else{
            errortxt.setVisibility(View.VISIBLE);
            errortxt.setText("Please pick Audience");
        }
    }
    private Boolean audienceChecked(){
        return audience != null;
    }
    private Boolean writtenSomething(){
        return text2post.getText() != null;
    }
}