package databaseConnection;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/ville_france";
    private static final String USER = "postgres"; // Remplacez par votre utilisateur
    private static final String PASSWORD = "manel"; // Remplacez par votre mot de passe

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Charge le driver PostgreSQL (optionnel à partir de Java 6+)
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion réussie à PostgreSQL !");
            } catch (ClassNotFoundException e) {
                System.err.println("Driver JDBC introuvable !");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Échec de connexion à la base PostgreSQL !");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
