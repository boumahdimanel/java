package main;

import service.CityDataLoader;

import java.util.*;
import java.util.stream.Collectors;

import model.*;

public class Main {
	
	    public static void main(String[] args) {
	    	
	         	List<Personne> personnes = new ArrayList<>();
	    	    CityDataLoader loader = new CityDataLoader();
	    	  
	    	    List<Ville> villes = loader.getToutesLesVilles();
	    	    
	    	        Scanner scanner = new Scanner(System.in);
	    	        List<Discipline> disciplines = new ArrayList<>();

	    	        while (true) {
	    	            afficherMenu();
	    	            int choix = scanner.nextInt();
	    	            scanner.nextLine(); // Consommer la nouvelle ligne

	    	            switch (choix) {
	    	                case 1:
	    	                    creerPersonne(personnes, villes);
	    	                    break;
	    	                
	    	                  
	    	                case 2:
	    	                    rechercherCycleHamiltonien(personnes, villes);
	    	                    break;
	    	                case 3:
	    	                    System.out.println("Au revoir !");
	    	                    return;
	    	                default:
	    	                    System.out.println("Choix invalide. Veuillez réessayer.");
	    	            }
	    	        }
	    	    }

	    	    private static void afficherMenu() {
	    	        System.out.println("\n--- Menu ---");
	    	        System.out.println("1. Créer une personne (étudiant, chercheur, titulaire)");
	                System.out.println("2. Rechercher un cycle hamiltonien");
	    	        System.out.println("3. Quitter");
	    	        System.out.print("Votre choix : ");
	    	    }

	    	    private static void creerPersonne(List<Personne> personnes, List<Ville> villes) {
	    	        Scanner scanner = new Scanner(System.in);
	    	        System.out.println("Quel type de personne voulez-vous créer ?");
	    	        System.out.println("1. Étudiant");
	    	        System.out.println("2. Chercheur");
	    	        System.out.println("3. Titulaire");
	    	        int choix = scanner.nextInt();
	    	        scanner.nextLine(); 
	    	        switch (choix) {
	    	            case 1:
	    	                creerEtudiant(personnes, villes);
	    	                break;
	    	            case 2:
	    	                creerChercheur(personnes, villes);
	    	                break;
	    	            case 3:
	    	                creerTitulaire(personnes, villes);
	    	                break;
	    	            default:
	    	                System.out.println("Choix invalide.");
	    	        }
	    	    }
	    	    
	    	    private static void creerEtudiant(List<Personne> personnes, List<Ville> villes) {
	    	        Scanner scanner = new Scanner(System.in);
	    	        System.out.print("Nom de l'étudiant : ");
	    	        String nom = scanner.nextLine();
	    	        System.out.print("Prénom de l'étudiant : ");
	    	        String prenom = scanner.nextLine();
	    	        System.out.print("Âge de l'étudiant : ");
	    	        int age = scanner.nextInt();
	    	        scanner.nextLine(); // Consommer la nouvelle ligne

	    	        System.out.println("Choisissez une ville :");
	    	        for (int i = 0; i < villes.size(); i++) {
	    	            System.out.println(i + ". " + villes.get(i).getNom());
	    	        }
	    	        int villeIndex = scanner.nextInt();
	    	        scanner.nextLine(); // Consommer la nouvelle ligne
	    	        Ville ville = villes.get(villeIndex);

	    	        System.out.print("Sujet de thèse : ");
	    	        String sujetDeThese = scanner.nextLine();

	    	        System.out.println("Choisissez une discipline :");
	    	        Discipline[] disciplines = Discipline.values();
	    	        for (int i = 0; i < disciplines.length; i++) {
	    	            System.out.println(i + ". " + disciplines[i]);
	    	        }
	    	        int disciplineIndex = scanner.nextInt();
	    	        scanner.nextLine(); // Consommer la nouvelle ligne
	    	        Discipline discipline = disciplines[disciplineIndex];

	    	        System.out.print("Année de thèse (1, 2 ou 3) : ");
	    	        int anneeDeThese = scanner.nextInt();
	    	        scanner.nextLine(); // Consommer la nouvelle ligne

	    	        System.out.println("Choisissez un encadrant parmi les titulaires :");
	    	        List<Titulaire> titulaires = personnes.stream()
	    	                .filter(p -> p instanceof Titulaire)
	    	                .map(p -> (Titulaire) p)
	    	                .collect(Collectors.toList());
	    	        for (int i = 0; i < titulaires.size(); i++) {
	    	            System.out.println(i + ". " + titulaires.get(i).getNomComplet());
	    	        }
	    	        int encadrantIndex = scanner.nextInt();
	    	        scanner.nextLine(); // Consommer la nouvelle ligne
	    	        Titulaire encadrant = titulaires.get(encadrantIndex);

	    	        Etudiant etudiant = new Etudiant(nom, prenom, age, ville, sujetDeThese, discipline, anneeDeThese, encadrant);
	    	        personnes.add(etudiant);
	    	        System.out.println("Étudiant créé avec succès.");
	    	    }

	    	    private static void creerChercheur(List<Personne> personnes, List<Ville> villes) {
	    	        Scanner scanner = new Scanner(System.in);
	    	        System.out.print("Nom du chercheur : ");
	    	        String nom = scanner.nextLine();
	    	        System.out.print("Prénom du chercheur : ");
	    	        String prenom = scanner.nextLine();
	    	        System.out.print("Âge du chercheur : ");
	    	        int age = scanner.nextInt();
	    	        scanner.nextLine(); // Consommer la nouvelle ligne

	    	        System.out.println("Choisissez une ville :");
	    	        for (int i = 0; i < villes.size(); i++) {
	    	            System.out.println(i + ". " + villes.get(i).getNom());
	    	        }
	    	        int villeIndex = scanner.nextInt();
	    	        scanner.nextLine(); // Consommer la nouvelle ligne
	    	        Ville ville = villes.get(villeIndex);

	    	        Set<Discipline> disciplines = new HashSet<>();
	    	        System.out.println("Choisissez une ou deux disciplines :");
	    	        Discipline[] allDisciplines = Discipline.values();
	    	        for (int i = 0; i < allDisciplines.length; i++) {
	    	            System.out.println(i + ". " + allDisciplines[i]);
	    	        }
	    	        int discipline1Index = scanner.nextInt();
	    	        disciplines.add(allDisciplines[discipline1Index]);
	    	        System.out.print("Voulez-vous ajouter une deuxième discipline ? (o/n) : ");
	    	        if (scanner.next().toLowerCase().charAt(0) == 'o') {
	    	            System.out.println("Choisissez la deuxième discipline :");
	    	            int discipline2Index = scanner.nextInt();
	    	            disciplines.add(allDisciplines[discipline2Index]);
	    	        }
	    	        scanner.nextLine(); // Consommer la nouvelle ligne

	    	        Chercheur chercheur = new Chercheur(nom, prenom, age, ville, disciplines);
	    	        personnes.add(chercheur);
	    	        System.out.println("Chercheur créé avec succès.");
	    	    }

	    	    private static void creerTitulaire(List<Personne> personnes, List<Ville> villes) {
	    	        Scanner scanner = new Scanner(System.in);
	    	        System.out.print("Nom du titulaire : ");
	    	        String nom = scanner.nextLine();
	    	        System.out.print("Prénom du titulaire : ");
	    	        String prenom = scanner.nextLine();
	    	        System.out.print("Âge du titulaire : ");
	    	        int age = scanner.nextInt();
	    	        scanner.nextLine(); // Consommer la nouvelle ligne

	    	        System.out.println("Choisissez une ville :");
	    	        for (int i = 0; i < villes.size(); i++) {
	    	            System.out.println(i + ". " + villes.get(i).getNom());
	    	        }
	    	        int villeIndex = scanner.nextInt();
	    	        scanner.nextLine(); // Consommer la nouvelle ligne
	    	        Ville ville = villes.get(villeIndex);

	    	        Set<Discipline> disciplines = new HashSet<>();
	    	        System.out.println("Choisissez une ou deux disciplines :");
	    	        Discipline[] allDisciplines = Discipline.values();
	    	        for (int i = 0; i < allDisciplines.length; i++) {
	    	            System.out.println(i + ". " + allDisciplines[i]);
	    	        }
	    	        int discipline1Index = scanner.nextInt();
	    	        disciplines.add(allDisciplines[discipline1Index]);
	    	        System.out.print("Voulez-vous ajouter une deuxième discipline ? (o/n) : ");
	    	        if (scanner.next().toLowerCase().charAt(0) == 'o') {
	    	            System.out.println("Choisissez la deuxième discipline :");
	    	            int discipline2Index = scanner.nextInt();
	    	            disciplines.add(allDisciplines[discipline2Index]);
	    	        }
	    	        scanner.nextLine(); // Consommer la nouvelle ligne

	    	        System.out.println("Choisissez le type de titulaire :");
	    	        System.out.println("1. MCF");
	    	        System.out.println("2. Chercheur");
	    	        int choixType = scanner.nextInt();
	    	        scanner.nextLine(); // Consommer la nouvelle ligne

	    	        Titulaire titulaire;
	    	        if (choixType == 1) {
	    	            titulaire = new MCF(nom, prenom, age, ville, disciplines);
	    	        } else {
	    	            titulaire = new Chercheur(nom, prenom, age, ville, disciplines);
	    	        }
	    	        personnes.add(titulaire);
	    	        System.out.println("Titulaire créé avec succès.");
	    	    }
	    	    
	    	    private static void rechercherCycleHamiltonien(List<Personne> personnes, List<Ville> villes) {
	    	        Scanner scanner = new Scanner(System.in);
	    	        System.out.println("Choisissez le type de recherche :");
	    	        System.out.println("1. Visiter les étudiants d'un domaine spécifique");
	    	        System.out.println("2. Visiter les chercheurs de plus de 55 ans");
	    	        int choix = scanner.nextInt();
	    	        scanner.nextLine(); // Consommer la nouvelle ligne

	    	        List<Ville> villesAVisiter = new ArrayList<>();

	    	        switch (choix) {
	    	            case 1:
	    	                System.out.print("Entrez le domaine de recherche : ");
	    	                String domaine = scanner.nextLine();
	    	                villesAVisiter = getVillesEtudiantsDomaine(personnes, domaine);
	    	                break;
	    	            case 2:
	    	                villesAVisiter = getVillesChercheurs(personnes);
	    	                break;
	    	            default:
	    	                System.out.println("Choix invalide.");
	    	                return;
	    	        }

	    	        if (villesAVisiter.isEmpty()) {
	    	            System.out.println("Aucune ville à visiter trouvée.");
	    	            return;
	    	        }

	    	        AlgorithmeGenetique algo = new AlgorithmeGenetique(100, 0.01, 0.1, villesAVisiter);
	    	        Circuit meilleurCircuit = algo.resoudre();

	    	        System.out.println("Meilleur circuit trouvé :");
	    	        for (Ville ville : meilleurCircuit.getVilles()) {
	    	            System.out.println(ville.getNom());
	    	        }
	    	        System.out.println("Distance totale : " + meilleurCircuit.getDistance() + " km");
	    	    }
	    	    
	    	    
	    	    
	    	    private static List<Ville> getVillesEtudiantsDomaine(List<Personne> personnes, String domaine) {
	    	        return personnes.stream()
	    	            .filter(p -> p instanceof Etudiant)
	    	            .map(p -> (Etudiant) p)
	    	            .filter(e -> e.getDiscipline().toString().equalsIgnoreCase(domaine))
	    	            .map(Personne::getVille)
	    	            .distinct()
	    	            .collect(Collectors.toList());
	    	    }

	    	    private static List<Ville> getVillesChercheurs(List<Personne> personnes) {
	    	        return personnes.stream()
	    	            .filter(p -> p instanceof Chercheur )
	    	            .map(Personne::getVille)
	    	            .distinct()
	    	            .collect(Collectors.toList());
	    	    }



	   }

	    
	


