package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import java.sql.Connection;
//import java.sql.SQLException;
//import databaseConnection.DatabaseConnection;


public class Circuit {
    private List<Ville> villes;
    private double distance;
    //private Connection connection;

    public Circuit(List<Ville> villes) {
        this.villes = new ArrayList<>(villes);
        this.distance = -1;
       // this.connection = DatabaseConnection.getConnection();
    }

    public void melangerVilles() {
        Collections.shuffle(villes);
        distance = -1;
    }

    public double getDistance() {
        if (distance == -1) {
            double totalDistance = 0;
           
                for (int i = 0; i < villes.size(); i++) {
                    Ville villeDepart = villes.get(i);
                    Ville villeArrivee = villes.get((i + 1) % villes.size());
                    totalDistance += villeDepart.distanceTo(villeArrivee);
                }
                distance = totalDistance;
            
        }
        return distance;
    }

    public double getFitness() {
        return 1 / getDistance();
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public Ville getVille(int index) {
        return villes.get(index);
    }

    public void setVille(int index, Ville ville) {
        villes.set(index, ville);
        distance = -1;
    }

    public int getTaille() {
        return villes.size();
    }

    public boolean contientVille(Ville ville) {
        return villes.contains(ville);
    }
}
