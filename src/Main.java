
import VueControleur.VueControleur;
import modele.Jeu;
import modele.Niveau;

import java.awt.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Main {
    public static void main(String[] args) {
        Point heros = new Point(2,2 );
        Point[] blocs = new Point[]{new Point(3, 2)};
        Point[] goals = new Point[]{new Point(5, 8)};
        Niveau n = new Niveau(20, 10,heros, blocs, goals);
        Jeu jeu = new Jeu(n);

        VueControleur vc = new VueControleur(jeu);
        vc.setVisible(true);

    }
}
