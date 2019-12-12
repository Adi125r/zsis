package tsp;

import org.apache.commons.collections4.iterators.PermutationIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class TspThr extends Thread
{
    private TspReader tspReader;
    private PermutationIterator permutationGenerator;
    private ArrayList<Integer> theBestRoad;
    private int theBestRoadValue;
    private TspBf tspBf;
    private int thrID;

    public TspThr(TspReader tspReader, PermutationIterator permutationGenerator, TspBf tspBf, int thrID)
    {
        this.tspReader = tspReader;
        this.permutationGenerator = permutationGenerator;
        theBestRoad = new ArrayList<Integer>();
        for (int i = 0; i < tspReader.getCountOfVert(); i++)
        {
            theBestRoad.add(0);
        }
        theBestRoadValue = Integer.MAX_VALUE;
        this.tspBf = tspBf;
        this.thrID = thrID;
    }

    @Override
    public void run()
    {
        while(true)
        {
            int temporaryRoadValue = 0;
            List<Integer> road = tspBf.getNextRoad();
            //System.out.println(thrID + ": " + road);
            if (road != null)
            {
                for (int i = 0; i < road.size() - 1; i++)
                {
                    temporaryRoadValue = temporaryRoadValue + tspReader.getGraph()[road.get(i)][road.get(i + 1)];
                }
                temporaryRoadValue = temporaryRoadValue + tspReader.getGraph()[road.get(road.size() - 1)][road.get(0)];
                if (temporaryRoadValue < theBestRoadValue)
                {
                    theBestRoadValue = temporaryRoadValue;
                    for (int i = 0; i < road.size(); i++)
                    {
                        theBestRoad.set(i, road.get(i));
                    }
                }
            }
            else
            {
                return;
            }
        }
    }

    public ArrayList<Integer> getTheBestRoad()
    {
        return theBestRoad;
    }

    public int getTheBestRoadValue()
    {
        return theBestRoadValue;
    }
}

