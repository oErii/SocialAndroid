
package com.example.progettosocial;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentContainerView;

import com.example.progettosocial.dao.PostDAO;
import com.example.progettosocial.databinding.ActivityLoginBinding;
import com.example.progettosocial.utils.DBManager;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            int ime = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom;
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom-ime);
            return insets;
        });
    }

    public FragmentContainerView getContainerView(){
        return binding.NavHost;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu_post, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        DBManager db = DBManager.getInstance(this);
        PostDAO postDao = db.getPostDao();
        if (item.getItemId()==R.id.Modifica){

        }else if(item.getItemId()==R.id.Elimina){

        }
        return super.onContextItemSelected(item);
    }
}

