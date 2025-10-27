package com.example.progettosocial.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.progettosocial.api.ApiManager;
import com.example.progettosocial.api.dto.request.CreateCommentoRequest;
import com.example.progettosocial.api.dto.response.CommentiByPostResponse;
import com.example.progettosocial.api.dto.response.CommentoDTO;
import com.example.progettosocial.databinding.FragmentCommentiBinding;
import com.example.progettosocial.model.CommentoAdapter;
import com.example.progettosocial.model.ListaPostCallback;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CommentiFragment extends Fragment implements Callback, ListaPostCallback {

    FragmentCommentiBinding binding;

    CommentiFragmentArgs commentiFA;
    Long postID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            commentiFA = CommentiFragmentArgs.fromBundle(getArguments());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCommentiBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
            int ime = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom;
            v.setPadding(
                    v.getPaddingLeft(),
                    v.getPaddingTop(),
                    v.getPaddingRight(),
                    Math.max(ime, 0)
            );
            return insets;
        });

        binding.recyclerViewPosts.setLayoutManager(new LinearLayoutManager(getContext()));


        List<CommentoDTO> listaCommenti =commentiFA.getListaCommenti().getListaCommenti();
        postID = commentiFA.getPost().getId();
        CommentoAdapter adapter = new CommentoAdapter(listaCommenti, this);
        binding.recyclerViewPosts.setAdapter(adapter);

        binding.CommentoBox.setEndIconOnClickListener(v -> {
            String contenutoCommento = binding.CommentoContent.getText().toString();
            if(contenutoCommento.isEmpty()) {
                Toast.makeText(getContext(), "Commento Vuoto", Toast.LENGTH_SHORT).show();
            }else {

                CreateCommentoRequest nuovoCommentoRequest = new CreateCommentoRequest(postID, contenutoCommento);
                ApiManager.getInstance().creaCommento(nuovoCommentoRequest, this, requireContext());
                binding.CommentoContent.setText(null);
            }
        });
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        requireActivity().runOnUiThread(()->{
            Toast.makeText(requireContext(), "Qualcosa è andato storto controlla la connessione internet e riprova", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        String url = call.request().url().toString();
        if(url.contains("createCommento")){
            if (response.isSuccessful()) {
                String body=response.body().string();
                requireActivity().runOnUiThread(() -> {
                    ApiManager.getInstance().getCommentiByPost(postID, this, requireContext());
                    Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
                });
            } else {
                String body=response.body().string();
                requireActivity().runOnUiThread(()->{
                    Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
                });
            }
        }else if(url.contains("getCommentiByPost")){
            if (response.isSuccessful()) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    CommentiByPostResponse lista = mapper.readValue(response.body().string(), CommentiByPostResponse.class);
                    requireActivity().runOnUiThread(() -> {
                        binding.recyclerViewPosts.setLayoutManager(new LinearLayoutManager(getContext()));
                        CommentoAdapter adapter = new CommentoAdapter(lista.getListaCommenti(), this);
                        binding.recyclerViewPosts.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else if(response.code()==400){
                String body=response.body().string();
                requireActivity().runOnUiThread(()->{
                    Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
                });
            }else if(response.code()==404){
                String body=response.body().string();
                requireActivity().runOnUiThread(()->{
                    Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
                });
            }
        }
    }

    @Override
    public void updateList() {
        ApiManager.getInstance().getCommentiByPost(postID, this, getContext());
    }
}
