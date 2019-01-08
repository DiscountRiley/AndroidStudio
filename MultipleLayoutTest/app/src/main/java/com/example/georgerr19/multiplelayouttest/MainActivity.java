package com.example.georgerr19.multiplelayouttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private boolean currLayout ;
    private Spinner spin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currLayout = true ;
        spin  = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        MenuInflater inflater = getMenuInflater() ;
        inflater.inflate(R.menu.options_menu, menu) ;
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_option_one:
                actionOne();
            return true ;
            case R.id.menu_option_two:
                actionTwo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void changeScreen( View v ) {
        if( currLayout ) {
            setContentView(R.layout.activity_minor) ;
            currLayout = false ;
        } else {
            setContentView(R.layout.activity_main) ;
            currLayout = true ;
            spin  = (Spinner)findViewById(R.id.spinner);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_choices, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin.setAdapter(adapter);
        }
    }

    public void actionOne(){
        setContentView(R.layout.activity_main) ;
        currLayout = true ;
        spin  = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
    }

    public void actionTwo(){
        setContentView(R.layout.activity_minor) ;
        currLayout = false ;
    }
}
