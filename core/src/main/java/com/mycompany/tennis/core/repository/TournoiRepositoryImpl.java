package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Tournoi;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;

public class TournoiRepositoryImpl {
    public void create(Tournoi tournoi, Session session) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        em.persist(tournoi); //  Ajout de l'objet dans la session Hibernate (état transient → état persistant)
    }

    public void delete(Long id) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Tournoi tournoi = em.find(Tournoi.class, id);
        if (tournoi !=  null) em.remove(tournoi);
    }

    public Tournoi getById(Long id) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Tournoi tournoi = em.find(Tournoi.class, id);
        return tournoi;
    }
}
