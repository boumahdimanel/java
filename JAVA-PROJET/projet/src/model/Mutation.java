package model;

import java.util.Collections;
import java.util.List;

public class Mutation {
    public static void permuter(Solution solution) {
        List<Ville> circuit = solution.getCircuit();
        int taille = circuit.size();
        int index1 = (int) (Math.random() * taille);
        int index2 = (int) (Math.random() * taille);

        Collections.swap(circuit, index1, index2);
        solution.setCircuit(circuit);
    }
}