package com.example.churchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

public class activity_inbox extends AppCompatActivity {
    LinearLayout inbox1LL, inboxSideBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        callViews();
    }
    private void callViews(){
        inbox1LL = findViewById(R.id.inboxLV);
        inboxSideBar = findViewById(R.id.inboxSideMenuLV);
    }

    public void OpenSideMenu(View view) {
        showLayoutWithAnimation();
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
    private void showLayoutWithAnimation() {
        inboxSideBar.post(new Runnable() {
            @Override
            public void run() {
                // Measure the width of the layout
                int width = inboxSideBar.getMeasuredWidth();

                // Set initial translation to the left of the screen
                inboxSideBar.setTranslationX(-width);

                // Set the layout to visible
                inboxSideBar.setVisibility(View.VISIBLE);
                inbox1LL.setAlpha(0.1f);
                for (int i = 0; i < inbox1LL.getChildCount(); i++) {
                    View childView = inbox1LL.getChildAt(i);
                    childView.setEnabled(false);
                }

                // Create and start the translation animation
                inboxSideBar.animate()
                        .translationX(0)
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setDuration(1) // Adjust the duration as needed
                        .start();
            }
        });
    }

    public void BackToInboxLL(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_inbox.class);
        startActivity(intent);
    }

    public void OpenChat(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_chat.class);
        startActivity(intent);
    }
}