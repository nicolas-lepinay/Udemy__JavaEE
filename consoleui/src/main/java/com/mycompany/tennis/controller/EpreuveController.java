package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.service.EpreuveService;

import java.util.Scanner;

public class EpreuveController {

    private EpreuveService epreuveService;

    public EpreuveController() { this.epreuveService = new EpreuveService(); }

    public void afficheDetailsEpreuve() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'ID de l'épreuve à afficher ?");
        long identifiant = scanner.nextLong();

        EpreuveFullDto epreuve = epreuveService.getEpreuveDetaillee(identifiant);
        System.out.println("Epreuve sélectionnée : " + epreuve.getAnnee());
        System.out.println("Tournoi : " + epreuve.getTournoi().getNom());
        System.out.println("Liste des participants :");
        for (JoueurDto joueurDto : epreuve.getParticipants()) {
            System.out.println("- " + joueurDto.getPrenom() + " " + joueurDto.getNom().toUpperCase());
        }
    }

    public void afficherRolandGarros() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'ID de l'épreuve à afficher ?");
        long identifiant = scanner.nextLong();

        EpreuveLightDto epreuve = epreuveService.getEpreuveSansTournoi(identifiant);
        System.out.println("Epreuve sélectionnée : " + epreuve.getAnnee());
    }

    public void afficherListeEpreuves() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est le code du tournoi des épreuves à afficher ?");
        String code = scanner.nextLine();

        for(EpreuveFullDto dto : epreuveService.getListeEpreuves(code)) {
            System.out.println(dto.getId() + " / " + dto.getAnnee() + " / " + dto.getTypeEpreuve() + " / " + dto.getTournoi().getNom());
        };
    }

}
