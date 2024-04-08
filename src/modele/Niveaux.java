package modele;

import java.io.File;
import java.util.ArrayList;

public class Niveaux {
    //attributes
    private ArrayList<Niveau> niveaux;
    private int currentLevel;

    //methods
    public Niveaux() {
        niveaux = new ArrayList<>();

        File dir = new File("Levels");
        File[] files = dir.listFiles();
        for (File f : files) {
            if (f.isFile()) {
                addNiveau(new Niveau(f.getPath()));
            }
        }
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

    public void previousLevel() {
        currentLevel--;
    }

}
