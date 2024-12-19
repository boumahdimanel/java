package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/ville_france";
    private static final String USER = "postgres"; // Remplacez par votre utilisateur
    private static final String PASSWORD = "manel"; // Remplacez par votre mot de passe

    private static Connection connection;

    // Prépare la requête pour calculer la distance
   /* private static void prepareCalculDistance(Connection connection) throws SQLException {
        String prepareSQL = """
            PREPARE calcul_distance(varchar, varchar, varchar, varchar) AS
            SELECT 
                6371 * 2 * ASIN(SQRT(
                   POWER(SIN(RADIANS(v2.ville_latitude_deg - v1.ville_latitude_deg) / 2), 2) +
                   COS(RADIANS(v1.ville_latitude_deg)) * COS(RADIANS(v2.ville_latitude_deg)) *
                   POWER(SIN(RADIANS(v2.ville_longitude_deg - v1.ville_longitude_deg) / 2), 2)
                )) AS distance_km
             FROM 
                villes_france_free v1,
                villes_france_free v2
             WHERE 
                v1.ville_nom = $1 AND
                v1.ville_departement = $2 AND
                v2.ville_nom = $3 AND
                v2.ville_departement = $4;
            """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(prepareSQL);
            System.out.println("Requête PREPARE calcul_distance initialisée avec succès.");
        }}*/
    

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Charger le driver PostgreSQL (optionnel à partir de Java 6+)
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connexion réussie à PostgreSQL !");
                
                // Préparer la déclaration calcul_distance
              //  prepareCalculDistance(connection);
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
