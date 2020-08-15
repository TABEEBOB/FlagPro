package com.example.flagsandcountries;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnAns1, btnAns2, btnAns3, btnReturn, btnPause;
    TextView scoreCount, txvTimer;
    ImageView imgFlag;
    int score;
    CountDownTimer timer;
    boolean pause;


    List<CountryChoice> list;
    Random random;
    int turn = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgFlag = (ImageView)findViewById(R.id.imgFlag);
        btnAns1 = (Button)findViewById(R.id.btnAns1);
        btnAns2 = (Button)findViewById(R.id.btnAns2);
        btnAns3 = (Button)findViewById(R.id.btnAns3);
        btnReturn = (Button)findViewById(R.id.btnReturn);
        btnPause = (Button)findViewById(R.id.btnPause);
        scoreCount = (TextView)findViewById(R.id.score1);
        txvTimer = (TextView)findViewById(R.id.txvTimer);


        //set timer for 60 seconds
        timer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                txvTimer.setText(millisUntilFinished/1000 + "sec left");

            }

            @Override
            public void onFinish() {

                txvTimer.setText("time ended"); //display at end of time
                gameOver();

            }
        };
        timer.start();

        list = new ArrayList<>();
        random = new Random();

        for(int i = 0; i< new Database().answers.length; i++) {

            list.add(new CountryChoice(new Database().answers[i], new Database().flags[i]));
        }

        Collections.shuffle(list);

        newQuestion(turn);

        btnAns1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnAns1.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())) {

                        score = score + 10; // score increase by 10 for every correct answer
                        scoreCount.setText("Score: " +score);
                       // newQuestion(random.);

                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show(); //hint for correct answers

                    if(turn < list.size()) {
                        turn++;
                        newQuestion(turn);

                    } else {
                        gameOver();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show(); //hint for incorrect answers
                    turn++;
                    newQuestion(turn);
                }
            }
        });
        btnAns2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnAns2.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())) {

                    score = score + 10; // score increase by 10 for every correct answer
                    scoreCount.setText("Score: " +score);

                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                    if(turn < list.size()) {
                        turn++;
                        newQuestion(turn);

                    } else {
                       gameOver();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                    turn++;
                    newQuestion(turn);
                }

            }
        });
        btnAns3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnAns3.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())) {

                    score = score + 10;
                    scoreCount.setText("Score: " +score);
                    Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                    if(turn < list.size()) {
                        turn++;
                        newQuestion(turn);

                    } else {
                        gameOver();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Incorrect", Toast.LENGTH_SHORT).show();
                    turn++;
                    newQuestion(turn);

                }

            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void timePause(boolean pauseTime){
        this.pause = pauseTime;


    }
    private void newQuestion(int num){
        imgFlag.setImageResource(list.get(num - 1).getImage()); //set flag image to screen
        int right_ans = random.nextInt(3) + 1;

        int btn1 = num - 1;
        int btn2;
        int btn3;

        switch (right_ans) {

            case 1:

            btnAns1.setText(list.get(btn1).getName());

            do {
                btn2 = random.nextInt(list.size());
            } while(btn2 == btn1);
            do{

                btn3 = random.nextInt(list.size());
            } while(btn3 == btn1 || btn3 == btn2);

            btnAns2.setText(list.get(btn2).getName());
            btnAns3.setText(list.get(btn3).getName());

            break;

            case 2:
                btnAns2.setText(list.get(btn1).getName());

                do {
                    btn2 = random.nextInt(list.size());
                } while(btn2 == btn1);
                do{

                    btn3 = random.nextInt(list.size());
                } while(btn3 == btn1 || btn3 == btn2);

                btnAns1.setText(list.get(btn2).getName());
                btnAns3.setText(list.get(btn3).getName());

                break;
            case 3:
                btnAns3.setText(list.get(btn1).getName());

                do {
                    btn2 = random.nextInt(list.size());
                } while(btn2 == btn1);
                do{

                    btn3 = random.nextInt(list.size());
                } while(btn3 == btn1 || btn3 == btn2);

                btnAns2.setText(list.get(btn2).getName());
                btnAns1.setText(list.get(btn3).getName());

                break;
        }
    }
    public void gameOver(){


        //pops up after the last question.
        //displays the score
        //provive Exit button to exit game and Restart button to restart game
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        alertDialogBuilder.setMessage("Game Over! Your Total Score" +score);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("New Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        alertDialogBuilder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}