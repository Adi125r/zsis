package tsp;
import org.apache.commons.collections4.iterators.PermutationIterator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TspBf
{
    private static TspReader tspReader;
    private static ArrayList<List<Integer>> possibleRoads;
    private static int theBestRoadValue, possibleRoadItr;

    private static void generateData()
    {
        ArrayList<Integer> verts = new ArrayList<Integer>();
        for (int i = 0; i < tspReader.getCountOfVert(); i++)
        {
            verts.add(i);
        }
        possibleRoads = new ArrayList<List<Integer>>();
        PermutationIterator permutationGenerator = new PermutationIterator(verts);
        while (permutationGenerator.hasNext())
        {
            possibleRoads.add(permutationGenerator.next());
        }
    }


    private static void algorithmBf()
    {
        int temporaryRoadValue;
        theBestRoadValue = Integer.MAX_VALUE;
        possibleRoadItr = 0;

        for (int i = 0; i < possibleRoads.size(); i++)
        {
            temporaryRoadValue = 0;
            for (int j = 0; j < possibleRoads.get(i).size() - 1; j++)
            {
                temporaryRoadValue = temporaryRoadValue + tspReader.getGraph()[possibleRoads.get(i).get(j)][possibleRoads.get(i).get(j + 1)];
            }
            temporaryRoadValue = temporaryRoadValue + tspReader.getGraph()[possibleRoads.get(i).get(possibleRoads.get(i).size() - 1)][possibleRoads.get(i).get(0)];
            if (temporaryRoadValue < theBestRoadValue)
            {
                theBestRoadValue = temporaryRoadValue;
                possibleRoadItr = i;
            }
        }
    }

    private static void algorithmBf2()
    {
        int temporaryRoadValue;
        theBestRoadValue = Integer.MAX_VALUE;
        possibleRoadItr = 0;

        Runnable runnableBf = new Runnable() {
            @Override
            public void run() {
                for (int i = possibleRoads.size() / 2; i < possibleRoads.size(); i++)
                {
                    int temporaryRoadValue2;

                    temporaryRoadValue2 = 0;
                    for (int j = 0; j < possibleRoads.get(i).size() - 1; j++)
                    {
                        temporaryRoadValue2 = temporaryRoadValue2 + tspReader.getGraph()[possibleRoads.get(i).get(j)][possibleRoads.get(i).get(j + 1)];
                    }
                    temporaryRoadValue2 = temporaryRoadValue2 + tspReader.getGraph()[possibleRoads.get(i).get(possibleRoads.get(i).size() - 1)][possibleRoads.get(i).get(0)];
                    if (temporaryRoadValue2 < theBestRoadValue)
                    {
                        theBestRoadValue = temporaryRoadValue2;
                        possibleRoadItr = i;
                    }
                }
            }
        };
        Thread threadBf = new Thread(runnableBf);

        threadBf.start();
        for (int i = 0; i < possibleRoads.size() / 2; i++)
        {
            temporaryRoadValue = 0;
            for (int j = 0; j < possibleRoads.get(i).size() - 1; j++)
            {
                temporaryRoadValue = temporaryRoadValue + tspReader.getGraph()[possibleRoads.get(i).get(j)][possibleRoads.get(i).get(j + 1)];
            }
            temporaryRoadValue = temporaryRoadValue + tspReader.getGraph()[possibleRoads.get(i).get(possibleRoads.get(i).size() - 1)][possibleRoads.get(i).get(0)];
            if (temporaryRoadValue < theBestRoadValue)
            {
                theBestRoadValue = temporaryRoadValue;
                possibleRoadItr = i;
            }
        }
    }

    private static void printScore()
    {
        System.out.println(theBestRoadValue);
        for (int i = 0; i < possibleRoads.get(possibleRoadItr).size(); i++)
        {
            System.out.print(possibleRoads.get(possibleRoadItr).get(i));
        }
        System.out.print("0\n");
    }



    public static void main(String[] args) throws FileNotFoundException
    {
        tspReader = new TspReader("graf.txt");
        tspReader.writeGraph();
        generateData();
        for (int i = 0; i < 10; i++)
        {
            long firstTime = System.nanoTime();
            algorithmBf();
            long secondTime = System.nanoTime();
            printScore();
            long timeScore = secondTime - firstTime;
            System.out.println("Czas: " + (timeScore));
        }
    }
}