package com.example.flagsandcountries;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class tweenRotate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startgame);
        ImageView imgRotate =(ImageView)findViewById(R.id.imgTween);
        imgRotate.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotation));
    }
}
