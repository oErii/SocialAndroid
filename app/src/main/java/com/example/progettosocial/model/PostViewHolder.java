package com.example.progettosocial.model;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.example.progettosocial.api.dto.request.DeletePostRequest;
import com.example.progettosocial.api.dto.response.CommentiByPostResponse;
import com.example.progettosocial.api.dto.response.LastPostResponse;
import com.example.progettosocial.api.dto.response.PostDTO;
import com.example.progettosocial.dao.PostDAO;
import com.example.progettosocial.ui.HomeFragmentDirections;
import com.example.progettosocial.utils.DBManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PostViewHolder extends RecyclerView.ViewHolder implements Callback {
    TextView textViewUser, textViewDate, textViewPostContent, textViewLikes, textViewCommenti;
    Post post;
    PostAdapter postA;
    public PostViewHolder(@NonNull View itemView, PostAdapter postA) {
        super(itemView);
        this.postA = postA;
        textViewUser = itemView.findViewById(R.id.RowUserName);
        textViewDate = itemView.findViewById(R.id.RowPostDate);
        textViewPostContent = itemView.findViewById(R.id.RowPostContent);
        textViewLikes = itemView.findViewById(R.id.numeroLike);
        textViewCommenti = itemView.findViewById(R.id.numeroCommenti);


        GestureDetector gestureDetector = new GestureDetector(itemView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                ApiManager.getInstance().getCommentiByPost(post.getId(), PostViewHolder.this, itemView.getContext());
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                
                ApiManager.getInstance().toggleLike(new CreateLikeRequest(post.getId()), PostViewHolder.this, itemView.getContext());
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
                                if(post.isMine()) {
                                    ApiManager.getInstance().deletePost(new DeletePostRequest(post.getId()), this, itemView.getContext());
                                }
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
        String url = call.request().url().toString();

        if(url.contains("toggleLike")){
            if(response.isSuccessful()){
                String body=response.body().string();
                itemView.post(() -> {
                    Toast.makeText(itemView.getContext(), body, Toast.LENGTH_SHORT).show();
                    // Aggiorna localmente
                    if (body.contains("rimosso")) {
                        textViewLikes.setText(Long.toString(Long.parseLong(textViewLikes.getText().toString())-1));
                    } else {
                        textViewLikes.setText(Long.toString(Long.parseLong(textViewLikes.getText().toString())+1));
                    }
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


        } else if (url.contains("getCommentiByPost")) {
            if(response.isSuccessful()){
                ObjectMapper mapper = new ObjectMapper();
                try {
                    CommentiByPostResponse lista = mapper.readValue(response.body().string(), CommentiByPostResponse.class);
                    itemView.post(() -> {
                        NavController controller = Navigation.findNavController(itemView);
                        NavDirections destinazione = HomeFragmentDirections.actionHomeFragmentToCommentiFragment(lista);
                        controller.navigate(destinazione);
                    });
                }catch(Exception e){
                    throw new RuntimeException(e);
                }
            } else if (response.code()==400) {
                String body=response.body().string();
                itemView.post(() -> {
                    Toast.makeText(itemView.getContext(), body, Toast.LENGTH_SHORT).show();
                });
            }else if (response.code()==404) {
                String body=response.body().string();
                itemView.post(() -> {
                    Toast.makeText(itemView.getContext(), body, Toast.LENGTH_SHORT).show();
                });
            }
        } else if (url.contains("deletePost")) {
            if(response.isSuccessful()){
                String body=response.body().string();
                itemView.post(() -> {
                    Toast.makeText(itemView.getContext(), body, Toast.LENGTH_SHORT).show();
                    ApiManager.getInstance().getLastPost(this, itemView.getContext());
                });
            } else if (response.code()==400) {
            String body=response.body().string();
            itemView.post(() -> {
                Toast.makeText(itemView.getContext(), body, Toast.LENGTH_SHORT).show();
            });
            }else if (response.code()==403) {
                String body=response.body().string();
                itemView.post(() -> {
                    Toast.makeText(itemView.getContext(), body, Toast.LENGTH_SHORT).show();
                });
            }else if (response.code()==404) {
                String body = response.body().string();
                itemView.post(() -> {
                    Toast.makeText(itemView.getContext(), body, Toast.LENGTH_SHORT).show();
                });
            }
        }else if (url.contains("getLastPost")) {

            if (!response.isSuccessful()) {
                itemView.post(() -> {
                    Toast.makeText(itemView.getContext(), "Impossibile caricare i post", Toast.LENGTH_SHORT).show();
                });
            } else {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    LastPostResponse lastPost = mapper.readValue(response.body().string(), LastPostResponse.class);
                    itemView.post(() -> {
                        DBManager db = DBManager.getInstance(itemView.getContext());
                        PostDAO postDao = db.getPostDao();
                        postDao.deleteAll();
                        for (PostDTO post : lastPost.getPostList()) {
                            postDao.insertPost(new Post(post.getId(), post.getAutore().getUsername(), post.getTesto(), post.getDataPubblicazione(), post.getNumeroLikes(), post.getNumeroCommenti(), post.isLiked(), post.isMine()));
                        }

                        postA.aggiornaLista(postDao.getAll());
                        postA.notifyDataSetChanged();

                    });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}