package com.example.progettosocial;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.progettosocial.databinding.ActivityHomeBinding;
import com.example.progettosocial.model.DatabasePost;
import com.example.progettosocial.model.Post;
import com.example.progettosocial.model.PostAdapter;
import com.example.progettosocial.model.Utente;

import java.time.LocalDateTime;

public class HomeActivity  extends AppCompatActivity {

    private ActivityHomeBinding binding;

    DatabasePost db = DatabasePost.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  = ActivityHomeBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Utente user =(Utente)  getIntent().getSerializableExtra("utente");

        // --- Aggiungo un post di esempio/test ---
        if (DatabasePost.getInstance().getPosts().isEmpty()) {

            Post esempio = new Post(user, "IlSocial.", LocalDateTime.now());
            DatabasePost.getInstance().getPosts().add(esempio);
        }

        binding.recyclerViewPosts.setLayoutManager(new LinearLayoutManager(this));
        PostAdapter adapter = new PostAdapter();
        binding.recyclerViewPosts.setAdapter(adapter);

    }
}
