package com.example.progettosocial.model;

import java.util.ArrayList;
import java.util.List;

public class DatabaseUtenti {
    private static DatabaseUtenti instance;
    private List<Utente> utenti;

    private DatabaseUtenti() {
        utenti = new ArrayList<>();
    }

    public static DatabaseUtenti getInstance() {
        if (instance == null) {
            instance = new DatabaseUtenti();
        }
        return instance;
    }

    public List<Utente> getUtenti() {
        return utenti;
    }
}
