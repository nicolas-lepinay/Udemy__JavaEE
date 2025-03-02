package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Joueur;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EpreuveRepositoryImpl {
    public void create(Epreuve epreuve, Session session) {
        session.persist(epreuve); // Ajout de l'objet dans la session Hibernate (état transient → état persistant)
    }

    public void delete(Long id, Session session) {
        Epreuve epreuve = session.get(Epreuve.class, id);
        if (epreuve != null) session.remove(epreuve);
    }

    public Epreuve getById(Long id, Session session) {
        Epreuve epreuve = session.get(Epreuve.class, id);
        return epreuve;
    }
}
