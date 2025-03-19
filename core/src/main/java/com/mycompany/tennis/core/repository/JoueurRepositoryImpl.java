package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {
    public void create(Joueur joueur, Session session) {
        session.persist(joueur); // Ajout de l'objet dans la session Hibernate (état transient → état persistant)
    }

    public void delete(Long id, Session session) {
        Joueur joueur = session.get(Joueur.class, id);
        if (joueur != null) session.remove(joueur);
    }

    public Joueur getById(Long id, Session session) {
        Joueur joueur = session.get(Joueur.class, id);
        return joueur;
    }

    public List<Joueur> list(char sexe) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();

        // 1. Version sans @NamedQuery dans l'entité (et Hibernate) :
        //Query<Joueur> query = session.createQuery("SELECT j FROM Joueur j WHERE j.sexe = ?1", Joueur.class);

        // 2. Version avec les NamedQueries définies dans l'entité (sans Hibernate) :
        TypedQuery<Joueur> query = em.createNamedQuery("given_sexe", Joueur.class);
        query.setParameter(1, sexe);
        List<Joueur> joueurs = query.getResultList();
        return joueurs;
    }

}
