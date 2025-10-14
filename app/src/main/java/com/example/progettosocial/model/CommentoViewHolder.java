package com.example.progettosocial.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettosocial.R;

public class CommentoViewHolder extends RecyclerView.ViewHolder{

    TextView textViewUser, textViewDate, textViewPostContent;

    public CommentoViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewUser = itemView.findViewById(R.id.RowUserName);
        textViewDate = itemView.findViewById(R.id.RowPostDate);
        textViewPostContent = itemView.findViewById(R.id.RowPostContent);
    }
}
