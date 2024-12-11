
public class KnightsTour {

    private int size;
    private Node[][] board;

    public KnightsTour() {

    }

    public KnightsTour(int size) {
        this.size = size;
        this.board = new Node[size][size];

        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Node(i, j);
            }
        }
    }

}
