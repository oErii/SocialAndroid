package com.example.progettosocial.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.progettosocial.R;
import com.example.progettosocial.api.ApiManager;
import com.example.progettosocial.api.dto.request.LoginRequest;
import com.example.progettosocial.api.dto.request.UpdateUsernameRequest;
import com.example.progettosocial.api.dto.response.UtenteInfoDTO;
import com.example.progettosocial.databinding.FragmentModificaPswBinding;
import com.example.progettosocial.databinding.FragmentModificaUsernameBinding;
import com.example.progettosocial.databinding.FragmentModificaUtenteBinding;
import com.example.progettosocial.utils.Preferences;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ModificaUsernameFragment extends Fragment implements Callback {

    FragmentModificaUsernameBinding binding;
    String psw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentModificaUsernameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        psw=Preferences.loadLoginRequest(getContext()).getPassword();
        binding.imgHome.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(binding.getRoot());
            NavDirections destinazione = ModificaUsernameFragmentDirections.actionModificaUsernameFragmentToProfileFragment();
            controller.navigate(destinazione);
        });

        binding.ModificaBtn.setOnClickListener(v->{
            ApiManager.getInstance().updateUsername(new UpdateUsernameRequest(binding.Username.getText().toString()), this, getContext());
        });
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        requireActivity().runOnUiThread(()->{
            Toast.makeText(requireContext(),  getString(R.string.ErrorConn), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        if (response.isSuccessful()){
            ObjectMapper mapper = new ObjectMapper();
            try {
                UtenteInfoDTO utente = mapper.readValue(response.body().string(), UtenteInfoDTO.class);
                Preferences.saveLoginRequest(getContext(), new LoginRequest(utente.getUsername(), psw));
                Preferences.saveUtente(getContext(), utente);

                requireActivity().runOnUiThread(() -> {
                    NavController controller = Navigation.findNavController(binding.getRoot());
                    NavDirections destinazione = ModificaUsernameFragmentDirections.actionModificaUsernameFragmentToProfileFragment();
                    controller.navigate(destinazione);
                });
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }else {
            String body=response.body().string();
            requireActivity().runOnUiThread(()->{
                Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
            });
        }

    }
}