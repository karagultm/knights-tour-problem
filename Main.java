import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // inputları kullanıcıdan almamız gerekiyor.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the board: ");
        int size = scanner.nextInt();

        System.out.println("Which algorithm do you want to use?");
        System.out.println("a- Breadth First Search"); // yazıına bak
        System.out.println("b- Depth First Search");
        System.out.println("c- Depth First Search with Heuristic h1b");
        System.out.println("d- Depth First Search with Heuristic h2");
        String algorithm = scanner.next();
        if (!algorithm.equals("a") && !algorithm.equals("b") && !algorithm.equals("c") && !algorithm.equals("d")) {
            System.out.println("Invalid algorithm");
            System.exit(0);
        }

        // System.out.println("What is the time limit for the algorithm?");
        // int timeLimit = scanner.nextInt();

        scanner.close();

        KnightsTour knightsTour = new KnightsTour(size);

        // ilk ana node u burada mı oluşturmalıyız?
        Node startNode = new Node(0, 0);
        TreeSearch treeSearch = new TreeSearch();
        boolean b5 = treeSearch.treeSearch(knightsTour, startNode, algorithm);
        System.out.println("Is there a solution? " + b5);

        switch (algorithm) {
            case "a":
            BreadthFirstSearch bfs = new BreadthFirstSearch();
            boolean b2 = bfs.breadthFirstSearch(knightsTour, startNode);
            System.out.println("Is there a solution? " + b2);
                break;
            case "b":
                DepthFirstSearch dfs = new DepthFirstSearch();
                boolean b = dfs.depthFirstSearch(knightsTour, startNode);
                System.out.println("Is there a solution? " + b);
                break;
            case "c":
                DepthFirstSearchWithH1b dfs2 = new DepthFirstSearchWithH1b();
                boolean b3 = dfs2.depthFirstSearch(knightsTour, startNode);
                System.out.println("Is there a solution? " + b3);
                break;
            case "d":
                DepthFirstSearchWithH2 dfs3 = new DepthFirstSearchWithH2();
                boolean b4 = dfs3.depthFirstSearch(knightsTour, startNode);
                System.out.println("Is there a solution? " + b4);
                break;
        }

    }
}
