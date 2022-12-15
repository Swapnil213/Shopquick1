package com.example.shopquick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.window.SplashScreen;

import com.google.firebase.auth.FirebaseAuth;

public class Splashscreen extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        auth = FirebaseAuth.getInstance();
        NextScreen();
        Fullscreen();
    }
    private void NextScreen(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(auth.getCurrentUser()==null)
                {
                    startActivity(new Intent(Splashscreen.this, Intro.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(Splashscreen.this, HomeActivity.class));
                    finish();

                }
            }
        },2500);
    }
    private void Fullscreen(){
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                |View.SYSTEM_UI_FLAG_IMMERSIVE
                |View.SYSTEM_UI_FLAG_FULLSCREEN


        );
    }
}