import Util.Read;
import bb.BranchBound;
import bf.BruteForce;

import java.io.FileNotFoundException;

public class Main  {


    public static void main(String[] args) {
        Read read =new Read();
        try {
            read.readFile("6");
            read.writedate();
        }catch (FileNotFoundException e){}

        BruteForce bruteForce = new BruteForce(read.getNumberVerticles(),read.getCost());
        for(int i=0;i<10;i++)
        bruteForce.algorithm(0);
        bruteForce.solove();
        BranchBound branchBound = new BranchBound(read.getNumberVerticles(),read.getCost());
        for(int i=0;i<10;i++)
        branchBound.algorithm(0);
        branchBound.solove();
    }
}