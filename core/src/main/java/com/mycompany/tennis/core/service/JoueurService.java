package com.mycompany.tennis.core.service;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.dto.JoueurDto;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class JoueurService {

    private final JoueurRepositoryImpl joueurRepository;

    public JoueurService() {
        this.joueurRepository = new JoueurRepositoryImpl();
    }
    public void createJoueur(Joueur joueur) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            joueurRepository.create(joueur, session);
            tx.commit();

            System.out.println("Joueur créé avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
        }
    }

    public Joueur getJoueur(Long id) {
        Joueur joueur = null;
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            joueur = joueurRepository.getById(id, session);
            tx.commit();

            System.out.println("Joueur lu avec succès.");
        } catch (Throwable t) {
            t.printStackTrace();
            if (tx != null) tx.rollback();
        }
        return joueur;
    }

    public void renameJoueur(Long id, String nouveauNom) {
        Transaction tx = null;
        Joueur joueur = getJoueur(id); // La variable joueur est détachée de la session (transient)

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            joueur.setNom(nouveauNom);
            session.merge(joueur); // Rajoute l'objet joueur à la session (persistant)
            tx.commit();

            System.out.println("Joueur renommé avec succès.");
        } catch (Throwable t) {
            t.printStackTrace();
            if (tx != null) tx.rollback();
        }
    }

    public void deleteJoueur(Long id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Joueur joueur = joueurRepository.getById(id, session);
            if (joueur != null) {
                joueurRepository.delete(id, session);
                tx.commit();
                System.out.println("Joueur supprimé avec succès.");
            } else {
                System.out.println("Aucun joueur trouvé avec l'id : " + id);
            }
        } catch (Throwable t) {
            t.printStackTrace();
            if (tx != null) tx.rollback();
        }
    }

    public List<JoueurDto> getListeJoueurs(char sexe) {
        Transaction tx = null;
        List<JoueurDto> dtos = new ArrayList<JoueurDto>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Liste de Joueurs
            List<Joueur> joueurs = joueurRepository.list(sexe, session);

            // Conversion de la liste en liste de Dto
            for (Joueur joueur : joueurs) {
                JoueurDto dto = new JoueurDto();
                dto.setId(joueur.getId());
                dto.setNom(joueur.getNom());
                dto.setPrenom(joueur.getPrenom());
                dto.setSexe(joueur.getSexe());
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
