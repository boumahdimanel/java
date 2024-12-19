package model;

import java.util.ArrayList;

import java.util.List;



import java.util.Comparator;

import java.util.stream.Collectors;

public class Population {
    private List<Circuit> circuits;

    public Population(int taille) {
        circuits = new ArrayList<>(taille);
    }

    public void ajouterCircuit(Circuit circuit) {
        circuits.add(circuit);
    }

    public Circuit getCircuit(int index) {
        return circuits.get(index);
    }

    public Circuit getMeilleurCircuit() {
        return circuits.stream().max(Comparator.comparingDouble(Circuit::getFitness)).orElse(null);
    }

    public List<Circuit> getMeilleursCircuits(int nombre) {
        return circuits.stream()
                .sorted(Comparator.comparingDouble(Circuit::getFitness).reversed())
                .limit(nombre)
                .collect(Collectors.toList());
    }

    public void remplacerPires(List<Circuit> nouveauxCircuits) {
        circuits.sort(Comparator.comparingDouble(Circuit::getFitness));
        for (int i = 0; i < nouveauxCircuits.size(); i++) {
            circuits.set(i, nouveauxCircuits.get(i));
        }
    }

    public double getFitnessTotale() {
        return circuits.stream().mapToDouble(Circuit::getFitness).sum();
    }

    public List<Circuit> getCircuits() {
        return circuits;
    }

    public int getTaille() {
        return circuits.size();
    }
}
