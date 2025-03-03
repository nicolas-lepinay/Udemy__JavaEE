package com.mycompany.tennis;

import com.mycompany.tennis.controller.EpreuveController;
import com.mycompany.tennis.controller.JoueurController;

public class UI {
    public static void main(String... args) {
        JoueurController controller = new JoueurController();
        EpreuveController epreuveController = new EpreuveController();

        //controller.creerJoueur();
        //controller.afficherDetailsJoueur();
        //controller.renommerJoueur();
        //controller.supprimerJoueur();
        epreuveController.afficherDerniereEpreuve();
        epreuveController.afficherRolandGarros();
    }
}
