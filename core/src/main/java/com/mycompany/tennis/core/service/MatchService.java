package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.*;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.repository.MatchRepositoryImpl;
import com.mycompany.tennis.core.repository.ScoreRepositoryImpl;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    private ScoreRepositoryImpl scoreRepository;
    private MatchRepositoryImpl matchRepository;
    public MatchService() {
        this.scoreRepository = new ScoreRepositoryImpl();
        this.matchRepository = new MatchRepositoryImpl();
    }

    public void enregistrerNouveauMatch(Match match) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            matchRepository.create(match, session);
            scoreRepository.create(match.getScore(), session);
        } catch (Throwable t) {
            t.printStackTrace();
            if (tx != null) tx.rollback();
        }
    }

    public MatchDto getMatch(Long id) {
        Match match = null;
        MatchDto dto = null;
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            match = matchRepository.getById(id, session);

            // Charger explicitement l'épreuve et le tournoi
            Hibernate.initialize(match.getEpreuve());
            Hibernate.initialize(match.getEpreuve().getTournoi());

            dto = new MatchDto();
            dto.setId(match.getId());

            // Finaliste
            JoueurDto finalisteDto = new JoueurDto();
            finalisteDto.setId(match.getFinaliste().getId());
            finalisteDto.setNom(match.getFinaliste().getNom());
            finalisteDto.setPrenom(match.getFinaliste().getPrenom());
            finalisteDto.setSexe(match.getFinaliste().getSexe());
            dto.setFinaliste(finalisteDto);

            // Vainqueur
            JoueurDto vainqueurDto = new JoueurDto();
            vainqueurDto.setId(match.getVainqueur().getId());
            vainqueurDto.setNom(match.getVainqueur().getNom());
            vainqueurDto.setPrenom(match.getVainqueur().getPrenom());
            vainqueurDto.setSexe(match.getVainqueur().getSexe());
            dto.setVainqueur(vainqueurDto);

            // Epreuve
            EpreuveFullDto epreuveDto = new EpreuveFullDto();
            epreuveDto.setId(match.getEpreuve().getId());
            epreuveDto.setAnnee(match.getEpreuve().getAnnee());
            epreuveDto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());

            // Tournoi de l'épreuve
            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(match.getEpreuve().getTournoi().getId());
            tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
            tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());
            epreuveDto.setTournoi(tournoiDto);
            dto.setEpreuve(epreuveDto);

            tx.commit();
            System.out.println("Match lu avec succès.");
        } catch (Throwable t) {
            t.printStackTrace();
            if (tx != null) tx.rollback();
        }
        return dto;
    }
}
