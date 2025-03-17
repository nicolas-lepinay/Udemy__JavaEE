package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.Session;

import java.sql.*;

public class ScoreRepositoryImpl {

    public void create(Score score, Session session) {
        session.persist(score); // Ajout de l'objet dans la session Hibernate (état transient → état persistant)
    }

    public void delete(Long id, Session session) {
        Score score = session.get(Score.class, id);
        if (score != null) session.remove(score);
    }
}
