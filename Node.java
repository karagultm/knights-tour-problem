

public class Node {

    private Node parent;
    private final int x; // x y kordinatı daha farklı şekilde tutulabilir belki
    private final int y;
    private int visitOrder;


    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.visitOrder = -1; // Henüz ziyaret edilmediği için -1
        this.parent = null; //0a0 konumu hariç hepsinin parentının null olması gerekir
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getVisitOrder() {
        return visitOrder;
    }
  
    public void setVisitOrder(Node node) {
        if (node.getParent() == null) {
            this.visitOrder = 1;
            return;
        }
        this.visitOrder = node.getParent().getVisitOrder() + 1;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return String.valueOf(visitOrder);
    }
}
