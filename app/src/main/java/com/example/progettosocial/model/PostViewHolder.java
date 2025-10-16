package com.example.progettosocial.model;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettosocial.R;

public class PostViewHolder extends RecyclerView.ViewHolder{

    TextView textViewUser, textViewDate, textViewPostContent;
    Post post;
    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewUser = itemView.findViewById(R.id.RowUserName);
        textViewDate = itemView.findViewById(R.id.RowPostDate);
        textViewPostContent = itemView.findViewById(R.id.RowPostContent);
        itemView.setOnClickListener(v->{
            Intent intent = new Intent(itemView.getContext(), CommentiActivity.class);
            intent.putExtra("post",post);
            itemView.getContext().startActivity(intent);
        });
    }
}
