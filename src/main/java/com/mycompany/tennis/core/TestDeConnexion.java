package com.mycompany.tennis.core;

import java.sql.*;

public class TestDeConnexion {
    public static void main(String... args){
        Connection conn = null;
        try {
            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false","root","root");
            conn.setAutoCommit(false);

            // Requete SQL
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

