package com.example.hijaz.braintrainer;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView resultTextView;
    TextView scoreTextView;
    ArrayList<Integer>answers;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgain;
    RelativeLayout gameRelativeLayout;


    public void playAgain(View view){
            score = 0;
            numberOfQuestions = 0;
                timerTextView.setText("30s");
                scoreTextView.setText("0/0");
                resultTextView.setText("");
                playAgain.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000 + "s"));

            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("your Score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

            }
        }.start();
    }

    public void generateQuestion() {


        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();
        int inCorrectAnswer;

        for(int i = 0; i<4 ; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                inCorrectAnswer = rand.nextInt(41);
                while (inCorrectAnswer == a + b) {
                    inCorrectAnswer = rand.nextInt(41);
                }

                answers.add(inCorrectAnswer);
            }
        }
        button2.setText(Integer.toString(answers.get(0)));
        button3.setText(Integer.toString(answers.get(1)));
        button4.setText(Integer.toString(answers.get(2)));
        button5.setText(Integer.toString(answers.get(3)));

    }


    public void chooseAnswer(View view) {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");

        } else {
            resultTextView.setText("wrong!");
        }

        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public MainActivity() {
        answers = new ArrayList<Integer>();
    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.playAgain));
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);


  }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        button2 = (Button)findViewById(R.id.button2);
         button3 = (Button)findViewById(R.id.button3);
         button4 = (Button)findViewById(R.id.button4);
         button5 = (Button)findViewById(R.id.button5);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgain = (Button)findViewById(R.id.playAgain);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);







    }

}
