package com.example.progettosocial;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.progettosocial.databinding.ActivityLoginBinding;
import com.example.progettosocial.databinding.FragmentLoginBinding;
import com.example.progettosocial.model.Utente;
import com.example.progettosocial.utils.Preferences;

public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    Utente utente = new Utente("oErii", "enrico.tarulli@gmail.com", "psw", "Enrico", "Tarulli");

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


        String email = Preferences.caricaEmail(getContext());
        String psw = Preferences.caricaPsw(getContext());

        if (email.equals(utente.getEmail())&&psw.equals(utente.getPassword())){

            NavController controller = Navigation.findNavController(binding.getRoot());
            NavDirections destinazione=LoginFragmentDirections.actionLoginFragmentToHomeFragment(utente);
            controller.navigate(destinazione);

        }

        binding.Accedi.setOnClickListener(
                v->{
                    String emailIns = binding.Email.getText().toString().toLowerCase().trim();
                    String pswIns = binding.Psw.getText().toString().trim();
                    if (utente.getEmail().equals(emailIns)&& utente.getPassword().equals(pswIns)){

                        Preferences.salvaEmail(getContext(), emailIns);
                        Preferences.salvaPsw(getContext(), pswIns);

                        NavController controller = Navigation.findNavController(binding.getRoot());
                        NavDirections destinazione=LoginFragmentDirections.actionLoginFragmentToHomeFragment(utente);
                        controller.navigate(destinazione);
                    } else {
                        Toast.makeText(getContext(), "Username o password errati", Toast.LENGTH_SHORT).show();
                    }


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
}