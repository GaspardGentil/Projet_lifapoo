package util;


import java.io.File;

public class Tool {
    //methods
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

    private static int convertToInt(String str) {
        switch (str) {
            case "h":
                return 1;
            case "b":
                return 2;
            case "g":
                return 3;
            case "w":
                return 4;
            default:
                return 0; // Valeur par défaut pour les autres cas
        }
    }
}
