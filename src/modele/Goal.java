package modele;

public class Goal extends Case  {

    private Heros hero;
    private Bloc b;

    public Goal(Jeu _jeu) { super(_jeu); }
    public boolean peutEtreParcouru() { return e == null; }

}
