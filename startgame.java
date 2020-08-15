package com.example.flagsandcountries;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class startgame extends AppCompatActivity {

    TextView txvNotes, txvPoints, txvMaxPoints,txvTimer;
    ImageView imgTween;
    Button btnStart;
    Animation animation;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startgame);

        txvNotes = (TextView)findViewById(R.id.txvNotes);
        txvPoints = (TextView)findViewById(R.id.txvPoints);
        txvMaxPoints = (TextView)findViewById(R.id.txvMaxPoints);
        imgTween = (ImageView)findViewById(R.id.imgTween);
        btnStart = (Button)findViewById(R.id.btnStart);
       // txvTimer = (TextView)findViewById(R.id.txvTimer1);

        gameStart(); // call gameStart()

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(startgame.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void gameStart() {
        imgTween = (ImageView) findViewById(R.id.imgTween);
        animation = AnimationUtils.loadAnimation(this, R.anim.rotation);
        imgTween.startAnimation(animation); }


}
