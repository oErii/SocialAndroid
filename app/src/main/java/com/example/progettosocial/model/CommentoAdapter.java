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
    ListaPostCallback listaPostCallback;

    public CommentoAdapter(List<CommentoDTO> all, ListaPostCallback listaPostCallback) {
        this.commenti = all;
        this.listaPostCallback = listaPostCallback;
    }

    @NonNull
    @Override
    public CommentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_commento, parent, false);
        return new CommentoViewHolder(view, this, listaPostCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentoViewHolder holder, int position) {
        CommentoDTO commento = commenti.get(position);
        holder.textViewUser.setText(commento.getAutore().getUsername());
        holder.textViewPostContent.setText(commento.getTesto());
        holder.textViewDate.setText(commento.getDataPubblicazione());
        holder.setCommento(commento);
    }

    @Override
    public int getItemCount() {
        return commenti.size();
    }

    public List<CommentoDTO> getCommenti() {
        return commenti;
    }

}
