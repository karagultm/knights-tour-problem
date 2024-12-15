
public class Main {

    public static void main(String[] args) {

        int size = 6;
        //inputları kullanıcıdan almamız gerekiyor.

        KnightsTour knightsTour = new KnightsTour(size);
        // knightsTour.printSolution();

        //ilk ana node u burada mı oluşturmalıyız?
        Node startNode = new Node(0, 0);

        // DepthFirstSearch dfs = new DepthFirstSearch();
        // boolean b = dfs.depthFirstSearch(knightsTour,startNode);
        // System.out.println("Is there a solution? " + b);

        BreadthFirstSearch bfs = new BreadthFirstSearch();
        boolean b2 = bfs.breadthFirstSearch(knightsTour,startNode);
        System.out.println("Is there a solution? " + b2);

        //heuristicleri dfs ile birleştireceğim. 
        DepthFirstSearchWithH1b dfs2 = new DepthFirstSearchWithH1b();
        boolean b3 = dfs2.depthFirstSearch(knightsTour,startNode);
        System.out.println("Is there a solution? " + b3);


    }
}
