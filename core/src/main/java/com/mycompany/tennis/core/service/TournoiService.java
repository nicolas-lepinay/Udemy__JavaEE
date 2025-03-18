package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.repository.TournoiRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TournoiService {

    private final TournoiRepositoryImpl tournoiRepository;

    public TournoiService() {
        this.tournoiRepository = new TournoiRepositoryImpl();
    }
    public void createTournoi(TournoiDto tournoiDto) {
        EntityTransaction tx = null;
        try (EntityManager em = EntityManagerHolder.getCurrentEntityManager()) {
            tx = em.getTransaction();
            tx.begin();

            // Transformation du Dto en Entity
            Tournoi tournoi = new Tournoi();
            tournoi.setId(tournoiDto.getId());
            tournoi.setNom(tournoiDto.getNom());
            tournoi.setCode(tournoiDto.getCode());

            tournoiRepository.create(tournoi, null);
            tx.commit();
            System.out.println("Tournoi créé avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
        }
    }

    public TournoiDto getTournoi(Long id) {
        Tournoi tournoi = null;
        EntityTransaction tx = null;
        TournoiDto dto = null;

        try (EntityManager em = EntityManagerHolder.getCurrentEntityManager()) {
            tx = em.getTransaction();
            tx.begin();

            tournoi = tournoiRepository.getById(id);
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
        EntityTransaction tx = null;
        try (EntityManager em = EntityManagerHolder.getCurrentEntityManager()) {
            tx = em.getTransaction();
            tx.begin();

            Tournoi tournoi = tournoiRepository.getById(id);
            if (tournoi != null) {
                tournoiRepository.delete(id);
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
