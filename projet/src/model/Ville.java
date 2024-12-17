package model;


public class Ville {
    private String nom;
    private String codePostal;
    private int population;
    private double superficie; // en km²
    private String departement;
    private double longitude;
    private double latitude;

    public Ville(String nom, String codePostal, int population, double superficie, String departement, double longitude, double latitude) {
        this.nom = nom;
        this.codePostal = codePostal;
        this.population = population;
        this.superficie = superficie;
        this.departement = departement;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Ville(String nom, String codePostal, int population, double superficie, String departement) {
    	this.nom = nom;
        this.codePostal = codePostal;
        this.population = population;
        this.superficie = superficie;
        this.departement = departement;
    }
    // Ajout des getters pour longitude et latitude
    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    // Ajout des setters pour longitude et latitude
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    @Override
    public String toString() {
        return "Ville{" +
               "nom='" + nom + '\'' +
               ", codePostal='" + codePostal + '\'' +
               ", population=" + population +
               ", superficie=" + superficie +
               ", departement='" + departement + '\'' +
               ", longitude=" + longitude +
               ", latitude=" + latitude +
               '}';
    }
    
    public double distanceTo(Ville autreVille) {
        final int R = 6371; // Rayon de la Terre en kilomètres

        double lat1 = Math.toRadians(this.latitude);
        double lat2 = Math.toRadians(autreVille.latitude);
        double lon1 = Math.toRadians(this.longitude);
        double lon2 = Math.toRadians(autreVille.longitude);

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return R * c;
    }
}
