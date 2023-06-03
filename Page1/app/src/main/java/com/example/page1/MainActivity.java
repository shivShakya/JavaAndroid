package com.example.page1;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //for image
        /*
        ImageView imageView = findViewById(R.id.img);
        String imageUrl = "https://media.istockphoto.com/id/493687446/photo/fresh-garden-vegetablesin-vintage-metal-basket.jpg?s=612x612&w=0&k=20&c=Q1w0PUL-ddnEyWwEnal7hfwQaq1QQXzrpTvNsHIrITc=";
        Glide.with(this)
                .load(imageUrl)
                .into(imageView);
        */

      // for video
        VideoView videoView = findViewById(R.id.videoView);
        TextView text = findViewById(R.id.editTextTextPersonName);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
        videoView.setVideoURI(uri);
        videoView.start();
        //for animation
        float startY = videoView.getTranslationY();
        float endY = startY - 500f;
        ObjectAnimator aniX = ObjectAnimator.ofFloat(videoView, "translationY", startY, endY);
        aniX.setDuration(2400);
        text.setVisibility(TextView.INVISIBLE);
        aniX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                text.setVisibility(TextView.VISIBLE);
            }
        });
        aniX.start();


    }
}