package com.example.georgerr19.spyfall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultsScreen extends AppCompatActivity {

    private String spy ;
    private String location ;

    private TextView spyText ;
    private TextView locationText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_screen);

        Bundle extras = getIntent().getExtras() ;
        spy = extras.getString( "spy" ) ;
        location = extras.getString( "location" ) ;

        spyText = findViewById( R.id.spyText ) ;
        locationText = findViewById( R.id.locationText ) ;
        spyText.setText( "The spy was " + spy ) ;
        locationText.setText( "The location was " + location ) ;
        spyText.setVisibility( View.INVISIBLE ) ;
        locationText.setVisibility( View.INVISIBLE ) ;
    }

    public void revealSpy( View v ) {
        spyText.setVisibility( View.VISIBLE ) ;
    }
    public void revealLocation( View v ) {
        locationText.setVisibility( View.VISIBLE ) ;
    }

    public void restart( View v ) {
        Intent intent = new Intent( this, SpyFallMain.class ) ;
        startActivity( intent ) ;
    }
}
