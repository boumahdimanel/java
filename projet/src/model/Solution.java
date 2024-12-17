package model;

import java.util.List;
import java.util.ArrayList;

public class Solution {
    private List<Ville> circuit;
    private double fitness;

    public Solution() {
        this.circuit = new ArrayList<>();
        this.fitness = 0.0;
    }

    public Solution(List<Ville> circuit) {
        this.circuit = new ArrayList<>(circuit);
        this.fitness = calculerFitness();
    }

    public List<Ville> getCircuit() {
        return circuit;
    }

    public void setCircuit(List<Ville> circuit) {
        this.circuit = new ArrayList<>(circuit);
        this.fitness = calculerFitness();
    }

    public double getFitness() {
        return fitness;
    }

    public double calculerFitness() {
        double distance = 0.0;
        for (int i = 0; i < circuit.size() - 1; i++) {
            distance += (circuit.get(i)).distanceTo(circuit.get(i + 1));
        }
        // Ajouter la distance de retour à la ville de départ
        distance += circuit.get(circuit.size() - 1).distanceTo(circuit.get(0));
        this.fitness = 1.0 / distance; // Plus la distance est courte, plus la fitness est élevée
        return this.fitness;
    }

    public void ajouterVille(Ville ville) {
        circuit.add(ville);
        this.fitness = calculerFitness();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Circuit: ");
        for (Ville ville : circuit) {
            sb.append(ville.getNom()).append(" -> ");
        }
        sb.append(circuit.get(0).getNom()); // Retour à la ville de départ
        sb.append("\nFitness: ").append(fitness);
        return sb.toString();
    }
}
