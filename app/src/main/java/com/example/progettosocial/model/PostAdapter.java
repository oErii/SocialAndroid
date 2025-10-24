package com.example.progettosocial.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettosocial.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<NuovoPostViewHolder>{

    private List<Post> posts;

    public PostAdapter(List<Post> all) {
        this.posts = all;
    }

    @NonNull
    @Override
    public NuovoPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_post, parent, false);
        return new NuovoPostViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NuovoPostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.textViewUser.setText(post.getUsername());
        holder.textViewPostContent.setText(post.getTesto());
        holder.textViewDate.setText(post.getData());
        holder.textViewCommenti.setText(post.getNumeroCommenti().toString());
        holder.textViewLikes.setText(post.getLikes().toString());
        holder.setPost(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void aggiornaLista(List<Post> posts) {
        this.posts = posts;
    }

}
