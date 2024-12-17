package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Solution;
import model.Ville;

class SolutionTest {

	

	    @Test
	    void testCalculerFitness() {
	        Ville paris = new Ville("Paris", "75000", 2000000, 105.4, "75", 2.3522, 48.8566);
	        Ville lyon = new Ville("Lyon", "69000", 500000, 47.87, "69", 4.8357, 45.7640);
	        Ville marseille = new Ville("Marseille", "13000", 800000, 240.62, "13", 5.3698, 43.2965);

	        List<Ville> circuit = Arrays.asList(paris, lyon, marseille);
	        Solution solution = new Solution(circuit);

	        double expectedFitness = 1.0 / (paris.distanceTo(lyon) + lyon.distanceTo(marseille) + marseille.distanceTo(paris));
	        assertEquals(expectedFitness, solution.getFitness(), 0.0001);
	    }

	    @Test
	    void testAjouterVille() {
	        Solution solution = new Solution();
	        Ville paris = new Ville("Paris", "75000", 2000000, 105.4, "75", 2.3522, 48.8566);
	        
	        solution.ajouterVille(paris);
	        assertEquals(1, solution.getCircuit().size());
	        assertEquals(paris, solution.getCircuit().get(0));
	    }
}



