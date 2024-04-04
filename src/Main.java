
import VueControleur.VueControleur;
import modele.Jeu;
import modele.Niveaux;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Main {
    public static void main(String[] args) {
        Niveaux niveaux = new Niveaux();
        niveaux.nextLevel();
        niveaux.nextLevel();
        Jeu jeu = new Jeu(niveaux.getNiveau());

        VueControleur vc = new VueControleur(jeu);
        vc.setVisible(true);
    }
}
