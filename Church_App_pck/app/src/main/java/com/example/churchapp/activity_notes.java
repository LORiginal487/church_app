package com.example.churchapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

public class activity_notes extends AppCompatActivity {
    LinearLayout layout1withRV;
    ConstraintLayout layout2withInNote;
    AppCompatImageView submitNewNote, addNewNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        callViews();
    }
    private void callViews(){
        layout1withRV = findViewById(R.id.home_M_L);
        layout2withInNote = findViewById(R.id.addingNoteL);
        submitNewNote = findViewById(R.id.submitNote);
        addNewNote = findViewById(R.id.addNote);
    }

    public void OpenApps(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_menu.class);
        startActivity(intent);
    }

    public void AddNote(View view) {
        showLayoutWithAnimation();
    }
    private void showLayoutWithAnimation() {
        layout2withInNote.post(new Runnable() {
            @Override
            public void run() {
                // Measure the width of the layout
                int width = layout2withInNote.getMeasuredWidth();

                // Set initial translation to the left of the screen
                layout2withInNote.setTranslationX(-width);

                // Set the layout to visible
                layout2withInNote.setVisibility(View.VISIBLE);
                layout1withRV.setVisibility(View.GONE);
                submitNewNote.setVisibility(View.VISIBLE);
                addNewNote.setVisibility(View.GONE);


                // Create and start the translation animation
                layout2withInNote.animate()
                        .translationX(0)
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setDuration(1) // Adjust the duration as needed
                        .start();
            }
        });
    }

    public void OpenMenu(View view) {
        Intent intent = new Intent(getApplicationContext(), activity_menu.class);
        startActivity(intent);
    }
}