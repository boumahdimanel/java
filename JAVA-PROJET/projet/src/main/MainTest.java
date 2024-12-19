package main;

import java.util.ArrayList;
import java.util.List;

import model.AlgorithmeGenetique;
import model.Circuit;
import model.Ville;
import service.CityDataLoader;

public class MainTest {
	
	public static void main(String[] args) {
		CityDataLoader loader = new CityDataLoader();
  	  
	    List<Ville> villes = loader.getToutesLesVilles();

	    AlgorithmeGenetique algo = new AlgorithmeGenetique(100, 0.01, 0.1, villes);
	    Circuit meilleurCircuit = algo.resoudre();

	    System.out.println("Meilleur circuit trouvé :");
	    for (Ville ville : meilleurCircuit.getVilles()) {
	        System.out.println(ville.getNom());
	    }
	    System.out.println("Distancemb totale : " + meilleurCircuit.getDistance() + " km");
	}


}
