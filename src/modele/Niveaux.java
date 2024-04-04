package modele;

import java.util.ArrayList;

public class Niveaux {
    //attributes
    private ArrayList<Niveau> niveaux;
    private int currentLevel;

    //methods
    public Niveaux() {
        niveaux = new ArrayList<>();

        //Niveau 1
        Niveau n = new Niveau("Levels/level1.txt");
        addNiveau(n);
        Niveau n2 = new Niveau("Levels/level2.txt");
        addNiveau(n2);
        Niveau n3 = new Niveau("Levels/level3.txt");
        addNiveau(n3);
        currentLevel = 0;
    }

    public void addNiveau(Niveau n) {
        niveaux.add(n);
    }

    public Niveau getNiveau() {
        return niveaux.get(currentLevel);
    }

    public void nextLevel() {
        currentLevel++;
    }

}
