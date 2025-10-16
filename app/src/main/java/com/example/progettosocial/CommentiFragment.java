package com.example.progettosocial;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.progettosocial.databinding.FragmentCommentiBinding;
import com.example.progettosocial.model.Commento;
import com.example.progettosocial.model.CommentoAdapter;
import com.example.progettosocial.model.Post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CommentiFragment extends Fragment {

    FragmentCommentiBinding binding;

    CommentiFragmentArgs commentiFA;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            commentiFA = CommentiFragmentArgs.fromBundle(getArguments());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCommentiBinding.inflate(inflater, container, false);
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        binding.recyclerViewPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<Commento> listaCommenti= new ArrayList<>();
        CommentoAdapter adapter = new CommentoAdapter(listaCommenti);
        binding.recyclerViewPosts.setAdapter(adapter);
        Post post =commentiFA.getPost();

        binding.CommentoBox.setEndIconOnClickListener(v -> {
            String contenutoCommento = binding.CommentoContent.getText().toString();
            if(contenutoCommento.isEmpty()) {
                Toast.makeText(getContext(), "Commento Vuoto", Toast.LENGTH_SHORT).show();
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