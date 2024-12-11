
public class KnightsTour {

    private final int size;
    private Node[][] board;
    private final int[][] moves = {
        {2, 1},
        {1, 2},
        {-1, 2},
        {-2, 1},
        {-2, -1},
        {-1, -2},
        {1, -2},
        {2, -1}
    };

    public KnightsTour(int size) {
        this.size = size;
        this.board = new Node[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Node(i, j);
            }
        }
    }
    public boolean isValidMove(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size && !board[x][y].isVisited();
    }
    
    public void printSolution() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%3d ", board[i][j].getVisitOrder());
            }
            System.out.println();
        }
    }
}
