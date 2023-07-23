package com.devdroiddev.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.window.SplashScreen;

public class SplashActivity extends AppCompatActivity {

    ImageView messageLogoImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        messageLogoImg = findViewById(R.id.message_logo);

        // Set Animation Icon
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.icon_message_scale_animation);
        messageLogoImg.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the next activity
                Intent intent = new Intent(SplashActivity.this, AuthenticationActivity.class);
                startActivity(intent);
                finish(); // Call finish() to close the splash activity
            }
        }, 5000);
    }
}