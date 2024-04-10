package util;


import java.io.File;

//cette classe contient des methodes statiques pour convertir des fichiers en tableau et vice versa
public class Tool {
    //methods

    //convertit un fichier en tableau d'entiers (methode "aller")
    public static int[][] fileToTab(String filename){
        File file = new File(filename);
        int[][] tab = new int[0][0];
        try {
            java.util.Scanner sc = new java.util.Scanner(file);
            int i = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] str = line.split(" ");
                if (i == 0) {
                    tab = new int[str.length][str.length];
                }
                for (int j = 0; j < str.length; j++) {
                    tab[i][j] = convertToInt(str[j]);
                }
                i++;
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Fichier non trouvé");
        }
        return tab;
    }

    //convertit un tableau d'entiers en fichier (methode "retour")
    public static void tabToFile(int[][] tab, String filename){
        try {
            java.io.PrintWriter writer = new java.io.PrintWriter(filename);
            for (int i = 0; i < tab.length; i++) {
                for (int j = 0; j < tab[i].length; j++) {
                    writer.print(convertToString(tab[i][j]) + " ");
                }
                writer.println();
            }
            writer.close();
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Fichier non trouvé");
        }
    }

    //convertit un string en entier (utile pour la conversion de fichier en tableau)
    private static int convertToInt(String str) {
        int result;
        switch (str) {
            case "h":
                result = 1;
                break;
            case "b":
                result = 2;
                break;
            case "g":
                result = 3;
                break;
            case "w":
                result = 4;
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }

    //convertit un entier en string (utile pour la conversion de tableau en fichier)
    private static String convertToString(int i) {
        String result;
        switch (i) {
            case 1:
                result = "h";
                break;
            case 2:
                result = "b";
                break;
            case 3:
                result = "g";
                break;
            case 4:
                result = "w";
                break;
            default:
                result = "o";
                break;
        }
        return result;
    }
}
