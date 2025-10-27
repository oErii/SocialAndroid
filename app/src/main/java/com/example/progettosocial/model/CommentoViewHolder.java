package com.example.progettosocial.model;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettosocial.LoginActivity;
import com.example.progettosocial.R;
import com.example.progettosocial.api.ApiManager;
import com.example.progettosocial.api.dto.request.DeleteCommentoRequest;
import com.example.progettosocial.api.dto.response.CommentoDTO;
import com.example.progettosocial.dao.PostDAO;
import com.example.progettosocial.utils.DBManager;
import com.example.progettosocial.utils.Preferences;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CommentoViewHolder extends RecyclerView.ViewHolder implements Callback {

    TextView textViewUser, textViewDate, textViewPostContent;
    CommentoDTO commento;
    CommentoAdapter commentoAdapter;

    ListaPostCallback listaPostCallback;
    public CommentoViewHolder(@NonNull View itemView, CommentoAdapter commentoAdapter, ListaPostCallback listaPostCallback) {
        super(itemView);
        this.commentoAdapter=commentoAdapter;
        textViewUser = itemView.findViewById(R.id.RowUserName);
        textViewDate = itemView.findViewById(R.id.RowPostDate);
        textViewPostContent = itemView.findViewById(R.id.RowPostContent);
        this.listaPostCallback=listaPostCallback;


    }

    public void setForContextMenu(View v){ //registra la possibilità di creare un contextMenu
        ((LoginActivity) itemView.getContext()).registerForContextMenu(v);
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        itemView.post(() -> {
            Toast.makeText(itemView.getContext() , "Qualcosa è andato storto controlla la connessione internet e riprova", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        String url = call.request().url().toString();
        if (url.contains("deleteCommento")) {
            if (response.isSuccessful()) {
                String body = response.body().string();
                itemView.post(() -> {
                    listaPostCallback.updateList();
                    Toast.makeText(itemView.getContext(), body, Toast.LENGTH_SHORT).show();
                });
            }else {
                String body = response.body().string();
                itemView.post(() -> {
                    Toast.makeText(itemView.getContext(), body, Toast.LENGTH_SHORT).show();
                });
            }
        }
    }

    public void setCommento(CommentoDTO commentoDto){
        this.commento=commentoDto;

        itemView.setOnLongClickListener(v -> {
            PopupMenu menu = new PopupMenu(itemView.getContext(), itemView);
            MenuInflater menuInf = menu.getMenuInflater();
            menuInf.inflate(R.menu.context_menu_post, menu.getMenu());

            menu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.Elimina) {
                    View myDialogCustomView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.dialog_elimina, null);
                    new MaterialAlertDialogBuilder(itemView.getContext())
                            .setView(myDialogCustomView)
                            .setNeutralButton("Cancel", (dialog, position) -> {

                            })
                            .setNegativeButton("Elimina", (dialog, position) -> {
                                String username = Preferences.loadLoginRequest(itemView.getContext()).getUsername();
                                if(textViewUser.getText().toString().equals(username)) {
                                    ApiManager.getInstance().deleteCommento(new DeleteCommentoRequest(commento.getId()), this, itemView.getContext());
                                }else {
                                    Toast.makeText(itemView.getContext() , "Impossibile eliminare commento", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }
                return true;
            });
            menu.show();
            return true;
        });

        setForContextMenu(itemView);
    }
}
