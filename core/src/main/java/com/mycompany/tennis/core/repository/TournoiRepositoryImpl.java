package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;

public class TournoiRepositoryImpl {
    public void create(Tournoi tournoi, Session session) {
        session.persist(tournoi); //  Ajout de l'objet dans la session Hibernate (état transient → état persistant)
    }

    public void delete(Long id, Session session) {
        Tournoi tournoi = session.get(Tournoi.class, id);
        if (tournoi !=  null) session.remove(tournoi);
    }

    public Tournoi getById(Long id, Session session) {
        Tournoi tournoi = session.get(Tournoi.class, id);
        return tournoi;
    }
}
