package model;

import java.util.HashSet;
import java.util.Set;

public abstract class Titulaire extends Personne {
    protected Set<Discipline> disciplines = new HashSet<>(); 

    public Titulaire(String nom, String prenom, int age, Ville ville, Set<Discipline> disciplines) {
        super(nom, prenom, age, ville);
        if (disciplines == null || disciplines.size() < 1 || disciplines.size() > 2) {
            throw new IllegalArgumentException("Titulaire doit avoir sur 1 ou 2 disciplines.");
        }
        this.disciplines = disciplines;
    }

    public Set<Discipline> getDisciplines() {
        return disciplines;
    }

    public String getNomComplet() {
        return this.toString();
    }

    @Override
    public String toString() {
        return super.toString() + ", Titulaire[disciplines=" + disciplines + "]";
    }
}
