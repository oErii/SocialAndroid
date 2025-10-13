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
import com.example.progettosocial.utils.Preferences;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    Utente utente = new Utente("oErii", "enrico.tarulli@gmail.com", "psw", "Enrico", "Tarulli");


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

        String email = Preferences.caricaEmail(this);
        String psw = Preferences.caricaPsw(this);

        if (email.equals(utente.getEmail())&&psw.equals(utente.getPassword())){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.putExtra("utente",utente);
            startActivity(intent);
        }

        binding.Accedi.setOnClickListener(
                v->{
                    String emailIns = binding.Email.getText().toString().toLowerCase().trim();
                    String pswIns = binding.Psw.getText().toString().trim();
                    if (utente.getEmail().equals(emailIns)&& utente.getPassword().equals(pswIns)){
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        Preferences.salvaEmail(this, emailIns);
                        Preferences.salvaPsw(this, pswIns);
                        intent.putExtra("utente",utente);
                        startActivity(intent);
                        finish();
                    } else {
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