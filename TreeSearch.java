
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

        while (!frontier.isEmpty()) {
            // Check for timeout
            if (System.currentTimeMillis() - startTime > timeLimit) {
                System.out.println("Timeout.");
                System.out.println("Nodes expanded: " + nodesExpanded);
                return;
            }

            Node currentNode = algorithm.equals("a") ? frontier.poll() : frontier.pop();
            currentNode.setVisitOrder(currentNode);

            if (knightsTour.isCompleteTour(currentNode)) {
                long endTime = System.currentTimeMillis();
                long timeTaken = endTime - startTime;

                // Solution visualization
                int[][] solution = knightsTour.getSolution(currentNode);

                System.out.println("A solution found.");
                System.out.println("Time Taken: " + (timeTaken / 1000.0) + " seconds");
                System.out.println("Nodes expanded: " + nodesExpanded);

                knightsTour.printSolution(currentNode);
                KnightsTourGUI.displaySolution(solution);
                return;
            }

            List<Node> children = new ArrayList<>();
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
            nodesExpanded++;
        }
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;

        System.out.println("No solution exists.");
        System.out.println("Time Taken: " + (timeTaken / 1000.0) + " seconds");
        System.out.println("Nodes expanded: " + nodesExpanded);

    }
}
