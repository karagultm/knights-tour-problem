
import java.util.Stack;

public class DepthFirstSearch {

    private final Node startNode;

    public DepthFirstSearch(Node node) {
        this.startNode = node;

    }

    public boolean depthFirstSearch(KnightsTour knightsTour) {
        Stack<Node> frontier = new Stack<>();

        frontier.push(startNode);

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.pop();
            currentNode.setVisitOrder(currentNode);
            if (knightsTour.isCompleteTour(currentNode)) {
                knightsTour.printSolution(currentNode);
                return true;
            }
            for (int[] move : knightsTour.getKnightMoves()) {
                Node nextNode = new Node(currentNode.getX() + move[0], currentNode.getY() + move[1]);
                nextNode.setParent(currentNode);
                if (knightsTour.isValidMove(nextNode)) {
                    frontier.push(nextNode);
                }
            }
        }
        return false;
    }
}
