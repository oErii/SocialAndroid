package com.example.progettosocial.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettosocial.R;

import java.util.List;

public class CommentoAdapter extends RecyclerView.Adapter<CommentoViewHolder>{
    private List<Commento> commenti;

    public CommentoAdapter(List<Commento> all) {
        this.commenti = all;
    }

    @NonNull
    @Override
    public CommentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_post, parent, false);
        return new CommentoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentoViewHolder holder, int position) {
        Commento commento = commenti.get(position);
        holder.textViewUser.setText(commento.getNomeCompleto());
        holder.textViewPostContent.setText(commento.getContent());
        holder.textViewDate.setText(commento.getData());
    }

    @Override
    public int getItemCount() {
        return commenti.size();
    }
}
