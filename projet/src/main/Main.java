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
	    	        System.out.println("1. Créer une personne (étudiant,titulaire)");
	                System.out.println("2. Rechercher un cycle hamiltonien");
	    	        System.out.println("3. Quitter");
	    	        System.out.print("Votre choix : ");
	    	    }

	    	    private static void creerPersonne(List<Personne> personnes,List<Ville> villes) {
	    	        Scanner scanner = new Scanner(System.in);
	    	        System.out.println("Quel type de personne voulez-vous créer ?");
	    	        System.out.println("1. Étudiant");
	    	
	    	        System.out.println("2. Titulaire");
	    	        int choix = scanner.nextInt();
	    	        scanner.nextLine();

	    	        // Collecter les données communes
	    	        Map<String, Object> commonData = collecterDonneesCommunes(scanner, villes);

	    	        switch (choix) {
	    	            case 1:
	    	                creerEtudiant(personnes, commonData, scanner);
	    	                break;
	    	            
	    	           
	    	            case 2:
	    	                creerTitulaire(personnes, commonData, scanner);
	    	                break;
	    	            default:
	    	                System.out.println("Choix invalide.");
	    	        }
	    	    }

	    	    private static Map<String, Object> collecterDonneesCommunes(Scanner scanner, List<Ville> villes) {
	    	        Map<String, Object> data = new HashMap<>();
	    	        System.out.print("Nom : ");
	    	        data.put("nom", scanner.nextLine());
	    	        System.out.print("Prénom : ");
	    	        data.put("prenom", scanner.nextLine());
	    	        System.out.print("Âge : ");
	    	        data.put("age", scanner.nextInt());
	    	        scanner.nextLine();

	    	        // Choix de la ville
	    	        data.put("ville", choisirVille(scanner, villes));
	    	        return data;
	    	    }

	    	    
	    	    private static Ville choisirVille( Scanner scanner,List<Ville> villes) {
	    	        Ville ville = null;
	    	        while (ville == null) {  // Tant qu'aucune ville valide n'est trouvée
	    	            System.out.print("Nom de la ville : ");
	    	            String nomVille = scanner.nextLine();

	    	            // Filtrer les villes par nom
	    	            List<Ville> villesFiltrees = villes.stream()
	    	                    .filter(v -> v.getNom().equalsIgnoreCase(nomVille))
	    	                    .collect(Collectors.toList());

	    	            if (villesFiltrees.isEmpty()) {
	    	                System.out.println("Aucune ville trouvée avec ce nom. Veuillez réessayer.");
	    	            } else {
	    	                // Si plusieurs villes existent avec le même nom, demander de spécifier le département
	    	                if (villesFiltrees.size() == 1) {
	    	                    ville = villesFiltrees.get(0);
	    	                } else {
	    	                    System.out.println("Plusieurs villes trouvées. Veuillez préciser le département :");
	    	                    for (int i = 0; i < villesFiltrees.size(); i++) {
	    	                        System.out.println(i + ". " + villesFiltrees.get(i).getNom() + " (Département : " + villesFiltrees.get(i).getDepartement() + ")");
	    	                    }
	    	                    int villeIndex = scanner.nextInt();
	    	                    scanner.nextLine(); // Consommer la nouvelle ligne
	    	                    ville = villesFiltrees.get(villeIndex);
	    	                }
	    	            }
	    	        }
	    	        return ville;  // Retourne la ville choisie une fois trouvée
	    	    }


	    	    private static void creerEtudiant(List<Personne> personnes, Map<String, Object> commonData, Scanner scanner) {
	    	        System.out.print("Sujet de thèse : ");
	    	        String sujetDeThese = scanner.nextLine();

	    	        Discipline discipline = choisirDiscipline(scanner);

	    	        System.out.print("Année de thèse (1, 2 ou 3) : ");
	    	        int anneeDeThese = scanner.nextInt();
	    	        scanner.nextLine();

	    	        List<Titulaire> titulaires = personnes.stream()
	    	                .filter(p -> p instanceof Titulaire)
	    	                .map(p -> (Titulaire) p)
	    	                .toList();
	    	        System.out.println("Choisissez un encadrant parmi les titulaires :");
	    	        Titulaire encadrant = choisirTitulaire(scanner, titulaires);

	    	        Ville ville = (Ville) commonData.get("ville");
	    	        Etudiant etudiant = new Etudiant(
	    	                (String) commonData.get("nom"),
	    	                (String) commonData.get("prenom"),
	    	                (int) commonData.get("age"),
	    	                ville, sujetDeThese, discipline, anneeDeThese, encadrant
	    	        );
	    	        personnes.add(etudiant);
	    	        System.out.println("Étudiant créé avec succès.");
	    	    }

	    	    private static Discipline choisirDiscipline(Scanner scanner) {
	    	        System.out.println("Choisissez une discipline :");
	    	        Discipline[] disciplines = Discipline.values();
	    	        for (int i = 0; i < disciplines.length; i++) {
	    	            System.out.println(i + ". " + disciplines[i]);
	    	        }
	    	        int disciplineIndex = scanner.nextInt();
	    	        scanner.nextLine();
	    	        return disciplines[disciplineIndex];
	    	    }

	    	    private static Titulaire choisirTitulaire(Scanner scanner, List<Titulaire> titulaires) {
	    	        for (int i = 0; i < titulaires.size(); i++) {
	    	            System.out.println(i + ". " + titulaires.get(i).getNomComplet());
	    	        }
	    	        int encadrantIndex = scanner.nextInt();
	    	        scanner.nextLine();
	    	        return titulaires.get(encadrantIndex);
	    	    }

	    	   



private static void creerTitulaire(List<Personne> personnes, Map<String, Object> commonData, Scanner scanner) {
    System.out.println("Choisissez le type de titulaire :");
    System.out.println("1. MCF");
    System.out.println("2. Chercheur");
    int choixTitulaire = scanner.nextInt();
    scanner.nextLine();  // Consomme la nouvelle ligne

    Discipline discipline = choisirDiscipline(scanner);

    System.out.print("Souhaitez-vous ajouter une autre discipline ? (o/n) : ");
    String ajouterDeuxiemeDiscipline = scanner.nextLine();

    Set<Discipline> disciplines = new HashSet<>();
    disciplines.add(discipline);
    if (ajouterDeuxiemeDiscipline.equalsIgnoreCase("o")) {
        Discipline discipline2 = choisirDiscipline(scanner);
        disciplines.add(discipline2);
    }

    Ville ville = (Ville) commonData.get("ville");
    Titulaire titulaire;

    if (choixTitulaire == 1) {
        titulaire = new MCF(
                (String) commonData.get("nom"),
                (String) commonData.get("prenom"),
                (int) commonData.get("age"),
                ville,
                disciplines
        );
    } else if (choixTitulaire == 2) {
        titulaire = new Chercheur(
                (String) commonData.get("nom"),
                (String) commonData.get("prenom"),
                (int) commonData.get("age"),
                ville,
                disciplines
        );
    } else {
        System.out.println("Choix invalide.");
        return;
    }

    personnes.add(titulaire);
    System.out.println("Titulaire créé avec succès !");
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

	    
	
	    
	


