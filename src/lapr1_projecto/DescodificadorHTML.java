package LAPR1_Projecto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DescodificadorHTML {

    public static String eqremov(int line, int ex) throws FileNotFoundException {

        Scanner fout = new Scanner(new File(ex + ".html"));
        String linha = "";

        for (int i = 1; i < line; i++) {
            fout.nextLine();
        }
        linha = fout.nextLine();
        fout.close();

        linha = linha.replaceAll("<br>", "");
        return linha;
    }

    public static float prem(int line, int ex, int pos) throws FileNotFoundException {

        Scanner fout = new Scanner(new File(ex + ".html"));
        String linha = "";
        String[] temp;
        float pont = 0;

        for (int i = 1; i < line; i++) {
            fout.nextLine();
        }
        linha = fout.nextLine();
        fout.close();

        linha = linha.replaceAll("\\)<br>", "");
        linha = linha.replaceAll("P\\(", "");
        temp = linha.split(",");
        pont = Float.parseFloat(temp[pos]);

        return pont;
    }

    public static float plrem(int line, int ex, int pos, String letter) throws FileNotFoundException {

        Scanner fout = new Scanner(new File("FichEx" + ex + ".html"));
        String linha = "";
        String[] temp;
        float pont = 0;

        for (int i = 1; i < line; i++) {
            fout.nextLine();
        }
        linha = fout.nextLine();
        fout.close();

        linha = linha.replaceAll("\\)<br>", "");
        linha = linha.replaceAll("Ponto " + letter + " = \\(", "");
        temp = linha.split(",");
        pont = Float.parseFloat(temp[pos]);

        return pont;
    }
    
    public static float vlrem(int line, int ex, int pos, String letter) throws FileNotFoundException {

        Scanner fout = new Scanner(new File("FichEx" + ex + ".html"));
        String linha = "";
        String[] temp;
        float pont = 0;

        for (int i = 1; i < line; i++) {
            fout.nextLine();
        }
        linha = fout.nextLine();
        fout.close();

        linha = linha.replaceAll("\\)<br>", "");
        linha = linha.replaceAll("Vetor " + letter + " = \\(", "");
        temp = linha.split(",");
        pont = Float.parseFloat(temp[pos]);

        return pont;
    }
    
    

    public static float eqrem(int line, int ex, int pos) throws FileNotFoundException {

        Scanner fout = new Scanner(new File("FichEx" + ex + ".html"));
        String linha = "";
        String[] temp;
        float pont = 0;

        for (int i = 1; i < line; i++) {
            fout.nextLine();
        }
        linha = fout.nextLine();
        fout.close();

        linha = linha.replaceAll("\\(x,y,z\\)=\\(", "");
        linha = linha.replaceAll("\\)\\+k\\(", ",");
        linha = linha.replaceAll("\\)", "");
        linha = linha.replaceAll("<br>", "");
        temp = linha.split(",");
        pont = Float.parseFloat(temp[pos]);

        return pont;
    }
}
