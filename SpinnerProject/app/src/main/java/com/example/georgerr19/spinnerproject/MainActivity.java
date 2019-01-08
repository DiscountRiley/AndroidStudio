package com.example.georgerr19.spinnerproject;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner inputSpinner ;
    private Spinner outputSpinner ;

    private EditText input ;

    private TextView outputGreeting ;
    private TextView output ;

    private ArrayAdapter inputAdapter ;
    private ArrayAdapter outputAdapter ;

    private ImageView inputImage ;
    private ImageView outputImage ;

    private int inputAge ;
    private double inRatio ;
    private double outRatio ;

    private double human ;
    private double dog ;
    private double cat ;
    private double horse ;
    private double ant ;
    private double turtle ;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState ) ;
        setContentView( R.layout.activity_main ) ;

        inputSpinner = findViewById( R.id.inputSpinner ) ;
        outputSpinner = findViewById( R.id.outputSpinner ) ;

        input = findViewById( R.id.input ) ;
        outputGreeting = findViewById( R.id.outputGreeting ) ;
        output = findViewById( R.id.output ) ;

        inputAdapter = ArrayAdapter.createFromResource(this, R.array.inputChoices, android.R.layout.simple_spinner_item ) ;
        inputAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item ) ;
        inputSpinner.setAdapter( inputAdapter ) ;

        outputAdapter = ArrayAdapter.createFromResource(this, R.array.outputChoices, android.R.layout.simple_spinner_item ) ;
        outputAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item ) ;
        outputSpinner.setAdapter( outputAdapter ) ;

        inputImage = findViewById( R.id.inputImage ) ;
        outputImage = findViewById( R.id.outputImage ) ;

        inputAge = 0 ;
        inRatio = 1 ;
        outRatio = 1 ;

        human = 1 ;
        dog = 6.58 ;
        cat = 4.93 ;
        horse = 2.82 ;
        ant = 5.26 ;
        turtle = 0.4 ;

        inputSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView<?> parent, View view, int position, long id ) {
                updateOutput() ;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        } ) ;

        outputSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected( AdapterView<?> parent, View view, int position, long id ) {
                updateOutput() ;
            }
            @Override
            public void onNothingSelected( AdapterView<?> parent ) {
            }
        } ) ;

        input.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged( CharSequence s, int start, int count, int after ) {
            }
            @Override
            public void onTextChanged( CharSequence s, int start, int before, int count ) {
            }
            @Override
            public void afterTextChanged( Editable s ) {
                updateOutput() ;
            }
        } ) ;
        input.setFilters( new InputFilter[] { (new InputFilter() {

            @Override
            public CharSequence filter( CharSequence source, int start, int end, Spanned dest, int dstart, int dend ) {
                if( dest.length() == 6 ) {
                    return "" ;
                }
                return null;
            }
        } ) } ) ;
    }

    public void updateOutput() {
        if ( ! ( input.getText().toString() ).equals( "" ) ) {
            try {
                inputAge = Integer.parseInt(input.getText().toString());
            } catch ( Exception e ) {
                input.getText().clear() ;
                inputAge = 0 ;
            }
        }

            switch ( ( String ) inputSpinner.getSelectedItem() ) {
                case "Dog":
                    inRatio = dog ;
                    inputImage.setImageResource( R.drawable.dog ) ;
                    break ;
                case "Cat":
                    inRatio = cat ;
                    inputImage.setImageResource( R.drawable.cat ) ;
                    break ;
                case "Horse":
                    inRatio = horse ;
                    inputImage.setImageResource( R.drawable.horse ) ;
                    break ;
                case "Ant":
                    inRatio = ant ;
                    inputImage.setImageResource( R.drawable.ant ) ;
                    break ;
                case "Turtle":
                    inRatio = turtle ;
                    inputImage.setImageResource( R.drawable.turtle ) ;
                    break ;
                default:
                    inRatio = human ;
                    inputImage.setImageResource( R.drawable.human ) ;
            }

            switch ( ( String ) outputSpinner.getSelectedItem() ) {
                case "Dog":
                    outRatio = dog ;
                    outputImage.setImageResource( R.drawable.dog ) ;
                    break ;
                case "Cat":
                    outRatio = cat ;
                    outputImage.setImageResource( R.drawable.cat ) ;
                    break ;
                case "Horse":
                    outRatio = horse ;
                    outputImage.setImageResource( R.drawable.horse ) ;
                    break ;
                case "Ant":
                    outRatio = ant ;
                    outputImage.setImageResource( R.drawable.ant ) ;
                    break ;
                case "Turtle":
                    outRatio = turtle ;
                    outputImage.setImageResource( R.drawable.turtle ) ;
                    break ;
                default:
                    outRatio = human ;
                    outputImage.setImageResource( R.drawable.human ) ;
            }

            outputGreeting.setText( inputAge + " " + inputSpinner.getSelectedItem() + " is equal to: " ) ;
            output.setText( ( Math.round( inputAge / inRatio * outRatio ) ) + " " + outputSpinner.getSelectedItem() + " years" ) ;
    }
}
