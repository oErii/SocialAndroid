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
import com.example.progettosocial.api.dto.response.UtenteInfoDTO;
import com.example.progettosocial.databinding.FragmentLoginBinding;
import com.example.progettosocial.databinding.FragmentProfileBinding;
import com.example.progettosocial.utils.Preferences;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ProfileFragment extends Fragment implements Callback {
    FragmentProfileBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UtenteInfoDTO utente = Preferences.loadUtente(getContext());

        binding.Nome.setText(utente.getNome());
        binding.Cognome.setText(utente.getCognome());
        binding.Username.setText(utente.getUsername());

        binding.imgHome.setOnClickListener(v->{
            NavController controller = Navigation.findNavController(binding.getRoot());
            NavDirections destinazione=ProfileFragmentDirections.actionProfileFragmentToHomeFragment();
            controller.navigate(destinazione);
        });

        binding.Logout.setOnClickListener(v->{
            ApiManager.getInstance().logout(this, getContext());
        });

        binding.ModificaUtente.setOnClickListener(v->{
            NavController controller = Navigation.findNavController(binding.getRoot());
            NavDirections destinazione = ProfileFragmentDirections.actionProfileFragmentToModificaFragment();
            controller.navigate(destinazione);
        });

        binding.ModificaUsername.setOnClickListener(v->{
            NavController controller = Navigation.findNavController(binding.getRoot());
            NavDirections destinazione = ProfileFragmentDirections.actionProfileFragmentToModificaUsernameFragment();
            controller.navigate(destinazione);
        });

        binding.ModificaPsw.setOnClickListener(v->{
            NavController controller = Navigation.findNavController(binding.getRoot());
            NavDirections destinazione = ProfileFragmentDirections.actionProfileFragmentToModificaPswFragment();
            controller.navigate(destinazione);
        });
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        requireActivity().runOnUiThread(()->{
            Toast.makeText(requireContext(),  "Qualcosa è andato storto controlla la connessione internet e riprova", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        if (response.isSuccessful()) {
            Preferences.saveTKN(requireContext(), "");
            Preferences.setLoggato(requireContext(),false);
            String body=response.body().string();
            requireActivity().runOnUiThread(() -> {
                Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
                NavController controller = Navigation.findNavController(binding.getRoot());
                NavDirections destinazione= ProfileFragmentDirections.actionProfileFragmentToLoginFragment();
                controller.navigate(destinazione);
            });
        }else {
            String body=response.body().string();
            requireActivity().runOnUiThread(()->{
                Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
            });
        }
    }
}