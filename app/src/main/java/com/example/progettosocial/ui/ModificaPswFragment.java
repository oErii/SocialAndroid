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
import com.example.progettosocial.api.dto.request.UpdatePasswordRequest;
import com.example.progettosocial.api.dto.response.UtenteInfoDTO;
import com.example.progettosocial.databinding.FragmentModificaPswBinding;
import com.example.progettosocial.databinding.FragmentProfileBinding;
import com.example.progettosocial.utils.Preferences;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ModificaPswFragment extends Fragment implements Callback {

    FragmentModificaPswBinding binding;
    String psw;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentModificaPswBinding.inflate(inflater, container, false);
        return binding.getRoot();    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.imgHome.setOnClickListener(v->{
            NavController controller = Navigation.findNavController(binding.getRoot());
            NavDirections destinazione=ModificaPswFragmentDirections.actionModificaPswFragmentToProfileFragment();
            controller.navigate(destinazione);
        });

        binding.SalvaBtn.setOnClickListener(v->{
            if (binding.Psw.getText().toString().equals(binding.RipPsw.getText().toString())) {
                psw=binding.Psw.getText().toString();
                ApiManager.getInstance().updatePassword(new UpdatePasswordRequest(binding.Psw.getText().toString()), this, getContext());
            }else{
                Toast.makeText(getContext(), getString(R.string.ErrorePSW), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        requireActivity().runOnUiThread(()->{
            Toast.makeText(requireContext(), getString(R.string.ErrorConn), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        if (response.isSuccessful()) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                UtenteInfoDTO utente = mapper.readValue(response.body().string(), UtenteInfoDTO.class);
                Preferences.saveLoginRequest(getContext(), new LoginRequest(utente.getUsername(), psw));
                Preferences.saveUtente(getContext(), utente);

                requireActivity().runOnUiThread(() -> {
                    NavController controller = Navigation.findNavController(binding.getRoot());
                    NavDirections destinazione = ModificaPswFragmentDirections.actionModificaPswFragmentToProfileFragment();
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