package tsp;
import org.apache.commons.collections4.iterators.PermutationIterator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TspBf
{
    private TspReader tspReader;
    private PermutationIterator permutationGenerator;
    private ArrayList<Integer> theBestRoad;
    private int theBestRoadValue;
    private int thrItr = -1, maxOfItr = 1;

    public static void main(String[] args) throws FileNotFoundException, InterruptedException
    {
        TspBf tspBf = new TspBf();
    }

    public TspBf() throws FileNotFoundException, InterruptedException
    {
        tspReader = new TspReader("graf.txt");
        tspReader.writeGraph();
        generateData();
        long firstTime = System.nanoTime();
        algorithmBf();
        long secondTime = System.nanoTime();
        printScore();
        long timeScore = secondTime - firstTime;
        System.out.println("Czas: " + (timeScore));
    }

    private void generateData()
    {
        ArrayList<Integer> verts = new ArrayList<Integer>();
        for (int i = 0; i < tspReader.getCountOfVert(); i++)
        {
            verts.add(i);
        }
        permutationGenerator = new PermutationIterator(verts);
        theBestRoadValue = Integer.MAX_VALUE;
        for (int i = 1; i <= tspReader.getCountOfVert(); i++)
        {
            maxOfItr = maxOfItr * i;
        }
    }

    private void algorithmBf() throws InterruptedException
    {
        TspThr[] tspThrs = new TspThr[20];
        for (int i = 0; i < tspThrs.length; i++)
        {
            tspThrs[i] = new TspThr(tspReader, permutationGenerator, this, i + 1);
        }
        for (int i = 0; i < tspThrs.length; i++)
        {
            tspThrs[i].start();
        }
        for (int i = 0; i < tspThrs.length; i++)
        {
            tspThrs[i].join();
        }
        for (int i = 0; i < tspThrs.length; i++)
        {
            if (tspThrs[i].getTheBestRoadValue() < theBestRoadValue)
            {
                theBestRoadValue = tspThrs[i].getTheBestRoadValue();
                theBestRoad = tspThrs[i].getTheBestRoad();
            }
        }
    }

    private void printScore()
    {
        System.out.println(theBestRoadValue);
        for (int i = 0; i < theBestRoad.size(); i++)
        {
            System.out.print(theBestRoad.get(i));
        }
        System.out.println(theBestRoad.get(0));
    }

    synchronized List<Integer> getNextRoad()
    {
        thrItr++;
        if (thrItr < maxOfItr)
        {
            return permutationGenerator.next();
        }
        return null;
    }
}