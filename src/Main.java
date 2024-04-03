
import VueControleur.VueControleur;
import modele.Jeu;
import modele.Niveau;
import modele.Niveaux;

import static util.Tool.fileToTab;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Main {
    public static void main(String[] args) {
        Niveaux niveaux = new Niveaux();
        Niveau n = niveaux.getNiveau();
        System.out.println(n.getSIZE_X());
        System.out.println(n.getSIZE_Y());
        System.out.println(n.getHerosPosition());
        System.out.println(n.getBlocsPosition());
        System.out.println(n.getGoalPosition());
        System.out.println(n.getWallsPosition());
        System.out.println(n.getBestScore());
        System.out.println(n.getBestScore());
        Jeu jeu = new Jeu(n);

        VueControleur vc = new VueControleur(jeu);
        vc.setVisible(true);
    }
}
