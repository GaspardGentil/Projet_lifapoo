package util;

import java.io.BufferedReader;
import java.io.FileReader;

public class Tool {
    //methods
    public static int[][] fileToTab(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();

            String matrixString = sb.toString();
            String[] lines = matrixString.split("\n");

            int[][] tab = new int[lines.length][lines[0].split(" ").length];
            for (int i = 0; i < lines.length; i++) {
                String[] columns = lines[i].split(" ");
                for (int j = 0; j < columns.length; j++) {
                    switch (columns[j]) {
                        case "h":
                            tab[i][j] = 1;
                            break;
                        case "b":
                            tab[i][j] = 2;
                            break;
                        case "g":
                            tab[i][j] = 3;
                            break;
                        case "w":
                            tab[i][j] = 4;
                            break;
                        default:
                            break;
                    }
                }
            }
            return tab;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
