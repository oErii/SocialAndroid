package com.example.progettosocial.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.progettosocial.R;
import com.example.progettosocial.api.ApiManager;
import com.example.progettosocial.api.dto.request.CreatePostRequest;
import com.example.progettosocial.api.dto.request.UpdatePostRequest;
import com.example.progettosocial.api.dto.response.LastPostResponse;
import com.example.progettosocial.api.dto.response.PostDTO;
import com.example.progettosocial.dao.PostDAO;
import com.example.progettosocial.databinding.FragmentHomeBinding;
import com.example.progettosocial.model.Post;
import com.example.progettosocial.model.PostAdapter;
import com.example.progettosocial.utils.DBManager;
import com.example.progettosocial.utils.Preferences;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class HomeFragment extends Fragment implements Callback {

    FragmentHomeBinding binding;
    DBManager db;
    PostDAO postDao;
    PostAdapter adapter;
    public static Boolean modificaOn=false;
    public static Post post;
    public static TextInputEditText modificaTesto;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        ApiManager.getInstance().getLastPost(this, requireContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = DBManager.getInstance(requireContext());
        postDao = db.getPostDao();

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

        /* Top App Bar */

        MaterialToolbar toolbar = binding.topAppBar;

        binding.imgUserProfile.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(binding.getRoot());
            NavDirections destinazione= HomeFragmentDirections.actionHomeFragmentToProfileFragment();
            controller.navigate(destinazione);
        });

        /* Contenuto Pagina*/

        modificaTesto=binding.PostContent;
        binding.recyclerViewPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PostAdapter(postDao.getAll());
        binding.recyclerViewPosts.setAdapter(adapter);

        binding.PostBox.setEndIconOnClickListener(v -> {
            if(!modificaOn) {
                String contenutoPost = binding.PostContent.getText().toString();
                if (contenutoPost.isEmpty()) {
                    Toast.makeText(getContext(), "Post Vuoto", Toast.LENGTH_SHORT).show();
                } else {
                    CreatePostRequest nuovoPostRequest = new CreatePostRequest(contenutoPost);
                    ApiManager.getInstance().creaPost(nuovoPostRequest, this, requireContext());
                    binding.PostContent.setText(null);//svuota il campo dopo avere creato il post
                }
            } else{
                UpdatePostRequest updatePostRequest = new UpdatePostRequest(post.getId(), binding.PostContent.getText().toString());
                ApiManager.getInstance().updatePost(updatePostRequest, this, getContext());
                binding.PostContent.setText(null);
                modificaOn = false;
                post = null;
            }
        });


    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        requireActivity().runOnUiThread(()->{
            Toast.makeText(requireContext(), "Qualcosa è andato storto controlla la connessione internet e riprova", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        String url = call.request().url().toString();
        if (url.contains("getLastPost")) {

            if (!response.isSuccessful()) {
                requireActivity().runOnUiThread(() -> {
                    Toast.makeText(requireContext(), "Impossibile caricare i post", Toast.LENGTH_SHORT).show();
                });
            } else {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    LastPostResponse lastPost = mapper.readValue(response.body().string(), LastPostResponse.class);
                    requireActivity().runOnUiThread(() -> {
                        db = DBManager.getInstance(requireContext());
                        postDao = db.getPostDao();
                        postDao.deleteAll();
                        for (PostDTO post : lastPost.getPostList()) {
                            postDao.insertPost(new Post(post.getId(), post.getAutore().getUsername(), post.getTesto(), post.getDataPubblicazione(), post.getNumeroLikes(), post.getNumeroCommenti(), post.isLiked(), post.isMine()));
                        }
                        binding.recyclerViewPosts.setLayoutManager(new LinearLayoutManager(getContext()));
                        adapter = new PostAdapter(postDao.getAll());
                        binding.recyclerViewPosts.setAdapter(adapter);
                    });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }else if(url.contains("createPost")){
            if (response.isSuccessful()) {
                requireActivity().runOnUiThread(() -> {
                    ApiManager.getInstance().getLastPost(this, requireContext());
                });
            } else {
                String body=response.body().string();
                requireActivity().runOnUiThread(()->{
                    Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
                });
            }
        }else if(url.contains("logout")){
            if (response.isSuccessful()) {
                Preferences.saveTKN(requireContext(), "");
                Preferences.setLoggato(requireContext(),false);
                String body=response.body().string();
                requireActivity().runOnUiThread(() -> {
                    Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
                    NavController controller = Navigation.findNavController(binding.getRoot());
                    NavDirections destinazione= HomeFragmentDirections.actionHomeFragmentToLoginFragment();
                    controller.navigate(destinazione);
                });
            }else {
                String body=response.body().string();
                requireActivity().runOnUiThread(()->{
                    Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
                });
            }
        } else if (url.contains("updatePost")) {
            if(response.isSuccessful()){
                String body=response.body().string();
                requireActivity().runOnUiThread(()->{
                    Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
                    ApiManager.getInstance().getLastPost(this, getContext());
                });
            }else {
                String body=response.body().string();
                requireActivity().runOnUiThread(()->{
                    Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
                });
            }
        }
    }
}