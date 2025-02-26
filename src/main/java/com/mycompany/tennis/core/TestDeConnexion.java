package com.mycompany.tennis.core;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class TestDeConnexion {
    public static void main(String... args){
        Connection conn = null;
        try {

            /* V1 → 🌐 Connexion avec le driver manager MySQL Driver Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false","root","root");
             */

            /* V2 → 🌐 Connexion avec l'interface MySQL DataSource
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("tennis");
            dataSource.setUseSSL(false);
            dataSource.setUser("root");
            dataSource.setPassword("root");
            */

            /* V3 → 🌐 Connexion avec la librairie de pool de connexions "DBCP" d'Apache */
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setInitialSize(5);
            dataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false");
            dataSource.setUsername("root");
            dataSource.setPassword("root");

            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            // Requête SQL
            PreparedStatement statement = conn.prepareStatement("INSERT INTO JOUEUR (NOM, PRENOM, SEXE) VALUES (?, ?, ?)");
            String nom = "Capriati";
            String prenom = "Jennifer";
            String sexe = "F";

            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setString(3, sexe);

            statement.executeUpdate();

            String nom2 = "Johannson";
            String prenom2 = "Thomas";
            String sexe2 = "M";

            statement.setString(1, nom2);
            statement.setString(2, prenom2);
            statement.setString(3, sexe2);

            statement.executeUpdate();

            conn.commit();

            // Success
            System.out.println("Connection was successful.");
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

