package com.example.progettosocial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.progettosocial.databinding.ActivityMainBinding;
import com.example.progettosocial.model.Utente;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private  static final String USERNAME = "admin";
    private  static final String PASSWORD = "psw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.Accedi.setOnClickListener(
                v->{
                    if (USERNAME.equals(binding.Email.getText().toString())&& PASSWORD.equals(binding.Psw.getText().toString())){
                        Intent intent = new Intent(MainActivity.this, PostActivity.class);
                        // intent.putExtra("username",binding.username.getText().toString());
                        Utente utente = new Utente(USERNAME,PASSWORD,"Marco","Rossi");
                        intent.putExtra("utente",utente);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(this, "Username o password errati", Toast.LENGTH_SHORT).show();
                    }
                }d
        );
    }
}