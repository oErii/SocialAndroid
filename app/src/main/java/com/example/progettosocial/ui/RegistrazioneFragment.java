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

import com.example.progettosocial.api.ApiManager;
import com.example.progettosocial.api.dto.request.RegistrazioneRequest;
import com.example.progettosocial.databinding.FragmentRegistrazioneBinding;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegistrazioneFragment extends Fragment implements Callback {

    FragmentRegistrazioneBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistrazioneBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.Accedi.setOnClickListener(
                v->{
                    NavController controller = Navigation.findNavController(binding.getRoot());
                    NavDirections destinazione= RegistrazioneFragmentDirections.actionRegistrazioneFragmentToLoginFragment();
                    controller.navigate(destinazione);
                }
        );

        binding.Registrati.setOnClickListener(
                v->{
                    if(!binding.Psw.getText().toString().equals(binding.RipPsw.getText().toString())){
                        Toast.makeText(getContext(), "Le Password non corrispondono", Toast.LENGTH_SHORT).show();
                    }else if(binding.Psw.getText().toString().isEmpty()||binding.Username.getText().toString().isEmpty()||binding.Email.getText().toString().isEmpty()||binding.Nome.getText().toString().isEmpty()||binding.Cognome.getText().toString().isEmpty()){
                        Toast.makeText(getContext(), "Dati Mancanti", Toast.LENGTH_SHORT).show();
                    }else{
                        RegistrazioneRequest registrazioneRequest = new RegistrazioneRequest(binding.Username.getText().toString(), binding.Email.getText().toString(), binding.Psw.getText().toString(), binding.Nome.getText().toString(), binding.Cognome.getText().toString());
                        ApiManager.getInstance().registra(registrazioneRequest, this);
                    }

                }
        );


    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        requireActivity().runOnUiThread(()->{
            Toast.makeText(requireContext(),  "Qualcosa è andato storto controlla la connessione internet e riprova", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        if (response.isSuccessful()){
            String body=response.body().string();
            requireActivity().runOnUiThread(()->{
                Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
                NavController controller = Navigation.findNavController(binding.getRoot());
                NavDirections destinazione=RegistrazioneFragmentDirections.actionRegistrazioneFragmentToLoginFragment();
                controller.navigate(destinazione);
            });
        } else if (response.code()==400) {
            String body=response.body().string();
            requireActivity().runOnUiThread(()->{
                Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
            });
        }else if (response.code()==409) {
            String body=response.body().string();
            requireActivity().runOnUiThread(()->{
                Toast.makeText(getContext(),body,Toast.LENGTH_LONG).show();
            });
        }
    }
}

//email: enrico.tarulli@gmail.com
//username: EnricoT
//psw: pswEri