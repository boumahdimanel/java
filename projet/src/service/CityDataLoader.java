package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import databaseConnection.DatabaseConnection;
import model.*;


public class CityDataLoader {

    // Charge toutes les villes
    public List<Ville> getToutesLesVilles() {
        List<Ville> villes = new ArrayList<>();
        String query = "SELECT ville_nom, ville_code_postal, ville_population_2012, ville_surface, ville_departement,ville_longitude_deg,ville_latitude_deg FROM public.villes_france_free";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String nom = rs.getString("ville_nom");
                String codePostal = rs.getString("ville_code_postal");
                int population = rs.getInt("ville_population_2012");
                double superficie = rs.getDouble("ville_surface");
                String departement = rs.getString("ville_departement");
                double longitude=rs.getDouble("ville_longitude_deg");
                double latitude=rs.getDouble("ville_latitude_deg");

                Ville ville = new Ville(nom, codePostal, population, superficie, departement,longitude,latitude);
                villes.add(ville);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return villes;
    }
}


