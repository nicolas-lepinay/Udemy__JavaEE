package com.mycompany.tennis;

import com.mycompany.tennis.controller.EpreuveController;
import com.mycompany.tennis.controller.JoueurController;
import com.mycompany.tennis.controller.MatchController;
import com.mycompany.tennis.controller.TournoiController;

public class UI {
    public static void main(String... args) {
        JoueurController controller = new JoueurController();
        EpreuveController epreuveController = new EpreuveController();
        MatchController matchController = new MatchController();
        TournoiController tournoiController = new TournoiController();

        //controller.creerJoueur();
        //controller.afficherDetailsJoueur();
        //controller.renommerJoueur();
        //controller.supprimerJoueur();

        //matchController.tapisVert();
        //matchController.afficheDetailsMatch();

        //matchController.ajouterMatch();

        //epreuveController.afficheDetailsEpreuve();
        //epreuveController.afficherRolandGarros();
        //epreuveController.afficherListeEpreuves();
        controller.afficherListeJoueurs();

        //tournoiController.supprimerTournoi();
    }
}
