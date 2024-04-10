/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import java.awt.Point;
import java.util.HashMap;
import java.util.Observable;


public class Jeu extends Observable {

    public static final int SIZE_X = 20;
    public static final int SIZE_Y = 10;

    public Niveau n;

    private Heros heros;

    private HashMap<Case, Point> map = new  HashMap<Case, Point>(); // permet de récupérer la position d'une case à partir de sa référence
    private Case[][] grilleEntites = new Case[SIZE_X][SIZE_Y]; // permet de récupérer une case à partir de ses coordonnées


    public Jeu(Niveau n) {
        this.n = n;
        initialisationNiveau();
    }


    
    public Case[][] getGrille() {
        return grilleEntites;
    }
    
    public Heros getHeros() {
        return heros;
    }

    public void deplacerHeros(Direction d) {
        n.incrementCurrentScore();
        if(!heros.avancerDirectionChoisie(d)){
            n.decrementCurrentScore();
        }
        setChanged();
        notifyObservers();
    }



    private void initialisationNiveau() {

        // On vide la grille
        for (int x = 0; x < n.getSIZE_X(); x++) {
            for (int y = 0; y < n.getSIZE_Y(); y++) {
                addCase(new Vide(this), x, y);
            }
        }

        // On ajoute les murs
        for (Point p : n.getWallsPosition()) {
            addCase(new Mur(this), p.x, p.y);
        }

        // On ajoute les blocs
        for (Point p : n.getBlocsPosition()) {
            new Bloc(this, grilleEntites[p.x][p.y]);
        }

        // On ajoute les goals
        for (Point p : n.getGoalPosition()) {
            addCase(new Goal(this), p.x, p.y);
        }

        // On ajoute le héros
        heros = new Heros(this, grilleEntites[n.getHerosPosition().x][n.getHerosPosition().y]);

    }

    public void resetNiveau() {
        initialisationNiveau();
        n.resetCurrentScore();
        setChanged();
        notifyObservers();
    }

    private void addCase(Case e, int x, int y) {
        grilleEntites[x][y] = e;
        map.put(e, new Point(x, y));
    }

    public boolean finPartie() {

        for (int x = 0; x < SIZE_X; x++) {
            for (int y = 0; y < SIZE_Y; y++) {
                Case c = grilleEntites[x][y];
                // On vérifie si la case est un Goal
                if (c instanceof Goal) {
                    Entite e = c.getEntite();
                    // Si la case où se trouve Goal ne contient pas Bloc alors ce n'est pas fini
                    if (!(e instanceof Bloc)) {
                        return false;
                    }
                }
            }
        }
        System.out.println("Partie terminée !");
        System.out.println("Score actuel : " + n.getCurrentScore());
        n.setBestScore();
        System.out.println("Meilleur score : " + n.getBestScore());
        return true;
        //heros = new Heros(this, grilleEntites[1][1]);
    }
    

    
    /** Si le déplacement de l'entité est autorisé (pas de mur ou autre entité), il est réalisé
     * Sinon, rien n'est fait.
     */
    public boolean deplacerEntite(Entite e, Direction d) {
        boolean retour = true;

        Point pCourant = map.get(e.getCase());

        Point pCible = calculerPointCible(pCourant, d);

        if (contenuDansGrille(pCible)) {
            Entite eCible = caseALaPosition(pCible).getEntite();

            // Vérification si plusieurs Bloc sont collés => Ne peut être poussé
            if (eCible instanceof Bloc) {
                // Si la cible est un Bloc :
                Point pApresCible = calculerPointCible(pCible, d);

                if (contenuDansGrille(pApresCible)) {
                    Case caseApresCible = caseALaPosition(pApresCible);
                    // Si la cible après pCible est un Bloc (donc + de 1 Bloc) alors retour = false et le Hero ne pourra pas pousser
                    if (caseApresCible.contientBloc()) {
                        System.out.println("Trop de Bloc à pousser !");
                        retour = false;
                    }
                }
            }
            if (retour && eCible != null) {
                eCible.pousser(d);
            }
            if (retour && caseALaPosition(pCible).peutEtreParcouru()) {

                if (caseALaPosition(pCible) instanceof Vide) {
                    e.getCase().quitterLaCase();
                    caseALaPosition(pCible).entrerSurLaCase(e);

                } else if (caseALaPosition(pCible) instanceof Goal) {

                    if (e instanceof Bloc) {

                        System.out.println("Un Bloc a atteint Goal !");
                        ((Bloc) e).setSurGoal(true);
                        e.getCase().quitterLaCase();
                        caseALaPosition(pCible).entrerSurLaCase(e);

                    } else if (e instanceof Heros) {
                        System.out.println("Hero sur Goal");

                    } else {
                        System.out.println("L'entité n'est pas un Bloc !");
                    }
                } else if (e instanceof Bloc) {
                    System.out.println("Un Bloc n'est plus sur Goal !");
                    ((Bloc) e).setSurGoal(false);
                }
                e.getCase().quitterLaCase();
                caseALaPosition(pCible).entrerSurLaCase(e);
            } else {
                retour = false;
            }
        } else {
            retour = false;
        }
        return retour;
    }


    private Point calculerPointCible(Point pCourant, Direction d) {
        Point pCible = null;
        
        switch(d) {
            case haut: pCible = new Point(pCourant.x, pCourant.y - 1); break;
            case bas : pCible = new Point(pCourant.x, pCourant.y + 1); break;
            case gauche : pCible = new Point(pCourant.x - 1, pCourant.y); break;
            case droite : pCible = new Point(pCourant.x + 1, pCourant.y); break;     
            
        }
        
        return pCible;
    }
    

    
    /** Indique si p est contenu dans la grille
     */
    private boolean contenuDansGrille(Point p) {
        return p.x >= 0 && p.x < SIZE_X && p.y >= 0 && p.y < SIZE_Y;
    }
    
    private Case caseALaPosition(Point p) {
        Case retour = null;
        
        if (contenuDansGrille(p)) {
            retour = grilleEntites[p.x][p.y];
        }
        
        return retour;
    }

}
