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
                Niveau n = new Niveau(f.getPath());
                n.setName(f.getName());
                addNiveau(n);
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
        if (currentLevel < niveaux.size() - 1) {
            currentLevel++;
        }
    }

    public void previousLevel() {
        if (currentLevel > 0) {
            currentLevel--;
        }
    }

    public void setCurrentLevel(int i) {
        if (i >= 0 && i < niveaux.size()) {
            currentLevel = i;
        }
    }

    public String[] getNiveauxNames() {
        String[] names = new String[niveaux.size()];
        for (int i = 0; i < niveaux.size(); i++) {
            names[i] = niveaux.get(i).getName();
        }
        return names;
    }



}
