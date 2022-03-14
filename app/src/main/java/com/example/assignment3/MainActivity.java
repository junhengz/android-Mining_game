package com.example.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static Timer skip_timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ImageView lego_bricks = (ImageView) findViewById(R.id.lego_bricks);
        final View lego_brick_view = (View) findViewById(R.id.lego_bricks);
        final ImageView lego = (ImageView) findViewById(R.id.lego);
        View lego_view = (View) findViewById(R.id.lego);
        final ImageView lego2 = (ImageView) findViewById(R.id.lego2);
        View lego_view2 = (View) findViewById(R.id.lego2);
        final ImageView lego3 = (ImageView) findViewById(R.id.lego3);
        View lego_view3 = (View) findViewById(R.id.lego3);
        final ImageView lego4 = (ImageView) findViewById(R.id.lego4);
        View lego_view4 = (View) findViewById(R.id.lego4);
        final ImageView lego5 = (ImageView) findViewById(R.id.lego5);
        View lego_view5 = (View) findViewById(R.id.lego5);

        final Animation Fadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_image_in);
        final Animation Fadeout = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_image_out);

        lego_view.setVisibility(View.INVISIBLE);
        lego_view2.setVisibility(View.INVISIBLE);
        lego_view3.setVisibility(View.INVISIBLE);
        lego_view4.setVisibility(View.INVISIBLE);
        lego_view5.setVisibility(View.INVISIBLE);

        lego_bricks.startAnimation(Fadeout);
        lego_brick_view.setVisibility(View.INVISIBLE);
        new CountDownTimer(2500,500)
        {
            public void onTick(long time)
            {

            }
            public void onFinish()
            {

                lego.startAnimation(Fadein);
                lego2.startAnimation(Fadein);
                lego3.startAnimation(Fadein);
                lego4.startAnimation(Fadein);
                lego5.startAnimation(Fadein);
                new CountDownTimer(2000,1000)
                {
                    public void onTick(long time)
                    {

                    }
                    public void onFinish()
                    {
                        lego.startAnimation(Fadeout);
                        lego2.startAnimation(Fadeout);
                        lego3.startAnimation(Fadeout);
                        lego4.startAnimation(Fadeout);
                        lego5.startAnimation(Fadeout);
                    }
                }.start();

            }
        }.start();

        skip_timer = new Timer();
        TimerTask skip_to_next = new TimerTask() {
            public void run(){
                if(skip_timer!=null)
                {
            Intent i = gamestart.makeLaunchgamestart(MainActivity.this);
            skip_timer.cancel();
            finish();
            startActivity(i);}}
        };
        skip_timer.schedule(skip_to_next,10000);



        Button skip = (Button) findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                skip_timer.cancel();
                skip_timer.purge();
                skip_timer=null;
                Intent i = gamestart.makeLaunchgamestart(MainActivity.this);
                finish();
                startActivity(i);

            }
        });












    }
}
