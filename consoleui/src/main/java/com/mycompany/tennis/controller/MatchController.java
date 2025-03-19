package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.MatchDto;
import com.mycompany.tennis.core.dto.ScoreFullDto;
import com.mycompany.tennis.core.service.MatchService;
import java.util.Scanner;

public class MatchController {
    private MatchService matchService;

    public MatchController() { this.matchService = new MatchService(); }

    public void afficheDetailsMatch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'ID du match à afficher ?");
        long identifiant = scanner.nextLong();

        MatchDto match = matchService.getMatch(identifiant);
        System.out.println("Année du match : " + match.getEpreuve().getAnnee());
        System.out.println("Tournoi du match : " + match.getEpreuve().getTournoi().getNom());
        System.out.println("Vainqueur du match : " + match.getVainqueur().getPrenom() + " " + match.getVainqueur().getNom());
        System.out.println("Finaliste du match : " + match.getFinaliste().getPrenom() + " " + match.getFinaliste().getNom());
        System.out.println("Score du match :");
        System.out.println("    - Set 1 : " + match.getScore().getSet1());
        System.out.println("    - Set 2 : " + match.getScore().getSet2());
        if(match.getScore().getSet3() != null) {
            System.out.println("    - Set 3 : " + match.getScore().getSet3());
        }
        if(match.getScore().getSet4() != null) {
            System.out.println("    - Set 4 : " + match.getScore().getSet4());
        }
        if(match.getScore().getSet5() != null) {
            System.out.println("    - Set 5 : " + match.getScore().getSet5());
        }
    }

    public void tapisVert() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'ID du match à annuler ?");
        long identifiant = scanner.nextLong();

        matchService.tapisVert(identifiant);
    }

    public void supprimerMatch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'ID du match à supprimer ?");
        long matchId = scanner.nextLong();

        matchService.deleteMatch(matchId);
    }

    public void ajouterMatch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'ID de l'épreuve du match ?");
        long epreuveId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Quel est l'ID du vainqueur du match ?");
        long vainqueurId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Quel est l'ID du finaliste du match ?");
        long finalisteId = scanner.nextLong();
        scanner.nextLine();

        MatchDto matchDto = new MatchDto();
        matchDto.setEpreuve(new EpreuveFullDto());
        matchDto.getEpreuve().setId(epreuveId);

        matchDto.setFinaliste(new JoueurDto());
        matchDto.getFinaliste().setId(finalisteId);

        matchDto.setVainqueur(new JoueurDto());
        matchDto.getVainqueur().setId(vainqueurId);

        System.out.println("Quel est la valeur du 1er set ?");
        byte set1 = scanner.nextByte();
        scanner.nextLine();

        System.out.println("Quel est la valeur du 2ème set ?");
        byte set2 = scanner.nextByte();
        scanner.nextLine();

        System.out.println("Quel est la valeur du 3ème set ?");
        byte set3 = scanner.nextByte();
        scanner.nextLine();

        System.out.println("Quel est la valeur du 4ème set ?");
        byte set4 = scanner.nextByte();
        scanner.nextLine();

        System.out.println("Quel est la valeur du 5ème set ?");
        byte set5 = scanner.nextByte();
        scanner.nextLine();

        ScoreFullDto scoreDto = new ScoreFullDto();
        scoreDto.setSet1(set1);
        scoreDto.setSet2(set2);
        scoreDto.setSet3(set3);
        scoreDto.setSet4(set4);
        scoreDto.setSet5(set5);
        matchDto.setScore(scoreDto);
        scoreDto.setMatch(matchDto);

        matchService.createMatch(matchDto);
    }
}
