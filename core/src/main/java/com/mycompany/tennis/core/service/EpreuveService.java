package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.dto.TournoiDto;
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

    public EpreuveFullDto getEpreuveAvecTournoi(Long id) {
        Epreuve epreuve = null;
        Transaction tx = null;
        EpreuveFullDto dto = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            // Récupère l'épreuve
            epreuve = epreuveRepository.getById(id, session);
            // Récupère le tournoi (nécessite Hibernate.initialize() (uniquement SANS Dto ?), car Tournoi est en LazyLoading
            // Inutile avec les Dto ?
            Hibernate.initialize(epreuve.getTournoi()); // Hibernate.unproxy() depuis Hibernate 5.2

            dto = new EpreuveFullDto();
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());

            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(epreuve.getTournoi().getId());
            tournoiDto.setNom(epreuve.getTournoi().getNom());
            tournoiDto.setCode(epreuve.getTournoi().getCode());
            dto.setTournoi(tournoiDto);

            tx.commit();
            System.out.println("Epreuve lue avec succès.");
        } catch (Throwable t) {
            t.printStackTrace();
            if (tx != null) tx.rollback();
        }
        return dto;
    }

    public EpreuveLightDto getEpreuveSansTournoi(Long id) {
        Epreuve epreuve = null;
        Transaction tx = null;
        EpreuveLightDto dto = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id, session);
            dto = new EpreuveLightDto();
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());
            // Contrairement à la méthode getEpreuveAvecTournoi(), on ne set pas le tournoi ici

            tx.commit();
            System.out.println("Epreuve lue avec succès.");
        } catch (Throwable t) {
            t.printStackTrace();
            if (tx != null) tx.rollback();
        }
        return dto;
    }

}
