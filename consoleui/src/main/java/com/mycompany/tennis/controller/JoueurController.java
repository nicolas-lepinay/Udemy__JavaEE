package com.mycompany.tennis.controller;

import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.service.JoueurService;

import java.util.Scanner;

public class JoueurController {

    private JoueurService joueurService;

    public JoueurController() { this.joueurService = new JoueurService(); }
    public void afficherDetailsJoueur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'ID du joueur à afficher ?");
        long identifiant = scanner.nextLong();

        Joueur joueur = joueurService.getJoueur(identifiant);
        System.out.println("Joueur sélectionné : " + joueur.getPrenom() + " " + joueur.getNom().toUpperCase());
    }

    public void creerJoueur() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quel est le nom du joueur à créer ?");
        String nom = scanner.nextLine();

        System.out.println("Quel est le prénom du joueur à créer ?");
        String prenom = scanner.nextLine();

        System.out.println("Quel est le sexe du joueur à créer ?");
        char sexe = scanner.nextLine().charAt(0);

        Joueur joueur = new Joueur();
        joueur.setNom(nom);
        joueur.setPrenom(prenom);
        joueur.setSexe(sexe);

        joueurService.createJoueur(joueur);
        System.out.println("Joueur #" + joueur.getId() + " créé avec succès : " + joueur.getPrenom() + " " + joueur.getNom().toUpperCase() + " (" + joueur.getSexe() + ")");
    }

    public void renommerJoueur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'ID du joueur à renommer ?");
        long identifiant = scanner.nextLong();
        scanner.nextLine(); // Obligatoire après un nextLong()

        System.out.println("Quel est le nouveau nom du joueur ?");
        String nom = scanner.nextLine();

        joueurService.renameJoueur(identifiant, nom);
    }

    public void supprimerJoueur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est l'ID du joueur à SUPPRIMER ?");
        long identifiant = scanner.nextLong();

        joueurService.deleteJoueur(identifiant);
    }

    public void afficherListeJoueurs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est le sexe des joueurs à afficher (H/F) ?");
        char sex = scanner.nextLine().charAt(0);

        for(JoueurDto dto : joueurService.getListeJoueurs(sex)) {
            System.out.println(dto.getPrenom() + " " + dto.getNom().toUpperCase() + " (" + dto.getSexe() + ")");
        };
    }
}
