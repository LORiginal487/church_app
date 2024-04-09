package com.example.churchapp.utilities;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.churchapp.MainActivity;
import com.example.churchapp.activity_SignUp;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database_Methods {
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    CollectionReference usersCol = db.collection(Constants.Key_Collection_Users);
    public void addUser(User user, Context context){
        //add user to db
        Map<String, Object> userMp = new HashMap<>();
        userMp.put(Constants.Key_Name, user.name);
        userMp.put(Constants.Key_Surname, user.surname);
        userMp.put(Constants.Key_Phone, user.phone);
        userMp.put(Constants.Key_Password, user.password);
        userMp.put(Constants.Key_Image, user.image);
        userMp.put(Constants.Key_Bio, user.bio);
        userMp.put(Constants.Key_BackgroundPic, user.bckGndP);
        userMp.put(Constants.Key_Church, user.church);
        userMp.put(Constants.Key_Title, user.title);
        userMp.put(Constants.Key_Gender, user.gender);
        userMp.put(Constants.Key_Role, user.role);
        userMp.put(Constants.Key_Id, user.idNum);
        userMp.put(Constants.Key_Dob, user.dob);
        userMp.put(Constants.Key_Email, user.email);

        // Add a new document with a generated ID

        usersCol.document(user.idNum).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                showToast("opened", context);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showToast("not opened"+e.getMessage(), context);
            }
        });
    }
    public int getCountOfUsers(){
        final int[] count = {0}; // Initializing count to 0



        // Retrieve all documents in the collection
        usersCol.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                // Count the number of documents returned
                count[0] = queryDocumentSnapshots.size();
                Log.d("DocumentCount", "Number of users: " + count[0]);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("DocumentCount", "Error getting documents: " + e.getMessage());
            }
        });

        return count[0];
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
    private void showToast(String mssg, Context context){
        Toast.makeText(context, mssg, Toast.LENGTH_SHORT).show();
    }


}
