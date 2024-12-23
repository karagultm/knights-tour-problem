
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class KnightsTour {

    private final int size;

    private final int[][] knightMoves = {
        {-2, -1},//8. konum
        {-1, -2}, //7. konum
        {1, -2}, //6. konum
        {2, -1}, //5. konum
        {2, 1}, //4. konum
        {1, 2}, //3. konum
        {-1, 2}, //2. konum
        {-2, 1} //1. konum
        
        
    };

    public KnightsTour(int size) {
        this.size = size;

    }

    public int getSize() {
        return size;
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

    public String getAlgorithmName(String algorithm) {
        return switch (algorithm) {
            case "a" ->
                "Breadth First Search";
            case "b" ->
                "Depth First Search";
            case "c" ->
                "Depth First Search with Heuristic h1b";
            case "d" ->
                "Depth First Search with Heuristic h2";
            default ->
                "Unknown Algorithm";
        };
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

    public void heuristic(List<Node> children, LinkedList<Node> frontier, KnightsTour knightsTour, boolean isHeuristicH2) {
        if (children.isEmpty()) {
            return;

        }
        // geçici arraylist children counta göre sıralanıyor ve frontiere en büyük olan
        // ilk eklenecek şekilde işleniyor.
        children.sort((node1, node2) -> {
            int childrenCount = knightsTour.getChildrenCount(node1) - knightsTour.getChildrenCount(node2);

            if (childrenCount == 0 && isHeuristicH2) {
                return knightsTour.calculateClosestCorner(node1) - knightsTour.calculateClosestCorner(node2);
            }
            return childrenCount;
        });

        while (!children.isEmpty()) {
            frontier.push(children.remove(children.size() - 1));
        }

    }

    public void printSolution(Node node) {
        int[][] solution = new int[size][size];
        StringBuilder output = new StringBuilder(); // Çıktıyı toplamak için StringBuilder
    
        while (node != null) {
            solution[node.getX()][node.getY()] = node.getVisitOrder();
            node = node.getParent();
        }
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                output.append(solution[i][j]).append("\t"); // Satırı biriktiriyoruz
            }
            output.append("\n"); // Yeni satır
        }
    

    
        //dosyaya yazıyoruz
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("knights_tour_solution.txt"))) {
            writer.write(output.toString());
            System.out.println("Solution written to knights_tour_solution.txt");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public int[][] getSolution(Node node) {
        int[][] solution = new int[size][size];
        while (node != null) {
            solution[node.getX()][node.getY()] = node.getVisitOrder();
            node = node.getParent();
        }
        return solution;
    }
}
