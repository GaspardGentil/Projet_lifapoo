package modele;

import java.awt.*;
import java.util.ArrayList;

import static util.Tool.fileToTab;

public class Niveau {
    //attributes
    private String name;

    //dimensions et caractéristiques du niveau
    private int SIZE_X;
    private int SIZE_Y;
    private Point herosPosition;
    private ArrayList<Point> blocsPosition;
    private ArrayList<Point> goalPosition;
    private ArrayList<Point> wallsPosition;

    //scores et meilleur score
    private int bestScore;
    private int currentScore;

    //methods

    //constructeur par defaut, il est en theorique inutile
    public Niveau(int _SIZE_X, int _SIZE_Y, Point _herosPosition, ArrayList<Point> _blocsPosition, ArrayList<Point> _goalPosition, ArrayList<Point> _wallsPosition) {
        name = "Niveau";
        SIZE_X = _SIZE_X;
        SIZE_Y = _SIZE_Y;
        herosPosition = _herosPosition;
        blocsPosition = _blocsPosition;
        goalPosition = _goalPosition;
        wallsPosition = _wallsPosition;
        bestScore = 0;
        currentScore = 0;
    }

    //constructeur par fichier, c'est celui qui est utilisé
    public Niveau(String filename){
        int[][] tab = fileToTab(filename);
        name = filename;
        InitWithTab(tab);
    }

    //initialisation du niveau avec un tableau, utilisé par le constructeur par fichier
    public void InitWithTab(int[][] tab){
        SIZE_X = tab.length;
        SIZE_Y = tab[0].length;
        blocsPosition = new ArrayList<>();
        goalPosition = new ArrayList<>();
        wallsPosition = new ArrayList<>();

        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                if (tab[i][j] == 1) {
                    herosPosition = new Point(j, i);
                }
                if (tab[i][j] == 2) {
                    blocsPosition.add(new Point(j, i));
                }
                if (tab[i][j] == 3) {
                    goalPosition.add(new Point(j, i));
                }
                if (tab[i][j] == 4) {
                    wallsPosition.add(new Point(j, i));
                }
            }
        }
        bestScore = 0;
        currentScore = 0;
    }

    //accesseurs et mutateurs
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSIZE_X() {
        return SIZE_X;
    }

    public int getSIZE_Y() {
        return SIZE_Y;
    }

    public Point getHerosPosition() {
        return herosPosition;
    }

    public ArrayList<Point> getBlocsPosition() {
        return blocsPosition;
    }

    public ArrayList<Point> getGoalPosition() {
        return goalPosition;
    }

    public ArrayList<Point> getWallsPosition() {
        return wallsPosition;
    }

    public int getBestScore() {
        return bestScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void resetCurrentScore() {
        currentScore = 0;
    }

    public void setBestScore() {
        if (currentScore < bestScore || bestScore == 0) {
            bestScore = currentScore;
        }
    }

    //traitements sur les scores
    public void incrementCurrentScore() {
        currentScore++;
    }

    public void decrementCurrentScore() {
        currentScore--;
    }
}
