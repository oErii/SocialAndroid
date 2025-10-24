package com.example.progettosocial.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettosocial.R;
import com.example.progettosocial.api.dto.response.CommentiByPostResponse;
import com.example.progettosocial.api.dto.response.CommentoDTO;

import java.util.List;

public class CommentoAdapter extends RecyclerView.Adapter<CommentoViewHolder>{
    private List<CommentoDTO> commenti;

    public CommentoAdapter(List<CommentoDTO> all) {
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
        CommentoDTO commento = commenti.get(position);
        holder.textViewUser.setText(commento.getAutore().getUsername());
        holder.textViewPostContent.setText(commento.getTesto());
        holder.textViewDate.setText(commento.getDataPubblicazione());
    }

    @Override
    public int getItemCount() {
        return commenti.size();
    }
}
