package model;

import java.util.List;

public class AlgorithmeGenetique {
    private int taillePopulation;
    private double probMutation;
    private double tauxElite;
    private List<Ville> villes;
    private Population population;

    public AlgorithmeGenetique(int taillePopulation, double probMutation, double tauxElite, List<Ville> villes) {
        this.taillePopulation = taillePopulation;
        this.probMutation = probMutation;
        this.tauxElite = tauxElite;
        this.villes = villes;
    }

    private void initialiserPopulation() {
        population = new Population(taillePopulation);
        for (int i = 0; i < taillePopulation; i++) {
            Circuit circuit = new Circuit(villes);
            circuit.melangerVilles();
            population.ajouterCircuit(circuit);
        }
    }

    private Circuit selectionParent() {
        double totalFitness = population.getFitnessTotale();
        double valeurAleatoire = Math.random() * totalFitness;
        double sommeFitness = 0;
        for (Circuit circuit : population.getCircuits()) {
            sommeFitness += circuit.getFitness();
            if (sommeFitness >= valeurAleatoire) {
                return circuit;
            }
        }
        return population.getCircuits().get(0);
    }

    private Circuit crossover(Circuit parent1, Circuit parent2) {
        Circuit enfant = new Circuit(villes);
        int startPos = (int) (Math.random() * parent1.getTaille());
        int endPos = (int) (Math.random() * parent1.getTaille());

        for (int i = 0; i < enfant.getTaille(); i++) {
            if (startPos < endPos && i > startPos && i < endPos) {
                enfant.setVille(i, parent1.getVille(i));
            } else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    enfant.setVille(i, parent1.getVille(i));
                }
            }
        }

        for (int i = 0; i < parent2.getTaille(); i++) {
            if (!enfant.contientVille(parent2.getVille(i))) {
                for (int j = 0; j < enfant.getTaille(); j++) {
                    if (enfant.getVille(j) == null) {
                        enfant.setVille(j, parent2.getVille(i));
                        break;
                    }
                }
            }
        }
        return enfant;
    }

    private void muter(Circuit circuit) {
        for (int pos1 = 0; pos1 < circuit.getTaille(); pos1++) {
            if (Math.random() < probMutation) {
                int pos2 = (int) (circuit.getTaille() * Math.random());
                Ville ville1 = circuit.getVille(pos1);
                Ville ville2 = circuit.getVille(pos2);
                circuit.setVille(pos2, ville1);
                circuit.setVille(pos1, ville2);
            }
        }
    }

    public Circuit resoudre() {
        initialiserPopulation();
        
        int generation = 0;
        int maxGenerations = 1000;
        
        while (generation < maxGenerations) {
            Population nouvellePopulation = new Population(taillePopulation);
            
            for (int i = 0; i < taillePopulation / 2; i++) {
                Circuit parent1 = selectionParent();
                Circuit parent2 = selectionParent();
                Circuit enfant1 = crossover(parent1, parent2);
                Circuit enfant2 = crossover(parent2, parent1);
                
                if (Math.random() < probMutation) muter(enfant1);
                if (Math.random() < probMutation) muter(enfant2);
                
                nouvellePopulation.ajouterCircuit(enfant1);
                nouvellePopulation.ajouterCircuit(enfant2);
            }
            
            int nbElites = (int) (taillePopulation * tauxElite);
            List<Circuit> elites = population.getMeilleursCircuits(nbElites);
            nouvellePopulation.remplacerPires(elites);
            
            population = nouvellePopulation;
            generation++;
        }
        
        return population.getMeilleurCircuit();
    }
}
