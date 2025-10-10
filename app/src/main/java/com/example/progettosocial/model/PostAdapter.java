package com.example.progettosocial.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettosocial.R;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder>{

    private List<Post> posts;

    public PostAdapter() {
        this.posts = DatabasePost.getInstance().getPosts();
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.textViewUser.setText(post.getUser().getNome()+" "+post.getUser().getCognome());
        holder.textViewPostContent.setText(post.getContent());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        holder.textViewDate.setText(post.getDate().format(formatter));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
