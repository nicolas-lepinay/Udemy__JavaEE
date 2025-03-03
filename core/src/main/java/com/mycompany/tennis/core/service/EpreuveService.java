package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EpreuveService {
    private final EpreuveRepositoryImpl epreuveRepository;

    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }

    public Epreuve getEpreuveAvecTournoi(Long id) {
        Epreuve epreuve = null;
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            // Récupère l'épreuve
            epreuve = epreuveRepository.getById(id, session);
            // Récupère le tournoi
            Hibernate.initialize(epreuve.getTournoi()); // Hibernate.unproxy() depuis Hibernate 5.2
            tx.commit();

            System.out.println("Epreuve lue avec succès.");
        } catch (Throwable t) {
            t.printStackTrace();
            if (tx != null) tx.rollback();
        }
        return epreuve;
    }

    public Epreuve getEpreuveSansTournoi(Long id) {
        Epreuve epreuve = null;
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id, session);
            tx.commit();

            System.out.println("Epreuve lue avec succès.");
        } catch (Throwable t) {
            t.printStackTrace();
            if (tx != null) tx.rollback();
        }
        return epreuve;
    }

}
