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
            TextView names = itemView.findViewById(R.id.posterName);
            names.setText(post.posterNames);
            TextView time = itemView.findViewById(R.id.timePosted);
            time.setText(post.postTime);
            TextView txt = itemView.findViewById(R.id.txtPOSTED);
            txt.setText(post.postTxt);
            RoundedImageView imageProfile = itemView.findViewById(R.id.posterPP);
            ImageView postedImage = itemView.findViewById(R.id.picture);
            if(post.postedPic != null){
                Glide.with(itemView.getContext()) // Pass the activity or fragment context
                        .load(post.postedPic) // Load the image from the URL
                        .into(postedImage);
            }else{
                postedImage.setVisibility(View.GONE);
            }


            TextView deleteBtn = itemView.findViewById(R.id.deleteBtn);
            TextView editBtn = itemView.findViewById(R.id.editbtn);
            AppCompatImageView menuDots = itemView.findViewById(R.id.dots);
            LinearLayoutCompat likeBtnL = itemView.findViewById(R.id.likeBtn);
            LinearLayoutCompat commBtnL = itemView.findViewById(R.id.commentBtn);
            LinearLayoutCompat menuPanel = itemView.findViewById(R.id.menuPanel);
            managePreferences = new ManagePreferences(itemView.getContext());


        }


    }
}
