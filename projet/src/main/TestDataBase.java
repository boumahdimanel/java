package main;

import databaseConnection.DatabaseConnection;

import java.sql.Connection;

public class TestDataBase {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            System.out.println("La connexion à la base est opérationnelle !");
        }
    }
}

