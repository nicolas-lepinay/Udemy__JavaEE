package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Match;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchRepositoryImpl {

    public void create(Match match) {
        Connection conn = null;
        try {
            BasicDataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();
            conn = dataSource.getConnection();

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

            // Success
            System.out.println("Match créé avec succès.");
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
