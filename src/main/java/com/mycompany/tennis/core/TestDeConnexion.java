package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;

import java.util.List;

public class TestDeConnexion {
    public static void main(String... args) {
        JoueurRepositoryImpl joueurRepository = new JoueurRepositoryImpl();
        // GET
        Joueur ferrer = joueurRepository.getById(41L);
        System.out.println(ferrer.getPrenom() + " " + ferrer.getNom().toUpperCase());

        // POST
        /*
        Joueur noah = new Joueur();
        noah.setNom("Noah");
        noah.setPrenom("Yannick");
        noah.setSexe('H');
        joueurRepository.create(noah);
        */

        // GET ALL
        List<Joueur> list = joueurRepository.list();

        for (Joueur joueur: list) {
            System.out.println(joueur.getPrenom() + " " + joueur.getNom().toUpperCase());
        }

    }
}

