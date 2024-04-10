# Projet lifapoo : Sokoban

## Rapport des seances
1. [Seance-1](#seance-1)
2. [Seance-2](#seance-2)
3. [Seance-3](#seance-3)
4. [Travail personnel](#travail-personnel)

## Seance-1
creation d'un projet, ajout du code de base sur celui-ci et versionning sur github, lecture du code de base et identification des structures
diagramme UML du code de base.
update images affiche les bonnes images


- A implémenter : 
    - Class Goal : interaction entre Hero et Bloc

## Seance-2
Implementation de la classe Goal qui fait la fin du niveau
Implementation de la classe Niveau qui stocke les infos d'un niveau

- A implémenter :
  - Class Niveaux : gestion multi-niveau
  - Class Jeu : gestion de fin de partie (bloc sur goal)
  - Class VueController : gestion des touches d'action

## Seance-3
Implementation de la classe Niveaux qui stocke les niveaux,
test de lecture de fichier pour charger un niveau
Implementation de la classe tool qui lit un fichier pur generer un tableau

- A implémenter :
    - Gestion d'un menu, avec progression des levels
    - Gestion des scores, avec les scores par niveau 
    - Gestion de fin de partie 
    - Gestion des 'autres' blocs basiques (ice, bloc basique, rail, etc)
    - Gestion des 'autres' blocs speciaux (teleporteur, rail, trou, magnet, etc)


## Travail personnel
- Gaspard :
  - Ecran de selection de niveau (avec nommage des niveaux)
  - Gestion des scores avec affichage dynamique
  - Gestion des fichiers de niveaux chargés par l'application (classe Tool)