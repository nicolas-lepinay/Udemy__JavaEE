package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.EntityManagerHolder;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EpreuveRepositoryImpl {
    public void create(Epreuve epreuve) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        em.persist(epreuve); //  Ajout de l'objet dans la session Hibernate (état transient → état persistant)
    }

    public void delete(Long id) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Epreuve epreuve = em.find(Epreuve.class, id);
        if (epreuve !=  null) em.remove(epreuve);
    }

    public Epreuve getById(Long id) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();
        Epreuve epreuve = em.find(Epreuve.class, id);
        return epreuve;
    }

    public List<Epreuve> list(String codeTournoi) {
        EntityManager em = EntityManagerHolder.getCurrentEntityManager();

        TypedQuery<Epreuve> query = em.createQuery("SELECT e FROM Epreuve e join fetch e.tournoi WHERE e.tournoi.code = ?1", Epreuve.class);
        query.setParameter(1, codeTournoi);
        List<Epreuve> epreuves = query.getResultList();
        return epreuves;
    }
}
