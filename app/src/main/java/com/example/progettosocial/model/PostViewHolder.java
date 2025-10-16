package com.example.progettosocial.model;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progettosocial.HomeFragmentDirections;
import com.example.progettosocial.LoginActivity;
import com.example.progettosocial.LoginFragmentDirections;
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
            NavController controller = Navigation.findNavController(itemView);
            NavDirections destinazione= HomeFragmentDirections.actionHomeFragmentToCommentiFragment(post);
            controller.navigate(destinazione);
        });

        setForContextMenu(itemView);
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
