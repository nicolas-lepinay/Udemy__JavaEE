package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.entity.Match;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchRepositoryImpl {

    public void create(Match match, Session session) {
        session.persist(match); // Ajout de l'objet dans la session Hibernate (état transient → état persistant)
    }

    public void delete(Long id, Session session) {
        Match match = session.get(Match.class, id);
        if (match != null) session.remove(match);
    }

    public Match getById(Long id, Session session) {
        Match match = session.get(Match.class, id);
        return match;
    }

}
