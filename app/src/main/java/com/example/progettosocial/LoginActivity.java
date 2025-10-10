package com.example.progettosocial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.progettosocial.databinding.ActivityLoginBinding;
import com.example.progettosocial.model.Utente;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private  static final String EMAIL = "enrico.tarulli@gmail.com";
    private  static final String PASSWORD = "Psw123!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityLoginBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.Accedi.setOnClickListener(
                v->{
                    if (EMAIL.equals(binding.Email.getText().toString())&& PASSWORD.equals(binding.Psw.getText().toString())){
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        // intent.putExtra("username",binding.username.getText().toString());
                        Utente utente = new Utente("oErii", EMAIL, PASSWORD, "Enrico", "Tarulli");
                        intent.putExtra("utente",utente);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(this, "Username o password errati", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        binding.Registrati.setOnClickListener(
                v->{
                    Intent intent = new Intent(LoginActivity.this, RegistrazioneActivity.class);
                    startActivity(intent);
                    finish();
                }
        );
    }
}