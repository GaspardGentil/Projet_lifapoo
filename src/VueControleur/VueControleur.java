package VueControleur;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;


import modele.*;


/** Cette classe a deux fonctions :
 *  (1) Vue : proposer une représentation graphique de l'application (cases graphiques, etc.)
 *  (2) Controleur : écouter les évènements clavier et déclencher le traitement adapté sur le modèle (flèches direction Pacman, etc.))
 *
 */
public class VueControleur extends JFrame implements Observer {
    private Jeu jeu; // référence sur une classe de modèle : permet d'accéder aux données du modèle pour le rafraichissement, permet de communiquer les actions clavier (ou souris)
    //on ajoute une liste de niveaux a charger dans la vue
    private Niveaux niveaux = new Niveaux();

    private int sizeX; // taille de la grille affichée
    private int sizeY;

    // icones affichées dans la grille
    private ImageIcon icoHero;
    private ImageIcon icoVide;
    private ImageIcon icoMur;
    private ImageIcon icoBloc;
    private ImageIcon icoBlocGoal;
    private ImageIcon icoGoal;
    private ImageIcon icoIceBox;
    private ImageIcon icoWildBox;


    private JLabel[][] tabJLabel; // cases graphique (au moment du rafraichissement, chaque case va être associée à une icône, suivant ce qui est présent dans le modèle)


    public VueControleur(Jeu _jeu) {
        sizeX = _jeu.n.getSIZE_X();
        sizeY = _jeu.n.getSIZE_Y();
        jeu = _jeu;
        jeu.n = niveaux.getNiveau();

        chargerLesIcones();
        placerLesComposantsGraphiques();
        ajouterEcouteurClavier();

        jeu.addObserver(this);

        mettreAJourAffichage();

    }


    private void ajouterEcouteurClavier() {
        addKeyListener(new KeyAdapter() { // new KeyAdapter() { ... } est une instance de classe anonyme, il s'agit d'un objet qui correspond au controleur dans MVC
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {  // on regarde quelle touche a été pressée

                    case KeyEvent.VK_LEFT : jeu.deplacerHeros(Direction.gauche); break;
                    case KeyEvent.VK_RIGHT : jeu.deplacerHeros(Direction.droite); break;
                    case KeyEvent.VK_DOWN : jeu.deplacerHeros(Direction.bas); break;
                    case KeyEvent.VK_UP : jeu.deplacerHeros(Direction.haut); break;

                    case KeyEvent.VK_R : jeu.resetNiveau(); break;
                    case KeyEvent.VK_N : niveaux.nextLevel();
                                        jeu.n = niveaux.getNiveau();
                                        jeu.resetNiveau();
                        break;
                    case KeyEvent.VK_B : niveaux.previousLevel();
                                        jeu.n = niveaux.getNiveau();
                                        jeu.resetNiveau();
                        break;
                    case KeyEvent.VK_ESCAPE : System.exit(0); break;

                    /*
                    Ajouter :
                    - U for undo. (à voir dans Jeu pour sauvegarder la position actuelle)
                    - R for reset. (reset toutes les entitées)
                    - Esc to close.
                    - E for swapping Players (Modern puzzles only)
                     */
                }
            }
        });
    }


    private void chargerLesIcones() {
        icoHero = chargerIcone("Images/Player.png");
        icoVide = chargerIcone("Images/Vide.png");
        icoMur = chargerIcone("Images/Mur.png");
        icoBloc = chargerIcone("Images/Box.png");
        icoBlocGoal = chargerIcone("Images/BoxGoal.png");
        icoGoal = chargerIcone("Images/Goal.png");
        icoIceBox = chargerIcone("Images/IceBox.png");
        icoWildBox = chargerIcone("Images/WildBox.png");
    }

    private ImageIcon chargerIcone(String urlIcone) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(urlIcone));
        } catch (IOException ex) {
            Logger.getLogger(VueControleur.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return new ImageIcon(image);
    }

    private void placerLesComposantsGraphiques() {
        setTitle("Sokoban");
        setSize(350, 370);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre

        //ajout d'un ecran de selection de niveau
        String[] niveauxNames = niveaux.getNiveauxNames();
        String niveauChoisi = (String) JOptionPane.showInputDialog(this, "Choisissez un niveau", "Niveau",
                                        JOptionPane.QUESTION_MESSAGE, null, niveauxNames, niveauxNames[0]);
        for (int i = 0; i < niveauxNames.length; i++) {
            if (niveauxNames[i].equals(niveauChoisi)) {
                niveaux.setCurrentLevel(i);
                jeu.n = niveaux.getNiveau();
                jeu.resetNiveau();
            }
        }

        JComponent grilleJLabels = new JPanel(new GridLayout(sizeX, sizeY)); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille

        tabJLabel = new JLabel[sizeX][sizeY];

        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                JLabel jlab = new JLabel();
                tabJLabel[x][y] = jlab; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                grilleJLabels.add(jlab);
            }
        }
        add(grilleJLabels);
    }

    
    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
    private void mettreAJourAffichage() {

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {

                Case c = jeu.getGrille()[x][y];

                if (c != null) {

                    Entite e = c.getEntite();

                    if (e!= null) {
                        if (c.getEntite() instanceof Heros) {
                            tabJLabel[x][y].setIcon(icoHero);
                        } else if (c.getEntite() instanceof Bloc) {
                            tabJLabel[x][y].setIcon(icoBloc);
                            // On vérifie si la case contient un Bloc ou BlocGoal
                            Bloc b = (Bloc) e;
                            if (jeu.finPartie()) {
                                // Si tous les Bloc sont arrivés aux Goal
                                tabJLabel[x][y].setIcon(icoBlocGoal);
                            } else if (b.estSurGoal()) {
                                // Si un Bloc est arrivé sur Goal
                                tabJLabel[x][y].setIcon(icoBlocGoal);
                            } else {
                                tabJLabel[x][y].setIcon(icoBloc);
                            }

                        }
                    } else {
                        if (jeu.getGrille()[x][y] instanceof Mur) {
                            tabJLabel[x][y].setIcon(icoMur);
                        } else if (jeu.getGrille()[x][y] instanceof Vide) {

                            tabJLabel[x][y].setIcon(icoVide);
                        } else if (jeu.getGrille()[x][y] instanceof Goal) {
                            tabJLabel[x][y].setIcon(icoGoal);
                        }
                    }

                }

            }
        }
        //mise à jour du titre de la fenêtre et du score
        setTitle("Sokoban - " + jeu.n.getName() + " - Score : " + jeu.n.getCurrentScore() + " / " + jeu.n.getBestScore());
        //si on fini le niveau courant, passe au suivant
        if(jeu.finPartie()){
            JOptionPane.showMessageDialog(this, "Niveau terminé !  score : " + jeu.n.getCurrentScore() + " / " + jeu.n.getBestScore());
            niveaux.nextLevel();
            jeu.n = niveaux.getNiveau();
            jeu.resetNiveau();
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        mettreAJourAffichage();
        /*

        // récupérer le processus graphique pour rafraichir
        // (normalement, à l'inverse, a l'appel du modèle depuis le contrôleur, utiliser un autre processus, voir classe Executor)


        SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mettreAJourAffichage();
                    }
                }); 
        */

    }
}
