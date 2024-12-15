
public class KnightsTour {

    private final int size;
    // private Node[][] board;

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
        // this.board = new Node[size][size];
        // initializeBoard();
    }

    // private void initializeBoard() {
    //     for (int i = 0; i < size; i++) {
    //         for (int j = 0; j < size; j++) {
    //             board[i][j] = new Node(i, j);
    //         }
    //     }
    // }
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
