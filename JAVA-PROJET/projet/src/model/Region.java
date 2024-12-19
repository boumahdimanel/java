package model;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.sql.*;
import java.util.ArrayList;




public enum Region {
    AUVERGNE_RHONE_ALPES("Lyon", 69711, 8235923, new String[]{"01", "03", "07", "15", "26", "38", "42", "43", "63", "69", "73", "74"}),
    BOURGOGNE_FRANCHE_COMTE("Dijon", 47784, 2791719, new String[]{"21", "25", "39", "58", "70", "71", "89", "90"}),
    BRETAGNE("Rennes", 27208, 3453023, new String[]{"22", "29", "35", "56"}),
    CENTRE_VAL_DE_LOIRE("Orléans", 39151, 2573295, new String[]{"18", "28", "36", "37", "41", "45"}),
    CORSE("Ajaccio", 8680, 355528, new String[]{"2A", "2B"}),
    GRAND_EST("Strasbourg", 57441, 5568711, new String[]{"08", "10", "51", "52", "54", "55", "57", "67", "68", "88"}),
    HAUTS_DE_FRANCE("Lille", 31806, 5983823, new String[]{"02", "59", "60", "62", "80"}),
    ILE_DE_FRANCE("Paris", 12011, 12419961, new String[]{"75", "77", "78", "91", "92", "93", "94", "95"}),
    NORMANDIE("Rouen", 29907, 3327077, new String[]{"14", "27", "50", "61", "76"}),
    NOUVELLE_AQUITAINE("Bordeaux", 84036, 6154772, new String[]{"16", "17", "19", "23", "24", "33", "40", "47", "64", "79", "86", "87"}),
    OCCITANIE("Toulouse", 72724, 6154729, new String[]{"09", "11", "12", "30", "31", "32", "34", "46", "48", "65", "66", "81", "82"}),
    PAYS_DE_LA_LOIRE("Nantes", 32082, 3926389, new String[]{"44", "49", "53", "72", "85"}),
    PROVENCE_ALPES_COTE_DAZUR("Marseille", 31400, 5198011, new String[]{"04", "05", "06", "13", "83", "84"});

    private final String chefLieu;
    private final int superficie;
    private final int population;
    private final String[] departements;

    Region(String chefLieu, int superficie, int population, String[] departements) {
        this.chefLieu = chefLieu;
        this.superficie = superficie;
        this.population = population;
        this.departements = departements;
    }

    public String getChefLieu() {
        return chefLieu;
    }

    public int getSuperficie() {
        return superficie;
    }

    public int getPopulation() {
        return population;
    }

    public String[] getDepartements() {
        return departements;
    }
}

