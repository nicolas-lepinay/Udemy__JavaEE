package com.mycompany.tennis.core;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceProvider {
    private static BasicDataSource singleDataSource;

    public static BasicDataSource getSingleDataSourceInstance() {
        if (singleDataSource == null) {
            /* 🌐 Connexion avec la librairie de pool de connexions "DBCP" d'Apache */
            singleDataSource = new BasicDataSource();
            singleDataSource.setInitialSize(5);
            singleDataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false");
            singleDataSource.setUsername("root");
            singleDataSource.setPassword("root");
        }
        return singleDataSource;
    }
}
