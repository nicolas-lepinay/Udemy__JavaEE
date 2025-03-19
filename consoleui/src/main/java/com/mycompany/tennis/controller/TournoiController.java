package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.service.EpreuveService;
import com.mycompany.tennis.core.service.TournoiService;

import java.util.Scanner;

public class TournoiController {

    private TournoiService tournoiService;

    public TournoiController() { this.tournoiService = new TournoiService(); }

    public void afficherDetailsTournoi() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'ID du tournoi à afficher ?");
        long identifiant = scanner.nextLong();

        TournoiDto tournoi = tournoiService.getTournoi(identifiant);
        System.out.println("Tournoi sélectionné : " + tournoi.getNom() + " (code : " + tournoi.getCode() + ")");
    }

    public void creerTournoi() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est le nom du tournoi à créer ?");
        String nom = scanner.nextLine();
        System.out.println("Quel est le code du tournoi à créer ?");
        String code = scanner.nextLine();

        TournoiDto tournoi = new TournoiDto();
        tournoi.setNom(nom);
        tournoi.setCode(code);

        tournoiService.createTournoi(tournoi);
    }

    public void supprimerTournoi() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'ID du tournoi à supprimer ?");
        long identifiant = scanner.nextLong();

        tournoiService.deleteTournoi(identifiant);
    }
}
