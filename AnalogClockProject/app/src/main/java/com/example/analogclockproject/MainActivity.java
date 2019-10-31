package com.example.analogclockproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    // Create time starting clock at midnight
    int Time = 0;
    int Minute = 0;
    int Hour = 0;

    // Runs the clock
    Thread ClockTimer = new Thread(){
        @Override
        public void run() {
            try{
                while (true){
                    // Update the time
                    Time++;
                    rotate(Time*6, R.id.SecondHand);
                    // Check if another hand needs to tick
                    while ( Time > 60){
                        Minute += Math.floor(Time / 60);
                        rotate( Minute*6, R.id.MinuteHand);
                        Time = Time % 60;
                    }
                    while ( Minute > 60){
                        Hour += Math.floor(Minute / 60);
                        rotate( Hour*30, R.id.HourHand);
                        Minute = Minute % 60;
                    }
                    sleep(1000);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    };


    // Code to allow an imageview to rotate
    private void rotate(float degree, int id) {
        final RotateAnimation rotateAnim = new RotateAnimation(0.0f, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnim.setDuration(0);
        rotateAnim.setFillAfter(true);
        ImageView imgview = findViewById(id);
        imgview.startAnimation(rotateAnim);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button start = findViewById( R.id.startClock);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClockTimer.start();
            }
        });

        // Adds an hour to clock for testing mainly
        final Button addButton = findViewById( R.id.AddHourButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Time += 3600;
            }
        });
    }
}
