package com.example.progettosocial.model;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.progettosocial.LoginActivity;
import com.example.progettosocial.R;
import com.example.progettosocial.api.ApiManager;
import com.example.progettosocial.api.dto.request.CreateLikeRequest;
import com.example.progettosocial.dao.PostDAO;
import com.example.progettosocial.ui.HomeFragmentDirections;
import com.example.progettosocial.utils.DBManager;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NuovoPostViewHolder extends RecyclerView.ViewHolder implements Callback {
    TextView textViewUser, textViewDate, textViewPostContent;
    Post post;
    PostAdapter postA;
    public NuovoPostViewHolder(@NonNull View itemView, PostAdapter postA) {
        super(itemView);
        this.postA = postA;
        textViewUser = itemView.findViewById(R.id.RowUserName);
        textViewDate = itemView.findViewById(R.id.RowPostDate);
        textViewPostContent = itemView.findViewById(R.id.RowPostContent);

        GestureDetector gestureDetector = new GestureDetector(itemView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                NavController controller = Navigation.findNavController(itemView);
                NavDirections destinazione = HomeFragmentDirections.actionHomeFragmentToCommentiFragment(post);
                controller.navigate(destinazione);
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                
                ApiManager.getInstance().toggleLike(new CreateLikeRequest(post.getId()),NuovoPostViewHolder.this, itemView.getContext());
                return true;
            }
        });

        itemView.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));

        itemView.setOnLongClickListener(v -> {
            PopupMenu menu = new PopupMenu(itemView.getContext(), itemView);
            MenuInflater menuInf = menu.getMenuInflater();
            menuInf.inflate(R.menu.context_menu_post, menu.getMenu());
            PostDAO postdao = DBManager.getInstance(itemView.getContext()).getPostDao();

            menu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.Elimina) {
                    View myDialogCustomView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.dialog_elimina, null);
                    new MaterialAlertDialogBuilder(itemView.getContext())
                            .setView(myDialogCustomView)
                            .setNeutralButton("Cancel", (dialog, position) -> {

                            })
                            .setNegativeButton("Elimina", (dialog, position) -> {
                                postdao.deletePost(post);
                                postA.aggiornaLista(postdao.getAll());
                                postA.notifyDataSetChanged();
                            })
                            .show();
                } else {
                    postdao.updatePost(post);
                }
                return true;
            });
            menu.show();
            return true;
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

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        /*chat ottengo un errore sul require activity*/
        itemView.post(() -> {
            Toast.makeText(itemView.getContext(),  "Qualcosa è andato storto controlla la connessione internet e riprova", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        if(response.isSuccessful()){
            String body=response.body().string();
            itemView.post(() -> {
                Toast.makeText(itemView.getContext(), body, Toast.LENGTH_SHORT).show();
            });
        } else if (response.code()==400) {
            String body=response.body().string();
            itemView.post(() -> {
                Toast.makeText(itemView.getContext(), body, Toast.LENGTH_SHORT).show();
            });
        } else if (response.code()==404) {
            String body=response.body().string();
            itemView.post(() -> {
                Toast.makeText(itemView.getContext(), body, Toast.LENGTH_SHORT).show();
            });
        }
    }
}