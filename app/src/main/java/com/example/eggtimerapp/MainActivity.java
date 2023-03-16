package com.example.eggtimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar seekbar;
    TextView textview;
    boolean counterisactive = false ;
    Button btn1;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seekbar = findViewById(R.id.seekbar);
        textview = findViewById(R.id.textview);


        seekbar.setMax(600);
        seekbar.setProgress(30);



        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updatetimer(i);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public  void  control(View view){

        if (counterisactive == false) {


            counterisactive = true;
            seekbar.setEnabled(false);
            btn1 = findViewById(R.id.btn1);
            btn1.setText("STOP");




            countDownTimer = new CountDownTimer(seekbar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long miliuntilfinished) {

                    updatetimer((int) miliuntilfinished / 1000);

                }

                @Override
                public void onFinish() {



                    resettimer();
                    //this will not work as conext here because this can not be used  in a any other class other than on create
                    //so we have to use getApplicationContext() in place of this
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.audio2);
                    mediaPlayer.start();


                }
            }.start();
        } else {
            resettimer();
        }
    }

    public  void resettimer(){
        seekbar.setProgress(30 );
        textview.setText("AGAIN");
        countDownTimer.cancel();
        btn1.setText("Go");
        seekbar.setEnabled(true);
        counterisactive = false;
    }


    public  void  updatetimer(int secondsleft){
        int min = (int) secondsleft /60;
        int seconds = secondsleft - min  * 60;

        String secondstring = Integer.toString(seconds);

        if (secondstring == "0") {

            secondstring = "00";
        }

        textview.setText(Integer.toString(min)+":"+ secondstring);

    }
}