package com.example.georgerr19.project1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText inputBill ;
    private TextView displayTip ;
    private TextView displayTotal ;
    private Spinner tipAmount ;
    private ArrayAdapter<CharSequence> adapter ;
    private InputFilter filter ;

    private double bill = 0.0;
    private double tip = 0;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState ) ;
        setContentView( R.layout.activity_main ) ;

        inputBill = findViewById( R.id.inputBill ) ;
        displayTip = findViewById( R.id.DisplayTip ) ;
        displayTotal = findViewById( R.id.displayTotal ) ;
        tipAmount = findViewById( R.id.spinner ) ;
        adapter = ArrayAdapter.createFromResource(this,R.array.tipAmounts, android.R.layout.simple_spinner_item ) ;
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item ) ;
        tipAmount.setAdapter( adapter ) ;

        inputBill.addTextChangedListener( new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            public void afterTextChanged(Editable s) {
                calculateTip() ;
            }
        } ) ;

        tipAmount.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calculateTip();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        } ) ;
        filter = new DecimalInputFilter(7,3) ;

        inputBill.setFilters( new InputFilter[] { filter } ) ;
    }

    public void calculateTip() {
            if ((inputBill.getText().toString()).equals("")) {
                bill = 0;
            } else {
                bill = Math.round(Double.parseDouble(inputBill.getText().toString()) * 100) / 100;
            }
            tip = Math.round(Double.parseDouble(tipAmount.getSelectedItem().toString()) * 100) / 100;
            double tipAmount = bill * tip * .01;

            String tipString = "Your tip is: $" + tipAmount;
            String totalString = "Your total is: $" + (tipAmount + bill);

            if( tipString.substring(tipString.indexOf( "." ) ).length() < 3 ) {
                while( tipString.substring(tipString.indexOf( "." ) ).length() < 3 ) {
                    tipString = tipString + "0";
                }
            }

            if( totalString.substring(totalString.indexOf( "." ) ).length() < 3 ) {
                while( totalString.substring(totalString.indexOf( "." ) ).length() < 3 ) {
                    totalString = totalString + "0";
                }
            }

            if( tipString.substring(tipString.indexOf( "." ) ).length() > 3 ) {
                tipString = tipString.substring(0,tipString.indexOf( "." ) ) + tipString.substring( tipString.indexOf("."),tipString.indexOf(".") + 2 ) ;
            }

            if( totalString.substring(totalString.indexOf( "." ) ).length() > 3 ) {
                totalString = totalString.substring(0,totalString.indexOf( "." ) ) + totalString.substring( totalString.indexOf("."),totalString.indexOf(".") + 2 ) ;
            }

            displayTip.setText(tipString);
            displayTotal.setText(totalString);
            }
}

