package model;

public class Etudiant extends Personne {
    private String sujetDeThese; 
    private Discipline discipline;
    private int anneeDeThese;
    private Titulaire encadrant; 

    public Etudiant(String nom, String prenom, int age, Ville ville,
                    String sujetDeThese, Discipline discipline, int anneeDeThese, Titulaire encadrant) {
        super(nom, prenom, age, ville);
        if (anneeDeThese < 1 || anneeDeThese > 3) {
            throw new IllegalArgumentException("Année doit prendre la valeur 1, 2 ou 3.");
        }
        if (sujetDeThese == null || discipline == null) {
            throw new IllegalArgumentException("SujetDeThese et Discipline doit prendre une valeur non null.");
        }
        this.sujetDeThese = sujetDeThese;
        this.discipline = discipline;
        this.anneeDeThese = anneeDeThese;
        this.encadrant = encadrant;
    }
    
    public String getSujetDeThese() {
        return sujetDeThese;
    }
    
    public void setSujetDeThese(String sujetDeThese) {
        if (sujetDeThese == null) throw new IllegalArgumentException("SujetDeThese ne peut être null.");
        this.sujetDeThese = sujetDeThese;
    }

    public void setAnneeDeThese(int anneeDeThese) {
        if (anneeDeThese < 1 || anneeDeThese > 3) throw new IllegalArgumentException("Année doit être 1, 2 ou 3.");
        this.anneeDeThese = anneeDeThese;
    }

    public void setEncadrant(Titulaire encadrant) {
        this.encadrant = encadrant;
    }
    

    @Override
    public String toString() {
        return super.toString() + ", Etudiant[sujetDeThese=" + sujetDeThese +
                ", discipline=" + discipline + ", anneeDeThese=" + anneeDeThese +
                ", encadrant=" + (encadrant != null ? encadrant.getNomComplet() : "None") + "]";
    }

	public Discipline getDiscipline() {
		return discipline;
		
	}
	
	public void setDiscipline(Discipline discipline) {
        this.discipline =discipline;
    }
	
}
