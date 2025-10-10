package com.example.progettosocial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.progettosocial.databinding.ActivityLoginBinding;
import com.example.progettosocial.databinding.ActivityRegistrazioneBinding;
import com.example.progettosocial.model.DatabaseUtenti;
import com.example.progettosocial.model.Utente;

public class RegistrazioneActivity extends AppCompatActivity {

    private ActivityRegistrazioneBinding binding;

    DatabaseUtenti db = DatabaseUtenti.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityRegistrazioneBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.Accedi.setOnClickListener(
                v->{
                    Intent intent = new Intent(RegistrazioneActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
        );

        binding.Registrati.setOnClickListener(
                v->{
                    String username = binding.Username.getText().toString();
                    String email = binding.Email.getText().toString();
                    String password = binding.Psw.getText().toString();
                    String nome = binding.Nome.getText().toString();
                    String cognome = binding.Cognome.getText().toString();

                    if(username.isEmpty() || email.isEmpty() || password.isEmpty() || nome.isEmpty() || cognome.isEmpty()) {
                        Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    boolean usernamePresente = false;
                    boolean emailPresente = false;

                    for (Utente u : db.getUtenti()) {
                        if (u.getUsername().equalsIgnoreCase(username)) {
                            usernamePresente = true;
                        }
                        if (u.getEmail().equalsIgnoreCase(email)) {
                            emailPresente = true;
                        }
                    }

                    if (usernamePresente) {
                        Toast.makeText(this, "Username già presente", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (emailPresente) {
                        Toast.makeText(this, "Email già presente", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Utente nuovoUtente = new Utente(username, email, password, nome, cognome);
                    db.getUtenti().add(nuovoUtente);

                    Toast.makeText(this, "Registrazione avvenuta!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrazioneActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                }
        );

    }
}
