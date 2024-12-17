
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeSearch {

    public void treeSearch(KnightsTour knightsTour, Node startNode, String algorithm, long timeLimit) {

        long startTime = System.currentTimeMillis();

        LinkedList<Node> frontier = new LinkedList<>();
        if (algorithm.equals("a")) {
            frontier.offer(startNode); // offer olarak yaptığımızda fronteira queue olarak ekleme işlemini
            // gerçekleştirir.
            // offer poll
        } else {
            frontier.push(startNode); // push olarak yaptığımızda fronteira stack olarak ekleme işlemini
            // gerçekleştirir.
            // push pop
        }
        int nodesExpanded = 0;

        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();

        try {
            while (!frontier.isEmpty()) {
                // Check for timeout
                if (System.currentTimeMillis() - startTime > timeLimit) {
                    System.out.println("Timeout.");
                    System.out.println("Search Method: " + knightsTour.getAlgorithmName(algorithm));
                    System.out.println("Board Size: " + knightsTour.getSize());
                    System.out.println("Nodes expanded: " + nodesExpanded);
                    return;
                }

                // Check for memory usage
                if (heapMemoryUsage.getUsed() > heapMemoryUsage.getMax() * 0.9) {
                    throw new OutOfMemoryError();
                }
                Node currentNode = algorithm.equals("a") ? frontier.poll() : frontier.pop();
                currentNode.setVisitOrder(currentNode);
                nodesExpanded++;
                if (knightsTour.isCompleteTour(currentNode)) {

                    long endTime = System.currentTimeMillis();
                    long timeTaken = endTime - startTime;

                    // Çözüm yolunu solutionList'e kaydet
                    List<Node> solutionList = new ArrayList<>();
                    Node node = currentNode;
                    while (node != null) {
                        solutionList.add(0, node);
                        node = node.getParent();
                    }

                    // KnightsTourVisualizer'ı çağır
                    KnightsTourVisualizer visualizer = new KnightsTourVisualizer(knightsTour, solutionList);
                    visualizer.setVisible(true);

                    knightsTour.printSolution(currentNode);

                    System.out.println("A solution found.");
                    System.out.println("Search Method: " + knightsTour.getAlgorithmName(algorithm));
                    System.out.println("Board Size: " + knightsTour.getSize());
                    System.out.println("Time Taken: " + (timeTaken / 1000.0) + " seconds");
                    System.out.println("Nodes expanded: " + nodesExpanded);
                    return;

                }

                List<Node> children = new ArrayList<>(); // bfs ve dfs de bunu oluşturmaya gerek var mı?
                for (int[] move : knightsTour.getKnightMoves()) {
                    Node nextNode = new Node(currentNode.getX() + move[0], currentNode.getY() + move[1]);
                    nextNode.setParent(currentNode);
                    if (knightsTour.isValidMove(nextNode)) {
                        switch (algorithm) {
                            case "a" ->
                                frontier.offer(nextNode);
                            case "b" ->
                                frontier.push(nextNode);
                            case "c", "d" ->
                                children.add(nextNode);
                        }

                    }
                }
                if (algorithm.equals("c") || algorithm.equals("d")) {
                    boolean isHeuristicH2 = algorithm.equals("d");
                    knightsTour.heuristic(children, frontier, knightsTour, isHeuristicH2);
                }

            }
            long endTime = System.currentTimeMillis();
            long timeTaken = endTime - startTime;

            System.out.println("No solution exists.");
            System.out.println("Search Method: " + knightsTour.getAlgorithmName(algorithm));
            System.out.println("Board Size: " + knightsTour.getSize());
            System.out.println("Time Taken: " + (timeTaken / 1000.0) + " seconds");
            System.out.println("Nodes expanded: " + nodesExpanded);

        } catch (OutOfMemoryError e) {
            System.out.println("Out of Memory.");
            System.out.println("Search Method: " + knightsTour.getAlgorithmName(algorithm));
            System.out.println("Board Size: " + knightsTour.getSize());
            System.out.println("Nodes expanded: " + nodesExpanded);
        }

    }

}
