package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class ScoreRepositoryImpl {

    public void create(Score score) {
        Connection conn = null;
        try {
            BasicDataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

            // Requête SQL (avec renvoi des enregistrements créés)
            PreparedStatement statement = conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH, SET_1, SET_2, SET_3, SET_4, SET_5) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setLong(1, score.getMatch().getId());

            statement.setByte(2, score.getSet1());

            statement.setByte(3, score.getSet2());

            if (score.getSet3() == null) {
                statement.setNull(4, Types.TINYINT);
            } else {
                statement.setByte(4, score.getSet3());
            }

            if (score.getSet4() == null) {
                statement.setNull(5, Types.TINYINT);
            } else {
                statement.setByte(5, score.getSet4());
            }

            if (score.getSet5() == null) {
                statement.setNull(6, Types.TINYINT);
            } else {
                statement.setByte(6, score.getSet5());
            }

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys(); // Tous les enregistrements créés

            if(rs.next()) {
                score.setId(rs.getLong(1)); // Les ID commencent à 1 ici
            }

            // Success
            System.out.println("Score créé avec succès.");
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
}
