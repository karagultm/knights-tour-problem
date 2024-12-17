
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class TreeSearch {

    public boolean treeSearch(KnightsTour knightsTour, Node startNode, String algorithm) {
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

        while (!frontier.isEmpty()) {
            Node currentNode = algorithm.equals("a") ? frontier.poll() : frontier.pop();
            currentNode.setVisitOrder(currentNode);
            if (knightsTour.isCompleteTour(currentNode)) {
                knightsTour.printSolution(currentNode);
                return true;
            }

            List<Node> children = new ArrayList<>(); // bfs ve dfs de bunu oluşturmaya gerek var mı?
            for (int[] move : knightsTour.getKnightMoves()) {
                Node nextNode = new Node(currentNode.getX() + move[0], currentNode.getY() + move[1]);
                nextNode.setParent(currentNode);
                if (knightsTour.isValidMove(nextNode)) {

                    if (algorithm.equals("a")) {
                        frontier.offer(nextNode);
                    } else if (algorithm.equals("b")) {
                        frontier.push(nextNode);
                    } else if (algorithm.equals("c") || algorithm.equals("d")) {
                        children.add(nextNode);
                    }

                }
            }
            if (algorithm.equals("c") || algorithm.equals("d")) {
                boolean isHeuristicH2 = algorithm.equals("d");
                knightsTour.heuristic(children, frontier, knightsTour, isHeuristicH2);
            }

        }
        return false;
    }

}
