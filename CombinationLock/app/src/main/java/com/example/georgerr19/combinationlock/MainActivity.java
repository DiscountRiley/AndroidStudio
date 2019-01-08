package com.example.georgerr19.combinationlock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ImageView lock ;
    private int[] combination ;
    private int[] answer ;
    private TextView debug ;

    private int prevAngle ;
    private int delta ;
    private int relX ;
    private int relY ;
    private int height ;
    private int width ;
    private int currAngle ;
    private int initialAngle ;
    private int finalAngle ;
    private int currNum ;
    private boolean dirr ;
    private boolean prevDirr ;
    private int distance ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState ) ;
        setContentView( R.layout.activity_main ) ;

        lock = findViewById( R.id.comboLock ) ;
        lock.setFocusableInTouchMode(true);

        combination = new int[]{ -1, -1, -1 } ;
        answer = new int[]{ 35, 50, 80 } ;

        DisplayMetrics displayMetrics = new DisplayMetrics() ;
        getWindowManager().getDefaultDisplay().getMetrics( displayMetrics ) ;
        height = displayMetrics.heightPixels ;
        width = displayMetrics.widthPixels ;

        relX = 0 ;
        relY = 0 ;
        currAngle = 0 ;
        initialAngle = 0 ;
        finalAngle = 0 ;
        currNum = 0 ;
        delta = 0 ;
        prevAngle = 0 ;
        distance  = 0 ;
        dirr = true ;
        prevDirr = dirr ;

        lock.setOnTouchListener( new View.OnTouchListener() {
            @Override
            public boolean onTouch( View view, MotionEvent event ) {
                if( event.getAction() == MotionEvent.ACTION_UP) {
                    finalAngle = currAngle ;
                    if( combination[1] > 0 ) {
                        addToCombo( prevDirr,distance ) ;
                        if( matchesAns() ) {
                            unlock() ;
                        }
                    }
                }
                else if( event.getAction() == MotionEvent.ACTION_DOWN ) {
                    relY = ( int )( event.getRawY() - height/2 ) ;
                    relX = ( int )( event.getRawX() - width/2 ) ;
                    initialAngle = ( -( int )( getAngle( relY, relX ) + 180 ) % 360 ) ;
                }
                else if ( event.getAction() == MotionEvent.ACTION_MOVE ) {
                    relY = ( int )( event.getRawY() - height/2 ) ;
                    relX = ( int )( event.getRawX() - width/2 ) ;
                    prevAngle = currAngle ;
                    prevDirr = dirr ;
                    currAngle = ( -( int )( ( getAngle( relY, relX ) ) ) - initialAngle + finalAngle + 180 ) % 360 ;
                    if( currAngle < 0 ) {
                        currAngle += 360 ;
                    }
                    currNum = ( int )Math.round( ( 100 - currAngle / 3.6 ) )  ;
                    delta = currAngle - prevAngle ;
                    if( ( delta > 0 && delta < 300 )|| delta < - 300 ) {
                        dirr = true ;
                    } else if ( ( delta < 0  && delta > -300 ) || delta > 300 ) {
                        dirr = false ;
                    }
                    if( currNum == 0 ) {
                        currNum = 100 ;
                    }
                    if( dirr != prevDirr ) {
                        addToCombo( prevDirr,distance ) ;
                        distance = 0 ;
                    }
                    if( Math.abs( delta ) > 300 ) {
                        distance  += Math.abs( Math.abs( delta ) - 360 ) ;
                    } else {
                        distance  += Math.abs( delta ) ;
                    }
                    if( distance > 720 && dirr == true ) {
                        resetLock() ;
                        distance = 0 ;
                    }
                    lock.animate().rotation( currAngle ).setDuration( 0 ).start() ;
                }
                else {
                    return false ;
                }
                    return true ;
            }
        } ) ;
    }

    public void unlock() {
        for( int v = 0 ; v < combination.length ; v++ ) {
            combination[v] = -1 ;
        }
        dirr = false ;
        prevDirr = false ;
        Intent intent = new Intent(this, UnlockedScreen.class ) ;
        startActivity( intent ) ;
    }

    public void resetLock() {
        Toast.makeText( this, "Combination Reset", Toast.LENGTH_SHORT ).show() ;
        for( int i = 0 ; i < combination.length ; i++ ) {
            combination[i] = -1 ;
        }
    }

    public double getAngle( int x, int y ) {
        return Math.toDegrees( Math.atan2( y, x ) ) ;
    }

    public boolean matchesAns() {
        for( int i = 0 ; i < answer.length ; i++ ) {
            if( ( Math.abs( answer[i] - combination[i] ) > 3 ) || combination[i] == - 1 )  {
                Toast.makeText(this, "Sorry, wrong combination.", Toast.LENGTH_SHORT).show();
                for( int v = 0 ; v < combination.length ; v++ ) {
                    combination[v] = -1 ;
                }
                dirr = false ;
                prevDirr = false ;
                return false ;
            }
        }
        return true ;
    }

    public void addToCombo( boolean dr, int dist) {
        if ( combination[0] < 0 && dr == true) {
            combination[0] = currNum ;
        } else if ( combination[1] < 0 && combination[0] > 0 ) {
            if ( dr == false && dist > 360 && dist < 720 ) {
                combination[1] = currNum ;
            } else {
                combination[1] = 1000 ;
            }
        } else if ( combination[2] < 0 && combination[0] > 0 && combination[1] > 0 ) {
            if ( dr == true && dist < 360) {
                combination[2] = currNum ;
            } else {
                combination[2] = 1000 ;
            }
        }
    }
}
