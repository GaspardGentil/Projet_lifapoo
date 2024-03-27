package modele;

import java.awt.*;

public class Niveau {
    //attributes

    private int SIZE_X;
    private int SIZE_Y;
    private Point herosPosition;
    private Point[] blocsPosition;
    private Point[] goalPosition;

    //methods
    public Niveau(int _SIZE_X, int _SIZE_Y, Point _herosPosition, Point[] _blocsPosition, Point[] _goalPosition) {
        SIZE_X = _SIZE_X;
        SIZE_Y = _SIZE_Y;
        herosPosition = _herosPosition;
        blocsPosition = _blocsPosition;
        goalPosition = _goalPosition;
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

    public Point[] getBlocsPosition() {
        return blocsPosition;
    }

    public Point[] getGoalPosition() {
        return goalPosition;
    }

    public void setSIZE_X(int _SIZE_X) {
        SIZE_X = _SIZE_X;
    }
}
