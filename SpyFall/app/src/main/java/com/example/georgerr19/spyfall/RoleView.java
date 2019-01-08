package com.example.georgerr19.spyfall;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RoleView extends AppCompatActivity {

    private Resources res;

    private Spinner timeSpinner ;
    private ArrayAdapter adapter ;
    private int time ;

    private ArrayList<String> names ;
    private ArrayList<Button> nameButtons ;
    private ArrayList<TextView> nameTexts ;

    private boolean spyAssigned ;
    private String location ;
    private int spyLocation ;

    private boolean revealed ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.role_view_layout);

        Bundle extras = getIntent().getExtras() ;
        res = getResources() ;

        timeSpinner = ( Spinner )findViewById( R.id.timeSpinner ) ;
        adapter = ArrayAdapter.createFromResource( this, R.array.times, android.R.layout.simple_spinner_item ) ;
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item ) ;
        timeSpinner.setAdapter( adapter ) ;

        timeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if( !( ( timeSpinner.getSelectedItem().toString() + "" ).equals( "" ) ) ) {
                String item = timeSpinner.getSelectedItem().toString() ;
                        switch ( item ) {
                        case "3 Minutes": time = 3; break;
                        case "4 Minutes": time = 4; break;
                        case "5 Minutes": time = 5; break;
                        case "6 Minutes": time = 6; break;
                        case "7 Minutes": time = 7; break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } ) ;

        names = extras.getStringArrayList( "names" ) ;

        nameButtons = new ArrayList<Button>() ;
        nameTexts = new ArrayList<TextView>() ;

        nameButtons.add( ( Button )findViewById( R.id.nameBut1 ) ) ;
        nameButtons.add( ( Button )findViewById( R.id.nameBut2 ) ) ;
        nameButtons.add( ( Button )findViewById( R.id.nameBut3 ) ) ;
        nameButtons.add( ( Button )findViewById( R.id.nameBut4 ) ) ;
        nameButtons.add( ( Button )findViewById( R.id.nameBut5 ) ) ;

        nameTexts.add( ( TextView )findViewById( R.id.nameText1 ) ) ;
        nameTexts.add( ( TextView )findViewById( R.id.nameText2 ) ) ;
        nameTexts.add( ( TextView )findViewById( R.id.nameText3 ) ) ;
        nameTexts.add( ( TextView )findViewById( R.id.nameText4 ) ) ;
        nameTexts.add( ( TextView )findViewById( R.id.nameText5 ) ) ;

        spyAssigned = false ;
        revealed = false ;

        for( Button b : nameButtons ) {
            b.setVisibility( View.INVISIBLE ) ;
        }

        for( int i = 0 ; i < names.size() ; i++ ) {
            nameButtons.get( i ).setText( names.get( i ) ) ;
            nameButtons.get( i ).setVisibility( View.VISIBLE ) ;
        }

        for( TextView t : nameTexts ) {
            t.setVisibility( View.INVISIBLE ) ;
        }

        HashMap<String,String[]> locationRoles = new HashMap<String,String[]>() ;

        locationRoles.put( res.getStringArray( R.array.location )[0], res.getStringArray(R.array.beachRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[1], res.getStringArray(R.array.theatreRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[2], res.getStringArray(R.array.casinoRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[3], res.getStringArray(R.array.CircusRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[4], res.getStringArray(R.array.bankRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[5], res.getStringArray(R.array.cathedralRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[6], res.getStringArray(R.array.officeRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[7], res.getStringArray(R.array.armyRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[8], res.getStringArray(R.array.hospitalRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[9], res.getStringArray(R.array.hotelRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[10], res.getStringArray(R.array.militaryRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[11], res.getStringArray(R.array.trainRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[12], res.getStringArray(R.array.pirateRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[13], res.getStringArray(R.array.polarRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[14], res.getStringArray(R.array.policeRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[15], res.getStringArray(R.array.restaurantRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[16], res.getStringArray(R.array.gasRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[17], res.getStringArray(R.array.spaceRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[18], res.getStringArray(R.array.submarineRoles ) ) ;
        locationRoles.put( res.getStringArray( R.array.location )[19], res.getStringArray(R.array.battalionRoles) ) ;

        location = res.getStringArray( R.array.location )[ ( int )( Math.random() * 20 ) ] ;

        while ( !spyAssigned ) {
            for (int i = 0; i < names.size(); i++) {
                String role = "";
                if (Math.random() > .8 && !spyAssigned) {
                    nameTexts.get(i).setText("Role: Spy | Location: ???");
                    spyLocation = i;
                    spyAssigned = true;
                } else {
                    role = locationRoles.get(location)[(int)(Math.random() * 5)];
                    nameTexts.get(i).setText("Role: " + role + " | Location: " + location);
                }
            }
        }
    }

    public void reveal( View v ) {
        int location = 0 ;
        for( int i = 0 ; i < nameButtons.size() ; i++ ) {
            if( nameButtons.get(i).equals( v ) ) {
                location = i ;
                break ;
            }
        }
        if( nameTexts.get( location ).getVisibility() == View.VISIBLE ) {
            nameTexts.get( location ).setVisibility( View.INVISIBLE ) ;
            revealed = false ;
        } else if ( !revealed ) {
            nameTexts.get( location ).setVisibility( View.VISIBLE ) ;
            revealed = true ;
        }
    }

    public void startGame( View v ) {
        Intent intent = new Intent( this, SpyfallGame.class ) ;
        intent.putExtra( "location", location ) ;
        intent.putExtra( "spy", names.get( spyLocation ) ) ;
        intent.putExtra( "time", time ) ;
        startActivity( intent ) ;
    }
}

