package model;

import java.util.HashSet;
import java.util.Set;

public class Chercheur extends Titulaire implements Superviseur {
    private Set<Etudiant> etudiants = new HashSet<>();

    public Chercheur(String nom, String prenom, int age, Ville ville, Set<Discipline> disciplines) {
        super(nom, prenom, age, ville, disciplines);
    }

    @Override
    public void encadrer(Etudiant etudiant) {
        if (etudiant != null) {
            etudiants.add(etudiant);
            etudiant.setEncadrant(this); 
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Chercheur[etudiants=" + etudiants + "]";
    }
}