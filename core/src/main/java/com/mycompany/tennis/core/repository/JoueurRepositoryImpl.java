package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public List<Joueur> list() {
        Connection conn = null;
        List<Joueur> joueurs = new ArrayList<>();

        try {
            BasicDataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            // Requête SQL
            PreparedStatement statement = conn.prepareStatement("SELECT ID, NOM, PRENOM, SEXE FROM JOUEUR");
            ResultSet rs =statement.executeQuery();

            while (rs.next()) {
                Joueur joueur = new Joueur();
                joueur.setId(rs.getLong("ID"));
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
                joueurs.add(joueur);
            }

            // Success
            System.out.println("Liste des Joueurs lue avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch(SQLException e2) {
                e2.printStackTrace();
            }
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return joueurs;
    }
}
