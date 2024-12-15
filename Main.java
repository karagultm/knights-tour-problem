
public class Main {

    public static void main(String[] args) {

        int size = 5;
        //inputları kullanıcıdan almamız gerekiyor.

        KnightsTour knightsTour = new KnightsTour(size);
        // knightsTour.printSolution();

        //ilk ana node u burada mı oluşturmalıyız?
        Node startNode = new Node(0, 0);
        DepthFirstSearch dfs = new DepthFirstSearch(startNode);
        boolean b = dfs.depthFirstSearch(knightsTour);
        System.out.println("Is there a solution? " + b);

    }
}
