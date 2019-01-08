package com.example.georgerr19.spyfall;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SpyfallGame extends AppCompatActivity {

    private int time ;
    private String spy ;
    private String location ;
    private CountDownTimer timer ;
    private int elapsedTime ;
    private boolean paused ;

    private TextView timerText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spyfall_game);

        Bundle extras = getIntent().getExtras() ;
        time = extras.getInt( "time" ) ;
        spy = extras.getString( "spy" ) ;
        location = extras.getString( "location" ) ;
        paused = false ;

        elapsedTime = time * 60 ;

        timerText = findViewById( R.id.timerText ) ;
        timer = new CountDownTimer( time * 60000, 1000 ) {
            @Override
            public void onTick(long millisUntilFinished) {
                elapsedTime-- ;
                if( elapsedTime % 60 < 10 ) {
                    timerText.setText( elapsedTime / 60 + ":0" + elapsedTime % 60 ) ;
                } else {
                    timerText.setText( elapsedTime / 60 + ":" + elapsedTime % 60 ) ;
                }
            }

            @Override
            public void onFinish() {
                    endGame() ;
            }
        } ;
        timer.start() ;
    }

    public void pause( View v ) {
        if( paused ) {
            timer = new CountDownTimer( time * 60000, 1000 ) {
                @Override
                public void onTick(long millisUntilFinished) {
                    elapsedTime-- ;
                    if( elapsedTime % 60 < 10 ) {
                        timerText.setText( elapsedTime / 60 + ":0" + elapsedTime % 60 ) ;
                    } else {
                        timerText.setText( elapsedTime / 60 + ":" + elapsedTime % 60 ) ;
                    }
                }

                @Override
                public void onFinish() {
                    endGame() ;
                }
            } ;
            timer.start() ;
            paused = false ;
        } else {
            timer.cancel() ;
            paused = true ;
        }
    }

    public void endGame( View v ) {
        endGame() ;
    }

    public void endGame() {
        Intent intent = new Intent( this, ResultsScreen.class ) ;
        intent.putExtra( "spy", spy ) ;
        intent.putExtra( "location", location ) ;
        startActivity( intent ) ;
    }
}
