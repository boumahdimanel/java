package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Crossover {
    public static List<Solution> cycleCrossover(Solution parent1, Solution parent2) {
        List<Ville> circuit1 = new ArrayList<>(parent1.getCircuit());
        List<Ville> circuit2 = new ArrayList<>(parent2.getCircuit());
        int taille = circuit1.size();
        boolean[] visite = new boolean[taille];
        int index = 0;

        while (!visite[index]) {
            visite[index] = true;
            Ville ville = circuit2.get(index);
            index = circuit1.indexOf(ville);
        }

        for (int i = 0; i < taille; i++) {
            if (!visite[i]) {
                Ville temp = circuit1.get(i);
                circuit1.set(i, circuit2.get(i));
                circuit2.set(i, temp);
            }
        }

        return Arrays.asList(new Solution(circuit1), new Solution(circuit2));
    }
}



