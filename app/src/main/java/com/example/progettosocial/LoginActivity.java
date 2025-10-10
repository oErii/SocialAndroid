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
import com.example.progettosocial.model.DatabaseUtenti;
import com.example.progettosocial.model.Utente;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    DatabaseUtenti db = DatabaseUtenti.getInstance();

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
                    String email = binding.Email.getText().toString().trim();
                    String password = binding.Psw.getText().toString().trim();

                    if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(this, "Inserisci Email e Password", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    boolean trovato = false;
                    Utente utenteTrovato = null;
                    for (Utente u : db.getUtenti()) {
                        if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                            trovato = true;
                            utenteTrovato = u;
                            break;
                        }
                    }

                    if (trovato) {
                        Toast.makeText(this, "Login riuscito!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.putExtra("utente",utenteTrovato);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Email o Password Errati", Toast.LENGTH_SHORT).show();
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