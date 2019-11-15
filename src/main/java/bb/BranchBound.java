package bb;

public class BranchBound {
    private final int numberOFVertices;
    private final int[][] costPaths;
    private int cost = Integer.MAX_VALUE;
    private int tempCost ;
    private boolean[] visitedPoint;
    private int[] path, tempPath;
    private int numberOfPoint ;

    public BranchBound(int numberOFVertices, int[][] costPaths) {
        this.numberOFVertices = numberOFVertices;
        this.costPaths = costPaths;
    }

    public void algorithm(int pointStart) {
        tempCost=0;
        numberOfPoint=0;
        path = new int[numberOFVertices + 1];
        tempPath = new int[numberOFVertices];
        visitedPoint = new boolean[numberOFVertices];

        for (int i = 0; i < numberOFVertices; i++) {
            visitedPoint[i] = false;
        }

        int bound = 0;

        for (int i = 0; i < numberOFVertices; i++) {

            bound += searchMin(i);
        }
        visitedPoint[0] = true;
        tempPath[0] = 0;
        long start=System.nanoTime();
        TSP(1, 0, bound);
        long stop=System.nanoTime();
        System.out.println("Czas wykonania:"+(stop-start));

    }

    protected void copyPath() {
        for (int i = 0; i < numberOFVertices; i++)
            path[i] = tempPath[i];
        path[numberOFVertices] = tempPath[0];
    }

    protected int searchMin(int point) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < numberOFVertices; i++) {

            if (costPaths[point][i] < min && point != i) {
                min = costPaths[point][i];
            }
        }
        return min;
    }


    private void TSP(int level, int tempCost, int bound) {
        if (level == numberOFVertices) {
            if (tempPath[level - 1] != tempPath[0]) {

                if ((tempCost + costPaths[tempPath[level - 1]][tempPath[0]]) <= cost) {
                    copyPath();
                    cost = (tempCost + costPaths[tempPath[level - 1]][tempPath[0]]);
                }
            }
            return;
        }
        for (int i = 0; i < numberOFVertices; i++) {
            if (tempPath[level - 1] != i && visitedPoint[i] == false) {
                int tempBound = bound;
                tempCost += costPaths[tempPath[level - 1]][i];

                bound -= calculateBound(tempPath[level - 1], i);

                if (bound + tempCost < cost) {
                    tempPath[level] = i;
                    visitedPoint[i] = true;

                    TSP(level + 1, tempCost, bound);
                }

                tempCost -= costPaths[tempPath[level - 1]][i];
                bound = tempBound;
                for (int k = 0; k < numberOFVertices; k++) {
                    visitedPoint[k] = false;
                }

                for (int j = 0; j <= level - 1; j++)
                    visitedPoint[tempPath[j]] = true;
            }
        }
    }

    public void solove() {
        System.out.print("Przebyta droga : ");

        for (int i = 0; i < numberOFVertices + 1; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.println();
        System.out.println("Minimalny koszt  : " + cost);

    }

    protected int calculateBound(int verticalPrev, int vertical) {
        return
                ((searchMin(verticalPrev) + searchMin(vertical)) / 2);
    }

    public int getNumberOFVertices() {
        return numberOFVertices;
    }

    public int getCost() {
        return cost;
    }

    public int[] getPath() {
        return path;
    }

    public int[] getTempPath() {
        return tempPath;
    }

    public void setTempPath(int[] tempPath) {
        this.tempPath = tempPath;
    }
}
