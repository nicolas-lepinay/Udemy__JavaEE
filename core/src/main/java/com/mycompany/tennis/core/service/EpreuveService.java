package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.EpreuveFullDto;
import com.mycompany.tennis.core.dto.EpreuveLightDto;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.dto.TournoiDto;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.EpreuveRepositoryImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class EpreuveService {
    private final EpreuveRepositoryImpl epreuveRepository;

    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }

    public EpreuveFullDto getEpreuveDetaillee(Long id) {
        Epreuve epreuve = null;
        Transaction tx = null;
        EpreuveFullDto dto = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            // Récupère l'épreuve
            epreuve = epreuveRepository.getById(id);
            // Récupère le tournoi (nécessite Hibernate.initialize() (uniquement SANS Dto ?), car Tournoi est en LazyLoading
            // Inutile avec les Dto ?
            Hibernate.initialize(epreuve.getTournoi()); // Hibernate.unproxy() depuis Hibernate 5.2

            dto = new EpreuveFullDto();
            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());

            // Tournoi
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(epreuve.getTournoi().getId());
            tournoiDto.setNom(epreuve.getTournoi().getNom());
            tournoiDto.setCode(epreuve.getTournoi().getCode());
            dto.setTournoi(tournoiDto);

            // Participants
            dto.setParticipants(new HashSet<>());
            for (Joueur joueur : epreuve.getParticipants()) {
                final JoueurDto joueurDto = new JoueurDto();
                joueurDto.setId(joueur.getId());
                joueurDto.setNom(joueur.getNom());
                joueurDto.setPrenom(joueur.getPrenom());
                joueurDto.setSexe(joueur.getSexe());
                dto.getParticipants().add(joueurDto);
            }

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
            epreuve = epreuveRepository.getById(id);
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

    public List<EpreuveFullDto> getListeEpreuves(String codeTournoi) {
        EntityTransaction tx = null;
        List<EpreuveFullDto> dtos = new ArrayList<EpreuveFullDto>();

        try (EntityManager em = EntityManagerHolder.getCurrentEntityManager()) {
            tx = em.getTransaction();
            tx.begin();

            // Liste d'Epreuves
            List<Epreuve> epreuves = epreuveRepository.list(codeTournoi);

            // Conversion de la liste en liste de Dto
            for (Epreuve epreuve : epreuves) {
                EpreuveFullDto dto = new EpreuveFullDto();
                dto.setId(epreuve.getId());
                dto.setAnnee(epreuve.getAnnee());
                dto.setTypeEpreuve(epreuve.getTypeEpreuve());
                // Tournoi
                TournoiDto tournoiDto = new TournoiDto();
                tournoiDto.setId(epreuve.getTournoi().getId());
                tournoiDto.setNom(epreuve.getTournoi().getNom());
                tournoiDto.setCode(epreuve.getTournoi().getCode());
                dto.setTournoi(tournoiDto);
                dtos.add(dto);
            }
            tx.commit();
        } catch (Throwable t) {
            t.printStackTrace();
            if (tx != null) tx.rollback();
        }
        return dtos;
    }

}
