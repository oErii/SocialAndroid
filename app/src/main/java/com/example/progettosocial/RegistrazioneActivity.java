package com.example.progettosocial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.progettosocial.databinding.ActivityRegistrazioneBinding;
import com.example.progettosocial.model.Utente;

public class RegistrazioneActivity extends AppCompatActivity {

    private ActivityRegistrazioneBinding binding;


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
                    Intent intent = new Intent(RegistrazioneActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
        );

    }
}
