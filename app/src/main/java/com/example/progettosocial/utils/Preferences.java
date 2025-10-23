package com.example.progettosocial.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.progettosocial.api.dto.request.LoginRequest;
import com.example.progettosocial.api.dto.response.UtenteInfoDTO;

public class Preferences {
    public static void saveLoginRequest(Context context , LoginRequest loginRequest) {
        SharedPreferences sp = context.getSharedPreferences("loginRequest" , MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", loginRequest.getUsername());
        editor.putString("psw", loginRequest.getPassword());
        editor.apply();
    }

    public static LoginRequest loadLoginRequest(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginRequest", MODE_PRIVATE);
        return new LoginRequest(
                sp.getString("username",""),
                sp.getString("psw","")
        );
    };

    public static void saveTKN(Context context, String jwt) {
        SharedPreferences sp = context.getSharedPreferences("jwt" , MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("jwt", jwt);
        editor.apply();
    }

    public static String loadTKN(Context context){
        SharedPreferences sp = context.getSharedPreferences("jwt", MODE_PRIVATE);
        return sp.getString("jwt", "");
    }

    public static void setLoggato(Context context, boolean loggato){
        SharedPreferences sp = context.getSharedPreferences("isLoggato", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("loggato", loggato);
        editor.apply();
    }

    public static boolean isLoggato(Context context){
        SharedPreferences sp = context.getSharedPreferences("isLoggato", MODE_PRIVATE);
        return sp.getBoolean("loggato", false);
    }
}
