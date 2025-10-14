package com.example.progettosocial;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.progettosocial.dao.PostDAO;
import com.example.progettosocial.databinding.ActivityCommentiBinding;
import com.example.progettosocial.databinding.ActivityHomeBinding;
import com.example.progettosocial.model.Commento;
import com.example.progettosocial.model.CommentoAdapter;
import com.example.progettosocial.model.Post;
import com.example.progettosocial.model.PostAdapter;
import com.example.progettosocial.model.Utente;
import com.example.progettosocial.utils.DBManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CommentiActivity extends AppCompatActivity {

    private ActivityCommentiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentiBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            int ime = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom;
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, Math.max(ime, systemBars.bottom));
            return insets;
        });

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        binding.recyclerViewPosts.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Commento> listaCommenti= new ArrayList<>();
        CommentoAdapter adapter = new CommentoAdapter(listaCommenti);
        binding.recyclerViewPosts.setAdapter(adapter);
        Post post =(Post) getIntent().getSerializableExtra("post");

        binding.CommentoBox.setEndIconOnClickListener(v -> {
            String contenutoCommento = binding.CommentoContent.getText().toString();
            if(contenutoCommento.isEmpty()) {
                Toast.makeText(this, "Commento Vuoto", Toast.LENGTH_SHORT).show();
            }else {

                Commento nuovoCommento = new Commento("Mario Rossi", contenutoCommento, LocalDateTime.now().format(formatter).toString());
                listaCommenti.add(nuovoCommento);
                CommentoAdapter adapterNuovo = new CommentoAdapter(listaCommenti);
                binding.recyclerViewPosts.setAdapter(adapterNuovo);

                binding.CommentoContent.setText(null);//svuota il campo dopo avere creato il post
            }
        });
    }
}
