package com.mycompany.tennis.core.dao;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Match;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class MatchDaoImpl {
    public void createMatchWithScore(Match match) {
        Connection conn = null;
        try {
            BasicDataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            // 1️⃣ MATCH
            // Requête SQL (avec renvoi des enregistrements créés)
            PreparedStatement statement = conn.prepareStatement("INSERT INTO MATCH_TENNIS (ID_EPREUVE, ID_VAINQUEUR, ID_FINALISTE) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setLong(1, match.getEpreuve().getId());
            statement.setLong(2, match.getVainqueur().getId());
            statement.setLong(3, match.getFinaliste().getId());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys(); // Tous les enregistrements créés

            if(rs.next()) {
                match.setId(rs.getLong(1)); // Les ID commencent à 1 ici
            }

            // ✅ Success
            conn.commit();
            System.out.println("Match créé avec succès.");

            // 2️⃣ SCORE
            // Requête SQL (avec renvoi des enregistrements créés)
            PreparedStatement statement2 = conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH, SET_1, SET_2, SET_3, SET_4, SET_5) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            statement2.setLong(1, match.getScore().getMatch().getId());

            statement2.setByte(2, match.getScore().getSet1());

            statement2.setByte(3, match.getScore().getSet2());

            if (match.getScore().getSet3() == null) {
                statement2.setNull(4, Types.TINYINT);
            } else {
                statement2.setByte(4, match.getScore().getSet3());
            }

            if (match.getScore().getSet4() == null) {
                statement2.setNull(5, Types.TINYINT);
            } else {
                statement2.setByte(5, match.getScore().getSet4());
            }

            if (match.getScore().getSet5() == null) {
                statement2.setNull(6, Types.TINYINT);
            } else {
                statement2.setByte(6, match.getScore().getSet5());
            }

            statement.executeUpdate();
            ResultSet rs2 = statement2.getGeneratedKeys(); // Tous les enregistrements créés

            if(rs2.next()) {
                match.getScore().setId(rs2.getLong(1)); // Les ID commencent à 1 ici
            }

            // ✅ Success
            conn.commit();
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
