package com.example.churchapp.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.churchapp.Listeners.LikesAndCommentListener;
import com.example.churchapp.R;
import com.example.churchapp.utilities.Constants;
import com.example.churchapp.utilities.Database_Methods;
import com.example.churchapp.utilities.ManagePreferences;
import com.example.churchapp.utilities.Post;
import com.example.churchapp.utilities.User;
import com.google.firebase.firestore.FirebaseFirestore;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder>{
    private final List<Post> post;
    private final LikesAndCommentListener likesAndCommentListener;
    ManagePreferences managePreferences;
    Database_Methods databaseMethods;
    FirebaseFirestore db;
    User user1;
    public PostAdapter(List<Post> post, LikesAndCommentListener likesAndCommentListener) {
        this.likesAndCommentListener = likesAndCommentListener;
        this.post = post;

    }


    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts, parent, false);
        return new PostViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {
        holder.setPostData(post.get(position));

    }


    @Override
    public int getItemCount() {
        return post.size();
    }

    public class PostViewHolder  extends RecyclerView.ViewHolder{
        View itemView;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
        void setPostData(Post post) {
            databaseMethods = new Database_Methods(itemView.getContext());
            Log.d("3434343434343434343","--------------------1");
            TextView names = itemView.findViewById(R.id.posterName);
            names.setText(post.posterNames);
            TextView time = itemView.findViewById(R.id.timePosted);
            TextView noImg = itemView.findViewById(R.id.noImage);
            time.setText(post.postTime);
            TextView txt = itemView.findViewById(R.id.txtPOSTED);
            Log.d("3434343434343434343","--------------------2");

            txt.setText(post.postTxt);
            RoundedImageView imageProfile = itemView.findViewById(R.id.posterPP);
            Glide.with(imageProfile.getContext()) // Pass the activity or fragment context
                    .load(post.posterPP) // Load the image from the URL
                    .into(imageProfile);
            ImageView postedImage = itemView.findViewById(R.id.picture);
            if(!post.postedPic.isEmpty()){
                noImg.setVisibility(View.GONE);
                Glide.with(postedImage.getContext()) // Pass the activity or fragment context
                        .load(post.postedPic) // Load the image from the URL
                        .into(postedImage);
                Log.d("3434343434343434343","--------------------3");

            }else {
                postedImage.setVisibility(View.GONE);
            }
            postedImage.getRootView().setOnClickListener(v -> {
                Log.d("6767575757557575765","--------------------7");

                likesAndCommentListener.onPictureClick(post);
            });
            imageProfile.setOnClickListener(v -> {
                Log.d("6767575757557575765","--------------------4");

                databaseMethods.getUserByString(Constants.Key_Id, post.posterId, new Database_Methods.FirestoreListenerUser() {
                            @Override
                            public void onSuccess(User user) {
                                Log.d("6767575757557575765","--------------------5");

                                likesAndCommentListener.onPersonClicked(user);
                                Log.d("6767575757557575765","--------------------6");

                            }

                            @Override
                            public void onFailure(String errorMessage) {

                            }
                });


            });
            names.setOnClickListener(v -> {
                databaseMethods.getUserByString(Constants.Key_Id, post.posterId, new Database_Methods.FirestoreListenerUser() {
                    @Override
                    public void onSuccess(User user) {
                        likesAndCommentListener.onPersonClicked(user);
                    }

                    @Override
                    public void onFailure(String errorMessage) {

                    }
                });
            });


        }


    }
}
