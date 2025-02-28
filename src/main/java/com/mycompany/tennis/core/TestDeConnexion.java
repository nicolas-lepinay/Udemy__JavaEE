package com.mycompany.tennis.core;

import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.core.service.JoueurService;
import com.mycompany.tennis.core.service.MatchService;

import java.util.List;

public class TestDeConnexion {
    public static void main(String... args) {
        MatchService matchService = new MatchService();

        // Joueurs
        Joueur federer = new Joueur();
        federer.setId(32L);

        Joueur murray = new Joueur();
        murray.setId(34L);

        // Epreuve
        Epreuve epreuve = new Epreuve();
        epreuve.setId(10L);

        // Score
        Score score = new Score();
        score.setSet1((byte)3);
        score.setSet2((byte)4);
        score.setSet3((byte)6);

        // Match
        Match match = new Match();
        match.setVainqueur(federer);
        match.setFinaliste(murray);
        match.setEpreuve(epreuve);
        match.setScore(score);

        score.setMatch(match);

        matchService.enregistrerNouveauMatch(match);
        System.out.println("L'ID du match créé est : " + match.getId());
    }
}

