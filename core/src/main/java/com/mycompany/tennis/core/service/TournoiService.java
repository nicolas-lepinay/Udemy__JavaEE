package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TournoiService {

    private final TournoiRepositoryImpl tournoiRepository;

    public TournoiService() {
        this.tournoiRepository = new TournoiRepositoryImpl();
    }
    public void createTournoi(TournoiDto tournoiDto) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Transformation du Dto en Entity
            Tournoi tournoi = new Tournoi();
            tournoi.setId(tournoiDto.getId());
            tournoi.setNom(tournoiDto.getNom());
            tournoi.setCode(tournoiDto.getCode());

            tournoiRepository.create(tournoi, session);
            tx.commit();
            System.out.println("Tournoi créé avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
        }
    }

    public TournoiDto getTournoi(Long id) {
        Tournoi tournoi = null;
        Transaction tx = null;
        TournoiDto dto = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            tournoi = tournoiRepository.getById(id, session);
            dto = new TournoiDto();
            dto.setId(tournoi.getId());
            dto.setNom(tournoi.getNom());
            dto.setCode(tournoi.getCode());

            tx.commit();
            System.out.println("Tournoi lu avec succès.");
        } catch (Throwable t) {
            t.printStackTrace();
            if (tx != null) tx.rollback();
        }
        return dto;
    }

    public void deleteTournoi(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Tournoi tournoi = tournoiRepository.getById(id, session);
            if (tournoi != null) {
                tournoiRepository.delete(id, session);
                tx.commit();
                System.out.println("Tournoi supprimé avec succès.");
            } else {
                System.out.println("Aucun tournoi trouvé avec l'id : " + id);
            }
        } catch (Throwable t) {
            t.printStackTrace();
            if (tx != null) tx.rollback();
        }
    }
}
