package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.service.EpreuveService;

import java.util.Scanner;

public class EpreuveController {

    private EpreuveService epreuveService;

    public EpreuveController() { this.epreuveService = new EpreuveService(); }

    public void afficherDerniereEpreuve() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'ID de l'épreuve à afficher ?");
        long identifiant = scanner.nextLong();

        EpreuveFullDto epreuve = epreuveService.getEpreuveAvecTournoi(identifiant);
        System.out.println("Epreuve sélectionnée : " + epreuve.getAnnee() + " | Tournoi : " + epreuve.getTournoi().getNom());
    }

    public void afficherRolandGarros() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'ID de l'épreuve à afficher ?");
        long identifiant = scanner.nextLong();

        EpreuveLightDto epreuve = epreuveService.getEpreuveSansTournoi(identifiant);
        System.out.println("Epreuve sélectionnée : " + epreuve.getAnnee());
    }

}
