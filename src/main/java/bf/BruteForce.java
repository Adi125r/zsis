package bf;

import java.util.List;

public class BruteForce {
   private final int numberOFVertices;
   private final int[][]  costPaths;
   private  int cost = Integer.MAX_VALUE;
   private  int tempCost=0;
   private  boolean[] visitedPoint;
   private  int[] path,tempPath;
   private  int numberOfPoint = 0;
    public BruteForce(int numberOFVertices, int[][] costPaths){

        this.numberOFVertices = numberOFVertices;
        this.costPaths = costPaths;

    }
    public void algorithm(int pointStart){
        path = new int[numberOFVertices];
        tempPath = new int[numberOFVertices];
        visitedPoint = new boolean[numberOFVertices];

        for (int i = 0; i < numberOFVertices; i++)
        {
            visitedPoint[i] = false;
        }
        TSP(pointStart);
    }
    private void TSP(int pointStart){
        int nextPoint;


        tempPath[numberOfPoint++] = pointStart;

        if (numberOfPoint < numberOFVertices)
        {
            visitedPoint[pointStart] = true;
            for (nextPoint = 0; nextPoint < numberOFVertices; nextPoint++)
                if (pointStart != nextPoint && !visitedPoint[nextPoint])
                {
                    tempCost += costPaths[pointStart][nextPoint];
                    TSP(nextPoint);
                    tempCost -= costPaths[pointStart][nextPoint];
                }
            visitedPoint[pointStart] = false;
        }
        else if (costPaths[0][pointStart] > 0)
        {
            tempCost += costPaths[pointStart][0];
            if (tempCost <= cost)
            {
                cost = tempCost;
                for (nextPoint = 0; nextPoint < numberOfPoint; nextPoint++)
                    path[nextPoint] = tempPath[nextPoint];

            }
            tempCost -= costPaths[pointStart][0];
        }
        numberOfPoint--;


    }
    public void solove(){
        System.out.print("Przebyta droga : ");
        for (int i = 0; i < numberOFVertices; i++){
            System.out.print(path[i]+ " ");
        }
        System.out.println(path[0]);
        System.out.println("Minimalny koszt : " + cost);

    }
}
