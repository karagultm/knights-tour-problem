
import java.util.Arrays;

public class KnightsTour {

    private final int size;

    private final int[][] knightMoves = {
        {-2, 1}, //fronteir a eklenmek için checklenecek ilk atın konumu
        {-1, 2}, //2. konum
        {1, 2}, //3. konum
        {2, 1}, //4. konum
        {2, -1}, //5. konum
        {1, -2}, //6. konum
        {-1, -2}, //7. konum
        {-2, -1}//8. konum
    };

    public KnightsTour(int size) {
        this.size = size;

    }

    public int[][] getKnightMoves() {
        return knightMoves;
    }

    public boolean isValidMove(Node node) {
        return node.getX() >= 0 && node.getX() < size && node.getY() >= 0 && node.getY() < size && !isVisited(node); //isVisited eklenmesi gerekiyor şuanda 
    }

    public boolean isVisited(Node node) {
        Node parent = node.getParent();
        while (parent != null) {
            if (parent.getX() == node.getX() && parent.getY() == node.getY()) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    public boolean isCompleteTour(Node node) {
        return node.getVisitOrder() == size * size;
    }

    public int getChildrenCount(Node node) {
        int count = 0;
        for (int[] move : knightMoves) {
            Node nextNode = new Node(node.getX() + move[0], node.getY() + move[1]);
            nextNode.setParent(node);
            if (isValidMove(nextNode)) {
                count++;
            }
        }
        return count;
    }

    public int calculateClosestCorner(Node node) {
        int[] distances = new int[4];
        int nodeX = node.getX();
        int nodeY = node.getY();

        distances[0] = Math.abs(nodeX - 0) + Math.abs(nodeY - 0); //left bottom corner
        distances[1] = Math.abs(nodeX - 0) + Math.abs(nodeY - size - 1); //left top corner
        distances[2] = Math.abs(nodeX - size - 1) + Math.abs(nodeY - 0); //right bottom corner
        distances[3] = Math.abs(nodeX - size - 1) + Math.abs(nodeY - size - 1); //right top corner

        int closest = Arrays.stream(distances).min().getAsInt();
        return closest;
    }

    public void printSolution(Node node) {
        int[][] solution = new int[size][size];
        while (node != null) {
            solution[node.getX()][node.getY()] = node.getVisitOrder();
            node = node.getParent();
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(solution[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
