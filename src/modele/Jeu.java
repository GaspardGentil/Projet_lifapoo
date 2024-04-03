/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import java.awt.Point;
import java.awt.desktop.SystemEventListener;
import java.util.HashMap;
import java.util.Observable;


public class Jeu extends Observable {

    //public static final int SIZE_X = 20;
    //public static final int SIZE_Y = 10;

    public Niveau n;


    private Heros heros;

    private HashMap<Case, Point> map = new  HashMap<Case, Point>(); // permet de récupérer la position d'une case à partir de sa référence
    private Case[][] grilleEntites = new Case[n.getSIZE_X()][n.getSIZE_Y()]; // permet de récupérer une case à partir de ses coordonnées


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
        heros.avancerDirectionChoisie(d);
        n.incrementCurrentScore();
        System.out.println("Score : " + n.getCurrentScore());
        setChanged();
        notifyObservers();
    }



    
    private void initialisationNiveau() {

        /*
        // murs extérieurs horizontaux
        for (int x = 0; x < n.getSIZE_X(); x++) {
            addCase(new Mur(this), x, 0);
            addCase(new Mur(this), x, 9);
        }

        // murs extérieurs verticaux
        for (int y = 1; y < n.getSIZE_Y(); y++) {
            addCase(new Mur(this), 0, y);
            addCase(new Mur(this), 19, y);
        }
        */

        for (int x = 1; x < n.getSIZE_X()-1; x++) {
            for (int y = 1; y < n.getSIZE_Y()-1; y++) {
                addCase(new Vide(this), x, y);
            }

        }

        for (Point p : n.getWallsPosition()) {
            addCase(new Mur(this), p.x, p.y);
        }

        for (Point p : n.getBlocsPosition()) {
            Bloc b = new Bloc(this, grilleEntites[p.x][p.y]);
        }

        for (Point p : n.getGoalPosition()) {
            addCase(new Goal(this), p.x, p.y);
        }

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


    private void removeCase(Case e, int x, int y) {
        grilleEntites[x][y] = null;
        map.remove(e, new Point(x, y));
    }

    public void finPartie() {

        for (int x = 0; x < 20; x++) {
            for (int y = 0; y < 10; y++) {
                addCase(new Vide(this), x, y);
            }
        }
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
            if (eCible != null) {
                eCible.pousser(d);
            }

            // si la case est libérée
            if (caseALaPosition(pCible).peutEtreParcouru()) {
                if (caseALaPosition(pCible) instanceof Vide) {  // Si la case cible est Vide
                    e.getCase().quitterLaCase(); // L'entité quitte la case actuelle
                    caseALaPosition(pCible).entrerSurLaCase(e); // L'entité va sur la case suivante (Cible)
                }

                if (caseALaPosition(pCible) instanceof Goal) {  // Si la case cible est Goal
                    e.getCase().quitterLaCase();
                    caseALaPosition(pCible).entrerSurLaCase(e);
                    // Bloc arrive sur le goal : Appel d'une méthode pour clear la grille
                    //removeCase(new Goal(this), pCible.x, pCible.y);
                    finPartie();
                    e.setCase(grilleEntites[1][1]);
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
        return p.x >= 0 && p.x < n.getSIZE_X() && p.y >= 0 && p.y < n.getSIZE_Y();
    }
    
    private Case caseALaPosition(Point p) {
        Case retour = null;
        
        if (contenuDansGrille(p)) {
            retour = grilleEntites[p.x][p.y];
        }
        
        return retour;
    }

}
