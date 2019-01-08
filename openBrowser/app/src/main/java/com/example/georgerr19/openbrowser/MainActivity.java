package com.example.georgerr19.openbrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText text ;
    private String string ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        string = "The_Stories_of_Ray_Bradbury" ;
        text = findViewById(R.id.editText) ;
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if( !(text.getText().toString() + "").equals("") ) {
                     string =text.getText().toString().replace( ' ', '_' ) ;
                }
            }
        });
    }

    public void openBrowser( View v ) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://en.wikipedia.org/wiki/" + string));
        startActivity(browserIntent);
    }
}
