
public class Node {

    private Node parent;
    private final int x; // x y kordinatı daha farklı şekilde tutulabilir belki
    private final int y;
    private int visitOrder;
    
    private boolean visited;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.visited = false;
        this.visitOrder = -1; // Henüz ziyaret edilmediği için -1
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getVisitOrder() {
        return visitOrder;
    }

    public void setVisitOrder(int visitOrder) {
        this.visitOrder = visitOrder;
    }

    @Override
    public String toString() {
        return String.valueOf(visitOrder);
    }
}
