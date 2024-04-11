package com.example.churchapp.Listeners;


import com.example.churchapp.utilities.Post;
import com.example.churchapp.utilities.User;

public interface LikesAndCommentListener {
    void onCommentClicked(Post post);
    void onLikeClicked(Post post);
    void onPictureClick(Post post);
    void onPersonClicked(User user);
    void onThreeDotsClick(String type, String postId);
}
