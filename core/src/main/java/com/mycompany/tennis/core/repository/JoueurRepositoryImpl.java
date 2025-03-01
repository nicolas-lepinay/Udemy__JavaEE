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
    public void create(Joueur joueur) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.persist(joueur); // Ajout de l'objet dans la session Hibernate (état transient → état persistant)
            tx.commit();

            System.out.println("Joueur créé avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) tx.rollback();
        }
    }

    public void update(Joueur joueur) {
        Connection conn = null;
        try {
            BasicDataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            // Requête SQL
            PreparedStatement statement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=?, SEXE=? WHERE ID=?");

            statement.setString(1, joueur.getNom());
            statement.setString(2, joueur.getPrenom());
            statement.setString(3, joueur.getSexe().toString());
            statement.setLong(4, joueur.getId());

            statement.executeUpdate();

            // Success
            System.out.println("Joueur mis à jour avec succès.");
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
    }

    public void delete(Long id) {
        Connection conn = null;
        try {
            BasicDataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            // Requête SQL
            PreparedStatement statement = conn.prepareStatement("DELETE FROM JOUEUR WHERE ID=?");
            statement.setLong(1, id);
            statement.executeUpdate();

            // Success
            System.out.println("Joueur supprimé avec succès.");
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
    }

    // Version avec Hibernate
    public Joueur getById(Long id) {
        Joueur joueur = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            joueur = session.get(Joueur.class, id);
            System.out.println("Joueur #" + id + " lu avec succès.");
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return joueur;
    }

    // Version avec JDBC uniquement et sans Hibernate
    /*
    public Joueur getById(Long id) {
        Connection conn = null;
        Joueur joueur = null;

        try {
            BasicDataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            // Requête SQL
            PreparedStatement statement = conn.prepareStatement("SELECT NOM, PRENOM, SEXE FROM JOUEUR WHERE ID=?");
            statement.setLong(1, id);
            ResultSet rs =statement.executeQuery();

            if (rs.next()) {
                joueur = new Joueur();
                joueur.setId(id);
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
            }

            // Success
            System.out.println("Joueur lu avec succès.");
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
        return joueur;
    }
     */

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
