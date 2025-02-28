package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;

public class JoueurService {

    private final JoueurRepositoryImpl joueurRepository;

    public JoueurService() {
        this.joueurRepository = new JoueurRepositoryImpl();
    }
    public void createJoueur(Joueur joueur) {
        joueurRepository.create(joueur);
    }
}
