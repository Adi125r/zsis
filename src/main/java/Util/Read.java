package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Read {
    private int numberVerticles;
   private int[][] cost;

    public void readFile(String name) throws FileNotFoundException {
        File file = new File("src/main/resources/" + name + ".txt");
        Scanner in = new Scanner(file);
        numberVerticles = in.nextInt();
        cost = new int[numberVerticles][numberVerticles];
        for (int i = 0; i < numberVerticles; i++) {
            for (int j = 0; j < numberVerticles; j++) {
                cost[i][j] = in.nextInt();
            }
        }
    }
    public void writedate(){
        System.out.println("Wczytane zostało : " +numberVerticles+ " miast.");
        for (int i = 0; i < numberVerticles; i++) {
            for (int j = 0; j < numberVerticles-1; j++) {
                System.out.print(cost[i][j]+ " " );
            }
            System.out.println(cost[i][numberVerticles-1]);
        }
    }

    public int getNumberVerticles() {
        return numberVerticles;
    }

    public int[][] getCost() {
        return cost;
    }
}
