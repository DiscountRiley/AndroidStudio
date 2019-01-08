package com.example.georgerr19.spyfall;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SpyFallMain extends AppCompatActivity {

    private TextView player ;
    private TextView timer ;

    private ImageButton playerUp ;
    private ImageButton playerDown ;
    private ImageButton timerUp ;
    private ImageButton timerDown ;

    private int playerCount ;
    private int timerCount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_spy_fall_main) ;

        player = findViewById( R.id.playCount ) ;
        timer = findViewById( R.id.timerCount ) ;

        playerCount = 3 ;
        timerCount = 5 ;

        player.setText( "" + playerCount ) ;
        timer.setText( timerCount + " minutes" ) ;
    }

    public void startGame( View v ) {
            Intent intent = new Intent( this, RoleView.class ) ;
            intent.putExtra( "playerCount", playerCount ) ;
        intent.putExtra( "timerCount", timerCount ) ;
            startActivity( intent ) ;
    }

    public void playerUp( View v ) {
        if( playerCount < 9 ) {
            playerCount++ ;
            player.setText( "" + playerCount ) ;
        }
    }

    public void playerDown( View v ) {
        if( playerCount > 3 ) {
            playerCount-- ;
            player.setText( "" + playerCount ) ;
        }
    }

    public void timerUp( View v ) {
        if( timerCount < 9 ) {
            timerCount++ ;
            timer.setText( "" + timerCount ) ;
        }
    }

    public void timerDown( View v ) {
        if( timerCount < 9 ) {
            timerCount-- ;
            timer.setText( timerCount + " minutes" ) ;
        }
    }
}

