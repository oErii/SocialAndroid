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
import com.example.progettosocial.api.dto.request.DeleteUtenteRequest;
import com.example.progettosocial.databinding.FragmentEliminaUtenteBinding;
import com.example.progettosocial.databinding.FragmentProfileBinding;
import com.example.progettosocial.utils.Preferences;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class EliminaUtenteFragment extends Fragment implements Callback {

    FragmentEliminaUtenteBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEliminaUtenteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.ModificaBtn.setOnClickListener(v -> {
            ApiManager.getInstance().deleteUtente(new DeleteUtenteRequest(binding.Psw.getText().toString()), this, getContext());
        });

        binding.imgHome.setOnClickListener(v->{
            NavController controller = Navigation.findNavController(binding.getRoot());
            NavDirections destinazione = EliminaUtenteFragmentDirections.actionEliminaUtenteFragment2ToProfileFragment();
            controller.navigate(destinazione);
        });
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        if (response.isSuccessful()) {
            Preferences.setLoggato(getContext(), false);
            String body=response.body().string();
            requireActivity().runOnUiThread(() -> {
                Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
                NavController controller = Navigation.findNavController(binding.getRoot());
                NavDirections destinazione = EliminaUtenteFragmentDirections.actionEliminaUtenteFragment2ToLoginFragment();
                controller.navigate(destinazione);
            });
        }else{
            String body=response.body().string();
            requireActivity().runOnUiThread(() -> {
                Toast.makeText(getContext(),body, Toast.LENGTH_LONG).show();
            });
        }
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        requireActivity().runOnUiThread(()->{
            Toast.makeText(requireContext(),  getContext().getString(R.string.ErrorConn), Toast.LENGTH_SHORT).show();
        });
    }
}