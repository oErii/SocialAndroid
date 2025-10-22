package com.example.progettosocial.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.progettosocial.api.dto.response.UtenteInfoDTO;

public class Preferences {
    public static void saveUtente(Context context , UtenteInfoDTO utente) {
        SharedPreferences sp = context.getSharedPreferences("utente" , MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", utente.getUsername());
        editor.putString("nome", utente.getNome());
        editor.putString("cognome", utente.getCognome());
        editor.putString("email", utente.getEmail());
        editor.putLong("id", utente.getId());
        editor.apply();
    }

    public static UtenteInfoDTO loadUtente(Context context) {
        SharedPreferences sp = context.getSharedPreferences("utente", MODE_PRIVATE);
        return new UtenteInfoDTO(

                sp.getString("username",""),
                sp.getString("email",""),
                sp.getString("nome",""),
                sp.getString("cognome",""),
                sp.getLong("id",0)
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
}
