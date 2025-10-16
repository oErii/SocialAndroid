package com.example.progettosocial;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.progettosocial.dao.PostDAO;
import com.example.progettosocial.databinding.FragmentHomeBinding;
import com.example.progettosocial.model.Post;
import com.example.progettosocial.model.PostAdapter;
import com.example.progettosocial.model.Utente;
import com.example.progettosocial.utils.DBManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    HomeFragmentArgs homeFA;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            homeFA = HomeFragmentArgs.fromBundle(getArguments());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
            int ime = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom;
            v.setPadding(
                    v.getPaddingLeft(),
                    v.getPaddingTop(),
                    v.getPaddingRight(),
                    Math.max(ime, 0)
            );
            return insets;
        });
        Utente user = homeFA.getUtente();
        String nome=user.getNome()+" "+user.getCognome();
        DBManager db = DBManager.getInstance(getContext());
        PostDAO postDao = db.getPostDao();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        binding.recyclerViewPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        PostAdapter adapter = new PostAdapter(postDao.getAll());
        binding.recyclerViewPosts.setAdapter(adapter);

        binding.PostBox.setEndIconOnClickListener(v -> {
            String contenutoPost = binding.PostContent.getText().toString();
            if(contenutoPost.isEmpty()) {
                Toast.makeText(getContext(), "Post Vuoto", Toast.LENGTH_SHORT).show();
            }else {

                Post nuovoPost = new Post(null, nome, contenutoPost, LocalDateTime.now().format(formatter).toString());
                postDao.insertPost(nuovoPost);
                PostAdapter adapterNuovo = new PostAdapter(postDao.getAll());
                binding.recyclerViewPosts.setAdapter(adapterNuovo);


                binding.PostContent.setText(null);//svuota il campo dopo avere creato il post
            }
        });

    }
}