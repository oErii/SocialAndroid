package com.example.progettosocial;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.progettosocial.dao.PostDAO;
import com.example.progettosocial.databinding.ActivityHomeBinding;
import com.example.progettosocial.model.Post;
import com.example.progettosocial.model.PostAdapter;
import com.example.progettosocial.model.Utente;
import com.example.progettosocial.utils.DBManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeActivity  extends AppCompatActivity {

    private ActivityHomeBinding binding;

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
        String nome=user.getNome()+" "+user.getCognome();
        DBManager db = DBManager.getInstance(this);
        PostDAO postDao = db.getPostDao();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Post esempio1 = new Post(null, nome, "IlSocial.", LocalDateTime.now().format(formatter).toString());
        Post esempio2 = new Post(null, nome, "IlSocial.", LocalDateTime.now().format(formatter).toString());
        Post esempio3 = new Post(null, nome, "IlSocial.", LocalDateTime.now().format(formatter).toString());
        postDao.insertPost(esempio1);
        postDao.insertPost(esempio2);
        postDao.insertPost(esempio3);

        binding.recyclerViewPosts.setLayoutManager(new LinearLayoutManager(this));
        PostAdapter adapter = new PostAdapter(postDao.getAll());
        binding.recyclerViewPosts.setAdapter(adapter);

    }
}
