package algorithms;

import java.util.LinkedList;
import java.util.Queue;

import KnightsTour;
import Node;

public class BreadthFirstSearch {

    public boolean breadthFirstSearch(KnightsTour knightsTour, Node startNode) {
        Queue<Node> frontier = new LinkedList<>();

        frontier.add(startNode);

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.remove();
            currentNode.setVisitOrder(currentNode);
            if (knightsTour.isCompleteTour(currentNode)) {
                knightsTour.printSolution(currentNode);
                return true;
            }
            for (int[] move : knightsTour.getKnightMoves()) {
                Node nextNode = new Node(currentNode.getX() + move[0], currentNode.getY() + move[1]);
                nextNode.setParent(currentNode);
                if (knightsTour.isValidMove(nextNode)) {
                    frontier.add(nextNode);
                }
            }
        }
        return false;
    }
}
