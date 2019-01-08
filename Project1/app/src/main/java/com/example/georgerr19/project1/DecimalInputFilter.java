package com.example.georgerr19.project1 ;

import android.text.InputFilter ;
import android.text.Spanned ;
import android.util.Log ;

public class DecimalInputFilter implements InputFilter {

    private int beforeDec ;
    private int afterDec ;

    public DecimalInputFilter(int beforeDec, int afterDec) {
        this.beforeDec = beforeDec;
        this.afterDec = afterDec;
    }

    public CharSequence filter( CharSequence source, int start, int end, Spanned dest, int dstart, int dend ) {
        int dotPos = -1 ;

        if ( source.equals( "." ) && dest.toString().length() == 0) {
            return "0." ;
        }

        if( dest.toString().contains( "." ) ) {
            dotPos = dest.toString().indexOf(".") ;
        }

        Log.d("DotPos: ", dotPos + "" ) ;
        if( dotPos >= 0 && dstart > dotPos && ( dest.toString().substring(dotPos).length()) == afterDec ) {
            return "" ;
        } else if ( dotPos >= 0 && dstart < dotPos && ( dest.toString().substring( 0 , dotPos ).length() ) == beforeDec ) {
            return "" ;
        }
        if( dotPos < 0 ) {
            if( dest.toString().length() == beforeDec && !source.equals( "." ) ) {
                return "" ;
            }
        }

        return null ;
    }
}

