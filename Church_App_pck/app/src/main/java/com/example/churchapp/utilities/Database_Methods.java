package com.example.churchapp.utilities;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Database_Methods {
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    public void addUser(User user){
        //add user to db
    }
    public User getUserByID(String id){
        //get user by their id
        return null;
    }
    public List<User> getAllUser(){
        //get all users
        return null;
    }
    public List<User> getMembersByCid(String churchId){
        //get all church members by the church id
        return null;
    }
    public void addPost(Post post){
        //add post to db

    }
    public List<Post> getAllChurchPosts(){
        //get all posts from church
        return null;
    }
    public List<Post> getAllLikedPosts(String id){
        //get all posts that user liked
        return null;
    }
    public List<Post> getAllPublicPosts(){
        //get all posts that are public
        return null;
    }
    public Post getPostByID(String postId){
        //get a post by its id
        return null;
    }
    public User assignRole(String id){
        //give user a different role
        return null;
    }
    public void removeMember(String id){
        //remove member from a church
    }
    public List<Church> getAllChurches(){
        //get all churches
        return null;
    }
    public List<Church> searchChurches(String chur__){
        //search all churches
        return null;
    }
    public Church getChurchById(String churchId){
        //get a church by the church id
        return null;
    }

}
