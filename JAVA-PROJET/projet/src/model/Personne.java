package model;



public class Personne {
	private static int nbPersonnes = 0; 
    private final int ID; 
    private String nom;
    private String prenom;
    private int age;
    private Ville ville;

    public Personne(String nom, String prenom, int age, Ville ville) {
        if (nom == null || prenom == null || ville == null) {
            throw new IllegalArgumentException("Nom, prenom, et ville ne peuvent prendre la valeur null.");
        }
        if (age <= 0) {
            throw new IllegalArgumentException("Age doit prendre une valeur positive.");
        }
        this.ID = ++nbPersonnes; 
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.ville = ville;
    }

    public static int getNbPersonnes() {
        return nbPersonnes;
    }

    public Ville getVille() {
        return ville;
    }
    
    public String getNom() {
    	return nom;
    }
    public String getPrenom() {
    	return prenom;
    }
    public void setNom(String nom) {
        if (nom == null) throw new IllegalArgumentException("Nom ne peut être null.");
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        if (prenom == null) throw new IllegalArgumentException("Prenom ne peut être null.");
        this.prenom = prenom;
    }
    
    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        if (age <= 0) throw new IllegalArgumentException("Age doit être un nombre positif.");
        this.age = age;
    }

    public void setVille(Ville ville) {
        if (ville == null) throw new IllegalArgumentException("Ville ne peut être null.");
        this.ville = ville;
    }

    
    @Override
    public String toString() {
        return "Personne[ID=" + ID + ", nom=" + nom + ", prenom=" + prenom +
                ", age=" + age + ", ville=" + ville + "]";
    }

}

















