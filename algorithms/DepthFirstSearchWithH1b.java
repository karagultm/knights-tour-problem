
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearchWithH1b {

    public boolean depthFirstSearch(KnightsTour knightsTour, Node startNode) {
        Stack<Node> frontier = new Stack<>();

        frontier.push(startNode);

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.pop();
            currentNode.setVisitOrder(currentNode);
            if (knightsTour.isCompleteTour(currentNode)) {
                knightsTour.printSolution(currentNode);
                return true;
            }
            List<Node> children = new ArrayList<>();
            for (int[] move : knightsTour.getKnightMoves()) {
                Node nextNode = new Node(currentNode.getX() + move[0], currentNode.getY() + move[1]);
                nextNode.setParent(currentNode);
                if (knightsTour.isValidMove(nextNode)) {

                    children.add(nextNode);

                }
            }

            heuristic1b(children, frontier, knightsTour);
        }
        return false;
    }

    public void heuristic1b(List<Node> children, Stack<Node> frontier, KnightsTour knightsTour) {
        if (children.isEmpty()) {
            return;

        }
        //geçici arraylist children counta göre sıralanıyor ve frontiere en büyük olan ilk eklenecek şekilde işleniyor.
        children.sort((node1, node2) -> knightsTour.getChildrenCount(node1) - knightsTour.getChildrenCount(node2));
        while (!children.isEmpty()) {
            frontier.push(children.remove(children.size() - 1));
        }

    }

}
