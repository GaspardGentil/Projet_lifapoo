package modele;

public class Goal extends Case  {

    public Goal(Jeu _jeu) { super(_jeu); }

    public boolean peutEtreParcouru() { return e == null; }
}
