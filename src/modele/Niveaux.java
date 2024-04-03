package modele;

import java.awt.*;
import java.util.ArrayList;

public class Niveaux {
    //attributes
    private ArrayList niveaux;
    private int currentLevel;

    //methods
    public Niveaux() {
        niveaux = new ArrayList();

        //Niveau 1
        Niveau n = new Niveau("Levels/level1.txt");
        currentLevel = 0;
    }

    public void addNiveau(Niveau n) {
        niveaux.add(n);
    }

    public Niveau getNiveau() {
        return (Niveau) niveaux.get(currentLevel);
    }

    public void nextLevel() {
        currentLevel++;
    }

}
