package com.rb.rbadmin.util;

import android.util.Patterns;
import android.widget.EditText;

public class Validator {

    public static boolean validateEmail(EditText editText, String msg){
        if(!((editText.getText().toString().length() != 0) && Patterns.EMAIL_ADDRESS.matcher(editText.getText().toString().trim()).matches())){
            editText.setError(msg);
            return false;
        }
        return true;
    }

    public static boolean validateET(EditText editText, String msg){
        if(editText.getText().toString().length() == 0){
            editText.setError(msg);
            return false;
        }
        return true;
    }

    public static boolean validatePass(EditText editText1,EditText editText2, String msg){
        String pass1 = editText1.getText().toString().trim();
        String pass2 = editText2.getText().toString().trim();
        if(pass1.equals(pass2) && pass1.length() != 0){
            return true;
        }
        editText2.setError(msg);
        return false;
    }

}
