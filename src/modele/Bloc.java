package modele;

public class Bloc extends Entite {


    private boolean surGoal;

    public Bloc(Jeu _jeu, Case c) {
        super(_jeu, c);
    }

    public boolean pousser(Direction d) {
        return jeu.deplacerEntite(this, d);
    }

    public boolean estSurGoal() {
        return surGoal;
    }

    public void setSurGoal(boolean surGoal) {
        this.surGoal = surGoal;
    }

}
