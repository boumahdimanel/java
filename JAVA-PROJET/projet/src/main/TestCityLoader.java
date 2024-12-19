package main;

import java.util.List;

import model.Ville;
import service.*;


public class TestCityLoader {
    public static void main(String[] args) {
        CityDataLoader loader = new CityDataLoader();
        List<Ville> villes = loader.getToutesLesVilles();

        for (Ville ville : villes) {
            System.out.println(ville);
        }
        
       

    }
}
