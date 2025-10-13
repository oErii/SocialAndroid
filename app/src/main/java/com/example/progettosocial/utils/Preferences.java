package com.example.progettosocial.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    public static void salvaEmail(Context context, String email){
        SharedPreferences preferences = context.getSharedPreferences("Email", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Email",email);
        editor.apply();
    }

    public static String caricaEmail(Context context){
        SharedPreferences preferences = context.getSharedPreferences("Email",MODE_PRIVATE);
        return preferences.getString("Email","");
    }

    public static void salvaPsw(Context context, String psw){
        SharedPreferences preferences = context.getSharedPreferences("Psw", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Psw",psw);
        editor.apply();
    }

    public static String caricaPsw(Context context){
        SharedPreferences preferences = context.getSharedPreferences("Psw",MODE_PRIVATE);
        return preferences.getString("Psw","");
    }
}
