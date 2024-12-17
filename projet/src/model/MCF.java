package model;

import java.util.Set;

public class MCF extends Titulaire implements Superviseur {
    private Etudiant etudiant; 

    public MCF(String nom, String prenom, int age, Ville ville, Set<Discipline> disciplines) {
        super(nom, prenom, age, ville, disciplines);
    }

    @Override
    public void encadrer(Etudiant etudiant) {
        this.etudiant = etudiant;
        if (etudiant != null) etudiant.setEncadrant(this); 
    }

    @Override
    public String toString() {
        return super.toString() + ", MCF[etudiant=" + etudiant + "]";
    }
}
