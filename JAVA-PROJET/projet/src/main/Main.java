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
                    while(true) {
	    	        afficherMenu();
	    	        int choix = -1; // Initialiser le choix à une valeur invalide
	    	        while (choix < 1 || choix > 3) { // Boucle pour valider le choix
	    	            try {
	    	                choix = Integer.parseInt(scanner.nextLine()); // Lire comme chaîne et convertir
	    	                if (choix < 1 || choix > 3) {
	    	                    System.out.println("Choix invalide. Veuillez réessayer.");
	    	                }
	    	            } catch (NumberFormatException e) {
	    	                System.out.println("Veuillez entrer un nombre valide.");
	    	            }
	    	        }
	    	            switch (choix) {
	    	                case 1:
	    	                    creerPersonne(personnes, villes);
	    	                    break;
	    	                
	    	                  
	    	                case 2:
	    	                	modifierPersonne(personnes, scanner, villes);
	    	                    
	    	                    break;
	    	                case 3:
	    	                	
	    	                	rechercherCycleHamiltonien(personnes, villes);
	    	                	
	    	                case 4:
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
	    	        System.out.println("2. Modifier les détails d'une personne");
	                System.out.println("3. Rechercher un cycle hamiltonien");
	    	        System.out.println("4. Quitter");
	    	        System.out.print("Votre choix : ");
	    	    }

	    	    private static void creerPersonne(List<Personne> personnes, List<Ville> villes) {
	    	        Scanner scanner = new Scanner(System.in);
	    	        int choix = 0;

	    	        // Boucle pour demander le type de personne jusqu'à obtenir un choix valide
	    	        while (choix != 1 && choix != 2) {
	    	            System.out.println("Quel type de personne voulez-vous créer ?");
	    	            System.out.println("1. Étudiant");
	    	            System.out.println("2. Titulaire");
	    	            
	    	            try {
	    	                choix = scanner.nextInt();
	    	                scanner.nextLine(); // Consommer la nouvelle ligne

	    	                if (choix == 1) {
	    	                    // Collecter les données communes
	    	                    Map<String, Object> commonData = collecterDonneesCommunes(scanner, villes);
	    	                    creerEtudiant(personnes, commonData, scanner);
	    	                } else if (choix == 2) {
	    	                    // Collecter les données communes
	    	                    Map<String, Object> commonData = collecterDonneesCommunes(scanner, villes);
	    	                    creerTitulaire(personnes, commonData, scanner);
	    	                } else {
	    	                    System.out.println("Choix invalide. Veuillez réessayer.");
	    	                }
	    	            } catch (InputMismatchException e) {
	    	                System.out.println("Veuillez entrer un nombre valide.");
	    	                scanner.nextLine(); // Consommer la ligne incorrecte
	    	            }
	    	        }
	    	    }


	    	    private static Map<String, Object> collecterDonneesCommunes(Scanner scanner, List<Ville> villes) {
	    	        Map<String, Object> data = new HashMap<>();
	    	        System.out.print("Nom : ");
	    	        data.put("nom", scanner.nextLine());
	    	        System.out.print("Prénom : ");
	    	        data.put("prenom", scanner.nextLine());
	    	       
	    	       
	    	        
	    	        int age = -1; // Initialiser l'âge à une valeur invalide
	    	        while (age < 0 || age > 120) { // Supposons que 150 est l'âge maximum raisonnable
	    	            System.out.print("Âge : ");
	    	            try {
	    	                age = scanner.nextInt();
	    	                scanner.nextLine(); // Consommer la nouvelle ligne

	    	                if (age < 0) {
	    	                    System.out.println("L'âge ne peut pas être négatif. Veuillez réessayer.");
	    	                } else if (age > 120) {
	    	                    System.out.println("L'âge semble trop élevé. Veuillez réessayer.");
	    	                }
	    	            } catch (InputMismatchException e) {
	    	                System.out.println("Veuillez entrer un nombre valide.");
	    	                scanner.nextLine(); // Consommer la ligne incorrecte
	    	            }
	    	        }
	    	        data.put("age", age);
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
	    	        if (commonData.get("age") == null) {
	    	            System.out.println("Erreur : l'âge n'est pas défini.");
	    	            return;
	    	        }

	    	      
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

private static void modifierPersonne(List<Personne> personnes, Scanner scanner, List<Ville> villes) {
    if (personnes.isEmpty()) {
        System.out.println("Aucune personne enregistrée.");
        return;
    }

    System.out.println("Liste des personnes disponibles :");
    for (int i = 0; i < personnes.size(); i++) {
        System.out.println(i + ". " + personnes.get(i));
    }

    System.out.print("Entrez le numéro de la personne à modifier : ");
    int index;
    try {
        index = Integer.parseInt(scanner.nextLine());
        if (index < 0 || index >= personnes.size()) {
            System.out.println("Numéro invalide.");
            return;
        }
    } catch (NumberFormatException e) {
        System.out.println("Veuillez entrer un nombre valide.");
        return;
    }

    Personne personne = personnes.get(index);

    if (personne instanceof Etudiant) {
        modifierEtudiant((Etudiant) personne, scanner, villes);
    } else if (personne instanceof Titulaire) {
        modifierTitulaire((Titulaire) personne, scanner, villes);
    } else {
        System.out.println("Type de personne inconnu.");
    }
}

private static void modifierEtudiant(Etudiant etudiant, Scanner scanner, List<Ville> villes) {
    System.out.println("Modification des détails de l'étudiant.");

    System.out.print("Nouveau nom (actuel : " + etudiant.getNom() + ") : ");
    etudiant.setNom(scanner.nextLine());

    System.out.print("Nouveau prénom (actuel : " + etudiant.getPrenom() + ") : ");
    etudiant.setPrenom(scanner.nextLine());

    System.out.print("Nouvel âge (actuel : " + etudiant.getAge() + ") : ");
    etudiant.setAge(Integer.parseInt(scanner.nextLine()));

    System.out.println("Choisir une nouvelle ville (actuelle : " + etudiant.getVille().getNom() + ") :");
    Ville nouvelleVille = choisirVille(scanner, villes);
    etudiant.setVille(nouvelleVille);

    System.out.print("Nouveau sujet de thèse (actuel : " + etudiant.getSujetDeThese() + ") : ");
    etudiant.setSujetDeThese(scanner.nextLine());

    Discipline nouvelleDiscipline = choisirDiscipline(scanner);
    etudiant.setDiscipline(nouvelleDiscipline);

    System.out.println("Détails de l'étudiant mis à jour avec succès !");
}

private static void modifierTitulaire(Titulaire titulaire, Scanner scanner, List<Ville> villes) {
    System.out.println("Modification des détails du titulaire.");

    System.out.print("Nouveau nom (actuel : " + titulaire.getNom() + ") : ");
    titulaire.setNom(scanner.nextLine());

    System.out.print("Nouveau prénom (actuel : " + titulaire.getPrenom() + ") : ");
    titulaire.setPrenom(scanner.nextLine());

    System.out.print("Nouvel âge (actuel : " + titulaire.getAge() + ") : ");
    titulaire.setAge(Integer.parseInt(scanner.nextLine()));

    System.out.println("Choisir une nouvelle ville (actuelle : " + titulaire.getVille().getNom() + ") :");
    Ville nouvelleVille = choisirVille(scanner, villes);
    titulaire.setVille(nouvelleVille);

    Discipline nouvelleDiscipline = choisirDiscipline(scanner);
    titulaire.getDisciplines().add(nouvelleDiscipline);

    System.out.println("Détails du titulaire mis à jour avec succès !");
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

	    
	
	    
	


	    
	


