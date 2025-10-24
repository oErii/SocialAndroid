package com.example.progettosocial.model;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettosocial.LoginActivity;
import com.example.progettosocial.R;
import com.example.progettosocial.dao.PostDAO;
import com.example.progettosocial.ui.HomeFragmentDirections;
import com.example.progettosocial.utils.DBManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class PostViewHolder extends RecyclerView.ViewHolder {

    TextView textViewUser, textViewDate, textViewPostContent;
    Post post;
    PostAdapter postA;

    public PostViewHolder(@NonNull View itemView, PostAdapter postA) {
        super(itemView);
        this.postA=postA;
        textViewUser = itemView.findViewById(R.id.RowUserName);
        textViewDate = itemView.findViewById(R.id.RowPostDate);
        textViewPostContent = itemView.findViewById(R.id.RowPostContent);
        itemView.setOnClickListener(v->{


        });

        setForContextMenu(itemView);

        itemView.setOnLongClickListener(v ->{
            PopupMenu menu = new PopupMenu(itemView.getContext(), itemView);
            MenuInflater menuInf = menu.getMenuInflater();
            menuInf.inflate(R.menu.context_menu_post, menu.getMenu());
            PostDAO postdao = DBManager.getInstance(itemView.getContext()).getPostDao();
            menu.setOnMenuItemClickListener(item -> {
                if(item.getItemId()==R.id.Elimina){

                    View myDialogCustomView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.dialog_elimina, null);
                    new MaterialAlertDialogBuilder(itemView.getContext())
                            .setView(myDialogCustomView)
                            .setNeutralButton("Cancel", (dialog, position) -> {
                                //non fa niente e chiude il dialog
                            })
                            .setNegativeButton("Elimina", (dialog, position) -> {
                                postdao.deletePost(post);
                                postA.aggiornaLista(postdao.getAll());
                                postA.notifyDataSetChanged();
                            })
                            .show();
                }else {
                    postdao.updatePost(post);
                }

                return true;
            });
            menu.show();
            return true;
        });
    }

    public void setPost(Post post){
        this.post=post;
    }

    public Post getPost() {
        return post;
    }

    public void setForContextMenu(View v){ //registra la possibilità di creare un contextMenu
        ((LoginActivity) itemView.getContext()).registerForContextMenu(v);
    }
}
