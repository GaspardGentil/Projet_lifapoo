package modele;

import java.awt.*;
import java.util.ArrayList;

import static util.Tool.fileToTab;

public class Niveau {
    //attributes

    private int SIZE_X;
    private int SIZE_Y;
    private Point herosPosition;
    private ArrayList<Point> blocsPosition;
    private ArrayList<Point> goalPosition;
    private ArrayList<Point> wallsPosition;

    private int bestScore;
    private int currentScore;

    //methods
    public Niveau(int _SIZE_X, int _SIZE_Y, Point _herosPosition, ArrayList<Point> _blocsPosition, ArrayList<Point> _goalPosition, ArrayList<Point> _wallsPosition) {
        SIZE_X = _SIZE_X;
        SIZE_Y = _SIZE_Y;
        herosPosition = _herosPosition;
        blocsPosition = _blocsPosition;
        goalPosition = _goalPosition;
        wallsPosition = _wallsPosition;
        bestScore = 0;
        currentScore = 0;
    }

    public Niveau(String filename){
        int[][] tab = fileToTab(filename);
        InitWithTab(tab);
    }


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
        if (currentScore < bestScore && bestScore == 0) {
            bestScore = currentScore;
        }
    }

    public void incrementCurrentScore() {
        currentScore++;
    }
}
