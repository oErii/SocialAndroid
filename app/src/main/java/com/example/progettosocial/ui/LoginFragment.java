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
import com.example.progettosocial.api.dto.request.LoginRequest;
import com.example.progettosocial.api.dto.response.UtenteInfoDTO;
import com.example.progettosocial.databinding.FragmentLoginBinding;
import com.example.progettosocial.model.Utente;
import com.example.progettosocial.utils.Preferences;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginFragment extends Fragment implements Callback {

    FragmentLoginBinding binding;
    LoginRequest loginRequest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if(Preferences.isLoggato(requireContext())) {
            loginRequest = Preferences.loadLoginRequest(requireContext());
            ApiManager.getInstance().login(loginRequest, this);
        }

        binding.Accedi.setOnClickListener(
                v->{
                    loginRequest = new LoginRequest(binding.Username.getText().toString(),
                            binding.Psw.getText().toString());
                    ApiManager.getInstance().login(loginRequest, this);
                }
        );

        binding.Registrati.setOnClickListener(
                v->{

                    NavController controller = Navigation.findNavController(binding.getRoot());
                    NavDirections destinazione=LoginFragmentDirections.actionLoginFragmentToRegistrazioneFragment();
                    controller.navigate(destinazione);


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
            String jwt = response.header("Authorization");
            Preferences.saveTKN(getContext(), jwt);
            Preferences.setLoggato(requireContext(), true);
            ObjectMapper mapper = new ObjectMapper();
            try {

                UtenteInfoDTO utente = mapper.readValue(response.body().string(), UtenteInfoDTO.class);
                Preferences.saveLoginRequest(getContext(), loginRequest);

            requireActivity().runOnUiThread(()->{
                NavController controller = Navigation.findNavController(binding.getRoot());
                NavDirections destinazione=LoginFragmentDirections.actionLoginFragmentToHomeFragment();
                controller.navigate(destinazione);
            });
            }
            catch(Exception e){
                throw new RuntimeException(e);
            }
        } else if (response.code()==400) {
            String body=response.body().string();
            requireActivity().runOnUiThread(()->{
                Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
            });
        }
    }
}